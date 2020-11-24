import java.awt.Graphics;

/**
 * scene object to deal with different scenes
 * @author kkcoo
 *
 */
public abstract class Scene {
	public abstract void update(double dt);
	public abstract void draw(Graphics g);
}
