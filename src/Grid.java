import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created on 25/01/2018.
 * this class is the logical grid of the game
 * stock the attack and is a graphical representation
 */
public class Grid {

    //empty grid of water
    public String grid[][]={
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"}}; 
    
    // grid of shots fired
    public String attackGrid[][]={
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"},
            {"~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~", "~~"}}; 
    //"XX" if something touched
    //"X~" if water touched
    //"~~" is just water

    // List of 1 player'ships
    public ArrayList<Ship> pShips = new ArrayList<Ship>();
    
    // checks the grid and update ships' positions
    public void setPosition() {
    	
    	String[] rows = {"A","B","C","D","E","F","G","H","I","J"};
    	List<String> Ct = new ArrayList<>();
    	List<String> Cr = new ArrayList<>();
    	List<String> Pa = new ArrayList<>();
    	List<String> Sm = new ArrayList<>();
    	List<String> To = new ArrayList<>();
    	
    	for(int i = 0; i<10; i++) {
    		for(int j = 0; j<10; j++) {
    			if(grid[i][j].equals("Ct")) Ct.add(rows[i]+Integer.toString(j));
    			else if(grid[i][j].equals("Cr")) Cr.add(rows[i]+Integer.toString(j));
    			else if(grid[i][j].equals("Pa")) Pa.add(rows[i]+Integer.toString(j));
    			else if(grid[i][j].equals("Sm")) Sm.add(rows[i]+Integer.toString(j));
    			else if(grid[i][j].equals("To")) To.add(rows[i]+Integer.toString(j));
    		}
    	}
    	for(Ship ship : pShips) {
    		if(isDead(ship.name)) continue;
    		if(ship.name.equals("Ct")) {
    			for(int k = 0; k<ship.size;k++) {
    				ship.position[k] = Ct.get(k);
    			}
    		}
    		else if(ship.name.equals("Cr")) {
    			for(int k = 0; k<ship.size;k++) {
    				ship.position[k] = Cr.get(k);
    			}
    		}
    		else if(ship.name.equals("Pa")) {
    			for(int k = 0; k<ship.size;k++) {
    				ship.position[k] = Pa.get(k);
    			}
    		}
    		else if(ship.name.equals("Sm")) {
    			for(int k = 0; k<ship.size;k++) {
    				ship.position[k] = Sm.get(k);
    			}
    		}
    		else if(ship.name.equals("To")) {
    			for(int k = 0; k<ship.size;k++) {
    				ship.position[k] = To.get(k);
    			}
    		}
    	}
    }

    
    //This method allows a ship deplacement and interact with the user
    public void moveShip(){

        
    	@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
    	String answer;
    	Ship movingShip;
    	String[] moves = new String[2];
    	int[] mvt = new int[2];
    	Coordonnees coord = new Coordonnees();
    	boolean direction;
    	boolean result = true;
    	
    	
    	do {
        	System.out.println("\n Do you want to move a ship ? (Y/N) : \n");
        	answer = read.next();
        }while( !answer.toUpperCase().equals("Y") &&  !answer.toUpperCase().equals("N"));
    	
    	if(answer.toUpperCase().equals("Y")){
    		do {
    			if(!result)System.out.println("\n You can't move there, try again ! \n");
    			do {
        			System.out.println("\n Select the ship you want to move (Ct, Cr, Pa, Sm or To) : \n");
            		answer = read.next();
            		if(isDead(answer)) {
            			answer = " ";
            			System.out.println("Your "+answer+" is dead... Try again !");
            		}
            	}while(!answer.toUpperCase().equals("CT") && !answer.toUpperCase().equals("CR") && !answer.toUpperCase().equals("PA") && !answer.toUpperCase().equals("SM") && !answer.toUpperCase().equals("TO") );        
        		int indice = 0;
            	while(!pShips.get(indice).name.toUpperCase().equals(answer.toUpperCase())) {
            		indice++;
            	}
            	movingShip = pShips.get(indice);
            	do {
            		System.out.println("\nYou can move your "+ movingShip.name +" up to 2 cases ! "
                			+ "Which direction do you chose ? (up, down, left or right) \n");
                	answer = read.next();
                }while( !answer.toUpperCase().equals("UP") &&  !answer.toUpperCase().equals("DOWN") && !answer.toUpperCase().equals("LEFT") &&  !answer.toUpperCase().equals("RIGHT"));
            	moves[0]=answer.toUpperCase();
            	do {       		
            		System.out.println("\nIf you want to move again, chose a 2nd direction (up, down, left or right) ! If not enter 'n' OR 'N' \n");
                	answer = read.next();
                }while(!answer.toUpperCase().equals("N") && !answer.toUpperCase().equals("UP") &&  !answer.toUpperCase().equals("DOWN") && !answer.toUpperCase().equals("LEFT") &&  !answer.toUpperCase().equals("RIGHT"));
            	if(!answer.toUpperCase().equals("N")) moves[1]=answer.toUpperCase();
            	else moves[1] = " ";
            	mvt[0] = 0;
            	mvt[1] = 0;
            	
            	
            	
            	if(moves[0].equals("UP")) {
            		mvt[0]=-1;
            		if(moves[1].equals("UP")) {
                		mvt[0] = -2;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 0;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = -1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = -1;
                		mvt[1] = -1;
                	}
            	}
            	else if(moves[0].equals("DOWN")){
            		mvt[0]=1;
            		if(moves[1].equals("UP")) {
            			mvt[0] = 0;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 2;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = 1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = 1;
                		mvt[1] = -1;
                	}
            	}
            	else if(moves[0].equals("RIGHT")) {
            		mvt[1]=1;
            		if(moves[1].equals("UP")) {
            			mvt[0] = -1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = 0;
                		mvt[1] = 2;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = 0;
                		mvt[1] = 0;
                	}
            	}
            	else if(moves[0].equals("LEFT")) {
            		mvt[1]=-1;
            		if(moves[1].equals("UP")) {
            			mvt[0] = -1;
                		mvt[1] = -1;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 1;
                		mvt[1] = -1;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = 0;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = 0;
                		mvt[1] = -2;
                	}
            	}
            	ArrayList<Integer> ligne = new ArrayList<>();
            	ArrayList<Integer> colonne = new ArrayList<>();
	        	for(int m = 0; m<movingShip.size; m++) {
					coord.parsePoint(movingShip.position[m]);
					ligne.add(coord.getX());
					colonne.add(coord.getY());
					
				}
				if(ligne.get(0) == ligne.get(1)) direction = true;
				else direction = false;
				int minL = ligne.indexOf(Collections.min(ligne));
				int minC = colonne.indexOf(Collections.min(colonne));
	            coord.setX(ligne.get(minL) + mvt[0]);
	            coord.setY(colonne.get(minC) + mvt[1]);
	            result = checkSpace(coord, movingShip.size, direction, movingShip);
    		}while(!result);
    		
    		System.out.println("Your "+movingShip.name + " moved "+ moves[0] + " "+ moves[1]);
    		
    		eraseShip(movingShip);
    		setShip(movingShip, coord, movingShip.size, direction);
        	printGird();
    	}	
    }



    // Checks if a ship is dead using its name
    public boolean isDead(String name) {
		for(int i = 0;i<10;i++) {
			for( int j = 0;j<10;j++) {
				if(grid[i][j].toUpperCase().equals(name.toUpperCase())) return false;
			}
		}
		return true;
	}


	//this method set specific Ship like child of Ship on the gird
    //coordonnees is the coo of the HEAD of the ship and horizontal the orientation
    public void setShip(Ship ship, Coordonnees coordonnees, int size, Boolean horizontal){

        int i=0;
        int x = coordonnees.getX();
        int y = coordonnees.getY();
        String name = ship.name;

        if(horizontal){
            while(i < size){

                grid[x][y]=name;
                y++;
                i++;

            }

        }else{
            while(i < size){

                grid[x][y]=name;
                x++;
                i++;

            }
        }

    }


    //Place the ship on the grid
    //with the correct name
    public void setShipTest(Ship ship) {
    	Coordonnees coord = new Coordonnees();
    	
    	for(int i = 0; i<ship.size; i++) {
    		coord.parsePointTest(ship.position[i]);
    		 grid[coord.getX()][coord.getY()]=ship.name;
    	}
    }
    
    
    //Erase ship
    //make a logical suppression of the ship on the interface
    public void eraseShip(Ship ship) {
    	Coordonnees coord = new Coordonnees();
    	
    	for(int i = 0; i<ship.size; i++) {
    		coord.parsePointTest(ship.position[i]);
    		 grid[coord.getX()][coord.getY()]="~~";
    	}
    }

    //check if there are any space (out of bounder OR an other boat on the same gird)
    //coordonnees is the coo of the HEAD of the ship
    // and horizontal the orientation
    //and size the length of the ship
    public boolean checkSpace(Coordonnees coordonnees, int size, Boolean horizontal, Ship ship){

        int i=0;
        int x = coordonnees.getX();
        int y = coordonnees.getY();

        if(horizontal){
            if((y+size-1)>9 || x<0 || y>9 || y<0 || x>9) {
            	return false;
            }
                
            while(i < size){

                if(! (grid[x][y].equals("~~"))) {
                	if(!grid[x][y].equals(ship.name)) {
                    	return false;
                    }
                }
                
                    
                y++;
                i++;
            }

        }else{
            if((x+size-1)>9 || x<0 || y>9 || y<0 || x>9)
                return false;
            while(i < size){
            	if(! (grid[x][y].equals("~~"))) {
                	if(!grid[x][y].equals(ship.name)) {
                    	return false;
                    }
                }
                x++;
                i++;

            }
        }
        return true;
    }
    
    
    // Check user's input while he places his ships
    public boolean checkPlacement(Ship ship, ArrayList<Ship> ships) {
		
		boolean goodPlacement = false;
		List<String> letters = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
			
			String[] pos = ship.position;
			int index = 0;
			while (index < ship.size) {
				
				if (pos[index].length()!=2 || !letters.contains(pos[index].substring(0, 1))) {
					System.out.println("Invalid coordinate, try again ! \n");
					return goodPlacement;
				}
				try {
					int test = Integer.parseInt(pos[index].substring(1));
					if (test > 9 || test < 0) {
						System.out.println("Invalid coordinate, try again ! \n");
						return goodPlacement;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid coordinate, try again ! \n");
					return goodPlacement;
				}
				index++;
			}
			for( int i = 0; i<ships.size();i++) {
				try {
					ships.get(i).position[0].equals("");
				}
				catch (Exception e) {
					continue;
				}
				if(ships.get(i).name.equals(ship.name)) {
					continue;
				}				
				else {
					for(int j = 0; j<ships.get(i).size;j++) {
						for(int k=0; k<ship.size; k++) {
							//System.out.println("j : "+j);
							if(ships.get(i).position[j].equals(ship.position[k])) {
								System.out.println("There is already a boat here, try again ! \n");
								return goodPlacement;
							}
						}
					}
						
					
				}
			}
			int[] ligne = new int[ship.size];
			int[] colonne = new int [ship.size];
			String choix = " ";
			Coordonnees coord = new Coordonnees();
			for(int m = 0; m<ship.size; m++) {
				coord.parsePoint(ship.position[m]);
				ligne[m] = coord.getX();
				colonne[m] = coord.getY();
				
			}
			if(ligne[0] == ligne[1]) choix = "ligne";
			if (colonne[0] == colonne[1]) choix = "colonne";
			if(choix.equals(" ")) {
				System.out.println("Position your boat on a line or a column, try again ! \n");
				return goodPlacement;	
			}
			for(int n=1; n <ship.size; n++) {
				if(choix.equals("ligne") && (ligne[n] != ligne[0] || colonne[n] != colonne[n-1]+1)) {
					System.out.println("Position your boat on a line, try again ! \n");
					return goodPlacement;
				}
				if(choix.equals("colonne") && (colonne[n] != colonne[0] || ligne[n] != ligne[n-1]+1)) {
					System.out.println("Position yout boat on a column, try again ! \n");
					return goodPlacement;
				}
					
				}
		goodPlacement = true;
		return goodPlacement;
	}


	
    //This method check if one boat is dead
    public boolean checkDead(){
    	
    	for (Ship myship : pShips) {
            if(myship.lives>=2){
                return true;
            }
        }
    	return false;
    }
    
    
   
    
    
    
    
	//This method check the proximity between a ship and it target 
    //return true if the target is in the corect range
    //return false if the target is out of the range
	public boolean checkProximity(Coordonnees coordonnees, String ship){
		
		
		Contre_torpilleur ct = new Contre_torpilleur();
		Croiseur cr = new Croiseur();
		Porte_avion pa = new Porte_avion();
		Sous_marin sm = new Sous_marin();
		Torpilleur to = new Torpilleur();

		
		String shipName = "";
		int range = 0;
		
		if(ship.equalsIgnoreCase(ct.name)){
			shipName = ct.name;
			range = ct.range;
		}else if(ship.equalsIgnoreCase(cr.name)){
			shipName = cr.name;
			range = cr.range;
		}else if(ship.equalsIgnoreCase(pa.name)){
			shipName = pa.name;
			range = pa.range;
		}else if(ship.equalsIgnoreCase(sm.name)){
			shipName = sm.name;
			range = sm.range;
		}else if(ship.equalsIgnoreCase(to.name)){
			shipName = to.name;
			range = to.range;
		}
	
		
		int i = 0;
		
		//init:
		int x = coordonnees.getX();
		int y = coordonnees.getY();
		if((x>=0 && x<=9)&&(y>=0 && y<=9)){
			//on the gird
			if(grid[x][y].equals("XX")){
				return false;//still touch
			}
		}
		
		
		
		
		//check on LEFT
		x = coordonnees.getX();
		y = coordonnees.getY();
		while(i< range+1){
			
			
			if((x>=0 && x<=9)&&(y>=0 && y<=9)){
				//on the gird
				
				if(grid[x][y].equals(shipName)){
					return true;//on a ship
				}else{
					x--;//left
				}
			}
			
			i++;
		}
		
		
		//check on RIGHT
		x = coordonnees.getX();
		y = coordonnees.getY();
		i = 0;
		while(i< range+1){
			
			
			if((x>=0 && x<=9)&&(y>=0 && y<=9)){
				//on the gird
				
				if(grid[x][y].equals(shipName)){
					return true;//on a ship
				}else{
					x++;//left
				}
			}
			
			i++;
		}
		
		//check on the UP
		x = coordonnees.getX();
		y = coordonnees.getY();
		i = 0;
		while(i< range+1){
			
			
			if((x>=0 && x<=9)&&(y>=0 && y<=9)){
				//on the gird
				
				if(grid[x][y].equals(shipName)){
					return true;//on a ship
				}else{
					y--;//left
				}
			}
			
			i++;
		}
		
		//check DOWN
		x = coordonnees.getX();
		y = coordonnees.getY();
		i = 0;
		while(i< range+1){
			
			
			if((x>=0 && x<=9)&&(y>=0 && y<=9)){
				//on the gird
				
				if(grid[x][y].equals(shipName)){
					return true;//on a ship
				}else{
					y++;//left
				}
			}
			
			i++;
		}
		
		
		return false;
	}

	
	//This method check if the ship is available for an attack
	 public boolean isAlive(String ship){
		 
		 for(int i = 0; i<10; i++)
	        {
	            for(int j = 0; j<10; j++)
	            {
	                if(grid[i][j].equalsIgnoreCase(ship)){
	                	return true;
	                }
	            }
	    	
	        }
		 return false;
	 }


    /*this method can be used for sending an attack from girdAttack to girdDef
   * it return true if the target is a ship of girdDef and false if is water
   * it increment the number of lives for each ship type and print the target on the girdAttack:
   *
   * 'XX' is print if the target touched a ship
   * 'X~' is print if the target is water
   *
   */
    public boolean target(Coordonnees coordonnees, Grid girdTarget){

        if(girdTarget.grid[coordonnees.getX()][coordonnees.getY()].equals("Ct")){
        	girdTarget.pShips.get(0).touched();
            girdTarget.attackGrid[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.grid[coordonnees.getX()][coordonnees.getY()].equals("Cr")){
        	girdTarget.pShips.get(1).touched();
            girdTarget.attackGrid[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.grid[coordonnees.getX()][coordonnees.getY()].equals("Pa")){
        	girdTarget.pShips.get(2).touched();
            girdTarget.attackGrid[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.grid[coordonnees.getX()][coordonnees.getY()].equals("Sm")){
        	girdTarget.pShips.get(3).touched();
            girdTarget.attackGrid[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.grid[coordonnees.getX()][coordonnees.getY()].equals("To")){
        	girdTarget.pShips.get(4).touched();
            girdTarget.attackGrid[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else{
        	girdTarget.attackGrid[coordonnees.getX()][coordonnees.getY()]="X~";
            
        }

        return false;

    }
    
    
    
    
    //this method return the range of the ship in parameter
   public int searchRange(String ship){
		
		
		Contre_torpilleur ct = new Contre_torpilleur();
		Croiseur cr = new Croiseur();
		Porte_avion pa = new Porte_avion();
		Sous_marin sm = new Sous_marin();
		Torpilleur to = new Torpilleur();
		
		
		
		int range = 0;
		
		if(ship.equalsIgnoreCase(ct.name)){
			
			range = ct.range;
		}else if(ship.equalsIgnoreCase(cr.name)){
			
			range = cr.range;
		}else if(ship.equalsIgnoreCase(pa.name)){
			
			range = pa.range;
		}else if(ship.equalsIgnoreCase(sm.name)){
			
			range = sm.range;
		}else if(ship.equalsIgnoreCase(to.name)){
			
			range = to.range;
		}
		
		return range;
}
	








    //this method can print the current gird
    public void printGird(){
    	String[] rows = {"A","B","C","D","E","F","G","H","I","J"};
    	System.out.println("\t0 \t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9");
	    System.out.println();

        for(int i = 0; i<10; i++)
        {
        	System.out.print(rows[i]+" ");
            for(int j = 0; j<10; j++)
            {
                System.out.print("\t"+ grid[i][j]);
            }
            System.out.println();

        }
    }
    //this method can print the current attack gird
    public void printAttackGird(){
    	String[] rows = {"A","B","C","D","E","F","G","H","I","J"};
    	System.out.println("\t0 \t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9");
	    System.out.println();

        for(int i = 0; i<10; i++)
        {
        	System.out.print(rows[i]+" ");
            for(int j = 0; j<10; j++)
            {
                System.out.print("\t"+ attackGrid[i][j]);
            }
            System.out.println();

        }
    }

    //this method moves a ship randomly 
	public void moveRandomShip() {
		
		Random rdm = new Random();
		ArrayList<String> listMove = new ArrayList<>();
		listMove.add("UP");
		listMove.add("DOWN");
		listMove.add("RIGHT");
		listMove.add("LEFT");
    	Ship movingShip;
    	String[] moves = new String[2];
    	int[] mvt = new int[2];
    	Coordonnees coord = new Coordonnees();
    	boolean direction;
    	
    	
    	
    	
    	if(Math.random() < 0.9){
    		do {
    			movingShip = pShips.get(rdm.nextInt(pShips.size()));
            	moves[0]=listMove.get(rdm.nextInt(listMove.size()));
            	listMove.add("N");
            	moves[1]=listMove.get(rdm.nextInt(listMove.size()));
            	
            	if(moves[0].equals("UP")) {
            		mvt[0]=-1;
            		if(moves[1].equals("UP")) {
                		mvt[0] = -2;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 0;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = -1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = -1;
                		mvt[1] = -1;
                	}
            	}
            	else if(moves[0].equals("DOWN")){
            		mvt[0]=1;
            		if(moves[1].equals("UP")) {
            			mvt[0] = 0;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 2;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = 1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = 1;
                		mvt[1] = -1;
                	}
            	}
            	else if(moves[0].equals("RIGHT")) {
            		mvt[1]=1;
            		if(moves[1].equals("UP")) {
            			mvt[0] = -1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 1;
                		mvt[1] = 1;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = 0;
                		mvt[1] = 2;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = 0;
                		mvt[1] = 0;
                	}
            	}
            	else if(moves[0].equals("LEFT")) {
            		mvt[1]=-1;
            		if(moves[1].equals("UP")) {
            			mvt[0] = -1;
                		mvt[1] = -1;
                	}
                	else if(moves[1].equals("DOWN")){
                		mvt[0] = 1;
                		mvt[1] = -1;
                	}
                	else if(moves[1].equals("RIGHT")) {
                		mvt[0] = 0;
                		mvt[1] = 0;
                	}
                	else if(moves[1].equals("LEFT")) {
                		mvt[0] = 0;
                		mvt[1] = -2;
                	}
            	}
            	// Checks if the ships can move there
            	ArrayList<Integer> ligne = new ArrayList<>();
            	ArrayList<Integer> colonne = new ArrayList<>();
	        	for(int m = 0; m<movingShip.size; m++) {
					coord.parsePoint(movingShip.position[m]);
					ligne.add(coord.getX());
					colonne.add(coord.getY());
					
				}
				if(ligne.get(0) == ligne.get(1)) direction = true;
				else direction = false;
				int minL = ligne.indexOf(Collections.min(ligne));
				int minC = colonne.indexOf(Collections.min(colonne));
	            coord.setX(ligne.get(minL) + mvt[0]);
	            coord.setY(colonne.get(minC) + mvt[1]);
    		}while(!checkSpace(coord, movingShip.size, direction, movingShip));
        	// moves the ship
    		for(int i  =0; i<movingShip.size; i++) {
        		coord.parsePoint(movingShip.position[i]);
        		grid[coord.getX()][coord.getY()]="~~";
        		grid[coord.getX()+mvt[0]][coord.getY()+mvt[1]]= movingShip.name;
        	}
    	}			
	}
}
