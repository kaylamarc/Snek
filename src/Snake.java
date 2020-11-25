import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Snake {
	// holds body peices of snake
	public Rect[] body = new Rect[100];
	public double bodyWidth, bodyHeight; // individual body peices

	public int size;
	public int tail = 0;
	public int head = 0;

	public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight) {
		this.size = size;
		this.bodyWidth = bodyWidth;
		this.bodyHeight = bodyHeight;
		
		// create each body piece
		for (int i = 0; i <= size; i++) {
			Rect bodyPiece = new Rect( startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
			body[i] = bodyPiece;
			head++;
		}
		head--;
	}

	public void draw(Graphics2D g2) {
		
		// get the wrap around effect of snake
		for (int i = tail; i != head; i = (i + 1) % body.length) {
			Rect piece = body[i];

			// 3 pixel gap between the body pieces
			double subWidth = (piece.width - 6.0) / 2.0;
			double subHeight = (piece.height - 6.0) / 2;
			
			// color of snake
			g2.setColor(Color.WHITE);
			
			// 4 little rectangles per body piece
			g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
			g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
			g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
			g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
		}

	}
}
