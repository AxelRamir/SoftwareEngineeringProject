package OCSF;

import java.awt.*;
import javax.swing.*;

import Database.Database;
import ServerCommunication.CreateAccountData;
import ServerCommunication.GameInstance;
import ServerCommunication.InvalidLogin;
import ServerCommunication.InvalidSelection;
import ServerCommunication.LoginData;
import ServerCommunication.PieceSelection;
import ServerCommunication.PlayerQueue;
import ServerCommunication.UnsuccessfulCreateAccount;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class GameServer extends AbstractServer {
	// Data fields for this chat server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database database;
	
	//maybe change
	private PlayerQueue playerQueue = new PlayerQueue();
	
	//not sure about this one but its an idea
	private ArrayList<GameInstance> games = new ArrayList<GameInstance>();
	//not sure about this one
	private String message;

	// Constructor for initializing the server with default settings.
	public GameServer() {
		super(8300);
		this.setTimeout(500);
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning() {
		return running;
	}

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log) {
		this.log = log;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	// When the server starts, update the GUI.
	public void serverStarted() {
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped() {
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed() {
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client) {
		log.append("Client " + client.getId() + " connected\n");
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		log.append("Message from Client" + arg1.getId() + ":" + arg0 + "\n");
		
		//if the server gets a loginData class
		if(arg0 instanceof LoginData) {
			LoginData data = (LoginData) arg0;
			if(validLogin(data)) {
				//if the login data is correct, we go to the managePlayer function
				try {
					arg1.sendToClient(data);
					managePlayer(arg1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					arg1.sendToClient(new InvalidLogin(message));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(arg0 instanceof CreateAccountData) {
			CreateAccountData data = (CreateAccountData) arg0;
			if(validCreateAccount(data)) {
				try {
					arg1.sendToClient(data);
					managePlayer(arg1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				try {
					arg1.sendToClient(new UnsuccessfulCreateAccount(message));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(arg0 instanceof PieceSelection) {
			PieceSelection selection = (PieceSelection) arg0;
			if(validSelection(selection, arg1)) {
				//if the selection is valid, we send this back to the client, causing the client to select the move location
				try {
					arg1.sendToClient(selection);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				//if the selection is invalid, we have to show the message
				try {
					arg1.sendToClient(new InvalidSelection(message));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) {
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}

	public void setDatabase(Database database) {
		this.database = database;
	}
	
	public void setQueue(PlayerQueue playerQueue) {
		this.playerQueue = playerQueue;
	}
	
	//methods for the server
	
	//methods for LoginData
	private boolean validLogin(LoginData loginData) {
		String query = "Select * from users where username = \'" + loginData.getUsername() + "\' and password = aes_encrypt(\'" + loginData.getPassword() + "\', \'hello\')";
		ArrayList<String> results = null;
		results = database.query(query);
		
		//if no user matches the login data return true for loginData
		if(results != null) {
			return true;
		}
		else {
			message = "Username or Password are incorrect";
			return false;
		}
	}
	private void managePlayer(ConnectionToClient player) {
		//adds player to a queue
		playerQueue.addPlayer(player);
		System.out.println("Player added to the Queue.");
		System.out.println(playerQueue.size());
		
		//if there are more than 2 players in the queue, the players are removed and their connection is saved
		if(playerQueue.size() > 1) {
			ConnectionToClient player1 = playerQueue.removePlayer();
			System.out.println("Player removed from queue");
			ConnectionToClient player2 = playerQueue.removePlayer();
			System.out.println("Player removed from queue");
			//start a new Game instance between the 2 players
			GameInstance game = new GameInstance();
			//add this game instance to collection of games
			game.setPlayer1(player1);
			game.setPlayer2(player2);
			games.add(game);
			System.out.println("Game started between 2 players");
			
			//send both players to the board panel
			try {
				player1.sendToClient(new String("Ready"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				player2.sendToClient(new String("Ready"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//functions for CreateAccountData
	private boolean validCreateAccount(CreateAccountData data) {
		//first we have to validate the password and username
		boolean inserted = false;
		if(!data.getUsername().contains("@")) {
			//doesnt contain an @
			message = "Username requires an @ symbol";
			return false;
		}
		if(data.getPassword().length() < 8) {
			message = "Password needs at least 8 characters";
			return false;
		}
		
		String query = "Select * from users where username = \'" + data.getUsername() + "\'";
		ArrayList<String> results = null;
		results = database.query(query);
		
		//if the username is taken, create account data is invalid
		if(results != null) {
			message = "Username already taken.";
			return false;
		}
		
		//if the two passwords are not equal inform user of it
		if(!data.getPassword().equals(data.getPassword2())) {
			message = "Passwords entered do not match.";
			return false;
		}
		else {
			//if the passwords match and the username is not taken, add the user to the database
			String DML = "insert into users values(\'" + data.getUsername() + "\' , aes_encrypt(\'" + data.getPassword() + "\', \'hello\'))";
			try {
				inserted = database.executeDML(DML);
				System.out.println("User added to database");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(!inserted) {
				message = "There was an issue creating the account";
			}
			return inserted;
		}
	}
	
	private GameInstance findClientGameInstance(ConnectionToClient client) {
		for(GameInstance game : games) {
			if(game.getPlayer1().getId() == client.getId() || game.getPlayer2().getId() == client.getId()) {
				return game;
			}
		}
		//no game instance was found
		return null;
	}
	public boolean validSelection(PieceSelection selection, ConnectionToClient client) {
		//this is going to be used to validate the selection for the player
		
		//first thought is to see whether this client is player 1 or player 2 for the game
		GameInstance game = findClientGameInstance(client);
		
		//checks to see if this client is player 1
		if(client.getId() == game.getPlayer1().getId()) {
			//if the client is player1, they can only select P1 or K1 pieces
			if(selection.getPieceName().equals("P1") || selection.getPieceName().equals("K1")) {
				return true;
			}
			//if the player selects another players piece or click space without a piece
			else {
				message = "Select your piece to move";
			}
		}
		
		//check to see if the client is player 2
		else if(client.getId() == game.getPlayer2().getId()) {
			//if the client is player2, they can only select P2 or K2 pieces
			if(selection.getPieceName().equals("P2") || selection.getPieceName().equals("K2")) {
				return true;
			}
			else {
				message = "Select your piece to move";
			}
		}
		return false;
	}
	private void startGame() {
		
	}
}
