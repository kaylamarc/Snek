import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class VictoryScene extends Scene {

	public KL keyListener;
	public ML mouseListener;

	public BufferedImage vict, play, playPressed, exit, exitPressed;
	public BufferedImage playCurrentImage, exitCurrentImage;

	public Rect playRect, exitRect, victRect;

	public static SoundHandler sounds;

	public VictoryScene(KL keyListener, ML mouseListener, SoundHandler sounds) {

		this.keyListener = keyListener;
		this.mouseListener = mouseListener;
		this.sounds = sounds;

		try {
			BufferedImage spritesheet = ImageIO.read(new File("assets/menuSpriteSnek.png"));
			vict = ImageIO.read(new File("assets/vict.png"));
			play = spritesheet.getSubimage(0, 121, 261, 121);
			playPressed = spritesheet.getSubimage(264, 121, 261, 121);
			exit = spritesheet.getSubimage(0, 0, 233, 93);
			exitPressed = spritesheet.getSubimage(264, 0, 233, 93);
		} catch (IOException e) {
			e.printStackTrace();
		}
		playCurrentImage = play;
		exitCurrentImage = exit;

		playRect = new Rect(310, 280, 150, 70);
		exitRect = new Rect(318, 355, 130, 55);
		victRect = new Rect(240, 40, 300, 100);
	}

	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

		g.drawImage(vict, (int) victRect.x, (int) victRect.y, (int) victRect.width, (int) victRect.height, null);
		g.drawImage(playCurrentImage, (int) playRect.x, (int) playRect.y, (int) playRect.width, (int) playRect.height, null);
		g.drawImage(exitCurrentImage, (int) exitRect.x, (int) exitRect.y, (int) exitRect.width, (int) exitRect.height, null);
	}

	public void update(double dt) {
		// check if mouse is within bounds of play button image
		if (mouseListener.getX() >= playRect.x && mouseListener.getX() <= playRect.x + playRect.width
				&& mouseListener.getY() >= playRect.y && mouseListener.getY() <= playRect.y + playRect.height) {
			playCurrentImage = playPressed;

			if (mouseListener.isPressed()) {
				Window.getWindow().changeState(1);
				sounds.playBegin();
			}
		} else {
			playCurrentImage = play;
		}

		if (mouseListener.getX() >= exitRect.x && mouseListener.getX() <= exitRect.x + exitRect.width
				&& mouseListener.getY() >= exitRect.y && mouseListener.getY() <= exitRect.y + exitRect.height) {
			exitCurrentImage = exitPressed;
			if (mouseListener.isPressed()) {
				Window.getWindow().close();
			}
		} else {
			exitCurrentImage = exit;
		}
	}

}
