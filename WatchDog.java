import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class WatchDog {
	
	private Runnable actionToNoReset; // action that occurs if reset() was not called within checkFrameTime
	private Runnable actionToOverrunMaxWatchingTime;// action that occurs if maxWatchingTime was overrun
	private long checkFrameTime;// in millis
	private long maxWatchingTime;// in millis
	private boolean resetFlag;//reset was called
	private ScheduledExecutorService checkersPool;// pool for watchdog checkers
	private boolean isRunning;// watchdog is running 
	
	public WatchDog(long checkFrameTime,long maxWatchingTime,Runnable actionToNoReset,Runnable actionToOverrunMaxWatchingTime) {
		this.checkFrameTime=checkFrameTime;
		this.maxWatchingTime=maxWatchingTime;
		this.actionToNoReset=actionToNoReset;
		this.actionToOverrunMaxWatchingTime=actionToOverrunMaxWatchingTime;
		isRunning=false;
		resetFlag=false;//reset was not done 
		this.checkersPool=Executors.newScheduledThreadPool(2);
		
	}
	
	
	public synchronized void reset() {
		resetFlag=true;
	}
	private synchronized boolean getResetStatus() {
		return resetFlag;
	}
	private synchronized void setResetStatus(boolean resetFlag) {
		this.resetFlag=resetFlag;
	}
	
	
	
	public  synchronized void start() {
		if (!isRunning) {
	    checkersPool.scheduleWithFixedDelay(new ResetFlagChecker(), checkFrameTime, checkFrameTime,TimeUnit.MILLISECONDS);
		if (actionToOverrunMaxWatchingTime != null) {
			checkersPool.scheduleWithFixedDelay(new OverrunExistingMaxTimeChecker(),maxWatchingTime, maxWatchingTime,TimeUnit.MILLISECONDS);
		}
		isRunning=true;
		}
	}
	
	public synchronized void stop()
	{	
		isRunning=false;
		checkersPool.shutdown();
	}
	
	
	
	class ResetFlagChecker implements Runnable{

		@Override
		public void run() {
			if (!getResetStatus() ) {
				actionToNoReset.run();
				stop();
			}
			else {
				
				setResetStatus(false);
			}
			
		}
		
	}
	class OverrunExistingMaxTimeChecker implements Runnable{

		@Override
		public void run() {
			if ( actionToOverrunMaxWatchingTime!=null ) {
				actionToOverrunMaxWatchingTime.run();
				stop();
			}
			
		}
		
	}

}


	

