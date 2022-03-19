package smingolera.controller;

import java.sql.Connection;
import java.sql.SQLException;

import smingolera.connection.ConnectionFactory;
import smingolera.dao.LoginDAO;
import smingolera.models.User;

public class LoginController {
	private LoginDAO loginDAO;
	
	
	//manage connections with DB for authenticate users
	public boolean login(User user) {
		Connection connection = new ConnectionFactory().getConnection();
		loginDAO = new LoginDAO(connection);
		try {
			connection.setAutoCommit(true);
			}catch(SQLException ex) {
				throw new RuntimeException(ex);
			}
		if(loginDAO.authenticateUser(user)) {
			return true;
		}else {
			return false;
		}
	}
	//manage connections with DB for change users password
	public boolean changedPassword(User user, String newPass, String confPass) {
		Connection connection = new ConnectionFactory().getConnection();
		loginDAO = new LoginDAO(connection);
		try {
			connection.setAutoCommit(true);
			}catch(SQLException ex) {
				throw new RuntimeException(ex);
			}
		if(loginDAO.updatePasswordOnDB(user, newPass, confPass)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//manage connections with DB for get the name, username and permission level of the user
	public User getUserData(User user) {
		Connection connection = new ConnectionFactory().getConnection();
		loginDAO = new LoginDAO(connection);
		return loginDAO.getUserData(user);
	}
	
}
