package smingolera.utilities.enums;

public enum PermissionLevelENUM {
	ADMIN("Admin"),
	SGT_DE_DIA("Sgt de Dia"),
	SENTINELA("Sentinela");
	
	private final String description;
	
	private PermissionLevelENUM(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
