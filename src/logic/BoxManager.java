package logic;

/**
 * Boxmanager represents production with a fixed number, code, lock, and check.
 * 
 * @author Kornphon Noiprasert
 * @author Vichakorn Yotboonrueang
 */
public class BoxManager {
	/** number in a box. */
	private int number;
	/** the invisible status of this box. */
	private boolean lock;
	/** the unique number of each block. */
	private int code;

	/**
	 * Create box of number with a fixed number, code, lock, and check. Change code
	 * for every box to make an unique box.
	 * 
	 * @param number - number in a box.
	 * @param lock - the invisible status of this box.
	 * @param code - a number to make an unique box.
	 */
	public BoxManager(int number, boolean lock, int code) {
		this.number = number;
		this.lock = lock;
		this.code = code;
	}

	/**
	 * Get a number of this box
	 * 
	 * @return number in a box.
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * Get the invisible status of this box.
	 * 
	 * @return number in a box.
	 */
	public boolean getLock() {
		return lock;
	}

	/**
	 * Get the code of each box.
	 * 
	 * @return code's number of this box.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * To set the status of each box.
	 * 
	 * @param lock - a status of each box.
	 */
	public void setLock(boolean lock) {
		this.lock = lock;
	}

}
