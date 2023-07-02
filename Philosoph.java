import java.util.concurrent.ThreadLocalRandom;

public class Philosoph extends Thread {
	int random_base;
	boolean rightIsFirst;
	Fork l_Fork;
	Fork r_Fork;
	String name;
	WatchDog watcher;
	public Philosoph(String name,Fork l_Fork, Fork r_Fork, boolean rightIsFirst, int random_base,WatchDog watcher) {
		this.rightIsFirst = rightIsFirst;
		this.l_Fork = l_Fork;
		this.r_Fork = r_Fork;
		this.random_base = random_base;
		this.name=name;
		this.watcher=watcher;
	}
	
	@Override
	public void run()
	{		System.out.println(this.name);
			while (true) {
				try {
					watcher.reset();
					if ( this.isInterrupted() ) {
						break;
					}
					think();
					if ( rightIsFirst) {
						//System.out.println(this.name+" пытается взять правую вилку");
						r_Fork.get();
						//System.out.println(this.name+" взял правую вилку");
						//System.out.println(this.name+" пытается взять левую вилку");
						l_Fork.get();
						//System.out.println(this.name+" взял левую вилку");
					}
					else {
						//System.out.println(this.name+" пытается взять левую вилку");
						l_Fork.put();
						//System.out.println(this.name+" взял левую вилку");
						//System.out.println(this.name+" пытается взять правую вилку");
						r_Fork.put();
						//System.out.println(this.name+" взял правую вилку");
					}
				eat();
				if ( rightIsFirst) {
					//System.out.println(this.name+" опустил правую вилку");
					r_Fork.put();
					//System.out.println(this.name+" опустил левую вилку");
					l_Fork.put();
				}
				else {
					//System.out.println(this.name+" опустил левую вилку");
					l_Fork.put();
					//System.out.println(this.name+" опустил правую вилку");
					r_Fork.put();
				}
				}
				catch (InterruptedException e) {
					break;
				}
			}
			System.out.printf("Филосов %s прекратил своё существование \n",this.name);
		
		
	}

	private void eat() throws InterruptedException {
		//System.out.println(this.name+" ест");
		Thread.sleep(ThreadLocalRandom.current().nextInt(0, random_base));
	}
	
	private void think() throws InterruptedException {
		//System.out.println(this.name+" думает");
		Thread.sleep(ThreadLocalRandom.current().nextInt(0, random_base));
	}
	}


