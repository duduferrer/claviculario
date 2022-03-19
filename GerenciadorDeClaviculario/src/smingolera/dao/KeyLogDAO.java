package smingolera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import smingolera.models.KeyLog;
import smingolera.models.User;

public class KeyLogDAO {
private Connection connection;
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public KeyLogDAO(Connection con) {
		this.connection = con;
	}
	
	//register a checkout on the DB
	public boolean keyCheckout(String username, String requesterName, String key){
		Timestamp timestamp = getCurrentDateHour();
		String dateCheckout = sdf.format(timestamp);
		String sql = "INSERT INTO key_log (key_number, date_checkout, requester_name, username_checkout) VALUES (?,?,?,?)";
		if(keyIsAvailable(key)) {
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setString(1, key);
				pstm.setString(2, dateCheckout);
				pstm.setString(3, requesterName);
				pstm.setString(4, username);
				pstm.execute();
				return true;
			}catch(SQLException ex) {
				throw new RuntimeException("Erro ao incluir registro de chave"+ex);
			}
		}else {
			return false;
		}
	}
	
	//get the current date and hour from the system
	public Timestamp getCurrentDateHour() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp;
	}
	
	//checks if the key is available to be borrowed
	public boolean keyIsAvailable(String key) {
		List<String> keysOut = keysOutByNumber();
		if(keysOut.contains(key)) {
			return false;
		}
		return true;
		
	}
	
	//generate a list containing the keys borrowed without full data
	public List<String> keysOutByNumber(){
		List<String> keysOut = new ArrayList<String>();
		String sql = "SELECT key_number FROM key_log WHERE date_checkin is NULL";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			ResultSet rs = pstm.getResultSet();
			while(rs.next()) {
				String keyNumber = rs.getString("key_number");
				keysOut.add(keyNumber);				
			}
			return keysOut;
			
		}catch(SQLException ex) {
			throw new RuntimeException("Erro ao gerar lista de chaves fora"+ex);
		}
		
	}
	
	//generate a list of keys borrowed with the full data
	public List<KeyLog> keysOut(){
		List<KeyLog> keysOut = new ArrayList<KeyLog>();
		String sql = "SELECT key_log.key_number, requester_name, date_checkout, key_description FROM key_log INNER JOIN key_register on key_register.key_number = key_log.key_number WHERE date_checkin is NULL;";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			ResultSet rs = pstm.getResultSet();
			while(rs.next()) {
				String keyNumber = rs.getString("key_number");
				String requesterName = rs.getString("requester_name");
				Timestamp timestamp = rs.getTimestamp("date_checkout");
				String dateCheckout = sdf.format(timestamp);
				String keyDescription = rs.getString("key_description");
				KeyLog keyRecord = new KeyLog(keyNumber, dateCheckout, requesterName, keyDescription);
				keysOut.add(keyRecord);				
			}
			return keysOut;
			
		}catch(SQLException ex) {
			throw new RuntimeException("Erro ao gerar lista de chaves fora"+ex);
		}
		
	}
	//update the register with the date that the key was received and the name of the receiver 
	public boolean keyCheckin(String keyNumber, User user) {
		Timestamp timestamp = getCurrentDateHour();
		String dateCheckin = sdf.format(timestamp);
		String sql = "UPDATE key_log SET date_checkin = ?, username_checkin = ? WHERE key_number = ? AND date_checkin is NULL";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setString(1, dateCheckin);
			pstm.setString(2, user.getUser());
			pstm.setString(3, keyNumber);
			pstm.execute();
			return true;
			}catch(SQLException ex) {
				throw new RuntimeException("Erro update chave checkin"+ex);
			}
	}

	
	public List<KeyLog> keyHistory(String keyNumber, LocalDate iniDate, LocalDate finDate) {
		List<KeyLog> keyHistory = new ArrayList<KeyLog>();
		String sql = "";
		//if keyNumber is empty, start the sql and the pstm without the number of the key
		if(keyNumber.isEmpty()) {
			sql = "SELECT key_description, date_checkout, date_checkin, requester_name, username_checkout, username_checkin FROM key_log INNER JOIN key_register on key_register.key_number = key_log.key_number "
					+ "WHERE key_log.date_checkout BETWEEN ? AND ? ORDER BY date_checkout";
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setObject(1, iniDate);
				pstm.setObject(2, finDate);
				pstm.execute();
				ResultSet rs = pstm.getResultSet();
				while (rs.next()) {
					String keyDescription = rs.getString("key_description");
					String dateCheckOut = rs.getString("date_checkout");
					String dateCheckIn = rs.getString("date_checkin");
					String requesterName = rs.getString("requester_name");
					//user that gave the key
					String userCheckOut = rs.getString("username_checkout");
					//user that received the key
					String userCheckIn = rs.getString("username_checkin");
					KeyLog keyRecord = new KeyLog(dateCheckOut, requesterName, dateCheckIn, userCheckOut, userCheckIn, keyDescription);
					keyHistory.add(keyRecord);
				}
			}catch(SQLException ex) {
				throw new RuntimeException("Erro pegando historico da chave"+ex);
			}
		//if keyNumber is not empty, start the sql and the pstm with the number of the key
		}else {
			sql = "SELECT key_description, date_checkout, date_checkin, requester_name, username_checkout, username_checkin FROM key_log INNER JOIN key_register on key_register.key_number = key_log.key_number "
					+ "WHERE key_log.key_number = ? AND key_log.date_checkout BETWEEN ? AND ? ORDER BY date_checkout";	
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
				pstm.setString(1, keyNumber);
				pstm.setObject(2, iniDate);
				pstm.setObject(3, finDate);
				pstm.execute();
				ResultSet rs = pstm.getResultSet();
				while (rs.next()) {
					String keyDescription = rs.getString("key_description");
					String dateCheckOut = rs.getString("date_checkout");
					String dateCheckIn = rs.getString("date_checkin");
					String requesterName = rs.getString("requester_name");
					//user that gave the key
					String userCheckOut = rs.getString("username_checkout");
					//user that received the key
					String userCheckIn = rs.getString("username_checkin");
					KeyLog keyRecord = new KeyLog(dateCheckOut, requesterName, dateCheckIn, userCheckOut, userCheckIn, keyDescription);
					keyHistory.add(keyRecord);
				}
			}catch(SQLException ex) {
				throw new RuntimeException("Erro pegando historico da chave"+ex);
			}
		}
		return keyHistory;			
	}
	
}
