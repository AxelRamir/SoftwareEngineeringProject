package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import ClientCommunications.LoginControl;

public class LoginPanel extends JPanel{
	private JLabel serverResponse;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	
	private JLabel passwordLabel;
	private JTextField passwordField;
	
	private JButton submitButton;
	private JButton backButton;
	
	private LoginControl loginControl;
	
	public LoginPanel(LoginControl loginControl) {
		this.loginControl = loginControl;
		JPanel mainPanel = getMainPanel();
		this.add(mainPanel);
	}
	private JPanel getMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//northPanel
		JPanel northPanel = new JPanel();
		JLabel title = new JLabel("Login");
		northPanel.add(title);
		
		//centerPanel
		JPanel centerPanel = new JPanel();
		
		//grid
		JPanel gridPanel = new JPanel(new GridLayout(3, 1, 10, 10));
		
		JPanel serverResponsePanel = new JPanel();
		serverResponse = new JLabel("");
		serverResponse.setForeground(Color.RED);
		serverResponsePanel.add(serverResponse);
		
		JPanel userPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		usernameLabel = new JLabel("Username: ", JLabel.RIGHT);
		usernameField = new JTextField(15);
		passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
		passwordField = new JTextField(15);
		
		userPanel.add(usernameLabel);
		userPanel.add(usernameField);
		userPanel.add(passwordLabel); 
		userPanel.add(passwordField);
		
		JPanel buttonPanel = new JPanel ();
		backButton = new JButton("Back");
		backButton.addActionListener(loginControl);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(loginControl);
		
		buttonPanel.add(backButton);
		buttonPanel.add(submitButton);
		
		gridPanel.add(serverResponsePanel);
		gridPanel.add(userPanel);
		gridPanel.add(buttonPanel);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		centerPanel.add(gridPanel);
		
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		
		
		return mainPanel;
	}
	
	//getters
	public JLabel getServerResponse() {
		return serverResponse;
	}
	public JTextField getUsernameField() {
		return usernameField;
	}
	public JTextField getPasswordField() {
		return passwordField;
	}
}
