package ClientCommunications;

import java.awt.CardLayout;

import javax.swing.JPanel;

import OCSF.GameClient;

public class WaitingControl {
	private JPanel container;
	private GameClient client;
	
	public WaitingControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}
	public void showBoard() {
		CardLayout cardLayout = (CardLayout) container.getLayout();
		cardLayout.show(container, "5");
	}
}
