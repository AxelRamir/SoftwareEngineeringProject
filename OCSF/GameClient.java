package OCSF;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ClientCommunications.CreateAccountControl;
import ClientCommunications.GameBoardControl;
import ClientCommunications.LoginControl;
import ClientCommunications.WaitingControl;
import ServerCommunication.CreateAccountData;
import ServerCommunication.InvalidLogin;
import ServerCommunication.LoginData;
import ServerCommunication.UnsuccessfulCreateAccount;
import ocsf.client.AbstractClient;

public class GameClient extends AbstractClient {
	//controllers for the GUIS
	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;
	private WaitingControl waitingControl;
	private GameBoardControl gameBoardControl;
	
	public GameClient(String host, int port) {
		super(host, port);
	}
	
	public void connectionEstablished() {
		
	}
	
	protected void handleMessageFromServer(Object arg0) {
		if(arg0 instanceof LoginData) {
			loginControl.showWait();
		}
		if(arg0 instanceof CreateAccountData) {
			createAccountControl.showWait();
		}
		if(arg0 instanceof InvalidLogin) {
			InvalidLogin invalidLogin = (InvalidLogin) arg0;
			loginControl.loginFailed(invalidLogin.getErrorMessage());
		}
		if(arg0 instanceof UnsuccessfulCreateAccount) {
			UnsuccessfulCreateAccount unsuccessfulCreateAccount = (UnsuccessfulCreateAccount) arg0;
			createAccountControl.createFailed(unsuccessfulCreateAccount.getErrorMessage());
		}
		//once the client gets the board object, it enables the board for the client and changes the UI to match the board object
		if(arg0 instanceof String) {
			String ready = (String) arg0;
			if(ready.equals("Ready")) {
				waitingControl.showBoard();
			}
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
	public void setWaitingControl(WaitingControl wc) {
		this.waitingControl = wc;
	}
	public void setGameBoardControl(GameBoardControl gbc) {
		this.gameBoardControl = gbc;
	}
}
