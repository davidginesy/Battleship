
public class Ship {
	
	public int maxLives = 2;
	public int lives = 0;
	public String name;
	public int size;
	public String[] position = new String[5];
	
	public void showPosition() {
		System.out.println("mon "+name+" occupe les positions ");
		for(int i = 0;i<size;i++) {
			System.out.println(position[i] +" ");
		}
		
	}

	public void touched(){
		lives++;
	}
}
