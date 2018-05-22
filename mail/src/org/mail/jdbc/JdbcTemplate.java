package org.mail.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * JDBC 工具类
 * @author VIC
 *
 */
public class JdbcTemplate {
	
	static{
		try {
			// 加载驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return
	 */
	public static Connection getConnection(){
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "orcl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 添加
	 * @param sql
	 * @param args
	 * @return
	 */
	public static Integer execute(String sql,Object...args){
		int modify = 0;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 获取连接
			connection = getConnection();
			// 创建执行预编译SQL的对象
			preparedStatement = connection.prepareStatement(sql);
			// 设置参数(如果有参数)
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			// 执行SQL
			modify = preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// 关闭连接
			release(preparedStatement,connection);
		}
		return modify;
	}

	/**
	 * 查询数据是否存在
	 * @param searchSql
	 * @param objects
	 * @return
	 */
	public static boolean exists(String searchSql, Object...args) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(searchSql);
			for(int i = 0; i < args.length; i++){
				preparedStatement.setObject(i+1, args[i]);
			}
			resultSet = preparedStatement.executeQuery();
			result = resultSet.next();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			release(resultSet, preparedStatement, connection);
		}
		return result;
	}
	
	private static void release(PreparedStatement preparedStatement, Connection connection) {
		release(null,preparedStatement,connection);
	}

	private static void release(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) {
		try {
			if(null != resultSet){
				resultSet.close();
			}
			if(null != preparedStatement){
				preparedStatement.close();
			}
			if(null != connection){
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
