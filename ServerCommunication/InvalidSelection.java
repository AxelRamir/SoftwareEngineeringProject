package ServerCommunication;

import java.io.Serializable;

public class InvalidSelection implements Serializable{
	private String message;
	
	public InvalidSelection(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return message;
	}
}
