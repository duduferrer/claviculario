package smingolera.models;

public class KeyLog {
	private String keyNumber;
	private String dateCheckout;
	private String requesterName;
	private String dateCheckin;
	private String usernameCheckout;
	private String usernameCheckin;
	private String keyDescription;
	
	public KeyLog(String keyNumber, String dateCheckout, String requesterName, String keyDescription) {
		
		this.keyNumber = keyNumber;
		this.dateCheckout = dateCheckout;
		this.requesterName = requesterName;
		this.keyDescription = keyDescription;
		
	}
	public KeyLog(String dateCheckout, String requesterName, String dateCheckin, String usernameCheckout, String usernameCheckin,	String keyDescription) {

		this.dateCheckout = dateCheckout;
		this.requesterName = requesterName;
		this.dateCheckin = dateCheckin;
		this.usernameCheckout = usernameCheckout;
		this.usernameCheckin = usernameCheckin;
		this.keyDescription = keyDescription;
		
	}
	
	
	public String getKeyDescription() {
		return keyDescription;
	}

	public void setKeyDescription(String keyDescription) {
		this.keyDescription = keyDescription;
	}
	
	public String getKeyNumber() {
		return keyNumber;
	}
	public String getDateCheckout() {
		return dateCheckout;
	}
	public String getRequesterName() {
		return requesterName;
	}
	public String getDateCheckin() {
		return dateCheckin;
	}
	public String getUsernameCheckout() {
		return usernameCheckout;
	}
	public String getUsernameCheckin() {
		return usernameCheckin;
	}
	public void setKeyNumber(String keyNumber) {
		this.keyNumber = keyNumber;
	}
	public void setDateCheckout(String dateCheckout) {
		this.dateCheckout = dateCheckout;
	}
	public void setRequesterName(String requesterName) {
		this.requesterName = requesterName;
	}
	public void setDateCheckin(String dateCheckin) {
		this.dateCheckin = dateCheckin;
	}
	public void setUsernameCheckout(String usernameCheckout) {
		this.usernameCheckout = usernameCheckout;
	}
	public void setUsernameCheckin(String usernameCheckin) {
		this.usernameCheckin = usernameCheckin;
	}
	
	
	
}
