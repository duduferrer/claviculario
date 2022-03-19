package smingolera.controller;

import java.sql.Connection;
import java.sql.SQLException;

import smingolera.connection.ConnectionFactory;
import smingolera.dao.UserRegistrationDAO;
import smingolera.models.User;

public class UserController {
	private UserRegistrationDAO userRegistrationDAO;
	
	//manage connections with DB for create a new user
	public boolean submit(User user) {
		Connection connection = new ConnectionFactory().getConnection();
		userRegistrationDAO = new UserRegistrationDAO(connection);
		try {
			connection.setAutoCommit(true);
			}catch(SQLException ex) {
				throw new RuntimeException(ex);
			}
		if(userRegistrationDAO.userRegistration(user)) {
			return true;
		}else {
			return false;
		}
	}
	
	//manage connections with DB for delete a user
	public boolean delete(String username) {
		Connection connection = new ConnectionFactory().getConnection();
		userRegistrationDAO = new UserRegistrationDAO(connection);
		try {
			connection.setAutoCommit(true);
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		if(userRegistrationDAO.deleteUserFromDB(username)) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
