package maze;


public class Main {

	public static void main(String[] args) {
	
		// en el main se inicializan todas las clases
		Maze m = new Maze();
		// aqui se crea el labreinto
		m.MazeCrwator();
		// este arreglo de booleanos es para validar en que direccion ira el o los hilos
		boolean[] start = {false,false,false,false};
		//                   N     S     W     E
		
		// aqui se inicia el hilo en la dirrecion a la que ira
		//TODO: validar forma para predecir en que dirreccion mandar el hilo inicial
		//       y verificar si debe crear mas de un hilo en otra direccion apartir de la inicial
		// lol eso sono como joa xD
		
		
		/*
		 * en esta parte se valida en que dirreccion ira el o los hilos dependiendo del punto del entrada
		 */
		
		if(m.maze[m.en_corx][m.en_cory]==0){
			// para ir a norte
			if(m.en_corx-1>0 && m.maze[m.en_corx-1][m.en_cory]==0){
				start[0] = true;
			}
			// para ir al sur
			if(m.en_corx+1<m.maze.length && m.maze[m.en_corx+1][m.en_cory] ==0){
				start[1] = true;
			}
			// para ir al oeste
			if(m.en_cory-1>0 && m.maze[m.en_corx][m.en_cory-1]==0){
				start[2] = true;
			}
			// para ir al este
			if(m.en_cory+1<m.maze.length && m.maze[m.en_corx][m.en_cory+1]==0){
				start[3] = true;
			}
		}
		// si alguna de las posiciones es verdadera creara un hilo en el arreglo
		// en la dirrecion descrita
			if(start[0]==true){
				ThreadArr.ways.add(new Thread(new MThreads(m.en_corx, m.en_cory,0, m.maze, "N","")));
			}
			if(start[1]==true){
				ThreadArr.ways.add(new Thread(new MThreads(m.en_corx, m.en_cory,0,m.maze, "S","")));
			}
			if(start[2]==true){
				ThreadArr.ways.add(new Thread(new MThreads(m.en_corx, m.en_cory,0,m.maze, "W","")));
			}
			if(start[3]==true){
				ThreadArr.ways.add(new Thread(new MThreads(m.en_corx, m.en_cory,0 ,m.maze, "E","")));
			}
			
			// el cual luego se enjecutara aqui, junto a todos los hilos del arreglo
		for(int i =0;i<ThreadArr.ways.size();i++){
			ThreadArr.ways.get(i).start();
			try {
				ThreadArr.ways.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int min = ThreadArr.min.get(0).s; // el camino minimo
		int iMin = 0; //Indice en arreglo con pasos minimos
		for(int j = 0;j<ThreadArr.min.size();j++){
			if(ThreadArr.min.get(j).s<min){
				min = ThreadArr.min.get(j).s;
				iMin = j;
			}
			System.out.println("el "+ThreadArr.min.get(iMin).t+" encontro la salida en "+ThreadArr.min.get(iMin).s+" pasos");
			System.out.println("Ruta utilizada: " + ThreadArr.min.get(iMin).ruta);
		}

	}

}
