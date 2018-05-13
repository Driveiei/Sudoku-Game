package application;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

/**
 * MusicBG represents sound in this application. Manage the sound to play all the time.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class MusicBG implements Runnable {
	
	/**
	 * Initialize this class.
	 */
	public MusicBG() {
		
	}

	/**
	 * Run sounds throughout the application runs.
	 * */
	private void playSound() {
		try {
			URL soundFile = getClass().getResource("/source/bg.wav");
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.loop(Integer.MAX_VALUE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * When an object implementing interface Runnable is used to create a thread,
	 * starting the thread causes the object's run method to be called in that
	 * separately executing thread.
	 */
	@Override
	public void run() {
		playSound();

	}

}