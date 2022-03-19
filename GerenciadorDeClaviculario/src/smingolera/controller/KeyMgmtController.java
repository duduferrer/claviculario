package smingolera.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import smingolera.connection.ConnectionFactory;
import smingolera.dao.KeyMgmtDAO;
import smingolera.models.Key;

public class KeyMgmtController {
	
	private KeyMgmtDAO keyMgmtDAO;
	
	//manage connections with DB for key insertion
	public boolean insertKey(Key key) {
		Connection connection = new ConnectionFactory().getConnection();
		keyMgmtDAO = new KeyMgmtDAO(connection);
		try {
			connection.setAutoCommit(true);
			if(keyMgmtDAO.insertKey(key) == true) {
				return true;
				}else {
					return false;
				}
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	//manage connections with DB for exclusion
	public boolean deleteKey(Key key) {
		Connection connection = new ConnectionFactory().getConnection();
		keyMgmtDAO = new KeyMgmtDAO(connection);
		try {
			connection.setAutoCommit(true);
			if(keyMgmtDAO.deleteKey(key) == true) {
				return true;
				}else {
					return false;
				}
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
		
	}
	
	//manage connections with DB for generate a list of keys registered
	public List<Key> keyListGenerator(){
		Connection connection = new ConnectionFactory().getConnection();
		keyMgmtDAO = new KeyMgmtDAO(connection);
		try {
			connection.setAutoCommit(true);
			return keyMgmtDAO.keyList(); 
		}catch(SQLException ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
}