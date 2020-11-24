import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * key listener
 * @author kkcoo
 *
 */
public class KL extends KeyAdapter implements KeyListener {
	private boolean[] keyPressed = new boolean[128];
	
	public void keyPressed(KeyEvent keyEvent) {
		keyPressed[keyEvent.getKeyCode()] = true;
	}
	
	public void keyReleased(KeyEvent keyEvent) {
		keyPressed[keyEvent.getKeyCode()] = false;
	}
	
	public boolean isKeyPressed(int keyCode) {
		return keyPressed[keyCode];
	}
}
