package logic;

public class BoxManager {
	private int number;
	private boolean lock;
	private boolean check;
	
	public BoxManager(int number,boolean lock,boolean check) {
		this.number = number;
		this.lock = lock;
		this.check = check;
	}
	
	public int getNumber() {
		return number;
	}
	
	public boolean getCheck(){
		return check;
	}
	
	public boolean getLock() {
		return lock;
	}
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}
}
