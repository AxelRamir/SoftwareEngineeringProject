package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;

import ClientCommunications.GameBoardControl;
import ClientCommunications.GameBoard;

public class GameBoardPanel extends JPanel{
	
	private GameBoard gameBoard;
	private GameBoardControl gameBoardControl;
	
	private JPanel boardPanel;
	private JPanel north;
	private JPanel south;
	

	public GameBoardPanel(GameBoardControl gameBoardControl) {
		
		
		this.gameBoardControl = gameBoardControl; 
		
		this.add(getGameBoardPanel());
		
		
		

	}
	
	
	
	public JPanel getGameBoardPanel() {
			
		int rows = 8;
		int columns = 8;
		
		gameBoard = new GameBoard();
		
		boardPanel = new JPanel(new BorderLayout());
		
		north = new JPanel();
		south = new JPanel();
		
		JPanel gameGrid = new JPanel(new GridLayout(8, 8, 1, 1));
		gameGrid.setBorder(BorderFactory.createLineBorder(Color.black, 4));
		
		
		// 1. creates the graphical appearance of the board
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				
				gameBoard.getSquare(r, c).setOpaque(true);
				gameBoard.getSquare(r, c).setBorderPainted(false);
				
			//----makes each square black or white and sets pieces------------
				if ((r + 1) % 2 == 0) {
					
					
					if ((c + 1) % 2 == 1) {
						gameBoard.getSquare(r, c).setBackground(Color.black);
					}
					else {
						gameBoard.getSquare(r, c).setBackground(Color.white);
						
						if (r < 3) {
							gameBoard.getSquare(r, c).setRed();
						}
						else if (r > 4 ){
							gameBoard.getSquare(r, c).setBlack();
						}
						
					}
				
				}
				else {
					if ((c + 1) % 2 == 1) {
						gameBoard.getSquare(r, c).setBackground(Color.white);
						
						if (r < 3) {
							gameBoard.getSquare(r, c).setRed();
						}
						else if (r > 4){
							gameBoard.getSquare(r, c).setBlack();
						}
						
					}
					else {
						gameBoard.getSquare(r, c).setBackground(Color.black);
					}
				}
				gameGrid.add(gameBoard.getSquare(r, c));
			//--------------------------------------------
				
				
			}
		}
			
		
		north.add(gameGrid);
		
		JButton quitButton = new JButton("Quit");
		south.add(quitButton);
		
		boardPanel.add(north, BorderLayout.NORTH);
		boardPanel.add(south, BorderLayout.SOUTH);
		
		
		
		return boardPanel;
	}
	
	
	
	/*test
	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setSize(650, 700);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		
		f.add(new GameBoardPanel());
		
		f.setVisible(true);
	}
	*/
	
	
}
	
	
