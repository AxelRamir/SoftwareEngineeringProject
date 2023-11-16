package ClientCommunications;

import java.awt.CardLayout;

import javax.swing.*;

import ClientUserInterface.HomePanel;
import ClientUserInterface.InitialPanel;
import OCSF.GameClient;

public class GameClientGUI extends JFrame{
	private GameClient client; 
	
	public GameClientGUI() {
		super();
		this.setTitle("Checkers");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//set container
		CardLayout cardLayout = new CardLayout();
		JPanel container = new JPanel(cardLayout);
		
		//set controls
		InitialControl ic = new InitialControl(container, client);
		HomeControl hc = new HomeControl(container, client);
		
		//set panels
		JPanel initialPanel = new InitialPanel(ic);
		JPanel homePanel = new HomePanel(hc);
		
		//adding panels to container
		container.add(initialPanel, "1");
		container.add(homePanel, "2");
		
		cardLayout.show(container, "1");
		
		this.add(container);
		
		this.setSize(500, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		new GameClientGUI();
	}
}
