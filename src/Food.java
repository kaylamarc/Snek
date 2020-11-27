import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Food {

	public Rect background;
	public Snake snake;
	public int width, height;
	public Color color;
	public Rect rect;

	public int xPadding; // center food within one of grid tiles
	
	public boolean isSpawned = false;

	public Food(Rect background, Snake snake, int width, int height, Color color) {
		this.background = background;
		this.snake = snake;
		this.width = width;
		this.height = height;
		this.color = color;
		this.rect = new Rect(0, 0, width, height);
		
		xPadding = (int)((Constants.TILE_WIDTH - this.width) / 2.0);
	}

	/**
	 * spawns food
	 */
	public void spawn() {
		do {
			changeColor();
			double randX = (int) (Math.random() * (int) (background.width / Constants.TILE_WIDTH))
					* Constants.TILE_WIDTH + background.x;
			double randY = (int) (Math.random() * (int) (background.height / Constants.TILE_WIDTH))
					* Constants.TILE_WIDTH + background.y;
			this.rect.x = randX;
			this.rect.y = randY;
		} while (snake.intersectingWithRect(this.rect));
		this.isSpawned = true;
	}
	
	/**
	 * when the food intersects with snake, grow snake and remove food
	 * @param dt
	 */
	public void update(double dt) {
		if(snake.intersectingWithRect(this.rect)) {
			snake.grow(color);
			this.rect.x = -100;
			this.rect.y = -100;
			isSpawned = false;
		}
	}

	/**
	 * change color of food (random color)
	 */
	public void changeColor() {
		// Colors to choose from
		Color colors[] = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.PINK};
		
		Random rand = new Random();
		
		int index = rand.nextInt(8);
		
		this.color = colors[index];
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(color);
		g2.fillRect((int)this.rect.x + xPadding, (int)this.rect.y + xPadding, width, height);
	}
}
