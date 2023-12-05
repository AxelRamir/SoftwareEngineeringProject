package Testing;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.io.IOException;
import java.net.SocketException;

import javax.swing.*;

import org.junit.*;

import ClientCommunications.HomeControl;
import ClientCommunications.LoginControl;
import ClientUserInterface.HomePanel;
import ClientUserInterface.LoginPanel;
import OCSF.GameClient;
import OCSF.GameServer;
import ServerCommunication.GameServerGUI;
import ServerCommunication.LoginData;

public class LoginPanelTest {

	//testing the panel means we imitate the controller
	private JFrame gui;
	private JButton submit;
	private JButton back;
	private JTextField usernameField;
	private JTextField passwordField;
	private JLabel serverResponse;
	private JPanel container;
	private CardLayout cards;
	private GameClient client;
	private GameServer server;
	
	private String[] usernames = {"jjones@yahoo.com", "tjones@yahoo.com", "msmith@uca.edu"};
	private String[] passwords = {"hello1234", "123456", "pass123"};
	
	//server has to be ran before tests
	//pointer exception ignores an error when it tries to update a status label
	@Before
	public void setUp() {
		gui = new JFrame();
		gui.setDefaultCloseOperation(0);
		
		client = new GameClient("localhost", 8300);
		
		container = new JPanel(new CardLayout());
		cards = (CardLayout)container.getLayout();
		
		HomeControl hc = new HomeControl(container, client);
		HomePanel hp= new HomePanel(hc);
		
		LoginControl lc = new LoginControl(container, client);
		LoginPanel lp = new LoginPanel(lc);
		JPanel p2 = new JPanel();
		
		container.add(hp, "1");
		container.add(lp, "2");
		
		cards.show(container, "2");
		gui.add(container);
		
		gui.setSize(600, 600);
		gui.setVisible(true);
	}
	
	//testing to see if we can press the submit button
	@Test
	public void testSubmitButton() throws SocketException{
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		submit = panel.getSubmitButton();
		submit.doClick(1000);
		String text = panel.getSubmitButton().getText();
		
		assertEquals("Submit button works", text, (String)submit.getActionCommand());
	}
	
	//testing to see if we can press the cancel button
	@Test
	public void testCancelButton() throws SocketException{
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		back = panel.getBackButton();
		back.doClick(1000);
		String text = panel.getBackButton().getText();
		
		assertEquals("Submit button works", text, (String)back.getActionCommand());
	}
	
	@Test
	//test if we can change the info in the usernameField
	public void testSetUsername() {
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		usernameField = panel.getUsernameField();
		
		int random = (int) (Math.random() * 3);
		String username = usernames[random];
		
		usernameField.setText(username);	
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			
		}
		
		//also tests the gettext at the same time
		assertEquals("Usernames match", usernames[random], usernameField.getText());
	}
	
	@Test
	//test if we can change the text info from the passwordField
	public void testSetPassword() {
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		passwordField = panel.getPasswordField();
		
		int random = (int) (Math.random() * 3);
		String password = passwords[random];
		
		//changes text
		passwordField.setText(password);	
		try {
			Thread.sleep(1000);
		}catch (Exception e){
			
		}
	
		//makes sure the text was changed
		assertEquals("Usernames match", passwords[random], passwordField.getText());
	}
	
	//test to see if we can change the server response
	@Test
	public void testSetServerResponse() {
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		serverResponse = panel.getServerResponse();
		
		String response = "Server responded";
		
		//change server response
		serverResponse.setText(response);
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			
		}
		assertEquals("Server responses are equal", response, serverResponse.getText());
	}
	
}
