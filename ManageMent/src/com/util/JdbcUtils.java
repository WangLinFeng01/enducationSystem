package com.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Properties;

public final class JdbcUtils {

	private static String url="jdbc:mysql://192.168.243.128:3306/test?useUnicode=true&characterEncoding=utf8";
	private static String user="root";
	private static String password="root";
	private static String driver="com.mysql.jdbc.Driver";
	private static int initCount=20;
	
	//准备一串connection  pools池子
	private static LinkedList<Connection> pools=new LinkedList<Connection>();
	
	static{
		//加载驱动
		try {
			Class.forName(driver);
			
			//初始化10条connection
			for(int i=0;i<initCount;i++) {
				Connection conn=createConnection();
				pools.add(proxy(conn));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//封装close方法的conn动态代理类
	//classLoader 类的加载器
	public static Connection proxy(Connection conn) {
		return (Connection) Proxy.newProxyInstance(conn.getClass().getClassLoader(),//
				new Class[] {Connection.class}, new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						Object result=null;
						//method   Connection.class 里的所有方法
						if(method.getName().equals("close")) {
							pools.add(proxy(conn));
//							System.out.println("进行了动态代理");
						}else {
							//原本是什么方法就执行什么方法
							result=method.invoke(conn, args);
						}
						return result;
					}
				});
	}
	
	//创建conn
	private static Connection createConnection() throws SQLException {
		return DriverManager.getConnection(url,user,password);
	}
	
	public static Connection getConnection() throws SQLException {
		return pools.removeFirst();
	}
	//这里的动态代理的理解：
	/*
	 * 当pools进行初始化时，pools.add(proxy(conn));是调用了动态代理了的，但没有执行代理改写的close()方法，所以没有打印语句，
	 * 此时的10条初始化后装满的pools中的conn里面的close()方法是：pools.add(proxy(conn));这里的conn是什么？
	 * 当conn对象调用close()方法时，添加一个又重新经过动态代理的新的connection连接？
	 * 
	 * 问题：如果是这样的话，这个连接池没有意义？那每次关闭连接之后pools.add(proxy(conn));中的conn是什么，是重新弄的连接，还是换回来的来的连接
	 * 
	 * 动态代理正确的结论：动态代理必须每次执行，才可以获取经过动态代理后的对象。不是经过一次之后，就永久改变底层结构（这种情况适用于代理内部没有再次调用动态代理）
	 */
	
	
	public static void close(Connection conn,Statement statmenet,ResultSet rs) {
		if(null!=conn) {
			try {
				conn.close();//我需要让这个conn.close()--->pools.add(conn)
				//必须在conn的运行代码内存中改动代码方法
//				pools.add(conn);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(null!=statmenet) {
			try {
				statmenet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(null!=rs) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}









//能否只对close方法进行补充    动态代理Proxy
//为了弄一个close方法，需要增加很大工作量
/*
 class ProxyConn implements Connection{

		@Override
		public <T> T unwrap(Class<T> iface) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isWrapperFor(Class<?> iface) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Statement createStatement() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String nativeSQL(String sql) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setAutoCommit(boolean autoCommit) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean getAutoCommit() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void commit() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void rollback() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void close() throws SQLException {
			// TODO Auto-generated method stub
			pools.add(this);
		}

		@Override
		public boolean isClosed() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public DatabaseMetaData getMetaData() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setReadOnly(boolean readOnly) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isReadOnly() throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setCatalog(String catalog) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getCatalog() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTransactionIsolation(int level) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getTransactionIsolation() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public SQLWarning getWarnings() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void clearWarnings() throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Map<String, Class<?>> getTypeMap() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHoldability(int holdability) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getHoldability() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public Savepoint setSavepoint() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Savepoint setSavepoint(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void rollback(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void releaseSavepoint(Savepoint savepoint) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
				throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
				int resultSetHoldability) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Clob createClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Blob createBlob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public NClob createNClob() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public SQLXML createSQLXML() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean isValid(int timeout) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setClientInfo(String name, String value) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setClientInfo(Properties properties) throws SQLClientInfoException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getClientInfo(String name) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Properties getClientInfo() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSchema(String schema) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public String getSchema() throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void abort(Executor executor) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getNetworkTimeout() throws SQLException {
			// TODO Auto-generated method stub
			return 0;
		}
		
	}
	
 */
