package ServerCommunication;

import java.io.Serializable;

import ocsf.server.ConnectionToClient;

public class GameInstance implements Serializable{
	private ConnectionToClient player1;
	private ConnectionToClient player2;
	
	//maybe board class here
	private String[][] board;
	
	public GameInstance() {
	}
	
	public void setPlayer1(ConnectionToClient player1) {
		this.player1 = player1;
	}
	
	public void setPlayer2(ConnectionToClient player2) {
		this.player2 = player2;
	}
	
	public ConnectionToClient getPlayer1() {
		return player1;
	}
	public ConnectionToClient getPlayer2() {
		return player2;
	}
	
	
	//function used to check if the response from the client exists in this game
	public boolean containsClient(ConnectionToClient client) {
		if(player1 == client || player2 == client) {
			return true;
		}	
		else {
			return false;
		}
	}
	
}	
