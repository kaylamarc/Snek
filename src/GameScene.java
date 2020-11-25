import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * scene for game state
 * 
 * @author kkcoo
 *
 */
public class GameScene extends Scene {
	public Rect background, foreground;
	Snake snake;
	KL keyListener;
	
	public Food food;

	public GameScene(KL keyListener) {
		background = new Rect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		foreground = new Rect(24, 48, Constants.TILE_WIDTH * 31, Constants.TILE_WIDTH * 22);
		snake = new Snake(10, 48, 48 + 24, 24, 24);
		this.keyListener = keyListener;
		food = new Food(foreground, snake, 12, 12, Color.GREEN);
		food.spawn();
	}

	/**
	 * update direction based on key pressed
	 */
	public void update(double dt) {
		if (keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			snake.changeDirection(Direction.UP);
			;
		} else if (keyListener.isKeyPressed(KeyEvent.VK_DOWN)) {
			snake.changeDirection(Direction.DOWN);
		} else if (keyListener.isKeyPressed(KeyEvent.VK_RIGHT)) {
			snake.changeDirection(Direction.RIGHT);
		} else if (keyListener.isKeyPressed(KeyEvent.VK_LEFT)) {
			snake.changeDirection(Direction.LEFT);
		}
		
		if(!food.isSpawned) food.spawn();
		
		food.update(dt);
		snake.update(dt);
	}

	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		// set up and display border
		g2.setColor(Color.DARK_GRAY);
		g2.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

		// set up and display game area
		g2.setColor(Color.BLACK);
		g2.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));

		snake.draw(g2);
		food.draw(g2);
	}

}
