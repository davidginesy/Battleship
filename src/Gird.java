import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Etudes on 25/01/2018.
 */
public class Gird {

    //empty gird of water
    public String gird[][]={
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

    public ArrayList<Ship> pShips = new ArrayList<Ship>();

    public void moveShip(){

        //TODO
        //deplacer le bateau donné en parametre
    	Scanner read = new Scanner(System.in);
    	String answer;
    	Ship movingShip;
    	String[] moves = new String[2];
    	do {
        	System.out.println("\n Do you want to move a ship ? (Y/N) : \n");
        	answer = read.next();
        }while( !answer.toUpperCase().equals("Y") &&  !answer.toUpperCase().equals("N"));
    	
    	if(answer.toUpperCase().equals("Y")){
    		do {
    			System.out.println("\n Select the ship you want to move (Ct, Cr, Pa, Sm or To) : \n");
        		answer = read.next();
        	}while(!answer.toUpperCase().equals("CT") && !answer.toUpperCase().equals("CR") && !answer.toUpperCase().equals("PA") && !answer.toUpperCase().equals("SM") && !answer.toUpperCase().equals("TO"));        
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
        	moves[0]=answer;
        	do {       		
        		System.out.println("\nIf you want to move again, chose a 2nd direction (up, down, left or right) ! If not enter 'n' OR 'N' \n");
            	answer = read.next();
            }while(!answer.toUpperCase().equals("N") && !answer.toUpperCase().equals("UP") &&  !answer.toUpperCase().equals("DOWN") && !answer.toUpperCase().equals("LEFT") &&  !answer.toUpperCase().equals("RIGHT"));
        	if(!answer.toUpperCase().equals("N")) moves[1]=answer;
        	else moves[1] = " ";
        	System.out.println("move(s) chosen : "+ moves[0] + " "+ moves[1]);
        	Coordonnees coord = new Coordonnees();
        	
        	for(int i =0; i<movingShip.size; i++) {
        		coord.parsePoint(movingShip.position[i]);
        		gird[coord.getX()][coord.getY()]="~~";
        	}
        	printGird();
    	}
    	
    	}




    //this method set specific Ship like child of Ship on the gird
    //coordonnees is the coo of the HEAD of the ship and horizontal the orientation
    public void setShip(Ship ship, Coordonnees coordonnees, int size, Boolean horizontal){

        int i=0, j=0;
        int x = coordonnees.getX();
        int y = coordonnees.getY();
        String name = ship.name;

        if(horizontal){
            while(i < size){

                gird[x][y]=name;
                y++;
                i++;

            }

        }else{
            while(i < size){

                gird[x][y]=name;
                x++;
                i++;

            }
        }

    }


    
    public void setShipTest(Ship ship) {
    	Coordonnees coord = new Coordonnees();
    	
    	for(int i = 0; i<ship.size; i++) {
    		coord.parsePointTest(ship.position[i]);
    		//System.out.println("ligne : "+coord.getX() + " colonne : " + coord.getY());
    		 gird[coord.getX()][coord.getY()]=ship.name;
    	}
    }

    //check if there are any space (out of bounder OR an other boat on the same gird)
    //coordonnees is the coo of the HEAD of the ship
    // and horizontal the orientation
    //and size the length of the ship
    public boolean checkSpace(Coordonnees coordonnees, int size, Boolean horizontal){

        int i=0, j=0;
        int x = coordonnees.getX();
        int y = coordonnees.getY();

        if(horizontal){
            if((y+size)>9)
                return false;
            while(i < size){

                if(! (gird[x][y].equals("~~")))
                    return false;
                y++;
                i++;
            }

        }else{
            if((x+size)>9)
                return false;
            while(i < size){

                if(! (gird[x][y].equals("~~")))
                    return false;
                x++;
                i++;

            }
        }


        return true;
    }
    
    // Cette methode teste si l'entree de l'utilisateur est correcte, si la place est libre et si le bateau est bien sur une ligne ou une colonne.
    public boolean checkPlacement(Ship ship, ArrayList<Ship> ships) {
		
		boolean goodPlacement = false;
		List<String> letters = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
			
			String[] pos = ship.position;
			int index = 0;
			// test le format de l'entr� de l'utilisateur
			while (index < ship.size) {
				
				if (pos[index].length()!=2 || !letters.contains(pos[index].substring(0, 1))) {
					System.out.println("Invalid coordinate, try again ! \n");
					return goodPlacement;
				}
				try {
					int test = Integer.parseInt(pos[index].substring(1));
					if (test > 9) {
						System.out.println("Invalid coordinate, try again ! \n");
						return goodPlacement;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid coordinate, try again ! \n");
					return goodPlacement;
				}
				index++;
			}
			// test si un bateau est deja sur une case rentr� par l'utilisateur
			for( int i = 0; i<ships.size();i++) {
				try {
					boolean test = ships.get(i).position[0].equals("");
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
			// test si le bateau est bien sur une ligne ou sur une colonne
			int[] ligne = new int[ship.size];
			int[] colonne = new int [ship.size];
			String choix = " ";
			Coordonnees coord = new Coordonnees();
			for(int m = 0; m<ship.size; m++) {
				coord.parsePoint(ship.position[m]);
				ligne[m] = coord.getY();
				colonne[m] = coord.getX();
				
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


	//this method check if the proximity of the parameter coordonnees respect the rules on the current gird
	public boolean checkProximity(Coordonnees coordonnees){

        if (!coordonnees.isCorrect()){
            return false;
        }

        int x = coordonnees.getX();
        int y = coordonnees.getY();
        int xp1, xp2, xm1, xm2;
        int yp1, yp2, ym1, ym2;

        if(x-1>=0)
            xm1 = x-1;
        else
            xm1 = x;

        if(x-2>=0)
            xm2 = x-2;
        else
            xm2 = x;


        if(y-1>=0)
            ym1 = y-1;
        else
            ym1 = y;

        if(y-2>=0)
            ym2 = y-2;
        else
            ym2 = y;



        if(x+1<=9)
            xp1 = x+1;
        else
            xp1 = x;

        if(x+2<=9)
            xp2 = x+2;
        else
            xp2 = x;


        if(y+1<=9)
            yp1 = y+1;
        else
            yp1 = y;

        if(y+2<=9)
            yp2 = y+2;
        else
            yp2 = y;


        if(! ((gird[x][y].equals("~~"))||(gird[x][y].equals("X~"))||(gird[x][y].equals("XX"))))
            return true;//on a ship



        if(! ((gird[xp1][y].equals("~~"))||(gird[xp1][y].equals("X~"))||(gird[xp1][y].equals("XX"))))
            return true;

        if(! ((gird[xp2][y].equals("~~"))||(gird[xp2][y].equals("X~"))||(gird[xp2][y].equals("XX"))))
            return true;

        if(! ((gird[xm1][y].equals("~~"))||(gird[xm1][y].equals("X~"))||(gird[xm1][y].equals("XX"))))
            return true;

        if(! ((gird[xm2][y].equals("~~"))||(gird[xm2][y].equals("X~"))||(gird[xm2][y].equals("XX"))))
            return true;


        if(! ((gird[x][yp1].equals("~~"))||(gird[x][yp1].equals("X~"))||(gird[x][yp1].equals("XX"))))
            return true;

        if(! ((gird[x][yp2].equals("~~"))||(gird[x][yp2].equals("X~"))||(gird[x][yp2].equals("XX"))))
            return true;

        if(! ((gird[x][ym1].equals("~~"))||(gird[x][ym1].equals("X~"))||(gird[x][ym1].equals("XX"))))
            return true;

        if(! ((gird[x][ym2].equals("~~"))||(gird[x][ym2].equals("X~"))||(gird[x][ym2].equals("XX"))))
            return true;


        return false;

    }



    /*this method can be used for sending an attack from girdAttack to girdDef
   * it return true if the target is a ship of girdDef and false if is water
   * it incremente the nulber of lives for each ship type and print the target on the girdAttack:
   *
   * 'XX' is print if the target touched a ship
   * 'X~' is print if the target is water
   *
   */
    public boolean target(Coordonnees coordonnees, Gird girdTarget){

        Contre_torpilleur contre_torpilleur = new Contre_torpilleur();
        String Ct = contre_torpilleur.name;

        Croiseur croiseur = new Croiseur();
        String Cr = croiseur.name;

        Porte_avion porte_avion = new Porte_avion();
        String Pa = porte_avion.name;

        Sous_marin sous_marin = new Sous_marin();
        String Sm = sous_marin.name;

        Torpilleur torpilleur = new Torpilleur();
        String To = torpilleur.name;




        if(girdTarget.gird[coordonnees.getX()][coordonnees.getY()].equals(Ct)){
            contre_torpilleur.touched();
            gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.gird[coordonnees.getX()][coordonnees.getY()].equals(Cr)){
            croiseur.touched();
            gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.gird[coordonnees.getX()][coordonnees.getY()].equals(Pa)){
            porte_avion.touched();
            gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.gird[coordonnees.getX()][coordonnees.getY()].equals(Sm)){
            sous_marin.touched();
            gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdTarget.gird[coordonnees.getX()][coordonnees.getY()].equals(To)){
            torpilleur.touched();
            gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else{
            gird[coordonnees.getX()][coordonnees.getY()]="X~";
        }

        return false;

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
                System.out.print("\t"+ gird[i][j]);
            }
            System.out.println();

        }
    }




}
