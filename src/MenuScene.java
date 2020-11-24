import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * Scene for menu state
 * @author kkcoo
 *
 */
public class MenuScene extends Scene {
	
	public KL keyListener;
	
	public MenuScene(KL keyListener) {
		this.keyListener = keyListener;
	}
	
	public void update(double dt) {
		// for testing key listener
		if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
			System.out.println("Up arrow is pressed");
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	}

}
