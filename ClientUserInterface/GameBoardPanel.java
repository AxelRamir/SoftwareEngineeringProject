package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;

import ClientCommunications.GameBoardControl;
import ClientCommunications.GameBoard;
import ClientCommunications.BoardSquare;

public class GameBoardPanel extends JPanel{
	
	private GameBoard gameBoard;
	private GameBoardControl gameBoardControl;
	
	private JPanel boardPanel;
	private JPanel north;
	private JPanel south;
	
	private JLabel teamLabel;
	private JButton quitButton;
	

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
				gameBoard.getSquare(r, c).addActionListener(gameBoardControl);
				
				gameGrid.add(gameBoard.getSquare(r, c));
			
				
				
			}
		}
			
		
		north.add(gameGrid);
		
		quitButton = new JButton("Leave Game");
		teamLabel = new JLabel("");
		
		
		south.add(quitButton);
		south.add(Box.createHorizontalStrut(30));
		south.add(new JLabel("You are: "));
		south.add(teamLabel);
		
		
		boardPanel.add(north, BorderLayout.NORTH);
		boardPanel.add(south, BorderLayout.SOUTH);
		
		
		
		return boardPanel;
	}
	
	
	public JLabel getTeamLabel() {
		return teamLabel;
	}
	
	public GameBoard getBoard() {
		return gameBoard;
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
	
	
