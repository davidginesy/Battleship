import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    		 gird[coord.getY()][coord.getX()]=ship.name;
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
    
    // Cette methode teste si l'entré de l'utilisateur est correcte, si la place est libre et si le bateau est bien sur une ligne ou une colonne.
    public boolean checkPlacement(Ship ship, ArrayList<Ship> ships) {
		
		boolean goodPlacement = false;
		List<String> letters = Arrays.asList("A","B","C","D","E","F","G","H","I","J");
			
			String[] pos = ship.position;
			int index = 0;
			// test le format de l'entré de l'utilisateur
			while (index < ship.size) {
				
				if (pos[index].length()!=2 || !letters.contains(pos[index].substring(0, 1))) {
					System.out.println("Invalid coordinate, try again !");
					return goodPlacement;
				}
				try {
					int test = Integer.parseInt(pos[index].substring(1));
					if (test > 9) {
						System.out.println("Invalid coordinate, try again !");
						return goodPlacement;
					}
				} catch (NumberFormatException e) {
					System.out.println("Invalid coordinate, try again !");
					return goodPlacement;
				}
				index++;
			}
			// test si un bateau est deja sur une case rentré par l'utilisateur
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
								System.out.println("There is already a boat here !");
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
			if(choix.equals(" ")) return goodPlacement;	
			for(int n=1; n <ship.size; n++) {
				if(choix.equals("ligne") && (ligne[n] != ligne[0] || colonne[n] != colonne[n-1]+1)) {
					System.out.println("Position your boat on a line !");
					return goodPlacement;
				}
				if(choix.equals("colonne") && (colonne[n] != colonne[0] || ligne[n] != ligne[n-1]+1)) {
					System.out.println("Position yout boat on a coloumn");
					return goodPlacement;
				}
					
				}
		goodPlacement = true;
		return goodPlacement;
	}







    //this method can print the current gird
    public void printGird(){
    	System.out.println("\tA \tB \tC \tD \tE \tF \tG \tH \tI \tJ");
	    System.out.println();

        for(int i = 0; i<10; i++)
        {
        	System.out.print((i)+" ");
            for(int j = 0; j<10; j++)
            {
                System.out.print("\t"+ gird[i][j]);
            }
            System.out.println();

        }
    }




}
