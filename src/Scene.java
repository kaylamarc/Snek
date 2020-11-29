import java.awt.Graphics2D;

/**
 * scene object to deal with different scenes
 * @author kkcoo
 *
 */
public abstract class Scene {
	public abstract void update(double dt);
	public abstract void draw(Graphics2D g);
}
