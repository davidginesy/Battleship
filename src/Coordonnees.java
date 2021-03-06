import java.util.Random;

public class Coordonnees {
	


	//from 0 to 9
    private int X = -1;
    private int Y = -1;
    private boolean correct = false;



    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    public void setX(int x) {
    	X=x;
    }
    public void setY(int y) {
    	Y=y;
    }
    


    public boolean isCorrect(){
        return correct;
    }
    
    //generate randoms between 0 and 9
    public void setRandom(){

        Random rand1 = new Random();
        Random rand2 = new Random();
        X = rand1.nextInt(10);
        Y = rand2.nextInt(10);

    }

    //cette methode prend en parametre les coordonnées rentrée au format 'B4'
    //retourne les coordonées sous forme d'un objet
    public boolean parsePoint(String coord) {

        coord = coord.toUpperCase();
        int size = coord.length();

        if ( size == 2) {

            int x = -1;
            char letter = coord.charAt(0);
            String number = coord.substring(1);

            if((letter>='A'&&letter<='J')){

                x = (letter - 'A');

            }else{
                return false;
            }

            try {
                int y = Integer.parseInt(number);
                X = x;
                Y = y;
            }
            catch (NumberFormatException e) {
                return false;
            }

        }else{
            return false;
        }


        if(X>-1 && X<=9 && Y>-1 && Y<=9){
        	
            return true;
        }

        return false;
    }


    // Transform string into coordinate
    public void parsePointTest(String coord) {
        
            
            char letter = coord.charAt(0);
            String number = coord.substring(1);
            X = (letter - 'A');           
            Y = Integer.parseInt(number);
    }



}
