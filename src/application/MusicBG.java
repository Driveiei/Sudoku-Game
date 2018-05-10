package application;

import java.net.URL;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;

public class MusicBG implements Runnable {

	private String musicFiles;

	public MusicBG(){
		musicFiles = "/source/bg.wav";

		

	}

	private void playSound(String fileName) {
		try {
			URL soundFile = getClass().getResource(fileName);
			AudioInputStream ais  = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ais);
			FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-10);
			clip.loop(Integer.MAX_VALUE);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		playSound(musicFiles);
		

	}

}