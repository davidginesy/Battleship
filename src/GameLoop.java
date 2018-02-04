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
        Scanner read = new Scanner(System.in);
        //player 1
        //game initialisation : ask for player 1
        Grid grid1 = new Grid();
     
        
        
        grid1.pShips.add(new Contre_torpilleur());
        grid1.pShips.add(new Croiseur());
        grid1.pShips.add(new Porte_avion());
        grid1.pShips.add(new Sous_marin());
        grid1.pShips.add(new Torpilleur());
        System.out.println("\n WELCOME TO BATTLESHIP+");
        do {
        	System.out.println(" 1 OR 2 Players (1/2) : ");
        	nbplayers = read.next();
        	System.out.println("You choose "+nbplayers+ " player(s) \n");
        }while( !nbplayers.equals("1") && !nbplayers.equals("2"));
    	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Player 1 turn: ~~~~~~~~~~~~~~~~~~~ \n");

        System.out.println("You are about to position your ships on the grid. In order to position a ship, enter its coordinates one by one (a0,A9,B4,j9,...)."
        		+ " \nThe order is IMPORTANT !"
        		+ "To position a 3-case long ship on the first line in the upper left of the grid, enter : a0, a1, a2 ! \nHave fun :D "
				+ " \n\n");
        
        grid1.printGird();
		for (Ship myship : grid1.pShips) {
			
			do {
				System.out.println("\n Ready to position your "+ myship.size +"-case long"+" "+ myship.name +" !? \n");
				for(int i=0; i < myship.size;i++) {
					System.out.println("Enter the coordinate number "+i+" of your boat on the grid (A0,B6,j9,...) : \n");
					String position = read.next(); 
					myship.position[i]=position.toUpperCase();
				}
				p1ShipsPlaced = grid1.checkPlacement(myship, grid1.pShips);
			} while (p1ShipsPlaced == false);
			//myship.showPosition();
			grid1.setShipTest(myship);
			grid1.printGird();
			}
		
		System.out.println("\n You have successfully placed your ships !! \n");
		
        //player 2 initialisation: AI or Real player
        
		
        Grid grid2 = new Grid();
        

        grid2.pShips.add(new Contre_torpilleur());
        grid2.pShips.add(new Croiseur());
        grid2.pShips.add(new Porte_avion());
        grid2.pShips.add(new Sous_marin());
        grid2.pShips.add(new Torpilleur());
       
        if(nbplayers.equals("1")) {  //random init for player 2
        	boolean direction;
        	System.out.println("~~~~~~~~~~~~~~~~~~~ A Virtual player is playing ~~~~~~~~~~~~~~~~~~~ \n");
    		for (Ship myship : grid2.pShips) {					
    			do {
    				
    				double rdm =  Math.random();
    				if(rdm < 0.5) direction = true;
    				else direction = false;
    				coordonnees.setRandom();
    		
    				} while (!grid2.checkSpace(coordonnees, myship.size, direction, myship));
    			grid2.setShip(myship, coordonnees,myship.size, direction);
    			}
    		System.out.println("\n Ships placed ! \n");
        }
        else {
        	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Player 2 turn: ~~~~~~~~~~~~~~~~~~~ \n");

        	System.out.println("You are about to position your ships on the grid. In order to position a ship, enter its coordinates one by one (a0,A9,B4,j9,...)."
            		+ " \nThe order is IMPORTANT !"
            		+ "To position a 3-case long ship on the first line in the upper left of the grid, enter : a0, b0, c0 ! \nHave fun :D "
    				+ " \n\n");
            
        	grid2.printGird();
    		for (Ship myship : grid2.pShips) {
    			  			
				do {
    				System.out.println("\n Ready to position your "+ myship.size +"-case long"+" "+ myship.name +" !? \n");
    				for(int i=0; i < myship.size;i++) {
    					System.out.println("Enter the coordinate number "+i+" of your boat on the grid (A0,B6,j9,...) : \n");
    					String position = read.next(); 
    					myship.position[i]=position.toUpperCase();
    				}
    				p2ShipsPlaced = grid2.checkPlacement(myship, grid2.pShips);
    			} while (p2ShipsPlaced == false);
    			grid2.setShipTest(myship);
    			grid2.printGird();
    			}
    		
    		System.out.println("\n You have successfully placed your ships !! \n");
        }
        grid2.setPosition();
        
        
        //Game loop
        String position = "A1";
        String boat = "Ct";
        Ship ship = new Ship();
        boolean canPlayerMove = false;


        
        /******** GAME LOOP ********/
        
        /******** SOLO PLAYER ********/
        if(nbplayers.equals("1")){

        	//game loop solo player
        	while(gameContinue){

            	//first step
            	//ask for target type
            	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Your turn: ~~~~~~~~~~~~~~~~~~~ \n");
            	if(canPlayerMove) {
        			grid1.moveShip();
        			grid1.setPosition();
        			canPlayerMove= false;
        		}
            	do{
            		grid1.printGird();
            		System.out.println("~~~~~~~~~~~~~~~~~~~ SHOTS FIRED : ~~~~~~~~~~~~~~~~~~~  \n");
            		grid2.printAttackGird();
            		System.out.println("Enter a conform SHIP ATTACKER name like (Ct,Cr,Pa,Sm or To): \n");
                	boat= read.next();
            	}while(!(ship.parseShip(boat)&&grid1.isAlive(boat)));
            	
            	//ask for a gird number like 'B5' to player 1 and check if coordonnees.isCorrect()
            	do{
            		
            		 System.out.println("Enter a TARGET COORDINATE on the grid like (A0,B6,j9,...). Your "+boat+" has a range of " + grid1.searchRange(boat)+" \n");
                     position = read.next();
                     coordonnees.parsePoint(position);
            	}while(coordonnees.parsePoint(position) && !grid1.checkProximity(coordonnees,boat));
               

                //use target method: player 1 attack player 2
            	if(grid1.target(coordonnees,grid2)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : grid2.pShips) {
                    	
                        if(myship.lives>=2){
                        	grid2.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ YOU KILLED: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
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
            	
        	
            	else {
            		System.out.println("It was just... water \n");
            		canPlayerMove = true;
            	}
            	
            	if(gameContinue == false)
            		break;
              

                //virtual player 2 try and check if coordonnees.isCorrect()
                System.out.println("~~~~~~~~~~~~~~~~~~~ A Virtual player is playing ~~~~~~~~~~~~~~~~~~~ \n");
                if(canPlayerMove) {
                	System.out.println("Virtual player can move...");
                	grid2.moveRandomShip();
                	grid2.setPosition();
                	canPlayerMove = false;
                }
                
                String shipName = "";
                do{
                	//generate random 
                    coordonnees.setRandom();
                    shipName = ship.generateRandom();

                }while (grid2.checkProximity(coordonnees,shipName));
      
                //use target method
            	if(grid2.target(coordonnees,grid1)){
            		
            		System.out.println("Congratulations, Virtual player touched something \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : grid1.pShips) {
                    	
                        if(myship.lives>=2){
                        	grid1.eraseShip(myship);
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
            	else {
            		canPlayerMove = true;
            		System.out.println("Virtual player touched water \n");
            	}
            	System.out.println("~~~~~~~~~~~~~~~~~~~ SHOTS FIRED : ~~~~~~~~~~~~~~~~~~~  \n");
                grid1.printAttackGird();
       
                
        	
        	}	
        /******** DUAL PLAYER ********/		
        	
        }else{
        	
        	//game loop with 2 players
        	while(gameContinue){

            	//first step
            	//ask for target type like ct, cr...
            	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Player 1 turn: ~~~~~~~~~~~~~~~~~~~ \n");
            	if(canPlayerMove) {
            		grid1.printGird();
        			grid1.moveShip();
        			grid1.setPosition();
        			canPlayerMove= false;
        		}
            	do{
            		
            		grid1.printGird();
            		System.out.println("~~~~~~~~~~~~~~~~~~~ SHOTS FIRED : ~~~~~~~~~~~~~~~~~~~  \n");
            		grid2.printAttackGird();
            		System.out.println("Enter a conform SHIP ATTACKER name like (Ct,Cr,Pa,Sm or To): \n");
                	boat= read.next();
            	}while(!(ship.parseShip(boat)&&grid1.isAlive(boat)));
            	
            	//ask for a gird number like 'B5' to player 1 and check if coordonnees.isCorrect()
            	do{
            		
            		System.out.println("Enter a TARGET COORDINATE on the grid like (A0,B6,j9,...). Your "+boat+" has a range of " + grid1.searchRange(boat)+" \n");                     position = read.next();
                     coordonnees.parsePoint(position);
            	}while(coordonnees.parsePoint(position) && !grid1.checkProximity(coordonnees,boat));
               

            	//use target method by player 1 on player 2
            	if(grid1.target(coordonnees,grid2)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : grid2.pShips) {
                    	
                        if(myship.lives>=2){
                        	grid2.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ PLAYER 1: YOU KILLED: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
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
            	else {
            		System.out.println("It was just... water \n");
            		canPlayerMove = true;
            	}
                
          
            
                
                
                
                //player 2 turn
            	//ask for target type
            	if(gameContinue == false)
            		break;
            	
            	System.out.println(" ~~~~~~~~~~~~~~~~~~~ Player 2 turn: ~~~~~~~~~~~~~~~~~~~ \n");
            	if(canPlayerMove) {
            		grid2.printGird();
        			grid2.moveShip();
        			grid2.setPosition();
        			canPlayerMove= false;
        		}
            	System.out.println(" Player 2 grid: \n");
        		grid2.printGird();
        		System.out.println("~~~~~~~~~~~~~~~~~~~ SHOTS FIRED : ~~~~~~~~~~~~~~~~~~~  \n");
        		grid1.printAttackGird();
            	do{
            		
            		System.out.println("Player 2: enter a conform SHIP ATTACKER name like (Ct,Cr,Pa,Sm or To): \n");
                	boat= read.next();
            	}while(!(ship.parseShip(boat)&&grid2.isAlive(boat)));
            	
            	//ask for a gird number 
            	do{
            		
            		System.out.println("Enter a TARGET COORDINATE on the grid like (A0,B6,j9,...). Your "+boat+" has a range of " + grid1.searchRange(boat)+" \n");                     position = read.next();
                     coordonnees.parsePoint(position);
            	}while(coordonnees.parsePoint(position) && !grid2.checkProximity(coordonnees,boat));
               

                //use target method
            	if(grid2.target(coordonnees,grid1)){
            		
            		System.out.println("Congratulation you touch something: \n");
            		
            		//check ship.lives >= 2
                    int i = 0;
                    for (Ship myship : grid1.pShips) {
                    	
                        if(myship.lives>=2){
                        	grid1.eraseShip(myship);
                            System.out.println("~~~~~~~~~~~~~~~~~~~ PLAYER 2: YOU KILLED: "+ myship.name +" ~~~~~~~~~~~~~~~~~~~  \n");
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
            	else {
            		System.out.println("Player 2: It was just... water \n");
            		canPlayerMove = true;
            	}
            	
          
            }
        	
        }
         
        read.close();

    }

}
