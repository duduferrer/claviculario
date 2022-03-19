package smingolera.models;

public class User {
	private String user;
	private String pass;
	private String name;
	private int level;
	private String levelDesc;
	
	//constructors
	public User(String user, String name, String pass, int level){
		this.user = user;
		this.name = name;
		this.pass = pass;
		this.level = level;
		
	}
	public User(String user) {
		this.user = user;
	}
	
	public User(String user, String pass) {
		this.user = user;
		this.pass = pass;
	}
	public User(String user, String name, String levelDesc) {
		this.user = user;
		this.name = name;
		this.levelDesc = levelDesc;
	}
	public User(String user, String name, int level) {
		this.user = user;
		this.name = name;
		this.level = level;
	}
	
	
	//getters and setters
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getLevelDesc() {
		return levelDesc;
	}

}
