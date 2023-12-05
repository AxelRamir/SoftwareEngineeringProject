/*
	This essentially represents the game state
	- elijah
*/

package ClientCommunications;

import java.awt.Color;

import ClientCommunications.BoardSquare;
import ClientUserInterface.GameBoardPanel;

public class GameBoard implements java.io.Serializable {
	
	
	BoardSquare[][] boardSquares = new BoardSquare[8][8];

	
	public GameBoard() {
		
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				boardSquares[r][c] = new BoardSquare(r, c);	// initialize
				boardSquares[r][c].setEnabled(true); // enable
				
if ((r + 1) % 2 == 0) {
					
					
					if ((c + 1) % 2 == 1) {
						getSquare(r, c).setBackground(Color.black);
						getSquare(r, c).setClickable(false);
					}
					else {
						getSquare(r, c).setBackground(Color.white);
						getSquare(r, c).setClickable(true);
						
						if (r < 3) {
							getSquare(r, c).setHasPiece(true);
							getSquare(r, c).setTeam("red");
						}
						else if (r > 4 ){
							getSquare(r, c).setHasPiece(true);
							getSquare(r, c).setTeam("black");
						}
						
					}
				
				}
				else {
					if ((c + 1) % 2 == 1) {
						getSquare(r, c).setBackground(Color.white);
						getSquare(r, c).setClickable(true);
						
						if (r < 3) {
							getSquare(r, c).setHasPiece(true);
							getSquare(r, c).setTeam("red");
						}
						else if (r > 4){
							getSquare(r, c).setHasPiece(true);
							getSquare(r, c).setTeam("black");
						}
						

					}
					else {
						getSquare(r, c).setBackground(Color.black);
						getSquare(r, c).setClickable(false);
					}
				}
				
				
			}
		}
		
		
		/*
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				
			//----sets pieces on white squares------------
				if ((r + 1) % 2 == 0) {
					
					if ((c + 1) % 2 == 0) {
						if (r < 3) {
							getSquare(r, c).setTeam("Red");
						}
						else if (r > 4 ){
							getSquare(r, c).setTeam("Black");
						}

					}
				
				}
				else {
					if ((c + 1) % 2 == 1) {
						if (r < 3) {
							getSquare(r, c).setTeam("Red");
						}
						else if (r > 4){
							getSquare(r, c).setTeam("Black");
						}
						
					}
				}
			}
		}
		*/
		
	}
	
	
	
	public BoardSquare getSquare(int row, int column) {
		return boardSquares[row][column];
	}
	
	public void copyTo(GameBoard other) {
		System.out.println("copying game board");
		for (int row=0; row<8; row++) {
			for (int col=0; col<8; col++) {
				getSquare(row, col).copyTo(other.getSquare(row, col));
			}
		}
	}
	

	
	// @Axel, The pieces already get set to red or black in GameBoardPanel
	// Wasn't sure if you had something specific in mind for this so I left it here
	
	// we might need this serverside actually -AL
	
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
