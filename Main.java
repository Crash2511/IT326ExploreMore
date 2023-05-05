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
		//initial login page
		LoginPage loginPage = new LoginPage(idandPassword.getLoginInfo());
	}
}


