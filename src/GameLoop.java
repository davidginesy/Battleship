import java.util.ArrayList;
import java.util.Scanner;

//import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by Etudes on 25/01/2018.
 */
public class GameLoop {


	public static ArrayList<Ship> p1Ships = new ArrayList<Ship>();
	public static boolean p1ShipsPlaced = false;

	public static ArrayList<Ship> p2Ships = new ArrayList<Ship>();

    //main activity of the game
    public static void main(String [ ] args)
    {
        boolean gameContinue = true;
        //Coordonnées object
        Coordonnees coordonnees = new Coordonnees();

        //player 1
        Gird gird1 = new Gird();
        
        gird1.printGird();
        p1Ships.add(new Contre_torpilleur());
        p1Ships.add(new Croiseur());
        p1Ships.add(new Porte_avion());
        p1Ships.add(new Sous_marin());
        p1Ships.add(new Torpilleur());
        System.out.println("You are about to position your ships on the grid. In order to position a ship, enter its coordinates one by one (a0,A9,B4,j9,...). \nThe order is IMPORTANT !"
        		+ "To position a 3-case long ship on the first line in the upper left of the grid, enter : a0, b0, c0 ! \nHave fun :D "
				+ " \n\n");
        Scanner read = new Scanner(System.in);
		
		for (Ship myship : p1Ships) {
			System.out.println("\nPosition your "+ myship.size +"-case long"+" "+ myship.name +"\n");
			do {
				
				for(int i=0; i < myship.size;i++) {
					System.out.println("Enter a coordinate on the grid (A0,B6,j9,...) : \n");
					String position = read.next(); // Scans the next token of the input as an int.
					myship.position[i]=position.toUpperCase();
				}
				p1ShipsPlaced = gird1.checkPlacement(myship, p1Ships);
			} while (p1ShipsPlaced == false);
			//myship.showPosition();
			gird1.setShipTest(myship);
			gird1.printGird();
			}
		read.close();
        
		/*
		Scanner read = new Scanner(System.in);
		System.out.println("You are about to position your ships on the grid. to position a ship enter a coordinate "
				+ "(A0,B6,j9,...) which is the head of your cheap. Then, when asked, enter a direction (horizontal or vertical).");
				
		for (Ship myship : p1Ships) {
			System.out.println("Position your "+ myship.size +"-box long"+" "+ myship.name +"\n");	
			
			do {
			System.out.println("Enter a box on the grid (A0,B6,j9,...) : \n");
			String position = read.next(); 
			myship.position=(String) position.toUpperCase();
			System.out.println("Enter a direction : horizontal or vertical (H/V) : \n");
			String direction = read.next(); 
			if(direction.toUpperCase().equals("H")) myship.direction=true;
			else if (direction.toUpperCase().equals("V")) myship.direction=false;
			coordonnees.parsePoint(myship.position);	
			
			}
			while(!coordonnees.isCorrect()&&gird1.checkSpace(coordonnees,myship.size,myship.direction));
			
			
			}
		read.close();
			*/
		

        

        //player 2
        Gird gird2 = new Gird();
        gird2.printGird();


        


        //Add ship example
        Porte_avion porte_avion = new Porte_avion();
        coordonnees.parsePoint("B5");
        System.out.println("X : "+coordonnees.getX());
        System.out.println("Y : "+coordonnees.getY());
        if(coordonnees.isCorrect()&&gird1.checkSpace(coordonnees,porte_avion.size,true))
            gird1.setShip(porte_avion,coordonnees,porte_avion.size,true);

        Torpilleur torpilleur = new Torpilleur();



        //print the gird
        gird1.printGird();






        //game initialisation : ask for the HEAD of each 5 ships and horizontal or vertical for player 1
        //random init for player 2
        //TODO

        //Game loop
        while(gameContinue){

            //TODO
            //ask for a gird number like 'B5' to player 1 and check if coordonnees.isCorrect()
            //use target method
            //if(target return true)
               //   play again
            //else
            //ask for a gird number like 'B5' to player 2 and check if coordonnees.isCorrect()
            //use target method
            //if(target return true)
            //   play again


            //chech ship.lives >= 2
            //if yes gameContinue = False
            //finish
            // ? who win
            // ? new game


        }





        //bout de test
        coordonnees.parsePoint("C7");
        System.out.println("space? "+gird1.checkSpace(coordonnees,1,false));


        System.out.println("X : "+coordonnees.getX());
        System.out.println("Y : "+coordonnees.getY());
        if(coordonnees.isCorrect())
            gird1.setShip(torpilleur,coordonnees,5,false);

        gird1.printGird();

    }

}
