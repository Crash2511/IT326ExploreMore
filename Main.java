package org.exploremore;
// main application for exploreMore
// note: change class name to app name

public class Main {

	public static void main(String[] args) {
		
		IDandPassword idandPassword = new IDandPassword();
				
		
		RegisterPage registerPage = new RegisterPage(idandPassword.getLoginInfo());

	}
}

