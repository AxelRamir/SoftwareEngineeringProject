package OCSF;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ClientCommunications.CreateAccountControl;
import ClientCommunications.LoginControl;
import ServerCommunication.InvalidLogin;
import ServerCommunication.LoginData;
import ServerCommunication.UnsuccessfulCreateAccount;
import ocsf.client.AbstractClient;

public class GameClient extends AbstractClient {
	//controllers for the GUIS
	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;
	
	public GameClient(String host, int port) {
		super(host, port);
	}
	
	public void connectionEstablished() {
		
	}
	
	protected void handleMessageFromServer(Object arg0) {
		//might need a loginSuccess class so server can send that? not sure what the class would contain tho so for now server just
		//sends the login data back to the client
		if(arg0 instanceof LoginData) {
			System.out.println("Successful Login");
		}
		
		if(arg0 instanceof InvalidLogin) {
			InvalidLogin invalidLogin = (InvalidLogin) arg0;
			loginControl.loginFailed(invalidLogin.getErrorMessage());
		}
		if(arg0 instanceof UnsuccessfulCreateAccount) {
			UnsuccessfulCreateAccount unsuccessfulCreateAccount = (UnsuccessfulCreateAccount) arg0;
			createAccountControl.createFailed(unsuccessfulCreateAccount.getErrorMessage());
		}
	}
	
	public void connectionClosed() {
		
	}

	public void setCreateAccountControl(CreateAccountControl cac) {
		this.createAccountControl = cac;
	}
	public void setLoginControl(LoginControl lc) {
		this.loginControl = lc;
	}
	
}
