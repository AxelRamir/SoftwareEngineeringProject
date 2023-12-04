package ClientCommunications;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EventListener;
import java.awt.event.AWTEventListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.font.*;

import ClientCommunications.BoardSquare;
import ClientUserInterface.GameBoardPanel;
import OCSF.GameClient;

public class GameBoardControl implements ActionListener, AWTEventListener{
	
	private JPanel container;
	private GameClient client;
	private JLabel teamLabel;
	
	private GameBoard gameBoard; // This is the game state	-elijah
	
	private boolean selected;
	private String team;
	
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
	
	
	public void setTeam(String team) {
		this.team = team;
		teamLabel.setText(team);
		if (team.equals("Red")) {
			teamLabel.setForeground(Color.red);
		}
		else {
			teamLabel.setForeground(Color.black);
		}
	}
	
	
	public void setTeamLabel(JLabel teamLabel) {
		this.teamLabel = teamLabel;	
	}
	

	@Override
	public void eventDispatched(AWTEvent event) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	/*
	 	defineBoardSquares() moved to the GameBoard class
	*/
	
}
