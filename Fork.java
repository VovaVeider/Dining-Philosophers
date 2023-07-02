import java.util.concurrent.locks.ReentrantLock;

public class Fork extends ReentrantLock {

	/**
	 * 
	 */
	//private static final long serialVersionUID = -7820562542382154566L;
	public Fork() {
		super();
	}
	public void get() {
		this.lock();
	}
	public void put() {
		if (this.isHeldByCurrentThread()) {
		this.unlock();}
	}
	

}
