import java.util.ArrayList;
import java.util.Scanner;

//import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by Etudes on 25/01/2018.
 */
public class GameLoop {



	public static boolean p1ShipsPlaced = false;
	public static boolean p2ShipsPlaced = false;
	public static String nbplayers;

    //main activity of the game
    public static void main(String [ ] args)
    {
        boolean gameContinue = true;
        //Coordonnées object
        Coordonnees coordonnees = new Coordonnees();
        GameRules gameRules = new GameRules();
        Scanner read = new Scanner(System.in);
        //player 1
        //game initialisation : ask for player 1
        Gird gird1 = new Gird();
     
        
        gird1.pShips.add(new Contre_torpilleur());
        gird1.pShips.add(new Croiseur());
        gird1.pShips.add(new Porte_avion());
        gird1.pShips.add(new Sous_marin());
        gird1.pShips.add(new Torpilleur());
        System.out.println("\n WELCOME TO BATTLESHIP+");
        do {
        	System.out.println(" 1 OR 2 Players (1/2) : ");
        	nbplayers = read.next();
        	System.out.println("You choose "+nbplayers+ " player(s) \n");
        }while( !nbplayers.equals("1") && !nbplayers.equals("2"));
        
        System.out.println("You are about to position your ships on the grid. In order to position a ship, enter its coordinates one by one (a0,A9,B4,j9,...)."
        		+ " \nThe order is IMPORTANT !"
        		+ "To position a 3-case long ship on the first line in the upper left of the grid, enter : a0, b0, c0 ! \nHave fun :D "
				+ " \n\n");
        
        gird1.printGird();
		for (Ship myship : gird1.pShips) {
			
			do {
				System.out.println("\n Ready to position your "+ myship.size +"-case long"+" "+ myship.name +" !? \n");
				for(int i=0; i < myship.size;i++) {
					System.out.println("Enter the coordinate number "+i+" of your boat on the grid (A0,B6,j9,...) : \n");
					String position = read.next(); 
					myship.position[i]=position.toUpperCase();
				}
				p1ShipsPlaced = gird1.checkPlacement(myship, gird1.pShips);
			} while (p1ShipsPlaced == false);
			//myship.showPosition();
			gird1.setShipTest(myship);
			gird1.printGird();
			}
		
		System.out.println("\n You have successfully placed your ships !! \n");
		System.out.println("\n player's 2 turn ! \n ");
		
        //player 2
        Gird gird2 = new Gird();
        //print the gird

        gird2.pShips.add(new Contre_torpilleur());
        gird2.pShips.add(new Croiseur());
        gird2.pShips.add(new Porte_avion());
        gird2.pShips.add(new Sous_marin());
        gird2.pShips.add(new Torpilleur());
       
        if(nbplayers.equals("1")) {  //random init for player 2
        	boolean direction;
    		for (Ship myship : gird2.pShips) {					
    			do {
    				
    				double rdm =  Math.random();
    				if(rdm < 0.5) direction = true;
    				else direction = false;
    				coordonnees.setRandom();
    		
    				} while (!gird2.checkSpace(coordonnees, myship.size, direction));
    			gird2.setShip(myship, coordonnees,myship.size, direction);
    			}
        }
        else {
        	System.out.println("You are about to position your ships on the grid. In order to position a ship, enter its coordinates one by one (a0,A9,B4,j9,...)."
            		+ " \nThe order is IMPORTANT !"
            		+ "To position a 3-case long ship on the first line in the upper left of the grid, enter : a0, b0, c0 ! \nHave fun :D "
    				+ " \n\n");
            
        	gird2.printGird();
    		for (Ship myship : gird2.pShips) {
    			  			
				do {
    				System.out.println("\n Ready to position your "+ myship.size +"-case long"+" "+ myship.name +" !? \n");
    				for(int i=0; i < myship.size;i++) {
    					System.out.println("Enter the coordinate number "+i+" of your boat on the grid (A0,B6,j9,...) : \n");
    					String position = read.next(); 
    					myship.position[i]=position.toUpperCase();
    				}
    				p2ShipsPlaced = gird2.checkPlacement(myship, gird2.pShips);
    			} while (p2ShipsPlaced == false);
    			//myship.showPosition();
    			gird2.setShipTest(myship);
    			gird2.printGird();
    			}
    		
    		System.out.println("\n You have successfully placed your ships !! \n");
        }
        
		gird2.printGird();
        //TODO



        //Game loop
        String position = "A1";


        while(gameContinue){


            //ask for a gird number like 'B5' to player 1 and check if coordonnees.isCorrect()



            System.out.println("Enter a TARGET coordinate on the grid (A0,B6,j9,...) : \n");
            position = read.next();
            coordonnees.parsePoint(position);

            //first step
            while(!coordonnees.isCorrect() && !gird1.checkProximity(coordonnees)) {
                if(!gird1.checkProximity(coordonnees)){
                    gird1.printGird();
                    System.out.println("Your target have to be at 2 square from one of your boat, enter an new coordinate: \n");
                }

                if(!coordonnees.isCorrect())
                    System.out.println("Enter another correct target coordinate on the grid like A0,B6,j9,... : \n");

                position = read.next(); 
                coordonnees.parsePoint(position);
            }

            //use target method
            while(gird1.target(coordonnees,gird2)){
                System.out.println("Congratulation you touch something: \n");
                System.out.println("Play again, enter a target coordinate on the grid (A0,B6,j9,...) : \n");

                //play again
                while(!coordonnees.isCorrect() && !gird1.checkProximity(coordonnees)) {

                    if(!gird1.checkProximity(coordonnees)){
                        gird1.printGird();
                        System.out.println("Your target have to be at 2 square from one of your boat, enter an new coordinate: \n");
                    }

                    if(!coordonnees.isCorrect())
                        System.out.println("Enter another correct target coordinate on the grid like A0,B6,j9,... : \n");

                    position = read.next(); // Scans the next token of the input as an int.
                    coordonnees.parsePoint(position);
                }

            }

            //chech ship.lives >= 2
            for (Ship myship : gird2.pShips) {
                if(myship.lives>=2){
                    System.out.println("CONGRATULATION YOU WIN \n");
                    gameContinue = false;
                }

            }


            //virtual player 2 try and check if coordonnees.isCorrect()
            do{
                coordonnees.setRandom();

            }while (gird2.checkProximity(coordonnees));

            while(gird2.target(coordonnees,gird1)){

                do{
                    coordonnees.setRandom();

                }while (gird2.checkProximity(coordonnees));

            }


            //chech ship.lives >= 2
            for (Ship myship : gird1.pShips) {
                if(myship.lives>=2){
                    System.out.println("CONGRATULATION YOU WIN \n");
                    gameContinue = false;
                }

            }
            // ? who win
            // ? new game




        }
        read.close();





        //bout de test
        /*coordonnees.parsePoint("C7");
        System.out.println("space? "+gird1.checkSpace(coordonnees,1,false));

        //Add ship example
        Porte_avion porte_avion = new Porte_avion();
        coordonnees.parsePoint("B5");
        System.out.println("X : "+coordonnees.getX());
        System.out.println("Y : "+coordonnees.getY());
        if(coordonnees.isCorrect()&&gird1.checkSpace(coordonnees,porte_avion.size,true))
            gird1.setShip(porte_avion,coordonnees,porte_avion.size,true);

        Torpilleur torpilleur = new Torpilleur();



        System.out.println("X : "+coordonnees.getX());
        System.out.println("Y : "+coordonnees.getY());
        if(coordonnees.isCorrect())
            gird1.setShip(torpilleur,coordonnees,5,false);

        gird1.printGird();*/



    }

}
