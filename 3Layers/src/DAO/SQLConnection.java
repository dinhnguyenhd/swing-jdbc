package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {

	private SQLConnection() {
	};

	public static Connection getConnect() {
		Connection con = null;
		try {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/swing", "root", "dinhnguyen");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

}
