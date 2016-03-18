package maze;

public class MazeThreads implements Runnable {
	private Maze m = new Maze();
	private Thread[] t = new Thread[100];
	private static Cord move;
	public int NumofT = 0;
	
	public void mazesolver(){
		move = new Cord(m.en_corx, m.en_cory);
		System.out.println(m.en_corx+","+m.en_cory);
		System.out.println("entrace "+m.maze[move.x][move.y]);
		int walker = m.maze[move.x][move.y];
		if(walker == 0){
			t[NumofT] = new Thread(new MazeThreads());
			t[NumofT].start();
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
		if(corx<m.maze.length && m.maze[x][corx]==0){
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

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		boolean ends=false,endn=false,ende=false,endw=false;
		if(GoNorth(move.x,move.y)){
			while(GoNorth(move.x,move.y)){
				move.x--;
				System.out.println(Thread.currentThread().getId()+" going north");
				System.out.println("number of thread now: "+NumofT);
				if(GoEast(move.x,move.y)){
					System.out.println("in east");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
				if(GoWest(move.x,move.y)){
					System.out.println("in west");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
			}
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
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
				if(GoWest(move.x,move.y)){
					System.out.println("in west");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}

			}
			ends =true;
		}
		if(GoEast(move.x,move.y)){
			while(GoEast(move.x,move.y)){
				move.y++;
				System.out.println(Thread.currentThread().getId()+" going east");
				System.out.println("number of thread now: "+NumofT);
				if(GoNorth(move.x,move.y)){
					System.out.println("in north");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
				if(GoSouht(move.x,move.y)){
					System.out.println("in south");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
			}
			ende =true;
		}
		if(GoWest(move.x, move.y)){
			while(GoWest(move.x,move.y)){
				move.y--;
				System.out.println(Thread.currentThread().getId()+" going west");
				System.out.println("number of thread now: "+NumofT);
				if(GoNorth(move.x,move.y)){
					System.out.println("in north");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
				if(GoSouht(move.x,move.y)){
					System.out.println("in south");
					NumofT++;
					t[NumofT] = new Thread(new MazeThreads());
					t[NumofT].start();
				}
			}
			endw = true;
		}
		if(endw == ends==ende==endn){
			t[NumofT].stop();
			System.out.println("a thread stop");
		}
		
	}

}
