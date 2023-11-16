package ClientCommunications;

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
		HomePanel homePanel = (HomePanel) container.getComponent(1);
		if(e.getActionCommand() == "CreateAccount") {
		}
		else if(e.getActionCommand() == "Login") {
		}
	}
}
