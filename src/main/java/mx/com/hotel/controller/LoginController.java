package mx.com.hotel.controller;

import mx.com.hotel.dao.LoginDAO;
import mx.com.hotel.factory.ConnectionFactory;

public class LoginController {
	private LoginDAO loginDAO;
	
	public LoginController() {
		loginDAO = new LoginDAO(new ConnectionFactory().getConnection());
	}

	public boolean login(String password, String user) {
		return loginDAO.login(password, user);
		
	}
}
