import java.util.LinkedList;
import java.util.List;

public class Game3Projectile {

	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Integer> type = new LinkedList<Integer>();
	public static List<Float> rotation = new LinkedList<Float>();
	public static List<Integer> timer = new LinkedList<Integer>();
	
	public Game3Projectile() {
		Xcoord.clear();
		Ycoord.clear();
		rotation.clear();
		timer.clear();
		type.clear();
	}
	
	public static void create(float x, float y, float rot, int t) {
		Xcoord.add(x);
		Ycoord.add(y);
		rotation.add(rot);
		timer.add(0);
		type.add(t);
	}
	
	public boolean refresh() {
		for (int i = 0; i < Xcoord.size(); i++) {
			Xcoord.set(i, (float) (Xcoord.get(i)+(Math.sin(Math.toRadians(rotation.get(i))))*(type.get(i) == 0 ? 18 : 1)));
			Ycoord.set(i, (float) (Ycoord.get(i)-(Math.cos(Math.toRadians(rotation.get(i))))*(type.get(i) == 0 ? 18 : 1)));
			if (type.get(i) == 0) {
				type.set(i, 1);
			}
			if (Xcoord.get(i) > Game3.posX - 12 && Xcoord.get(i) < Game3.posX + 12 && Ycoord.get(i) > Game3.posY - 12 && Ycoord.get(i) < Game3.posY + 12) {
				remove(i);
				return true;
			}
			if (Xcoord.get(i) < -400 || Xcoord.get(i) > 400 || Ycoord.get(i) < -400 || Ycoord.get(i) > 400) {
				remove(i);
			}
			else if (timer.get(i) > 1) {
				remove(i);
			}
		}
		return false;
	}
	
	public static void remove(int i) {
		Xcoord.remove(i);
		Ycoord.remove(i);
		rotation.remove(i);
		timer.remove(i);
		type.remove(i);
	}
}
