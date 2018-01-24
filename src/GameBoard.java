import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

	public static int[][] board = new int [10][10];;
	
	public GameBoard() {	
	}
	
	public static void initBoard() {
		for(int i =0;i<10;i++) {
			for(int j=0;j<10;j++) {
				board[i][j] = -1;
			}
		}
	}
	public static void showBoard(){
	       System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");
	       System.out.println();
	        
	       for(int row=0 ; row < 10 ; row++ ){
	           System.out.print((row+1)+"");
	           for(int column=0 ; column < 10 ; column++ ){
	               if(board[row][column]==-1){
	                   System.out.print("\t"+"~"); // ~ = eau  -1
	               }else if(board[row][column]==0){
	                   System.out.print("\t"+"*"); // * = tir tenté mais pas touché  0
	               }else if(board[row][column]==1){
	                   System.out.print("\t"+"X"); // X = touché  1
	               }
	               
	           }
	           System.out.println();
	       }
	}
	public static void placeShips() {
		ArrayList<Ship> ships = new ArrayList<Ship>();
		ships.add(new Contre_torpilleur());
		ships.add(new Croiseur());
		ships.add(new Porte_avion());
		ships.add(new Sous_marin());
		ships.add(new Torpilleur());
		Scanner read = new Scanner(System.in);
		int indice = 0;
		for (Ship myship : ships) {
			System.out.println("Je place mon "+ myship.name +" de taille "+ myship.size);
			for(int i=0; i < myship.size;i++) {
				System.out.println("Entrer une position du tableau (A1,B2,C3,...) : ");
				String position = read.next(); // Scans the next token of the input as an int.
				myship.position[indice]=position;
				indice++;
			}
			indice=0;
			myship.showPosition();
			
			/*Coordonnées coord = new Coordonnées(-1, -1);
			Coordonnées coord1 = coord.parsePoint(position);*/
			
		}
		read.close();
	}
	public static void main(String[] args) {
		initBoard();
		showBoard();
		placeShips();
		
	}
}
