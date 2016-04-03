package maze;

import java.io.*;

/*
 * esta clase lee el .txt y guarda la entrada, la salida;
 * tambien guarda el laberinto en una matriz
 * 
 * por ahora solo acepta numeros de 1 digito como tamano de la mattriz
 */

/*
 * TODO: impelmntar forma para contar cuantos pasos se dieron hasta la salida
 * 		 tambien, arreglar algunos errores numericos en los print
 */

public class Maze {

	static int[][] maze; // matriz donde se guarda el laberinto
	static File Filemaze = new File("src/maze.txt"); // accseo al archivo
	public static int en_corx,en_cory,e_corx,e_cory; // corrdenadas de entrada y salida
	
	//metodo para crear el laberinto
	public void MazeCrwator(){
		int size=0;// size del laberinto
		
		if(Filemaze.exists()){
			try{
				BufferedReader br = new BufferedReader(new FileReader(Filemaze));
				String line= null; // guarda cada line del txt
				int checkl =1,cont=0,ch=0; 
				String nSize = "";
				// este while se ejecuta por lineas y guarda la linea del documnto en line
				while((line = br.readLine())!= null){
					// check es un contador para verificar que linea esta leyendo
					switch (checkl) {
					// en el primer caso 1 le el size del arreglo
					case 1:
						nSize = line.substring(5);
						try{
							size = Integer.parseInt(nSize);
						}catch(Exception e){
							System.out.println("Error en formato de Tamano del laberinto");
							System.exit(0);
						}
						
						//size = Character.getNumericValue(n);
						System.out.println("este es el size: "+size);
						maze = new int[size][size];
						checkl++;
						break;
						//en el caso 2 la cordenada de entrada
					case 2:
						en_corx = Character.getNumericValue(line.charAt(6));
						en_cory = Character.getNumericValue(line.charAt(8));
						System.out.println("este es la entrada: "+(en_corx-1)+","+(en_cory-1));
						
						checkl++;
						break;
						// en el caso 3 la coordenada de salida
					case 3:
						e_corx = Character.getNumericValue(line.charAt(5));
						e_cory = Character.getNumericValue(line.charAt(7));
						System.out.println("este es la salida: "+(e_corx-1)+","+(e_cory-1));
						
						checkl++;
						break;
					default:
						// el resto de las lineas son el laberinto el cual se almacena en una matriz
						for(int c2nt=0;cont<maze.length && c2nt<maze[cont].length;c2nt++){
								maze[cont][c2nt] = Character.getNumericValue(line.charAt(ch));
								maze[e_corx-1][e_cory-1] = 0;
								maze[en_corx-1][en_cory-1] = 0;
								ch+=2;
							System.out.print(maze[cont][c2nt]+" ");
						}
						System.out.println();
						ch =0;
						cont++;
						break;
					}
				}
				br.close();
				/* esto es para configurar las cordenadas puestas en el 
				 * el documento y transformalas en cordenadas de un
				 * arreglo
				 */
				if(en_corx!=0){
					en_corx--;
				}
				if(en_cory!=0){
					en_cory--;
				}
				if(e_corx!=0){
					e_corx--;
				}
				if(e_cory!=0){
					e_cory--;
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		
	}

}
