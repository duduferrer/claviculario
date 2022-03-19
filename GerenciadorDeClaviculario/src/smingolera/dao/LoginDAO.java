package smingolera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import smingolera.models.User;

public class LoginDAO {
	
private Connection connection;
	
public LoginDAO(Connection con) {
	this.connection = con;
	}

//verify if the user and pass combination match. No case sensitive
public boolean authenticateUser(User user) {
	String sql = "SELECT * FROM users WHERE username=?";
	try (PreparedStatement pstm = connection.prepareStatement(sql)){	
		pstm.setString(1, user.getUser());
		pstm.execute();
		ResultSet rs = pstm.getResultSet();
		if(rs.next()){
			if(passwordsMatchForLogin(rs, user)){
				return true;
			}else {
				return false;
			}
			}else {
				return false;
			}				
} catch (SQLException ex) {
	throw new RuntimeException("Erro no Login" + ex);
}
	
}


//get the user data of the logged user
public User getUserData(User user){
	String sql = "SELECT * FROM users WHERE username=?";
	try (PreparedStatement pstm = connection.prepareStatement(sql)){	
		pstm.setString(1, user.getUser());
		pstm.execute();
		ResultSet rs = pstm.getResultSet();
		rs.next();
		String username = rs.getString("username");
		String name = rs.getString("person_name");
		int level = rs.getInt("permission_level");
		User authUser = new User(username, name, level);
		return authUser;	
	}catch (SQLException ex){
		throw new RuntimeException("Erro pegando dados do usuario"+ex);	
	}
}

//update user password on DB
public boolean updatePasswordOnDB(User user, String newPass, String confPass) {
	if(passwordsMatchForUpdate(newPass, confPass)) {
		String sql = "UPDATE users SET password = ? WHERE username = ?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){	
			pstm.setString(2, user.getUser());
			pstm.setString(1, newPass);
			System.out.println(pstm);
			int rowsUpdated = pstm.executeUpdate();
			if (rowsUpdated == 1) {
				return true;
			}else {
				return false;
			}
			}catch(SQLException ex) {
			throw new RuntimeException("Erro ao atualizar senha"+ex);
			}
		}else {
			return false;
		}
	}

//check if the informed password is the same registered on DB
public boolean passwordsMatchForUpdate(String newPass, String confPass) {
	if(newPass.contentEquals(confPass)) {
		return true;
	}else {
		return false;
	}
	}

//check if the informed password is the same registered on DB
public boolean passwordsMatchForLogin(ResultSet rs, User user)  throws SQLException{
	String passwordDB = rs.getString("password");
	String passwordGiven = user.getPass();
	if(passwordDB.equals(passwordGiven)) {
		return true;
	}else {
		return false;
	}
	
}



}