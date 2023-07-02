
public class StupidPhilosophers {
	private Philosoph[] phils;
	private Thread th;
	private void run() {
		//Псевдо-вачдог с МК
		//Если раз в 4сек не вызвали ресет выполняет ActionToDie() и стопится
		//Если прошло 10 сек и вызывали ресет минимум раз в 4 сек, то вызовется ActionToAllive()
		WatchDog watcher = new WatchDog(4000,10000, new ActionToDie(), new ActionToAllive() );
		Fork[] forks= new Fork[5];//Создаем и заполняем массив вилок
		for (int i = 0;i<=4;i++) {
			forks[i]= new Fork();
		}
		 phils = new Philosoph[5];
		for (int i = 0;i<=4;i++) {
			forks[i]= new Fork();
		}	
			int blck=5;
			phils[0]= new Philosoph("John",forks[0],forks[1],true,blck,watcher);// true и false задают берем ли првую вилку первой или нет
			phils[1]= new Philosoph("Bob",forks[1],forks[2],true,blck,watcher);
			phils[2]= new Philosoph("Igor",forks[2],forks[3],true,blck,watcher);
			phils[3]= new Philosoph("Loui",forks[3],forks[4],true,blck,watcher);
			phils[4]= new Philosoph("Carl",forks[4],forks[0],true,blck,watcher);
			watcher.start();
			System.out.println("STAAAAAAARTUEM");;
			for (int i = 0;i<=4;i++) {
				phils[i].start();
			}
	}
	public static void main(String[] args) {
		StupidPhilosophers prog = new StupidPhilosophers();
		prog.run();
	
	}
	
 class ActionToDie implements Runnable{

		@Override
		public void run() {
			System.out.println("4 сек прошло");
			System.out.println("ФИЛОСОФЫ МЕРТВЫ.Кушать им нечего.");
			System.exit(0);//Тут в deadlock и надо EXIT
//			for (int i = 0;i<=4;i++) {
//				phils[i].interrupt(); 
//			
//			
//			}
			
		}
		
		
	}
	 class ActionToAllive implements Runnable{

		@Override
		public void run() {
			System.out.println("10 сек прошло");
			System.out.println("ОНИ ЖИВЫЫЫЫЫЫЫЫЫ.Но им пора почить с миром.");
			for (int i = 0;i<=4;i++) {
				phils[i].interrupt();//Тут предполагается, что потоки не в deadlock-е
			}
			
				 
		}
		
	
	}
		
		
	

}
