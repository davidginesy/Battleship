
public class Coordonnées {
	
	public static Coordonnées coordonnées_invalides = new Coordonnées(-1, -1);

    private final int X;
    private final int Y;

    public Coordonnées (int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
    
    public static Coordonnées parsePoint(String coord) {
        coord = coord.toUpperCase();

        if (coord.length() < 3) {
            
            char letter = coord.charAt(0);
            String number = coord.substring(1);

            int x = (letter - 'A');
            try {
                int y = Integer.parseInt(number) - 1;
                return new Coordonnées(x, y);
            }
            catch (NumberFormatException e) {
                return Coordonnées.coordonnées_invalides;
            }
        }
        else {
            return Coordonnées.coordonnées_invalides;
        }
}
}
