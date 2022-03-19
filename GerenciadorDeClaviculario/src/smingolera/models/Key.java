package smingolera.models;

public class Key {
	private String keyDescription;
	private String keyNumber;
	
	public Key(String keyDescription, String keyNumber) {
		this.keyDescription = keyDescription;
		this.keyNumber = keyNumber;		
	}
	public Key(String keyNumber) {
		this.keyNumber = keyNumber;		
	}

	public String getKeyDescription() {
		return keyDescription;
	}

	public String getKeyNumber() {
		return keyNumber;
	}
	

}
