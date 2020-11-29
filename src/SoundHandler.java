import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundHandler {

	private AudioInputStream begin;
	private AudioInputStream nom;
	private AudioInputStream gameover;
	private AudioInputStream victory;

	public SoundHandler() {

	}

	/**
	 * plays begin sound when play button pressed
	 */
	public void playBegin() {
		try {
			begin = AudioSystem.getAudioInputStream(new File("assets/begin.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(begin);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	/**
	 * plays eating "nom" sound when food eaten
	 */
	public void playNom() {
		try {
			nom = AudioSystem.getAudioInputStream(new File("assets/nom.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(nom);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	/**
	 * plays game over sound when game lost
	 */
	public void playGameOver() {
		try {
			gameover = AudioSystem.getAudioInputStream(new File("assets/gameover.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(gameover);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}

	/**
	 * plays victory sound when game is won
	 */
	public void playVictory() {
		try {
			victory = AudioSystem.getAudioInputStream(new File("assets/victory.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(victory);
			clip.start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
	}
}
