package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.*;

import ClientCommunications.HomeControl;

public class HomePanel extends JPanel{
	private JButton createAccountButton;
	private JButton loginButton;
	
	private HomeControl homeControl;
	
	public HomePanel(HomeControl control) {
		this.homeControl = control;
		JPanel mainPanel = getMainPanel();
		this.add(mainPanel);
	}
	
	private JPanel getMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//northPanel
		JPanel northPanel = new JPanel();
		JLabel title = new JLabel("Login/Create Account");
		northPanel.add(title);
		
		//centerPanel
		JPanel centerPanel = new JPanel(new GridLayout(2, 1, 300, 300));
		
		JPanel firstPanel = new JPanel();
		createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(homeControl);
		firstPanel.add(createAccountButton);
		
		JPanel secondPanel = new JPanel();
		loginButton = new JButton("Login");
		loginButton.addActionListener(homeControl);
		secondPanel.add(loginButton);
		
		centerPanel.add(firstPanel);
		centerPanel.add(secondPanel);
		
		
		
		//adding Components to mainPanel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		return mainPanel;
	}
	
}
