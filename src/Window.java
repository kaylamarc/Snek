import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable {
	public static Window window = null;
	public boolean isRunning;
	
	public int currentState;
	public Scene currentScene;
	
	public KL keyListener = new KL();
	public ML mouseListener = new ML();
	
	public Window(int width, int height, String title) {
		setSize(width, height);
		setTitle(title);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(keyListener);
		addMouseListener(mouseListener);
		addMouseMotionListener(mouseListener);
		
		isRunning = true;
		
		// Start in menu state
		changeState(0);
	}
	
	/**
	 * singleton: there will only ever be 1 window
	 * when first getting the window
	 * call this method from anywhere
	 * @return
	 */
	public static Window getWindow() {
		if(Window.window == null) {
			Window.window = new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
		}
		
		return Window.window;
	}
	
	// stops window from running
	public void close() {
		isRunning =  false;
	}
	
	public void changeState(int newState) {
		currentState = newState;
		switch(currentState) {
			case 0:
				currentScene = new MenuScene(keyListener, mouseListener);
				break;
			case 1:
				currentScene = new GameScene();
				break;
			default:
				System.out.println("Unknown scene.");
				currentScene = null;
				break;
		}
	}
	
	/**
	 * update all game objects
	 * @param dt
	 */
	public void update(double dt) {
		Image dbImage = createImage(getWidth(), getHeight());
		Graphics dbg = dbImage.getGraphics();
		this.draw(dbg);
		getGraphics().drawImage(dbImage, 0, 0, this);
		
		currentScene.update(dt);
	}
	
	/**
	 * draw game objects
	 * @param g
	 */
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		currentScene.draw(g);
	}
	
	/**
	 * Overridden runnable method
	 * {@inheritDoc}
	 */
	public void run() {
		double lastFrameTime = 0.0;
		try {
			// while running calculate delta time and frame
			while(isRunning) {
				double time = Time.getTime();
				double deltaTime = time - lastFrameTime;
				lastFrameTime = time;
				
				update(deltaTime);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		this.dispose();
	}
}
