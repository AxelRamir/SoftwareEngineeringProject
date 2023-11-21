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
	
	private GameBoard gameBoard; // This is the game state	-elijah
	
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
	
	
	/*
	 	defineBoardSquares() moved to the GameBoard class
	*/
	
}
