package application;

import javafx.concurrent.Task;

public class TimeTask extends Task<Integer> {

	private int timer = 0;
	private boolean onRun = true;
	private int sec = 0;
	private int minute = 0;

	@Override
	protected Integer call() throws Exception {
		while (onRun) {
			updateMessage(String.valueOf(String.format("%02d : %02d : %03d", minute, sec, timer)));
			Thread.sleep(1);
			if (timer == 1000) {
				timer = 0;
				sec++;
				if (sec == 60) {
					sec = 0;
					minute++;
				}
			}
			timer++;
		}
		return timer;
	}
	
	public String getTime() {
		return String.format("%2d/%2d/%3d",minute,sec,timer );
	}

}