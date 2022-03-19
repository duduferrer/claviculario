package smingolera.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import smingolera.connection.ConnectionFactory;
import smingolera.dao.KeyLogDAO;
import smingolera.models.KeyLog;
import smingolera.models.User;

public class KeyLogController {
	
	private KeyLogDAO keyLogDAO;
	
	//manage connections with DB for checkout
	public boolean keyCheckout(String username, String requesterName, String key) {
		Connection connection = new ConnectionFactory().getConnection();
		keyLogDAO = new KeyLogDAO(connection);
		try {
			connection.setAutoCommit(true);
			if(keyLogDAO.keyCheckout(username, requesterName, key) == true) {
				return true;
				}else {
					return false;
				}
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	//manage connections with DB for checkin
	public boolean keyCheckin(String keyNumber, User user) {
		Connection connection = new ConnectionFactory().getConnection();
		keyLogDAO = new KeyLogDAO(connection);
		try {
			connection.setAutoCommit(true);
			if(keyLogDAO.keyCheckin(keyNumber, user) == true) {
				return true;
				}else {
					return false;
				}
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	//manage connections with DB for check the history of alterations by key
	public List<KeyLog> keyHistory(String keyNumber, LocalDate iniDate, LocalDate finDate) {
		Connection connection = new ConnectionFactory().getConnection();
		keyLogDAO = new KeyLogDAO(connection);
		try {
			connection.setAutoCommit(true);
			return keyLogDAO.keyHistory(keyNumber, iniDate, finDate);
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}

}
