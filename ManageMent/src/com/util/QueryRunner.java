package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class QueryRunner{
	//��ѯ���
	public static <T> Object query(String sql, Object[] params,ResultSetHandler<T> rsh) {
		// ��ȡ����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs=null;
		try {
			conn = JdbcUtils.getConnection();
			// Ԥ����sql
			// ��ȡpreparedstatement
			ps = conn.prepareStatement(sql);
			// ps �������
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// ִ��sql
			rs = ps.executeQuery();
			return rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, ps, rs);
		}
		return null;
	}
	//��ɾ�Ŀ��
	public int execute(String sql, Object[] params) {
		// ��ȡ����
		boolean bool = false;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcUtils.getConnection();
			conn.setAutoCommit(false);//������
			// Ԥ����sql
			// ��ȡpreparedstatement
			ps = conn.prepareStatement(sql);
			// ps �������
			if(params!=null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// ִ��sql
			int result = ps.executeUpdate();
			if(result>0) {
				bool = true;
				conn.commit();//�ύ
			}else {
				bool = false;//���ύ
				conn.rollback();
				JOptionPane.showMessageDialog(null, "�������ύ");				
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtils.close(conn, ps, null);
		}
		return 0;
	}
}
