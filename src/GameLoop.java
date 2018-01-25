import com.sun.xml.internal.bind.v2.TODO;

/**
 * Created by Etudes on 25/01/2018.
 */
public class GameLoop {




    //main activity of the game
    public static void main(String [ ] args)
    {
        boolean gameContinue = true;


        //player 1
        Gird gird1 = new Gird();
        gird1.printGird();


        //player 2
        Gird gird2 = new Gird();
        gird2.printGird();


        //Coordonnées object
        Coordonnees coordonnees = new Coordonnees();


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
