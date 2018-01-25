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






    //this method can print the current gird
    public void printGird(){
        for(int i = 0; i<10; i++)
        {
            for(int j = 0; j<10; j++)
            {
                System.out.print(gird[i][j]+"  ");
            }
            System.out.println();

        }
    }




}
