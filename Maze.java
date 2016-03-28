package maze;

import java.io.*;

public class Maze {
	
	static int[][] maze;
	static File Filemaze = new File("maze.txt");
	public static int en_corx,en_cory,e_corx,e_cory;
	
	public void MazeCrwator(){
		int size=0;
		
		if(Filemaze.exists()){
			try{
				BufferedReader br = new BufferedReader(new FileReader(Filemaze));
				String line= null;
				int checkl =1,cont=0,ch=0;
				char n;
				while((line = br.readLine())!= null){
					switch (checkl) {
					case 1:
						n = line.charAt(5);
						size = Character.getNumericValue(n);
						System.out.println("este es el size: "+size);
						maze = new int[size][size];
						checkl++;
						break;
					case 2:
						en_corx = Character.getNumericValue(line.charAt(6));
						en_cory = Character.getNumericValue(line.charAt(8));
						System.out.println("este es la entrada: "+(en_corx-1)+","+(en_cory-1));
						
						checkl++;
						break;
					case 3:
						e_corx = Character.getNumericValue(line.charAt(5));
						e_cory = Character.getNumericValue(line.charAt(7));
						System.out.println("este es la salida: "+(e_corx-1)+","+(e_cory-1));
						
						checkl++;
						break;
					default:
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
