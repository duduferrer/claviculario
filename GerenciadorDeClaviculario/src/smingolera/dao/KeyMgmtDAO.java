package smingolera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import smingolera.models.Key;
import smingolera.utilities.Verifiers;

public class KeyMgmtDAO {
	
	private Connection connection;
	
	public KeyMgmtDAO(Connection con) {
		this.connection = con;
	}
	
	//insert a new key on the DB
	public boolean insertKey(Key key) {
		String sql = "INSERT INTO key_register (key_number, key_description) VALUES (?, ?)";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setString(2, key.getKeyDescription());
			pstm.setString(1, key.getKeyNumber());
			if(keyIsValid(key)) {
				pstm.execute();
				return true;
			}else {
				return false;
			}
			}catch(SQLException ex) {
				throw new RuntimeException("Erro na Inserçao de chave"+ex);
			}
		
	}
	
	//checks if there is no other key with the same number on the DB
	public boolean keyIsNotUnique(Key key) throws SQLException{
		String sql = "SELECT key_number FROM key_register WHERE key_number = ?";
		PreparedStatement pstm = connection.prepareStatement(sql);
		pstm.setString(1, key.getKeyNumber());
		pstm.execute();
		ResultSet rs = pstm.getResultSet();
		if(rs.next() == false) {
			return false;
		}else { 
			return true;
		}
	}
	
	
	//checks if the key is valid, checking a series of criteria
	public boolean keyIsValid(Key key) throws SQLException{
		if(keyIsNotUnique(key)) {
			return false;
		}
		if(Verifiers.containSpecialCharacter(key.getKeyNumber())) {
			return false;
		}
		if(key.getKeyNumber().length()>5) {
			return false;
		}
		if(key.getKeyNumber().strip().isEmpty()) {
			return false;
		}
		if(key.getKeyDescription().strip().isEmpty()) {
			return false;
		}
		return true;
	}
	
	//generate a key list from all keys on the DB
	public List<Key> keyList(){
		String sql = "SELECT key_number, key_description FROM key_register ORDER BY key_number";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			ResultSet rs = pstm.getResultSet();
			List<Key> keyList = new ArrayList<>();
			while(rs.next())
	        {  
	           String keyNumber = rs.getString(1);
	           String keyDescription = rs.getString(2);
	           Key key = new Key(keyDescription, keyNumber);
	           keyList.add(key);
	        }return keyList;
		}catch(SQLException ex) {
			throw new RuntimeException("Erro na criação de tabela"+ex);
		}		
	}
	
	//delete key from the DB
	public boolean deleteKey(Key key) {
		String sql = "DELETE FROM key_register WHERE key_number = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setString(1, key.getKeyNumber());
			pstm.execute();
			return true;
		}catch(SQLException ex) {
			throw new RuntimeException("Erro ao deletar chave"+ex);
		}
	}
	
	
	
	
}
