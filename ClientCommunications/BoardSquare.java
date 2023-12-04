package ClientCommunications;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BoardSquare extends JButton{
		
	private int row;
	private int column;
	private boolean hasPiece = false;
	private String team = "black"; // this should probably be simply "black" or "red" // making this black by default -AL
	private boolean isKingPiece;
	private boolean isClickable;
	
	private ImageIcon blackPieceIcon = new ImageIcon("./images/blackpiece.png");
	private ImageIcon redPieceIcon = new ImageIcon("./images/redpiece.png");
	private ImageIcon blackPieceKing = new ImageIcon("./images/blackpiece_king.png");
	private ImageIcon redPieceKing = new ImageIcon("./images/redpiece_king.png");
	private ImageIcon emptySquare = new ImageIcon("./images/empty.png");
	
	
	public BoardSquare(int row, int column) {
		
		this.row = row;
		this.column = column;
		
	}
	
	
	//image formatting stuff, don't worry about this
	public ImageIcon redIcon() {
		Image image;
		if (isKingPiece)
			image = redPieceKing.getImage();
		else
			image = redPieceIcon.getImage();
		Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		
		return icon;
	}
	
	public ImageIcon blackIcon() {
		Image image;
		if (isKingPiece)
			image = blackPieceKing.getImage();
		else
			image = blackPieceIcon.getImage();
		Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		
		return icon;
	}
	
	public ImageIcon emptySquare() {
		Image image = emptySquare.getImage();
		Image newimg = image.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		
		return icon;
	}
	
	
	
	// These set the icon that displays the "piece" (red or black circle). --Also sets the team variable--
	// redundant with setTeam, i'm consolidating this into an "update appearance" method that runs in setTeam and setKing -AL
	private void updateAppearance() {
		if (!hasPiece)
			this.setIcon(emptySquare());
		else if (team == "red")
			this.setIcon(redIcon());
		else if (team == "black")
			this.setIcon(blackIcon());
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
		
		updateAppearance();
	}
	
	public void setTeam(String team) {
		this.team = team;
		
		updateAppearance();
	}
	
	public void setKing(boolean isKing) {
		this.isKingPiece = isKing;
		updateAppearance();
	}
	
	public void setClickable(boolean isClickable) {
		this.isClickable = isClickable;
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
	
	public boolean isClickable() {
		return isClickable;
	}
	
	public boolean getIsKing() {return isKingPiece;}
	
	

}
