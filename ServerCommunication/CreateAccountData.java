package ServerCommunication;

import java.io.Serializable;

public class CreateAccountData implements Serializable{
	private String username;
	private String password;
	private String password2;
	
	public CreateAccountData(String username, String password, String password2){
		this.username = username;
		this.password = password;
		this.password2 = password2;
	}
	
	//getters
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getPassword2() {
		return password2;
	}
	
	//setters
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
}
