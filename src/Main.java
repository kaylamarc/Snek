
public class Main {

	public static void main(String[] args) {
		
		// creates main game window
		Window window = Window.getWindow();
				
		// starts the game
		// the more complicated explanation is, the window object is an instance of the Runnable interface
		// which is the argument that new thread requires. when we call thread.start() it will call the run
		// method of the passed in Runnable, which in this case is window.run()
		Thread thread = new Thread(window);
		thread.start();
	}

}
