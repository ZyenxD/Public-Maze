package maze;
/*
 * esta clase yiene como atributos:
 * - el nombre del thread que llego a la salida
 * - el numero de pasos que dio 
 */
public class MinimunThread {
	String t; // nombre del thread que encontro la salida
	int s; // cantidad de pasos que uso para llegar
	String ruta = "";
	public MinimunThread(String t, int s,String Ruta) {
		this.t = t;
		this.s = s;
		this.ruta = Ruta;
	}
	

}
