package smingolera.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import smingolera.models.User;
import smingolera.utilities.Conversion;
import smingolera.utilities.Verifiers;

public class UserRegistrationDAO {
	
	private Connection connection;
	
	public UserRegistrationDAO(Connection con) {
		this.connection = con;
	}
	
	//create a new user on the DB
	public boolean userRegistration(User user){
		String sql = "INSERT INTO users (username, person_name, permission_level, password) VALUES (?, ?, ?, ?)";
			try (PreparedStatement pstm = connection.prepareStatement(sql)){
			
				pstm.setString(1, user.getUser());
				pstm.setString(2, user.getName());
				pstm.setInt(3, user.getLevel());
				pstm.setString(4, user.getPass());
				if (usernameIsValid(user.getUser())){
					pstm.execute();
					return true;
					
				}else {
					return false;
				}				
		} catch (SQLException ex) {
			throw new RuntimeException("Erro no Registro de Usuario" + ex);
		}
	}
	
	
	//verify if the username is already in use
	private boolean userAlreadyExists(String username) {
		List<String> usernameList = usernameList();
		if(usernameList.contains(username)) {
			return true;
		}else return false;
	}
	
	
	//generate a list with all data from all users
//	public List<User> allUserDataList(){
//		List<User> userList = new ArrayList<User>();
//		String sql = "SELECT * FROM users";
//		try(PreparedStatement pstm = connection.prepareStatement(sql)){
//			pstm.execute();
//			ResultSet rst = pstm.getResultSet();
//			while (rst.next()) {
//				String username = rst.getString("username");
//				String pass = rst.getString("password");
//				int permission = rst.getInt("permission_level");
//				String name = rst.getString("person_name");
//				User user = new User(username, name, pass, permission);
//				userList.add(user);
//			}
//			return userList;
//		}catch (SQLException ex) {
//			throw new RuntimeException("Erro ao gerar lista de usuarios"+ex);
//		}
//	}
	
	//generate a list of all users username
	public List<String> usernameList(){
		List<String> usernameList = new ArrayList<String>();
		String sql = "SELECT username FROM users";
		try(PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			ResultSet rst = pstm.getResultSet();
			while (rst.next()) {
				String username = rst.getString("username");
				usernameList.add(username);
			}
			return usernameList;
		}catch (SQLException ex) {
			throw new RuntimeException("Erro ao gerar lista de usuarios"+ex);
		}
	}
	
	//check if the username match all criteria
	private boolean usernameIsValid(String username) {
		if(username.length()<4 || username.length()>8) {
			return false;
		}
		if (userAlreadyExists(username)) {
			return false;
		}
		if(username.strip().isEmpty()) {
			return false;
		}
		if(Verifiers.containSpecialCharacter(username)) {
			return false;
		}
		return true;
	}
	
	//get all users with name and permission level
	public List<User> listOfUsers() {
		String sql = "SELECT username, person_name, permission_level FROM users";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.execute();
			ResultSet rs = pstm.getResultSet();
			List<User> userList = new ArrayList<>();
			while(rs.next())
	        {  
	           String username = rs.getString(1);
	           String name = rs.getString(2);
	           int level = rs.getInt(3);
	           String levelDescription = Conversion.permissionLevel_IntIntoStr(level);
	           User user = new User(username, name, levelDescription);
	           userList.add(user);
	        }return userList;
		}catch(SQLException ex) {
			throw new RuntimeException("Erro na criação de tabela"+ex);
		}
	}
	
	//delete a user from DB
	public boolean deleteUserFromDB(String username) {
		String sql = "DELETE FROM users WHERE username=?";
		try (PreparedStatement pstm = connection.prepareStatement(sql)){
			pstm.setString(1, username);
			pstm.execute();
			return true;
		}catch(SQLException ex) {
			throw new RuntimeException("Erro ao deletar usuário."+ex);
		}
	}
	
	
}
