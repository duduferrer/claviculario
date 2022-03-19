package smingolera.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import smingolera.models.User;

//CHECK CONDITIONS
public abstract class Verifiers {
	
	//CHECK IF USER IS ADMIN
	public static boolean isAdmin(User user) {
		int permission_level = user.getLevel();
		if(permission_level == 1) {
			return true;
		}else {
			return false;
		}
	}
	//CHECK IF THE STRING CONTAIN ANY SPECIAL CHAR
	public static boolean containSpecialCharacter(String text) {
		if(text.strip().isEmpty()) return true;
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(text);
		boolean matches = matcher.find();
		if(matches) { 
			return true;
		}else {
			return false;
		}
	}

}
