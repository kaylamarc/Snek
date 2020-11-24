import java.awt.Color;
import java.awt.Graphics;

/**
 * scene for game state
 * @author kkcoo
 *
 */
public class GameScene extends Scene {

	public void update(double dt) {
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

}
