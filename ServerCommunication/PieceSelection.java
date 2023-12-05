package ServerCommunication;

public class PieceSelection implements java.io.Serializable {
	public int fromX, fromY, toX, toY;
	
	public PieceSelection(int fromX, int fromY, int toX, int toY) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}
}
