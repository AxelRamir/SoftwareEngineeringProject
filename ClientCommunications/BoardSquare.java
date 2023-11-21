package ClientCommunications;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardSquare extends JButton{
		
	private int row;
	private int column;
	private boolean hasPiece;
	private String team; // this should probably be simply "black" or "red"
	
	private ImageIcon blackPieceIcon = new ImageIcon("./images/blackpiece.png");
	private ImageIcon redPieceIcon = new ImageIcon("./images/redpiece.png");
	
	
	public BoardSquare(int row, int column) {
		
		this.row = row;
		this.column = column;
		
	}
	
	
	//image formatting stuff, don't worry about this
	public ImageIcon redIcon() {
		Image image = blackPieceIcon.getImage();
		Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		
		return icon;
	}
	
	public ImageIcon blackIcon() {
		Image image = redPieceIcon.getImage();
		Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		
		return icon;
	}
	
	
	
	// These set the icon that displays the "piece" (red or black circle). Also sets the team variable
	public void setRed() {
		
		this.setIcon(redIcon());
		team = "red";
	}
		
	public void setBlack() {
		
		this.setIcon(blackIcon());
		team = "black";
	}
		
	
	
	//setters
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setColumn(int column) {
		this.column = column;
	}
	
	public void setHasPiece(boolean hasPiece) {
		this.hasPiece = hasPiece;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	
	//getters
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public boolean hasPiece() {
		return hasPiece;
	}
	
	public String getTeam() {
		return team;
	}
	
	

}
