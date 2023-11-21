/*
	This essentially represents the game state
	- elijah
*/

package ClientCommunications;

import ClientUserInterface.BoardSquare;
import ClientUserInterface.GameBoardPanel;

public class GameBoard {
	
	
	BoardSquare[][] boardSquares = new BoardSquare[8][8];

	
	public GameBoard() {
		
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				boardSquares[r][c] = new BoardSquare(r, c);	// initialize
				boardSquares[r][c].setEnabled(true); // enable
			}
		}
		
		
		
	}
	
	
	
	public BoardSquare getSquare(int row, int column) {
		return boardSquares[row][column];
	}
	

	
	// @Axel, The pieces already get set to red or black in GameBoardPanel
	// Wasn't sure if you had something specific in mind for this so I left it here
	
	/*
	public void defineBoardSquares(String[][] board) {
		
		//sets the buttons to show the image
		for(int r = 0; r < 8; r++) {
			for(int c = 0; c < 8; c++) {
				//sets the black checkers piece for player 1
				if(boardSquares[r][c].getPieceName().equals("P1")) {
					boardSquares[r][c].setBlack();
				}
				
				//sets the red checkers piece for player 2
				else if(boardSquares[r][c].getPieceName().equals("P2")) {
					boardSquares[r][c].setRed();
				}
			}
		}
	}
	*/
	
}
