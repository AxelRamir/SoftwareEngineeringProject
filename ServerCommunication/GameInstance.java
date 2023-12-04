package ServerCommunication;

import java.io.Serializable;

import ClientCommunications.GameBoard;
import ocsf.server.ConnectionToClient;

public class GameInstance implements Serializable{
	private ConnectionToClient player1; //red
	private ConnectionToClient player2; //black
	
	//maybe board class here
	private GameBoard board;
	private InvalidSelection p1LastError, p2LastError;
	
	public GameInstance() {
		board = new GameBoard();
		p1LastError = new InvalidSelection("Red - No error");
		p2LastError = new InvalidSelection("Black - No error");
	}
	
	public boolean tryMovePiece(ConnectionToClient cl, PieceSelection sel) {
		return false; // TODO tryMovePiece
	}
	
	public InvalidSelection getSelectionError(ConnectionToClient cl) {
		if (cl==player1) return p1LastError;
		if (cl==player2) return p2LastError;
		return new InvalidSelection("Client not in game");
	}
	
	public GameBoard getGameBoard() { return board; }
	
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
	
	public String getTeamOf(ConnectionToClient client) {
		if (client == this.player1) return "Red";
		if (client == this.player2) return "Black";
		return null;
	}
	
}	
