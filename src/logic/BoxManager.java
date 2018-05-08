package logic;

public class BoxManager {
	private int number;
	private boolean lock;
	private boolean check;
	private int code;
	
	public BoxManager(int number,boolean lock,boolean check,int code) {
		this.number = number;
		this.lock = lock;
		this.check = check;
		this.code = code;
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
	
	public int getCode() {
		return code;
	}
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
}
