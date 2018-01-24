
public class Ship {
	
	public int lives = 2;
	public String name;
	public int size;
	public String[] position = new String[5];
	
	public void showPosition() {
		System.out.println("mon "+name+" occupe les positions ");
		for(int i = 0;i<size;i++) {
			System.out.println(position[i] +" ");
		}
		
	}
}
