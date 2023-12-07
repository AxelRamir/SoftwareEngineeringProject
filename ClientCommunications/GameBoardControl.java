package ClientCommunications;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.EventListener;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import java.awt.font.*;

import ServerCommunication.PieceSelection;
import ClientCommunications.BoardSquare;
import ClientUserInterface.GameBoardPanel;
import OCSF.GameClient;


public class GameBoardControl implements ActionListener{
	
	private JPanel container;
	private GameClient client;
	private JLabel teamLabel;
	private JLabel turnLabel;
	private JLabel statusLabel;
	
	private GameBoard gameBoard;
	
	private boolean selected = false;
	private BoardSquare selectedPiece; //used in piece selection logic
	
	
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
		else if(e.getSource() instanceof BoardSquare) {
			
			BoardSquare clickedSquare = (BoardSquare) e.getSource();
			int fromX, fromY, toX, toY;
			
			// if a piece is not already selected and it's a click-able square and it's the right color..
			if (!selected && clickedSquare.hasPiece() && clickedSquare.getTeam().equals(client.team.toLowerCase())) {
				
				System.out.println("selected " + clickedSquare.getRow() + "," + clickedSquare.getColumn()); //testing
				
				selected = true;
				selectedPiece = clickedSquare;
				
				clickedSquare.setBackground(Color.lightGray); //highlight
				}
			
			// if the clicked piece is same as previously selected piece, deselect.
			else if (selected && clickedSquare == selectedPiece) {
				System.out.println("deselect");
				
				selected = false;
				clickedSquare.setBackground(Color.white);
				
			}
			
			// if the clicked piece is a white square and is not already occupied..
			else if (selected && clickedSquare.isClickable() && !clickedSquare.hasPiece()) {
				System.out.println("sent coordinates");
				
				fromX = selectedPiece.getRow();
				fromY = selectedPiece.getColumn();
				toX = clickedSquare.getRow();
				toY = clickedSquare.getColumn();
				
				PieceSelection selected = new PieceSelection(fromX, fromY, toX, toY);
				
				try {
					client.sendToServer(selected);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				this.selected = false;
				selectedPiece.setBackground(Color.white);
				
			}
			
			
			
		}
	}
	
	
	public void setTeam(String team) {
		client.team = team;
		teamLabel.setText(team);
		if (team.equals("red")) {
			teamLabel.setForeground(Color.red);
		}
		else {
			teamLabel.setForeground(Color.black);
		}
	}
	
	public void setTurn(String whoseTurn) {
		// this may not be necessary; this.turnLabel is set to the same reference in GameClientGUI
		//GameBoardPanel gameBoardPanel = (GameBoardPanel) container.getComponent(4);
		//gameBoardPanel.getTurnLabel().setText(whoseTurn);
		turnLabel.setText(whoseTurn);
	}
	
	public void setStatus(String status) {
		statusLabel.setText(status);
	}
	
	
	public void setTeamLabel(JLabel teamLabel) {
		this.teamLabel = teamLabel;	
	}
	
	public void setTurnLabel(JLabel turnLabel) {
		this.turnLabel = turnLabel;
	}
	
	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public void setGameBoard(GameBoard gameBoard) {
		this.gameBoard = gameBoard;
	}
	
	public GameBoard getGameBoard() {return this.gameBoard;}
	

	
	
	
	/*
	 	defineBoardSquares() moved to the GameBoard class
	*/
	
}
