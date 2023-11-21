package ClientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import ClientUserInterface.CreateAccountPanel;
import ClientUserInterface.GameBoardPanel;
import OCSF.GameClient;
import ServerCommunication.CreateAccountData;

public class CreateAccountControl implements ActionListener{
	private JPanel container;
	private GameClient client;
	
	public CreateAccountControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		CreateAccountPanel cap = (CreateAccountPanel) container.getComponent(2);
		CardLayout cardLayout = (CardLayout) container.getLayout();
		if(e.getActionCommand() == "Back") {
			cardLayout.show(container, "1");
		}
		else if(e.getActionCommand() == "Submit") {
			String username = cap.getUsernameField().getText();
			String password = cap.getPasswordField().getText();
			String password1 = cap.getPassword2Field().getText();
			CreateAccountData data = new CreateAccountData(username, password, password1);
			try {
				client.sendToServer(data);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public void createFailed(String message) {
		CreateAccountPanel cap = (CreateAccountPanel) container.getComponent(2);
		cap.getServerResponse().setText(message);
	}
	public void showWait() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "4");
	}
}
