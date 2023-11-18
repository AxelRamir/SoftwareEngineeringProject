package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import ClientCommunications.HomeControl;

public class HomePanel extends JPanel{
	private JLabel connectionLabel;
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
		JPanel centerPanel = new JPanel(new GridLayout(3, 1));
		
		JPanel firstPanel = new JPanel();
		connectionLabel = new JLabel("");
		connectionLabel.setForeground(Color.RED);
		firstPanel.add(connectionLabel);
		
		JPanel secondPanel = new JPanel();
		createAccountButton = new JButton("Create Account");
		createAccountButton.addActionListener(homeControl);
		secondPanel.add(createAccountButton);
		
		
		
		JPanel thirdPanel = new JPanel();
		loginButton = new JButton("Login");
		loginButton.addActionListener(homeControl);
		thirdPanel.add(loginButton);
		
		centerPanel.add(firstPanel);
		centerPanel.add(secondPanel);
		centerPanel.add(thirdPanel);
		
		
		
		//adding Components to mainPanel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	public JLabel getConnectionLabel() {
		return connectionLabel;
	}
	
}
