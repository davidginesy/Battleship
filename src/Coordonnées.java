
public class Coordonn�es {
	
	public static Coordonn�es coordonn�es_invalides = new Coordonn�es(-1, -1);

    private final int X;
    private final int Y;

    public Coordonn�es (int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    public static Coordonn�es parsePoint(String coord) {
        coord = coord.toUpperCase();

        if (coord.length() < 3) {
            
            char letter = coord.charAt(0);
            String number = coord.substring(1);

            int x = (letter - 'A');
            try {
                int y = Integer.parseInt(number) - 1;
                return new Coordonn�es(x, y);
            }
            catch (NumberFormatException e) {
                return Coordonn�es.coordonn�es_invalides;
            }
        }
        else {
            return Coordonn�es.coordonn�es_invalides;
        }
}
}
