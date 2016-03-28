package maze;
/*
 * Aqui se hicieron algunos cambios
 */
public class MazeThreads implements Runnable {
	// el arreglo de threads fue movido a una calse aparte
	//ya que al tenerlos aqui cada thread obtenia su propio arreglo
	//de threads y probocava nullpointer exception con el contador 
	private Maze m = new Maze();
	public static Cord move;
	private int NumofT = 0;
	public synchronized void numinc(){
		NumofT++;
	}
	
	public void mazesolver(){
		move = new Cord(m.en_corx,m.en_cory);
		System.out.println(move.x+","+move.y);
		System.out.println("entrace "+m.maze[move.x][move.y]);
		int walker = m.maze[move.x][move.y];
		if(walker == 0){
			ThreadArr.ways.add(new Thread(new MazeThreads()));
			ThreadArr.ways.get(NumofT).start();
		}
	}

	private boolean GoWest(int x,int y) {
		// TODO Auto-generated method stub
		int cory = y;
		cory--;
		System.out.println("West pos now: "+x+","+cory);
		if(cory>=0 && m.maze[x][cory]==0){
			return true;
		}else{
			return false;
		}
		
	}

	private boolean GoEast(int x,int y) {
		// TODO Auto-generated method stub
		int cory = y;
		cory++;
		System.out.println("East pos now: "+x+","+cory);
		if(cory<m.maze[x].length && m.maze[x][cory] == 0){
			return true;
		}else{
			return false;
		}
		
	}

	private boolean GoSouht(int x,int y) {
		// TODO Auto-generated method stub
		int corx = x;
		corx++;
		System.out.println("South pos now: "+corx+","+y);
		if(corx<m.maze.length && m.maze[corx][y]==0){
			return true;
		}else{
			return false;
		}
		
	}

	private boolean GoNorth(int x,int y) {
		// TODO Auto-generated method stub
		int corx = x;
		corx--;
		System.out.println("North pos now: "+corx+","+y);
		if(corx>=0 && m.maze[corx][y]==0){
			return true;
		}else{
			return false;
		}		
	}
	@Override
	public void run() {
		// se agrego un contador a parte, la cual se utiliaz para meter
		// los threads al arreglo dinamico
		int trun =0;
		System.out.println("entrando metodo run thread: "+Thread.currentThread().getName());
		boolean ends=false,endn=false,ende=false,endw=false;
		if(GoNorth(move.x,move.y)){
			while(GoNorth(move.x,move.y)){
				move.x--;
				System.out.println(Thread.currentThread().getId()+" going north");
				System.out.println("number of thread now: "+NumofT);
				if(GoEast(move.x,move.y)){
					System.out.println("in east");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
				if(GoWest(move.x,move.y)){
					System.out.println("in west");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
			}
			endn = true;
		}
		else{
			// se agrego esto en cada condicion, ya que , si no puede ir
			// en esa dirreccion tecnicamente ya comprovo esa dirreccion
			endn = true;
		}
		if(GoSouht(move.x, move.y)){
			while(GoSouht(move.x,move.y)){
				move.x++;
				System.out.println(Thread.currentThread().getId()+" going souht");
				System.out.println("number of thread now: "+NumofT);
				System.out.println("row now: "+move.x);
				if(GoEast(move.x,move.y)){
					System.out.println("in east");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
				if(GoWest(move.x,move.y)){
					System.out.println("in west");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}

			}
			ends =true;
		}
		else{
			ends = true;
		}
		if(GoEast(move.x,move.y)){
			while(GoEast(move.x,move.y)){
				move.y++;
				System.out.println(Thread.currentThread().getId()+" going east");
				System.out.println("number of thread now: "+NumofT);
				if(GoNorth(move.x,move.y)){
					System.out.println("in north");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
				if(GoSouht(move.x,move.y)){
					System.out.println("in south");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
			}
			ende =true;
		}
		else{
			ende = true;
		}
		if(GoWest(move.x, move.y)){
			while(GoWest(move.x,move.y)){
				move.y--;
				System.out.println(Thread.currentThread().getId()+" going west");
				System.out.println("number of thread now: "+NumofT);
				if(GoNorth(move.x,move.y)){
					System.out.println("in north");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
				if(GoSouht(move.x,move.y)){
					System.out.println("in south");
					numinc();
					ThreadArr.ways.add(new Thread(new MazeThreads()));
					trun++;
					beging(trun);
				}
			}
			endw = true;
		}else{
			endw = true;
		}
		if((endw == true)&&(ends== true)&&(ende==true)&&(endn==true)){
				Finish_it();
		}
		
	}
	// el start se paso a una funcion ya que lanzaba una exepcion
	// cuando 2 hilos intentaban comenzar al mismo tiempo
	public synchronized void beging(int n){
		ThreadArr.ways.get(n).start();
	}
	//Se cambio .stop por .interrupt, esto tambien paso a ser una
	// funcion por la misma razon de start
	public synchronized void Finish_it(){
		if((move.x == m.e_corx)&&(move.y == m.e_cory)){
			System.out.println("THE EXIT WAS FOUND");
			ThreadArr.ways.get(NumofT).interrupt();
		}
		else{
			System.out.println("A THREAD STOP IN POS"+move.x+","+move.y);
		}
		
	}

}
// actualmente el programa lanza la excepcion
//java.lang.IllegalThreadStateException, cndo los hilos
//intentan comenzar al mismo tiempo :/, sincronized no esta 
// funcionando