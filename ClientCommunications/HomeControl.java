package ClientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import ClientUserInterface.HomePanel;
import OCSF.GameClient;

public class HomeControl implements ActionListener{
	private JPanel container;
	private GameClient client;
	
	public HomeControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		HomePanel homePanel = (HomePanel) container.getComponent(0);
		CardLayout cardLayout = (CardLayout) container.getLayout();
		
		if(e.getActionCommand() == "Create Account") {
			try {
				client.openConnection();
				cardLayout.show(container, "3");
				homePanel.getConnectionLabel().setText("");
			}catch (Exception ex) {
				homePanel.getConnectionLabel().setText("Failed to connect to server");
			}
			
		}
		else if(e.getActionCommand() == "Login") {
			try {
				client.openConnection();
				cardLayout.show(container, "2");
				homePanel.getConnectionLabel().setText("");
			}catch (Exception ex) {
				homePanel.getConnectionLabel().setText("Failed to connect to server.");
			}
		}
	}
}
