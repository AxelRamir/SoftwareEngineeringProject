package ClientUserInterface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.*;

import ClientCommunications.GameBoardControl;

public class GameBoardPanel extends JPanel{
	private JPanel boardPanel;
	private BoardSquare[][] gridSquares;
	private JPanel leavePanel;
	private GameBoardControl gameBoardControl;
	private String[][] board;

	public GameBoardPanel(GameBoardControl gameBoardControl) {
		super();
		this.gameBoardControl = gameBoardControl; 
		JPanel main = new JPanel(new BorderLayout());
		boardPanel = new JPanel(new GridLayout(8, 8, 1, 1));
		boardPanel.setPreferredSize(new Dimension(500, 500));

		gridSquares = new BoardSquare[8][8];

		ImageIcon blackPiece = new ImageIcon("./images/blackpiece.png");
		ImageIcon redPiece = new ImageIcon("./images/redpiece.png");

		Image image = redPiece.getImage();
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		redPiece = new ImageIcon(newimg);

		image = blackPiece.getImage();
		newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		blackPiece = new ImageIcon(newimg);

		// 1. initializes the 2d array of buttons
		// 2. makes each button either black or white
		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				gridSquares[r][c] = new BoardSquare(r, c); // initialize

				gridSquares[r][c].setOpaque(true);
				gridSquares[r][c].setBorderPainted(false);
				gridSquares[r][c].setEnabled(false);

				// ----makes each square black or white------------
				if ((r + 1) % 2 == 0) {

					if ((c + 1) % 2 == 1) {
						gridSquares[r][c].setBackground(Color.black);
					} else {
						gridSquares[r][c].setBackground(Color.white);

						if (r < 3) {
							gridSquares[r][c].setRed();
						} else if (r > 4) {
							gridSquares[r][c].setBlack();
						}

					}

				} else {
					if ((c + 1) % 2 == 1) {
						gridSquares[r][c].setBackground(Color.white);

						if (r < 3) {
							gridSquares[r][c].setRed();
						} else if (r > 4) {
							gridSquares[r][c].setBlack();
						}

					} else {
						gridSquares[r][c].setBackground(Color.black);
					}
				}
				boardPanel.add(gridSquares[r][c]);
				// --------------------------------------------

			}

		}
		JPanel leavePanel = new JPanel();
		JButton leaveButton = new JButton("Leave Game");
		leavePanel.add(leaveButton);
		
		
		main.add(boardPanel, BorderLayout.CENTER);
		main.add(leavePanel, BorderLayout.SOUTH);
		
		this.add(main);
	}
	
	public void enableBoard() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				gridSquares[i][j].setEnabled(true);
			}
		}
	}
	public BoardSquare[][] getBoardSquares(){
		return gridSquares;
	}
	
}