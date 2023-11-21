package ClientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import ClientUserInterface.LoginPanel;
import OCSF.GameClient;
import ServerCommunication.LoginData;

public class LoginControl implements ActionListener{
	private JPanel container;
	private GameClient client;
	
	public LoginControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
		CardLayout cardLayout = (CardLayout) container.getLayout();
		if(e.getActionCommand() == "Submit") {
			String username = loginPanel.getUsernameField().getText();
			String password = loginPanel.getPasswordField().getText();
			LoginData data = new LoginData(username, password);
			try {
				client.sendToServer(data);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(e.getActionCommand() == "Back") {
			cardLayout.show(container, "1");
		}
	}
	public void loginFailed(String message) {
		LoginPanel loginPanel = (LoginPanel) container.getComponent(1);
		loginPanel.getServerResponse().setText(message);
	}
	public void showWait() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "4");
	}
	
}
