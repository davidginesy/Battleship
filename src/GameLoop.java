import java.util.ArrayList;
import java.util.Scanner;



/**
 * Created by Etudes on 25/01/2018.
 * This class is the main loop of the game and interact with the user
 * 
 */
public class GameLoop {



	//ATTRIBUTES
	public static boolean p1ShipsPlaced = false;
	public static boolean p2ShipsPlaced = false;
	public static String nbplayers = "1";

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
        		+ "To position a 3-case long ship on the first line in the upper left of the grid, enter : a0, a1, a2 ! \nHave fun :D "
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
		
        //player 2
        System.out.println("\n player's 2 turn ! \n ");
		
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
    		
    				} while (!gird2.checkSpace(coordonnees, myship.size, direction, myship));
    			gird2.setShip(myship, coordonnees,myship.size, direction);
    			}
    		System.out.println("\n Sips placed !! \n");
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
        gird2.setPosition();
        
        /******************** Ce bout de code correspond au deplacement d'un bateau al�atoire ou non en fonction du nombre de joueur ************************/
        System.out.println("\n player's 1 turn ! \n");
		gird1.printGird();	
		gird1.moveShip();
		gird1.setPosition();
		
		System.out.println("\n player's 2 turn ! \n");
		if(nbplayers.equals("2")) {
			gird2.printGird();	
			gird2.moveShip(); //TEST
			gird2.setPosition();
		}
		else {
			gird2.printGird();	
			gird2.moveRandomShip();
			gird2.setPosition();
		}
		
		/******** FIN DU CODE POUR LE DEPLACEMENT********/

        //Game loop
        String position = "A1";
        String boat = "Ct";
        Ship ship = new Ship();


        
        /******** GAME LOOP ********/
        
        /******** SOLO PLAYER ********/
        if(nbplayers.equals("1")){

        	//game loop solo player
        	while(gameContinue){

            	//first step
            	//ask for target type
            	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Your turn: ~~~~~~~~~~~~~~~~~~~ \n");
            	do{
            		gird1.printGird();
            		System.out.println("Enter a conform SHIP ATTACKER name like (Ct,Cr,Pa,Sm or To): \n");
                	boat= read.next();
            	}while(! ship.parseShip(boat));
            	
            	//ask for a gird number like 'B5' to player 1 and check if coordonnees.isCorrect()
            	do{
            		gird1.printGird();
            		 System.out.println("Enter a TARGET COORDINATE on the grid at the max range from your attacker ship: - "+ gird1.searchRange(boat)+"- , like (A0,B6,j9,...) : \n");
                     position = read.next();
                     coordonnees.parsePoint(position);
            	}while(coordonnees.parsePoint(position) && !gird1.checkProximity(coordonnees,boat));
               

                //use target method: player 1 attack player 2
            	if(gird1.target(coordonnees,gird2)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : gird2.pShips) {
                    	
                        if(myship.lives>=2){
                        	gird2.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ YOU KILL: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
                            i++;
                        }
                        if(i>=5){
                        	System.out.println("~~~~~~~~~~~~~~~~~~~ CONGRATULATION YOU WIN ~~~~~~~~~~~~~~~~~~~  \n");
                            gameContinue = false;
                            break;
                        }
                    }
                    i = 0;
            		
            	}
                System.out.println("It was just... water \n");
                gird1.moveShip();
                gird1.setPosition();
              

                //virtual player 2 try and check if coordonnees.isCorrect()
                System.out.println("~~~~~~~~~~~~~~~~~~~ Virtual player is playing ~~~~~~~~~~~~~~~~~~~ \n");
                String shipName = "";
                do{
                	//generate random 
                    coordonnees.setRandom();
                    shipName = ship.generateRandom();

                }while (gird2.checkProximity(coordonnees,shipName));
      
                //use target method
            	if(gird2.target(coordonnees,gird1)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : gird1.pShips) {
                    	
                        if(myship.lives>=2){
                        	gird1.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ VIRTUAL PLAYER KILL: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
                            i++;
                        }
                        if(i>=5){
                        	System.out.println("~~~~~~~~~~~~~~~~~~~ VIRTUAL PLAYER WIN ~~~~~~~~~~~~~~~~~~~  \n");
                            gameContinue = false;
                            break;
                        }
                    }
                    i = 0;
            		
            	}
            	 System.out.println("Virtual player touch water \n");
                 gird2.moveRandomShip();
                 gird2.setPosition();
            }
        	
        	
        /******** DUAL PLAYER ********/		
        	
        }else{
        	
        	//game loop with 2 players
        	while(gameContinue){

            	//first step
            	//ask for target type like ct, cr...
            	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Player 1 turn: ~~~~~~~~~~~~~~~~~~~ \n");
            	do{
            		gird1.printGird();
            		System.out.println("Enter a conform SHIP ATTACKER name like (Ct,Cr,Pa,Sm or To): \n");
                	boat= read.next();
            	}while(! ship.parseShip(boat));
            	
            	//ask for a gird number like 'B5' to player 1 and check if coordonnees.isCorrect()
            	do{
            		 gird1.printGird();
            		 System.out.println("Enter a TARGET COORDINATE on the grid at the max range from your attacker ship: - "+ gird1.searchRange(boat)+"- , like (A0,B6,j9,...) : \n");
                     position = read.next();
                     coordonnees.parsePoint(position);
            	}while(coordonnees.parsePoint(position) && !gird1.checkProximity(coordonnees,boat));
               

            	//use target method by player 1 on player 2
            	if(gird1.target(coordonnees,gird2)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : gird2.pShips) {
                    	
                        if(myship.lives>=2){
                        	gird2.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ PLAYER 1: YOU KILL: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
                            i++;
                        }
                        if(i>=5){
                        	System.out.println("~~~~~~~~~~~~~~~~~~~ PLAYER 1: CONGRATULATION YOU WIN ~~~~~~~~~~~~~~~~~~~  \n");
                            gameContinue = false;
                            break;
                        }
                    }
                    i = 0;
            		
            	}
                System.out.println("It was just... water \n");
                gird1.moveShip();
                gird1.setPosition();
            
                
                
                
                //player 2 turn
            	//ask for target type
            	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Player 2 turn: ~~~~~~~~~~~~~~~~~~~ \n");
            	do{
            		System.out.println(" Player 2 grid: \n");
            		gird2.printGird();
            		System.out.println("Player 2: enter a conform SHIP ATTACKER name like (Ct,Cr,Pa,Sm or To): \n");
                	boat= read.next();
            	}while(! ship.parseShip(boat));
            	
            	//ask for a gird number 
            	do{
            		 System.out.println(" Player 2 grid: \n");
            		 gird2.printGird();
            		 System.out.println("Player 2: enter a TARGET COORDINATE on the grid at the max range from your attacker ship: - "+ gird1.searchRange(boat)+"- , like (A0,B6,j9,...) : \n");
                     position = read.next();
                     coordonnees.parsePoint(position);
            	}while(coordonnees.parsePoint(position) && !gird2.checkProximity(coordonnees,boat));
               

                //use target method
            	if(gird2.target(coordonnees,gird1)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : gird1.pShips) {
                    	
                        if(myship.lives>=2){
                        	gird1.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ PLAYER 2: YOU KILL: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
                            i++;
                        }
                        if(i>=5){
                        	System.out.println("~~~~~~~~~~~~~~~~~~~ PLAYER 2: CONGRATULATION YOU WIN ~~~~~~~~~~~~~~~~~~~  \n");
                            gameContinue = false;
                            break;
                        }
                    }
                    i = 0;
            		
            	}
            	System.out.println("Player 2: It was just... water \n");
                System.out.println(" Player 2 grid: \n");
        		gird2.printGird();
                gird2.moveShip();
                gird2.setPosition();
                System.out.println(" Player 2 grid: \n");
        		gird2.printGird();
          
            }
        	
        }
         
        read.close();

    }

}
