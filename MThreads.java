package maze;


/*
 * esta clase implemeta el runnable, cada hilo tiene como atributos:
 * fila
 * columna
 * way( la direccion en la que hiran)
 * maze(laberinto en el cual correran)
 */

public class MThreads implements Runnable {
	public int fila,columna,Steps;
	public String recorrido = "";
	public int[][] mts; // maze to solve
	public String way;
	public Maze m = new Maze();
	public MThreads(int f,int c,int s, int[][] maze_to_solve,String w, String rec) {
		this.fila = f;
		this.columna =c;
		this.mts = maze_to_solve;
		this.way= w;
		this.Steps = s;
		this.recorrido = rec;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * el es solo un grupo de ifs, que filtraran la dirreccion en la que
	 * ira el hilo
	 * 
	 *  los hilos de este programa van en linea recta hasta que no 
	 *  pueden seguir adelante
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(way=="N"){
			NorthRun();
		}
		if(way=="S"){
			SouthRun();
		}
		if(way=="E"){
			EastRun();
		}
		if(way=="W"){
			WestRun();
		}
		
	}
	/*
	 * hay cuatro metodos para ir en cada direcion, Norte, sur, este y oeste
	 */
	private void NorthRun() {
		// TODO Auto-generated method stub
		int Nfila = fila;
		int Ncol = columna;
		int step = Steps;
		boolean the_end = false;
		System.out.println("norte");
		//un hilo cominza a correr en una dirrecion hasta que no pueda 
		//continuar
		while(Nfila>=0 && mts[Nfila][Ncol]==0){
			System.out.println(Nfila+","+Ncol);
			recorrido = recorrido + " (" + Nfila + "," + Ncol + ")";
			//en esta parte se llena el camino por donde el hilo paso
			//con 2, para identificar que ya se paso por esa direcion
			mts[Nfila][Ncol] = 2;
			step++;
			//esta condicion es para evitar el los nullpointer
			// exception de tamano mayor que el arreglo
			if(Nfila<mts.length && Ncol<mts.length){
				//si encuentra una entrada hacia el este 
				// creara un hilo en esa direcion
				if(Ncol+1<mts[Nfila].length && mts[Nfila][Ncol+1]==0){
					Ncol++;
					ThreadArr.ways.add(new Thread(new MThreads(Nfila, Ncol,step,m.maze, "E", recorrido)));
					Ncol--;
				}
				//si encuentra una entrada hacia el oeste 
				// creara un hilo en esa direcion
				if(Ncol-1>0 && mts[Nfila][Ncol-1]==0){
					Ncol--;
					ThreadArr.ways.add(new Thread(new MThreads(Nfila,Ncol,step,m.maze, "W",recorrido)));
					Ncol++;
				}				
			}
			//evita los null pointer exception -1
			if(Nfila>0){
				Nfila--;
			}
			// esta condicion es para validar que el hilo encontro la 
			// salida 
			if((Nfila==m.e_corx)&&(Ncol==m.e_cory)){
				the_end = true;
				break;
			}
		}
		// esta condicion es para evitar algunos errores en la impresion y en el en
		// encontrar la salida
		if(the_end==false&&Nfila<mts.length-1&&Ncol<mts.length-1 && mts[Nfila][Ncol]!=0){
			Nfila++;
		}
		// aqui se verifica si la salida fue encontrada
		if((Nfila==m.e_corx)&&(Ncol==m.e_cory)){
			step++; //Se aumenta un paso por que se verifica una posicion adelantada
			System.out.println();
			System.out.println("SALIDA ENCONTRADA");
			recorrido = recorrido + " (" + Nfila + "," + Ncol + ")";
			ThreadArr.min.add(new MinimunThread(Thread.currentThread().getName(), step,recorrido));
			System.out.println();
		}// sino solo imprime en poscision murio el hilo
		else{
			System.out.println();
			System.out.println(Thread.currentThread().getName()+" Finalizo en "+Nfila+","+Ncol);
			System.out.println("Recorrido " +  Thread.currentThread().getName() + " : " + recorrido );
			
			System.out.println();
		}
		
	}
	
	/*
	 * el resto de los metodos tiene la misma implemntacion
	 * solo varian algunos detalles ya que corren en direccciones distintas:
	 *  en Norte se disminuye la referencia de las filas para poder SUBIR
	 *  en Sur se incrementa la referencia de las filas para poder BAJAR
	 *  en Este se incremneta la referencia de las columnas para poder ir a la DERECHA
	 *  en Oeste se disminuye la referencia de las columnas para poder ir a la IZQUIERDA
	 */
	
	private void SouthRun() {
		// TODO Auto-generated method stub
		System.out.println("sur");
		int Sfila=fila;
		int Scol=columna;
		int step = Steps;
		boolean the_end = false;
		while(Sfila<mts.length && mts[Sfila][Scol]==0){
			System.out.println(Sfila+","+Scol);
			recorrido = recorrido + " (" + Sfila + "," + Scol + ")";
			mts[Sfila][Scol] = 2;
			step++;
			if(Sfila<mts.length && Scol<mts.length){
				if(Scol+1<mts[Sfila].length &&mts[Sfila][Scol+1]==0){
					Scol++;
					ThreadArr.ways.add(new Thread(new MThreads(Sfila,Scol,step,m.maze, "E",recorrido)));
					Scol--;
					
				}
				if(Scol-1>0 && mts[Sfila][Scol-1]==0){
					Scol--;
					ThreadArr.ways.add(new Thread(new MThreads(Sfila,Scol,step,m.maze, "W",recorrido)));
					Scol++;
				}	
			}
			if(Sfila<mts.length){
				Sfila++;
			}
			if((Sfila==m.e_corx)&&(Scol==m.e_cory)){
				the_end = true;
				break;
			}
		}
		if(the_end==false && Sfila<mts.length&&Scol<mts.length && mts[Sfila][Scol]!=0){
			Sfila--;	
		}
		if(Sfila==mts.length)
			Sfila--;
		if((Sfila==m.e_corx)&&(Scol==m.e_cory)){
			step++; //Se aumenta un paso por que se verifica una posicion adelantada
			System.out.println();
			System.out.println("SALIDA ENCONTRADA");
			recorrido = recorrido + " (" + Sfila + "," + Scol + ")";
			ThreadArr.min.add(new MinimunThread(Thread.currentThread().getName(), step,recorrido));
			System.out.println();
		}
		else{
			System.out.println();
			System.out.println(Thread.currentThread().getName()+" Finalizo en "+Sfila+","+Scol);
			System.out.println("Recorrido " +  Thread.currentThread().getName() + " : " + recorrido );
			System.out.println();
		}
		
	}
	private void EastRun() {
		// TODO Auto-generated method stub
		System.out.println("este");
		int Ecol = columna;
		int Efila= fila;
		int step = Steps;
		boolean the_end = false;
		while(Ecol<mts[Efila].length && mts[Efila][Ecol]==0){
			recorrido = recorrido + " (" + Efila + "," + Ecol + ")";
			System.out.println(Efila+","+Ecol);
			mts[Efila][Ecol]=2;
			step++;
			if(Efila<mts.length && Ecol<mts.length){
				if(Efila-1>0 && mts[Efila-1][Ecol]==0){
					Efila--;
					System.out.println("de este a norte");
					ThreadArr.ways.add(new Thread(new MThreads(Efila,Ecol,step,m.maze, "N",recorrido)));
					Efila++;

				}
				if(Efila+1<mts.length && mts[Efila+1][Ecol]==0){
					Efila++;
					System.out.println("de este a sur");
					ThreadArr.ways.add(new Thread(new MThreads(Efila,Ecol,step,m.maze, "S",recorrido)));
					Efila--;
				}
			}
			if(Ecol<mts.length){
				Ecol++;
			}
			if((Efila==m.e_corx)&&(Ecol==m.e_cory)){
				the_end = true;
				break;
			}
		}
		if(the_end==false && Efila<mts.length&&Ecol<mts.length && mts[Efila][Ecol]!=0){
			Ecol--;	
		}
		if(Ecol==mts.length)
			Ecol--;
		if((Efila==m.e_corx)&&(Ecol==m.e_cory)){
			step++; //Se aumenta un paso por que se verifica una posicion adelantada
			System.out.println();
			System.out.println("SALIDA ENCONTRADA");
			recorrido = recorrido + " (" + Efila + "," + Ecol + ")";
			ThreadArr.min.add(new MinimunThread(Thread.currentThread().getName(), step,recorrido));
			System.out.println();
		}
		else{
			System.out.println();
			System.out.println(Thread.currentThread().getName()+" Finalizo en "+Efila+","+Ecol);
			System.out.println("Recorrido " +  Thread.currentThread().getName() + " : " + recorrido );
			System.out.println();
		}
		
	}
	private void WestRun() {
		// TODO Auto-generated method stub
		System.out.println("oeste");
		int Wcol = columna;
		int Wfila = fila;
		int step = Steps;
		boolean the_end = false;
		while(Wcol>=0 && mts[Wfila][Wcol]==0){
			recorrido = recorrido + " (" + Wfila + "," + Wcol + ")";
			System.out.println(Wfila+","+Wcol);
			mts[Wfila][Wcol]=2;
			step++; 
			if(Wfila<mts.length && Wcol<mts.length){
				if(Wfila-1>0 && mts[Wfila-1][Wcol]==0){
					Wfila--;
					ThreadArr.ways.add(new Thread(new MThreads(Wfila,Wcol,step,m.maze, "N",recorrido)));
					Wfila++;

				}
				if(Wfila+1<mts.length &&mts[Wfila+1][Wcol]==0){
					Wfila++;
					ThreadArr.ways.add(new Thread(new MThreads(Wfila,Wcol,step,m.maze, "S",recorrido)));
					Wfila--;
				}
				
			}
			if(Wcol>0){
				Wcol--;
				//step++;
			}
			if((Wfila==m.e_corx)&&(Wcol==m.e_cory)){
				the_end = true;
				break;
			}
		}
		if(the_end== false && Wfila<mts.length&&Wcol<mts.length && mts[Wfila][Wcol]!=0){
			Wcol++;
		}
		
		if((Wfila==m.e_corx)&&(Wcol==m.e_cory)){
			step++; //Se aumenta un paso por que se verifica una posicion adelantada
			System.out.println();
			System.out.println("SALIDA ENCONTRADA");
			recorrido = recorrido + " (" + Wfila + "," + Wcol + ")";
			ThreadArr.min.add(new MinimunThread(Thread.currentThread().getName(), step,recorrido));
			System.out.println();
		}
		else{
			System.out.println();
			System.out.println(Thread.currentThread().getName()+" Finalizo en "+Wfila+","+Wcol);
			System.out.println("Recorrido " +  Thread.currentThread().getName() + " : " + recorrido );
			System.out.println();
		}
		
	}
	
}
