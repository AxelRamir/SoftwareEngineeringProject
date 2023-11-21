package ClientCommunications;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;

import ClientUserInterface.BoardSquare;
import ClientUserInterface.GameBoardPanel;
import OCSF.GameClient;

public class GameBoardControl implements ActionListener{
	private JPanel container;
	private GameClient client;
	
	private boolean selected;
	
	public GameBoardControl(JPanel container, GameClient client) {
		this.container = container;
		this.client = client;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "Leave Game") {
			//if the player chooses to leave the game, the connection is ended
			try {
				client.closeConnection();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else {
			BoardSquare square = (BoardSquare) e.getSource();
			
		}
	}
	public void enableBoard() {
		GameBoardPanel gbp = (GameBoardPanel) container.getComponent(3);
		gbp.enableBoard();
	}
	public void defineBoardSquares(String[][] board) {
		GameBoardPanel gbp = (GameBoardPanel) container.getComponent(3);
		BoardSquare[][] boardSquares = gbp.getBoardSquares();
		//defines the pieceName according to the board
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				boardSquares[i][j].setPieceName(board[i][j]);
			}
		}
		
		//sets the buttons to show the image
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				//sets the black checkers piece for player 1
				if(boardSquares[i][j].getPieceName().equals("P1")) {
					boardSquares[i][j].setBlack();
				}
				
				//sets the red checkers piece for player 2
				else if(boardSquares[i][j].getPieceName().equals("P2")) {
					boardSquares[i][j].setRed();
				}
			}
		}
	}
}
