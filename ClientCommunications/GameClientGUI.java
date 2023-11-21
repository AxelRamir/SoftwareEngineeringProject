package ClientCommunications;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.*;

import ClientUserInterface.CreateAccountPanel;
import ClientUserInterface.GameBoardPanel;
import ClientUserInterface.HomePanel;
import ClientUserInterface.LoginPanel;
import ClientUserInterface.WaitingPanel;
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
		WaitingControl wc = new WaitingControl(container, client);
		GameBoardControl gbc = new GameBoardControl(container, client);
		
		//set panels
		JPanel homePanel = new HomePanel(hc);
		JPanel LoginPanel = new LoginPanel(lc);
		JPanel createAccountPanel = new CreateAccountPanel(cac);
		JPanel waitingPanel = new WaitingPanel(wc);
		JPanel gameBoardPanel = new GameBoardPanel(gbc);
		
		client.setCreateAccountControl(cac);
		client.setLoginControl(lc);
		client.setWaitingControl(wc);
		client.setGameBoardControl(gbc);
		
		//adding panels to container
		container.add(homePanel, "1");
		container.add(LoginPanel, "2");
		container.add(createAccountPanel, "3");
		container.add(waitingPanel, "4");
		container.add(gameBoardPanel, "5");
		
		cardLayout.show(container, "1");
		
		this.add(container);
		
		this.setSize(600, 600);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		new GameClientGUI(host, port);
	}
}
