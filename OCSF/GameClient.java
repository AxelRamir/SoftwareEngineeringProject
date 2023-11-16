package OCSF;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ClientCommunications.InitialControl;
import ServerCommunication.InvalidLogin;
import ServerCommunication.LoginData;
import ServerCommunication.UnsuccessfulCreateAccount;
import ocsf.client.AbstractClient;

public class GameClient extends AbstractClient {
	//controllers for the GUIS
	private InitialControl initialControl;
	
	public GameClient() {
		super("localhost", 8300);
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
			System.out.println(invalidLogin.getErrorMessage());
		}
		if(arg0 instanceof UnsuccessfulCreateAccount) {
			UnsuccessfulCreateAccount unsuccessfulCreateAccount = (UnsuccessfulCreateAccount) arg0;
			System.out.println(unsuccessfulCreateAccount.getErrorMessage());
		}
	}
	
	public void connectionClosed() {
		
	}
	
	public void setInitialControl(InitialControl initialControl) {
		this.initialControl = initialControl;
	}
}
