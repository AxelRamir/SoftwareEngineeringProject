package ServerCommunication;

import java.io.Serializable;
import java.util.ArrayList;

import ClientCommunications.BoardSquare;
import ClientCommunications.GameBoard;
import ocsf.server.ConnectionToClient;

public class GameInstance implements Serializable{
	private ConnectionToClient player1; //red
	private ConnectionToClient player2; //black
	
	//maybe board class here
	private GameBoard board;
	private InvalidSelection p1LastError, p2LastError;
	private String turn = "red";
	
	public GameInstance() {
		board = new GameBoard();
		p1LastError = new InvalidSelection("Red - No error");
		p2LastError = new InvalidSelection("Black - No error");
	}
	
	public boolean tryMovePiece(ConnectionToClient cl, PieceSelection sel) {
		// TODO tryMovePiece
		String team = getTeamOf(cl);
		if (!turn.equals(team)) {
			if (cl == player1)
				p1LastError = new InvalidSelection("It's not your turn.");
			if (cl == player2)
				p2LastError = new InvalidSelection("It's not your turn.");
		}
		else if (validateMove(team, sel)) {
			BoardSquare piece = board.getSquare(sel.fromX, sel.fromY);
			piece.copyTo(board.getSquare(sel.toX, sel.toY));
			piece.setHasPiece(false);
			if (Math.abs(sel.fromX - sel.toX) == 2) { // diagonal guaranteed
				BoardSquare jumped = board.getSquare(
						sel.fromX + (sel.toX - sel.fromX) / 2,
						sel.fromY + (sel.toY - sel.fromY) / 2
						);
				jumped.setHasPiece(false);
			}
			if (turn.equals("red")) turn = "black";
			else if (turn.equals("black")) turn = "red";
		}
		else {
			if (cl == player1)
				p1LastError = new InvalidSelection("You can't move there.");
			if (cl == player2)
				p2LastError = new InvalidSelection("You can't move there.");
		}
		return false;
	}
	
	public InvalidSelection getSelectionError(ConnectionToClient cl) {
		if (cl==player1) return p1LastError;
		if (cl==player2) return p2LastError;
		return new InvalidSelection("Client not in game");
	}
	
	public boolean validateMove(String team, PieceSelection sel) {
		// invalidate black spaces
		// white spaces are on even columns on even rows
		// and on odd columns on odd rows
		// black spaces are only on even/odd mismatch
		// team 1 red, team 2 black
		if ((sel.toX % 2 == 0) != (sel.toY % 2 == 0))
			return false;

		BoardSquare source = board.getSquare(sel.fromX, sel.fromY);
		BoardSquare dest = board.getSquare(sel.toX, sel.toY);
		
		if (dest.hasPiece()) return false;
		
		if (!(source.hasPiece() && source.getTeam().equals(team)))
			return false;
		
		ArrayList<BoardSquare> options = checkOptions(source);
		
		for(BoardSquare i : options) {
			//FIXME: X AND Y ARE NOT ROW AND COLUMN AND I AM AN IDIOT
			if (i.getRow() == sel.toX && i.getColumn() == sel.toY) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<BoardSquare> checkOptions(BoardSquare square) {
		ArrayList<BoardSquare> result = new ArrayList<BoardSquare>();
		
		// trust me i write very clean code
		// nothing to see here
		int row = square.getRow();
		int col = square.getColumn();
		BoardSquare temp=null;
		if ((square.getTeam().equals("red") || square.getIsKing()) && (row+2 <= 7)) {
			if (col-2 >= 0) {
				temp = board.getSquare(row+1, col-1);
				if (temp.hasPiece() && !temp.getTeam().equals(square.getTeam()))
					result.add(board.getSquare(row+2, col-2));
			}
			if (col+2 <= 7) {
				temp = board.getSquare(row+1, col+1);
				if (temp.hasPiece() && !temp.getTeam().equals(square.getTeam()))
					result.add(board.getSquare(row+2, col+2));
			}
		}
		if ((square.getTeam().equals("black") || square.getIsKing()) && (row-2 >= 0)) {
			if (col-2 >= 0) {
				temp = board.getSquare(row-1, col-1);
				if (temp.hasPiece() && !temp.getTeam().equals(square.getTeam()))
					result.add(board.getSquare(row-2, col-2));
			}
			if (col+2 <= 7) {
				temp = board.getSquare(row-1, col+1);
				if (temp.hasPiece() && !temp.getTeam().equals(square.getTeam()))
					result.add(board.getSquare(row-2, col+2));
			}
		}
		
		if (result.isEmpty()) {
			if (row-1 >= 0 && col-1 >= 0)
				temp = board.getSquare(row-1, col-1);
				if (!temp.hasPiece()) result.add(temp);
			if (row+1 <= 7 && col-1 >= 0)
				temp = board.getSquare(row+1, col-1);
				if (!temp.hasPiece()) result.add(temp);
			if (row-1 >= 0 && col+1 <= 7)
				temp = board.getSquare(row-1, col+1);
				if (!temp.hasPiece()) result.add(temp);
			if (row+1 <= 7 && col+1 <= 7)
				temp = board.getSquare(row+1, col+1);
				if (!temp.hasPiece()) result.add(temp);
		}
		
		return result;
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
		if (client == this.player1) return "red";
		if (client == this.player2) return "black";
		return null;
	}
	
}	
