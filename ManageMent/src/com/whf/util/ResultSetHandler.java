package com.whf.util;

import java.sql.ResultSet;

public interface ResultSetHandler<T> {
	Object handle(ResultSet rs);
}
