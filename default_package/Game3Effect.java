import java.util.LinkedList;
import java.util.List;

public class Game3Effect {

	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Integer> timer = new LinkedList<Integer>();
	public static List<Integer> type = new LinkedList<Integer>();
	
	public Game3Effect() {
		Xcoord.clear();
		Ycoord.clear();
		timer.clear();
		type.clear();
	}
	
	public static void create(float x, float y, int t) {
		Xcoord.add(x);
		Ycoord.add(y);
		timer.add(0);
		type.add(t);
	}
	
	public void refresh() {
		for (int n =0 ; n < timer.size(); n++) {
			if (type.get(n) == 0) {
				if (timer.get(n) == 0) {
					timer.set(n, 40);
				}
				if (timer.get(n) == 1) {
					remove(n);
				}
				else {
					timer.set(n, timer.get(n)-1);
				}
			}
		}
	}
	
	public static void remove(int i) {
		Xcoord.remove(i);
		Ycoord.remove(i);
		timer.remove(i);
		type.remove(i);
	}
}
