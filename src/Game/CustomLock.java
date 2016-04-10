package Game;

public class CustomLock {
	private int lockCounter;
	private String lockName;
	private boolean wasSignaled;
	
	protected CustomLock() {
		lockCounter = 0;
		wasSignaled = false;
	}
	
	protected CustomLock(String lockName) {
		lockCounter = 0;
		this.lockName = lockName;
		wasSignaled = false;
	}
	
	protected void setLockName(String lockName) {
		this.lockName = lockName;
	}
	
	protected String getLockName() {
		return lockName;
	}
	
	protected synchronized void incrementCounter() { 
		lockCounter++;
	}
	
	protected synchronized int getCounter() {
		return lockCounter;
	}
	
	protected synchronized void signal(boolean signal) {
		wasSignaled = signal;
	}
	
	protected synchronized boolean wasSignaled() {
		return wasSignaled;
	}

}
