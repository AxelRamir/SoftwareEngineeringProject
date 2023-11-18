package ClientCommunications;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import OCSF.GameClient;
import ServerCommunication.CreateAccountData;
import ServerCommunication.LoginData;

public class GameClientGUI2 extends JFrame {
	
	//lab1in
	private JLabel statusLabel;
	private JLabel statusConnection;
	
	private JLabel clientID;
	private JTextField clientTxt;
	
	private JLabel serverURL;
	private JTextField serverTxt;
	
	private JLabel serverPort;
	private JTextField portTxt;
	private JLabel clientData;
	
	private JButton connect;
	private JButton submit;
	private JButton stop;
	
	//lab1out
	private String[] labels = {"Client ID", "Server URL", "Servet Port"};
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea clientArea = new JTextArea();
	private JTextArea serverArea = new JTextArea();
	
	//lab3out
	private GameClient client = new GameClient("localhost", 8300);

	public GameClientGUI2() {
		int i = 0;
		
		this.setTitle("Client");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ADD YOUR CODE HERE TO CREATE THE STATUS JLABEL AND THE JBUTTONS
		
	
		//North code
		statusLabel = new JLabel("Status: ");
		statusConnection = new JLabel("Not Connected");
		statusConnection.setForeground(Color.red);
		JPanel north = new JPanel(new FlowLayout());
		north.add(statusLabel);
		north.add(statusConnection);
		
		//center code
		//first Third
		
		//contents for first row
		clientID = new JLabel(labels[i]);
		clientTxt = new JTextField();
		textFields[i] = clientTxt;
		i++;
		clientTxt.setPreferredSize(new Dimension(100, 20));
		clientTxt.setEditable(false);
		
		//firstRow
		JPanel firstRow = new JPanel(new FlowLayout());
		firstRow.add(clientID);
		firstRow.add(clientTxt);
		
		//contents for second row
		serverURL = new JLabel(labels[i]);
		serverTxt = new JTextField("localhost");
		textFields[i] = serverTxt;
		i++;
		serverTxt.setPreferredSize(new Dimension(100, 20));
		
		//secondRow
		JPanel secondRow = new JPanel(new FlowLayout());
		secondRow.add(serverURL);
		secondRow.add(serverTxt);
		
		//contents for third row 
		serverPort = new JLabel(labels[i]);
		portTxt = new JTextField("8300");
		textFields[i] = portTxt;
		portTxt.setPreferredSize(new Dimension(80, 20));
		
		//third row
		JPanel thirdRow = new JPanel(new FlowLayout());
		thirdRow.add(serverPort);
		thirdRow.add(portTxt);
		
		//second Third
		JPanel p1 = new JPanel(new FlowLayout());
		clientData = new JLabel("Enter Client Data Below");
		p1.add(clientData);
		JPanel p2 = new JPanel(new FlowLayout());
		JScrollPane txt1 = new JScrollPane(clientArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txt1.setPreferredSize(new Dimension(250, 100));
		p2.add(txt1);
		
		//thirdThird
		JPanel p3 = new JPanel(new FlowLayout());
		JLabel serverData = new JLabel("Received Server Data");
		p3.add(serverData);
		JPanel p4 = new JPanel(new FlowLayout());
		serverArea.setEditable(false);
		JScrollPane txt2 = new JScrollPane(serverArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txt2.setPreferredSize(new Dimension(250, 100));
		p4.add(txt2);
		
		//center code
		GridLayout grid1 = new GridLayout(3, 1);
		grid1.setVgap(5);
		JPanel firstThird = new JPanel(grid1);
		firstThird.add(firstRow);
		firstThird.add(secondRow);
		firstThird.add(thirdRow);
		
		//secondThird
		JPanel secondThird = new JPanel();
		secondThird.setLayout(new BoxLayout(secondThird, BoxLayout.PAGE_AXIS));
		secondThird.add(p1);
		secondThird.add(p2);
		
		//thirdThird
		JPanel thirdThird = new JPanel();
		thirdThird.setLayout(new BoxLayout(thirdThird, BoxLayout.PAGE_AXIS));
		thirdThird.add(p3);
		thirdThird.add(p4);
		
		
		JPanel center = new JPanel(new GridLayout(3, 1));
		center.add(firstThird);
		center.add(secondThird);
		center.add(thirdThird);
		
		//South code
		connect = new JButton("Connect");
		connect.addActionListener(new EventHandler());
		submit = new JButton("Submit");
		submit.addActionListener(new EventHandler());
		stop = new JButton("Stop");
		stop.addActionListener(new EventHandler());
		JPanel south = new JPanel(new FlowLayout());
		south.add(connect);
		south.add(submit);
		south.add(stop);
		
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(south, BorderLayout.SOUTH);
		
		//setting the client
		/*
		client.setServerMsg(serverArea);
		client.setStatus(statusConnection);
		client.setClientID(clientTxt);*/
		
		
		setSize(600,700);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new GameClientGUI2(); //args[0] represents the title of the GUI
	}
	
	private class EventHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj == connect) {
				try {
					client.setHost(serverTxt.getText());
					client.setPort(Integer.parseInt(portTxt.getText()));
					client.openConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(obj == submit) {
				try {
					//LoginData data = new LoginData("jjones@yahoo.com", "hello1234");
					CreateAccountData data = new CreateAccountData("random5@yahoo.com", "hello", "hello");
					client.sendToServer(data);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(obj == stop) {
				try {
					client.closeConnection();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}