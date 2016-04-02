package maze;

public class Main {

	public static void main(String[] args) {
	
		// en el main se inicializan todas las clases
		Maze m = new Maze();
		// aqui se crea el labreinto
		m.MazeCrwator();
		// aqui se inicia el hilo en la dirrecion a la que ira
		//TODO: validar forma para predecir en que dirreccion mandar el hilo inicial
		//       y verificar si debe crear mas de un hilo en otra direccion apartir de la inicial
		// lol eso sono como joa xD
		Thread t = new Thread(new MThreads(m.en_corx,m.en_cory, m.maze, "S"));
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0;i<ThreadArr.ways.size();i++){
			ThreadArr.ways.get(i).start();
			try {
				ThreadArr.ways.get(i).join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
