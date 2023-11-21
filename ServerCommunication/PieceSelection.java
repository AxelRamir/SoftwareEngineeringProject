package ServerCommunication;

public class PieceSelection {
	private String pieceName;
	private int startingX;
	private int startingY;
	
	public PieceSelection(String pieceName, int startingX, int startingY) {
		this.pieceName = pieceName;
		this.startingX = startingX;
		this.startingY = startingY;
	}
	
	public String getPieceName() {
		return pieceName;
	}
	
	public int getStartingX() {
		return startingX;
	}
	public int getStartingY() {
		return startingY;
	}
}
