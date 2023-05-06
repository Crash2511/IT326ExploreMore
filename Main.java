package org.exploremore;
// main application for exploreMore
// note: change class name to app name
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {

		IDandPassword idandPassword = new IDandPassword();

		RegisterPage registerPage = new RegisterPage(idandPassword.getLoginInfo());

		//ExportController ec = new ExportController();
		//ec.setVisible(true);


		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jdbcdemo", "root", ""
			);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from user");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
				String updateSql = "UPDATE user SET fname = 'Phil' WHERE fname = 'Gary'";
				int rowsAffected = statement.executeUpdate(updateSql);
				System.out.println("Rows affected: " + rowsAffected);
				System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
			}
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

