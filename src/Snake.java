import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Snake {
	// holds body pieces of snake
	public Rect[] body = new Rect[100];
	public double bodyWidth, bodyHeight; // individual body pieces

	public int size;
	public int tail = 0;
	public int head = 0;

	public Direction direction = Direction.RIGHT;

	public Rect background;

	public double ogWaitBtwnUpdates = 0.3f; // how long it waits before each piece moves
	public double waitTimeLeft = ogWaitBtwnUpdates; // how much time before moving again

	public Color[] bodyColor = new Color[100]; // keeps track of body piece colors

	public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {
		this.size = size;
		this.bodyWidth = bodyWidth;
		this.bodyHeight = bodyHeight;
		this.background = background;

		// create each body piece
		for (int i = 0; i <= size; i++) {
			Rect bodyPiece = new Rect(startX + i * bodyWidth, startY, bodyWidth, bodyHeight);
			body[i] = bodyPiece;
			head++;
		}
		head--;

		for (int i = 0; i < 100; i++) {
			bodyColor[i] = Color.WHITE;
		}
	}

	/**
	 * updates snake position
	 * 
	 * @param dt
	 */
	public void update(double dt) {

		if (waitTimeLeft > 0) {
			waitTimeLeft -= dt;
			return;
		}

		// if intersect with self return to main window
		if (intersectingWithSelf()) {
			Window.getWindow().changeState(0);
			Window.getWindow().sounds.playGameOver();

		}

		waitTimeLeft = ogWaitBtwnUpdates;

		double newX = 0;
		double newY = 0;

		// update to right direction
		if (direction == Direction.RIGHT) {
			newX = body[head].x + bodyWidth;
			newY = body[head].y;
		} else if (direction == Direction.LEFT) {
			newX = body[head].x - bodyWidth;
			newY = body[head].y;
		} else if (direction == Direction.UP) {
			newX = body[head].x;
			newY = body[head].y - bodyHeight;
		} else if (direction == Direction.DOWN) {
			newX = body[head].x;
			newY = body[head].y + bodyHeight;
		}

		// picking up the tail and moving to new head position
		body[(head + 1) % body.length] = body[tail];
		body[tail] = null;
		head = (head + 1) % body.length;
		tail = (tail + 1) % body.length;

		body[head].x = newX;
		body[head].y = newY;

	}

	/**
	 * determines whether or not head is intersecting with a body piece
	 * 
	 * @return boolean
	 */
	public boolean intersectingWithSelf() {
		Rect headRec = body[head];
		return intersectingWithRect(headRec) || intersectingWithBoundary(headRec);
	}

	/**
	 * checks if snake head is intersects with a rectangle
	 * 
	 * @param rect
	 * @return
	 */
	public boolean intersectingWithRect(Rect rect) {
		for (int i = tail; i != head; i = (i + 1) % body.length) {
			if (intersecting(rect, body[i]))
				return true;
		}
		return false;
	}

	/**
	 * determines whether two rectangles are intersecting
	 * 
	 * @param r1
	 * @param r2
	 * @return boolean
	 */
	public boolean intersecting(Rect r1, Rect r2) {
		return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width && r1.y >= r2.y
				&& r1.y + r1.height <= r2.y + r2.height);
	}

	public boolean intersectingWithBoundary(Rect head) {
		return (head.x < background.x || (head.x + head.width) > background.x + background.width
				|| head.y < background.y || (head.y + head.height) > background.y + background.height);
	}

	/**
	 * grow the snake when food eaten append new rectangle to tail in direction
	 * moving
	 */
	public void grow(Color color) {
		Window.getWindow().sounds.playNom();
		size++;

		double newX = 0;
		double newY = 0;

		// add a tail
		if (direction == Direction.RIGHT) {
			newX = body[tail].x - bodyWidth;
			newY = body[tail].y;
		} else if (direction == Direction.LEFT) {
			newX = body[tail].x + bodyWidth;
			newY = body[tail].y;
		} else if (direction == Direction.UP) {
			newX = body[tail].x;
			newY = body[tail].y + bodyHeight;
		} else if (direction == Direction.DOWN) {
			newX = body[tail].x;
			newY = body[tail].y - bodyHeight;
		}

		Rect newBodyPiece = new Rect(newX, newY, bodyWidth, bodyHeight);

		tail = (tail - 1) % body.length;
		bodyColor[size - 1] = color;

		if (tail < 0) {
			tail = 0;
		}

		body[tail] = newBodyPiece;
	}

	// changes snake direction
	public void changeDirection(Direction newDirection) {
		// check user input; make sure they don't run into themselves
		if (newDirection == Direction.RIGHT && direction != Direction.LEFT) {
			direction = newDirection;
		} else if (newDirection == Direction.LEFT && direction != Direction.RIGHT) {
			direction = newDirection;
		} else if (newDirection == Direction.UP && direction != Direction.DOWN) {
			direction = newDirection;
		} else if (newDirection == Direction.DOWN && direction != Direction.UP) {
			direction = newDirection;
		}
	}

	/**
	 * draws the snake
	 * 
	 * @param g2
	 */
	public void draw(Graphics2D g2) {

		// get the wrap around effect of snake
		for (int i = tail, ci = size - 1; i != head; i = (i + 1) % body.length, ci--) {
			Rect piece = body[i];

			// 3 pixel gap between the body pieces
			double subWidth = (piece.width - 6.0) / 2.0;
			double subHeight = (piece.height - 6.0) / 2.0;

			// color of snake
			g2.setColor(bodyColor[ci]);

			// 4 little rectangles per body piece
			g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 2.0, subWidth, subHeight));
			g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 2.0, subWidth, subHeight));
			g2.fill(new Rectangle2D.Double(piece.x + 2.0, piece.y + 4.0 + subHeight, subWidth, subHeight));
			g2.fill(new Rectangle2D.Double(piece.x + 4.0 + subWidth, piece.y + 4.0 + subHeight, subWidth, subHeight));
		}

	}
}
