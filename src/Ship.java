
public class Ship {
	
	public int maxLives = 2;
	public int lives = 0;
	public String name;
	public int size;
	public String[] position = new String[5];
	public boolean direction;
	public int range;
	
	public void showPosition() {
		System.out.println("my "+name+" is on ");
		for(int i = 0;i<size;i++) {
			System.out.println(position[i] + "\t");
		}
		
	}
	

	public void touched(){
		lives++;
	}


	public int getMaxLives() {
		return maxLives;
	}


	public void setMaxLives(int maxLives) {
		this.maxLives = maxLives;
	}


	public int getLives() {
		return lives;
	}


	public void setLives(int lives) {
		this.lives = lives;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String[] getPosition() {
		return position;
	}


	public void setPosition(String[] position) {
		this.position = position;
	}


	public boolean isDirection() {
		return direction;
	}


	public void setDirection(boolean direction) {
		this.direction = direction;
	}


	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}
	
	
	//cette methode prend en parametre les coordonnées rentrée au format 'B4'
    //retourne les coordonées sous forme d'un objet ou -1, -1 si incorecte
    public boolean parseShip(String boat) {

        boat = boat.toUpperCase();
        int size = boat.length();

        if ( size == 2) {

            if(boat.equals("CT")||boat.equals("CR")||boat.equals("PA")||boat.equals("SM")||boat.equals("TO")){
            	return true;
            }

        }
        return false;
    }
    
    public String generateRandom(){
    	
    	int r = (int) (Math.random()*5);
        String name = new String [] {"CT","CR","PA","SM","TO"}[r];
        return name;
    	
    }

	
	
}
