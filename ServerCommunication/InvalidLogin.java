package ServerCommunication;

import java.io.Serializable;

public class InvalidLogin implements Serializable{
	String errorMessage;
	public InvalidLogin(String message) {
		this.errorMessage = message;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
