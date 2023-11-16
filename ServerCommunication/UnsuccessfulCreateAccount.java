package ServerCommunication;

import java.io.Serializable;

public class UnsuccessfulCreateAccount implements Serializable{
	private String errorMessage;
	
	public UnsuccessfulCreateAccount(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
