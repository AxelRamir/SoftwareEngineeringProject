package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import ClientCommunications.InitialControl;

public class InitialPanel extends JPanel{
	
	private JLabel statusLabel;
	private JLabel statusConnection;
	
	private JLabel serverResponse;
	
	private JLabel serverURL;
	private JTextField serverURLField;
	
	private JLabel serverPort;
	private JTextField serverPortField;
	
	private JButton submitButton;
	
	private InitialControl initialControl;
	
	public InitialPanel(InitialControl control) {
		this.initialControl = control;
		JPanel mainPanel = getMainPanel();
		
		this.add(mainPanel);
	}
	
	private JPanel getMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		
		//northPanel
		JPanel northPanel = new JPanel();
		JLabel titleLabel = new JLabel("Connection Setup");
		northPanel.add(titleLabel);
		
		//centerPanel
		JPanel centerPanel = new JPanel(new GridLayout(5, 1, 25, 20));
		
		//firstPanel
		JPanel firstPanel = new JPanel();
		statusLabel = new JLabel("Status: ", JLabel.RIGHT);
		statusConnection = new JLabel("Not Connected");
		statusConnection.setForeground(Color.RED);
		firstPanel.add(statusLabel);
		firstPanel.add(statusConnection);
		
		//secondPanel
		JPanel secondPanel = new JPanel();
		serverResponse = new JLabel();
		secondPanel.add(serverResponse);
		
		
		//thirdPanel
		JPanel thirdPanel = new JPanel();
		serverURL = new JLabel("Server URL: ", JLabel.RIGHT);
		serverURLField = new JTextField(10);
		thirdPanel.add(serverURL);
		thirdPanel.add(serverURLField);
		
		//fourthPanel
		JPanel fourthPanel = new JPanel();
		serverPort = new JLabel("Server Port: ", JLabel.RIGHT);
		serverPortField = new JTextField(10);
		fourthPanel.add(serverPort);
		fourthPanel.add(serverPortField);
		
		//fifthPanel
		JPanel fifthPanel = new JPanel();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(initialControl);
		fifthPanel.add(submitButton);
		
		//centerPanel
		centerPanel.add(firstPanel);
		centerPanel.add(secondPanel);
		centerPanel.add(thirdPanel);
		centerPanel.add(fourthPanel);
		centerPanel.add(fifthPanel);
		
		
		//adding components to the mainPanel
		mainPanel.add(northPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		return mainPanel;
	}
	
	public JTextField getServerHost() {
		return this.serverURLField;
	}
	
	public JTextField getPort() {
		return this.serverPortField;
	}
	public JLabel getResponseLabel() {
		return this.serverResponse;
	}
	public JLabel getStatusConnection() {
		return this.statusConnection;
	}
}
