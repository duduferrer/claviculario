package smingolera.utilities;


public abstract class Conversion {
	

	//CONVERTS PERMISSION LEVEL STR to INT
	public static int permissionLevel_StrIntoInt(String selectedPermissionDescription) {
		switch (selectedPermissionDescription) {
		case "Admin": {
			return 1;
		}case "Sgt de Dia": {
			return 2;
		}case "Sentinela": {
			return 3;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + selectedPermissionDescription);
		}
	}

	//CONVERTS PERMISSION LEVEL INT to STR
	public static String permissionLevel_IntIntoStr(int selectedPermissionLevel) {
		switch (selectedPermissionLevel) {
		case 1: {
			return "Admin";
		}case 2: {
			return "Sgt de Dia";
		}case 3: {
			return "Sentinela";
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + selectedPermissionLevel);
		}
	}

}
