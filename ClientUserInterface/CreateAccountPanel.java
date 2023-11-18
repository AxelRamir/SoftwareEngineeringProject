package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import ClientCommunications.CreateAccountControl;

public class CreateAccountPanel extends JPanel{
	CreateAccountControl cac;
	
	private JLabel serverResponse;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JTextField passwordField;
	private JLabel password2Label;
	private JTextField password2Field;
	
	private JButton backButton;
	private JButton submitButton;
	
	public CreateAccountPanel(CreateAccountControl createAccountControl) {
		this.cac = createAccountControl;
		JPanel mainPanel = getMainPanel();
		this.add(mainPanel);
	}
	private JPanel getMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel northPanel = new JPanel();
		JLabel title = new JLabel("Create Account");
		northPanel.add(title);
		
		JPanel grid = new JPanel(new GridLayout(3, 1, 10, 10));
		
		JPanel serverPanel = new JPanel();
		serverResponse = new JLabel("");
		serverResponse.setForeground(Color.RED);
		serverPanel.add(serverResponse);
		
		JPanel gridPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		usernameLabel = new JLabel("Username: ", JLabel.RIGHT);
		usernameField = new JTextField(15);
		passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
		passwordField = new JTextField(15);
		password2Label = new JLabel("Re-enter Password: ", JLabel.RIGHT);
		password2Field = new JTextField(15);
		gridPanel.add(usernameLabel);
		gridPanel.add(usernameField);
		gridPanel.add(passwordLabel);
		gridPanel.add(passwordField);
		gridPanel.add(password2Label);
		gridPanel.add(password2Field);
		
		JPanel buttonPanel = new JPanel();
		backButton = new JButton("Back");
		backButton.addActionListener(cac);
		submitButton = new JButton("Submit");
		submitButton.addActionListener(cac);
		buttonPanel.add(backButton);
		buttonPanel.add(submitButton);
		
		JPanel centerPanel = new JPanel();
		
		grid.add(serverPanel);
		grid.add(gridPanel);
		grid.add(buttonPanel);
		
		centerPanel.add(grid);
		
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
	
		return mainPanel;
	}
	public JTextField getUsernameField() {
		return usernameField;
	}
	public JTextField getPasswordField() {
		return passwordField;
	}
	public JTextField getPassword2Field() {
		return password2Field;
	}
	public JLabel getServerResponse() {
		return serverResponse;
	}
}
