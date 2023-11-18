package ClientCommunications;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.*;

import ClientUserInterface.CreateAccountPanel;
import ClientUserInterface.HomePanel;
import ClientUserInterface.LoginPanel;
import OCSF.GameClient;

public class GameClientGUI extends JFrame{
	private GameClient client; 
	

	private CardLayout cardLayout = new CardLayout();
	private JPanel container = new JPanel(cardLayout);
	
	public GameClientGUI(String host, int port) {
		super();
		client = new GameClient(host, port);
		this.setTitle("Checkers");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//set container
		try {
			client.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//set controls
		HomeControl hc = new HomeControl(container, client);
		LoginControl lc = new LoginControl(container, client);
		CreateAccountControl cac = new CreateAccountControl(container, client);
		
		//set panels
		JPanel homePanel = new HomePanel(hc);
		JPanel LoginPanel = new LoginPanel(lc);
		JPanel createAccountPanel = new CreateAccountPanel(cac);
		
		client.setCreateAccountControl(cac);
		client.setLoginControl(lc);
		
		//adding panels to container
		container.add(homePanel, "1");
		container.add(LoginPanel, "2");
		container.add(createAccountPanel, "3");
		
		cardLayout.show(container, "1");
		
		this.add(container);
		
		this.setSize(500, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		new GameClientGUI(host, port);
	}
}
