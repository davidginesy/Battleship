/**
 * Created by Etudes on 25/01/2018.
 */
public class GameRules {


    /*this method can be used for sending an attack from girdAttack to girdDef
    * it return true if the target is a ship of girdDef and false if is water
    * it incremente the nulber of lives for each ship type and print the target on the girdAttack
    */
    public boolean target(Coordonnees coordonnees, Gird grirdAttck, Gird girdDef){

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




        if(girdDef.gird[coordonnees.getX()][coordonnees.getY()].equals(Ct)){
            contre_torpilleur.touched();
            grirdAttck.gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdDef.gird[coordonnees.getX()][coordonnees.getY()].equals(Cr)){
            croiseur.touched();
            grirdAttck.gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdDef.gird[coordonnees.getX()][coordonnees.getY()].equals(Pa)){
            porte_avion.touched();
            grirdAttck.gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdDef.gird[coordonnees.getX()][coordonnees.getY()].equals(Sm)){
            sous_marin.touched();
            grirdAttck.gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else if(girdDef.gird[coordonnees.getX()][coordonnees.getY()].equals(To)){
            torpilleur.touched();
            grirdAttck.gird[coordonnees.getX()][coordonnees.getY()]="XX";
            return true;

        }else{
            grirdAttck.gird[coordonnees.getX()][coordonnees.getY()]="X~";
        }

        return false;

    }

}
