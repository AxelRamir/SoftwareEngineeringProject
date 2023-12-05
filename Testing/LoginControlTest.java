package Testing;

import static org.junit.Assert.*;

import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.*;

import ClientCommunications.HomeControl;
import ClientCommunications.LoginControl;
import ClientUserInterface.HomePanel;
import ClientUserInterface.LoginPanel;
import OCSF.GameClient;
import OCSF.GameServer;
import ServerCommunication.LoginData;

public class LoginControlTest {
	private String[] usernames = {"jjones@yahoo.com", "tjones@yahoo.com", "msmith@uca.edu"};
	private String[] passwords = {"hello1234", "123456", "pass123"};
	
	//components for a new gui
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
	private LoginControl lc;
	
	private LoginData data;
	
	@Before
	public void setUp() {
		gui = new JFrame();
		gui.setDefaultCloseOperation(0);
		
		client = new GameClient("localhost", 8300);
		
		container = new JPanel(new CardLayout());
		cards = (CardLayout)container.getLayout();
		
		HomeControl hc = new HomeControl(container, client);
		HomePanel hp= new HomePanel(hc);
		
		lc = new LoginControl(container, client);
		LoginPanel lp = new LoginPanel(lc);
		
		
		JPanel p2 = new JPanel();
		
		container.add(hp, "1");
		container.add(lp, "2");
		
		cards.show(container, "2");
		gui.add(container);
		
		gui.setSize(600, 600);
		gui.setVisible(true);
	}
	
	//testing to make sure the info inputed into gui is the same as the login data populated by the login control
	@Test
	public void testActionPerformed() {
		int random = (int) (Math.random() * 3);
		String username = usernames[random];
		String password = passwords[random];
		
		LoginData expected = new LoginData(username, password);
		
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		panel.getUsernameField().setText(username);
		panel.getPasswordField().setText(password);
		try {
			Thread.sleep(1000);
		}catch (Exception e) {
			
		}
		panel.getSubmitButton().doClick();
		LoginData actual = lc.getLoginData();
		
		String expectedString = expected.getUsername() + ", " + expected.getPassword();
		String actualString = actual.getUsername() + ", " + actual.getPassword();
		assertEquals("Expected login data is the same as actual", expectedString, actualString);
	}
	
	//making sure we can display login failed messages to the gui
	@Test 
	public void testLoginFailed() {
		//make sure that we can successfully update the gui with a login failed message
		String serverString = "Login failed";
		
		LoginPanel panel = (LoginPanel) container.getComponent(1);
		
		//update gui with failed message
		lc.loginFailed(serverString);
		
		//make sure the first failed message was correctly updated into the gui and is the same string
		assertEquals("Expected and actual data are equal", serverString, panel.getServerResponse().getText());
	}
}
