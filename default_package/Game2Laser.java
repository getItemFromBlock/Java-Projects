import java.util.LinkedList;
import java.util.List;

public class Game2Laser {
	
	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Integer> rotation = new LinkedList<Integer>();
	public static List<Integer> time = new LinkedList<Integer>();
	
	public Game2Laser() {
		Xcoord.clear();
		Ycoord.clear();
		rotation.clear();
		time.clear();
	}

	public void create(float x, float y, short rot) {
		Xcoord.add(x);
		Ycoord.add(y);
		rotation.add((int) rot);
		time.add(0);
	}
	
	public void refresh() {
		for (int i = 0; i < Xcoord.size(); i++) {
			Xcoord.set(i, (float) (Xcoord.get(i)+(Math.sin(Math.toRadians(rotation.get(i))))*3));
			Ycoord.set(i, (float) (Ycoord.get(i)-(Math.cos(Math.toRadians(rotation.get(i))))*3));
			time.set(i, time.get(i)+1);
			if (time.get(i) > 400) {
				remove(i);
			}
		}
	}
	
	public static void remove(int i) {
		time.remove(i);
		rotation.remove(i);
		Xcoord.remove(i);
		Ycoord.remove(i);
	}
	
	public static boolean laserTest(float x, float y) {
		for (int i = 0; i < Xcoord.size(); i++) {
			if (Xcoord.get(i) < x+48 && Xcoord.get(i) > x) {
				if (Ycoord.get(i) < y+48 && Ycoord.get(i) > y) {
					remove(i);
					return true;
				}
			}
		}
		return false;
	}
}
