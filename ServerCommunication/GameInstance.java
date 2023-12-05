package ServerCommunication;

import java.io.IOException;
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
	private String turn = "black";
	private boolean done = false;
	
	public GameInstance() {
		board = new GameBoard();
		p1LastError = new InvalidSelection("Red - No error");
		p2LastError = new InvalidSelection("Black - No error");
	}
	
	public boolean tryMovePiece(ConnectionToClient cl, PieceSelection sel) {
		// TODO tryMovePiece
		if (done) return false;
		String team = getTeamOf(cl);
		if (!turn.equals(team)) {
			if (cl == player1)
				p1LastError = new InvalidSelection("It's not your turn.");
			if (cl == player2)
				p2LastError = new InvalidSelection("It's not your turn.");
		}
		else if (validateMove(team, sel)) {
			try {
				cl.sendToClient(new InvalidSelection("Turn taken"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BoardSquare piece = board.getSquare(sel.fromX, sel.fromY);
			BoardSquare dest = board.getSquare(sel.toX, sel.toY);
			piece.copyTo(dest);
			piece.setHasPiece(false);
			if (Math.abs(sel.fromX - sel.toX) == 2) { // diagonal guaranteed
				BoardSquare jumped = board.getSquare(
						sel.fromX + (sel.toX - sel.fromX) / 2,
						sel.fromY + (sel.toY - sel.fromY) / 2
						);
				jumped.setHasPiece(false);
			}
			// king if applicable
			if ((team.equals("red") && dest.getRow() == 7) ||
					(team.equals("black") && dest.getRow() == 0))
				dest.setKing(true);
				
			// check if either player has won
			
			// switch turns if player can't chain capture
			if (true) {// !checkCanCapture(dest)) {  // hard code this logic for now, too many problems with checkCanCapture
				if (turn.equals("red")) {
					turn = "black";
					try {
						player1.sendToClient("blackTurn");
						player2.sendToClient("blackTurn");
						player2.sendToClient(new InvalidSelection("Your turn!"));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				else if (turn.equals("black")) {
					turn = "red";
					try {
						player1.sendToClient("redTurn");
						player2.sendToClient("redTurn");
						player1.sendToClient(new InvalidSelection("Your turn!"));
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
			return true;
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
		boolean redMoves = square.getTeam().equals("red") || square.getIsKing();
		boolean blackMoves = square.getTeam().equals("black") || square.getIsKing();
		
		BoardSquare temp=null;
		if (redMoves && (row+2 <= 7)) {
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
		if (blackMoves && (row-2 >= 0)) {
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
			if (blackMoves) {
				if (row-1 >= 0 && col-1 >= 0)
					temp = board.getSquare(row-1, col-1);
					if (!temp.hasPiece()) result.add(temp);
				if (row-1 >= 0 && col+1 <= 7)
					temp = board.getSquare(row-1, col+1);
					if (!temp.hasPiece()) result.add(temp);
			}
			if (redMoves) {
				if (row+1 <= 7 && col-1 >= 0) 
					temp = board.getSquare(row+1, col-1);
					if (!temp.hasPiece()) result.add(temp);
				if (row+1 <= 7 && col+1 <= 7)
					temp = board.getSquare(row+1, col+1);
					if (!temp.hasPiece()) result.add(temp);
			}
		}
		
		return result;
	}
	
	public boolean checkCanCapture(BoardSquare square) {
		// this is a cardinal sin. please don't do what i did
		ArrayList<BoardSquare> opts = checkOptions(square);
		for (BoardSquare i : opts) {
			if (Math.abs(i.getRow() - square.getRow()) == 2) {
				return true;
			}
		}
		return false;
	}
	
	public String checkVictory() {
		boolean redHasPieces = false;
		boolean blackHasPieces = false;
		for (int row=0; row<7; row++) {
			for (int col=0; col<7; col++) {
				BoardSquare s = board.getSquare(row, col);
				if(s.hasPiece() && s.getTeam().equals("red"))
					redHasPieces = true;
				if(s.hasPiece() && s.getTeam().equals("black"))
					blackHasPieces = true;
			}
		}
		if (redHasPieces && blackHasPieces) {
			return "continue";
		}
		else if (redHasPieces) {
			return "red";
		}
		else if (blackHasPieces) {
			return "black";
		}
		return "what the fuck?";
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
