package ClientCommunications;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import ClientUserInterface.InitialPanel;
import OCSF.GameClient;

public class InitialControl implements ActionListener{
	private JPanel container;
	private GameClient client;
	
	public InitialControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		InitialPanel initialPanel = (InitialPanel) container.getComponent(0);
		if(e.getActionCommand() == "Submit") {
			initialPanel.getResponseLabel().setText("");
			String host = initialPanel.getServerHost().getText();
			int port = Integer.parseInt(initialPanel.getPort().getText());
			this.client = new GameClient();
			this.client.setHost(host);
			this.client.setPort(port);
			try {
				//attempts to open connection to the server
				this.client.openConnection();
				successfulConnection();
			} catch (IOException e1) {
				//if the client couldn't connect to the server
				failedConnection();
			}
		}
	}
	
	private void failedConnection() {
		InitialPanel initialPanel = (InitialPanel) container.getComponent(0);
		initialPanel.getResponseLabel().setText("Server could not be found with host and port.");
		initialPanel.getResponseLabel().setForeground(Color.RED);
	}
	private void successfulConnection() {
		InitialPanel initialPanel = (InitialPanel) container.getComponent(0);
		initialPanel.getStatusConnection().setText("Connected");
		initialPanel.getStatusConnection().setForeground(Color.GREEN);
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "2");
	}
}
