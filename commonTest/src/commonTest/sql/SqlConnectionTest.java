package commonTest.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;

public class SqlConnectionTest {


	Connection conn = null;

	private String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

	/**
	 * mysql测试
	 */
	@Test
	public void testMysqlConnection() {

		try {
			// 注册 JDBC 驱动
			Class.forName(MYSQL_DRIVER);

			// 打开链接
			String dbUrl = "jdbc:mysql://localhost:3306/test";
			conn = DriverManager.getConnection(dbUrl, "root", "root");
			System.out.println("连接成功");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null) {
				return;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 */
	@Test
	public void testOracleConnection() {

		try {
			// 注册 JDBC 驱动
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 打开链接
			String dbUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
			conn = DriverManager.getConnection(dbUrl, "system", "system");
			System.out.println("连接成功");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null) {
				return;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Test
	public void testSqlserverConnection() {

		try {
			// 注册 JDBC 驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// 打开链接
			String dbUrl = "jdbc:sqlserver://localhost:1433;DatabaseName=demo;";
			conn = DriverManager.getConnection(dbUrl, "sa", "sqlserver");
			System.out.println("连接成功");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null) {
				return;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
	
	@Test
	public void testPostgresqlConnection() {

		try {
			// 注册 JDBC 驱动
			Class.forName("org.postgresql.Driver");

			// 打开链接
			String dbUrl = "jdbc:postgresql://192.168.3.160:10350/innode";
			conn = DriverManager.getConnection(dbUrl, "innodb", "888888");
			System.out.println("连接成功");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn == null) {
				return;
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
