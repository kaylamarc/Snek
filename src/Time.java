
/**
 * handles time
 * @author kkcoo
 *
 */
public class Time {
	
	// time since game was run
	public static double timeStarted = System.nanoTime();
	
	// actual time game is running
	public static double getTime() {return (System.nanoTime() - timeStarted) * 1E-9;}

}
