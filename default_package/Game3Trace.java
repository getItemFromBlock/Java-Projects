import java.util.LinkedList;
import java.util.List;

public class Game3Trace {

	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Float> rot = new LinkedList<Float>();
	
	public Game3Trace() {
		Xcoord.clear();
		Ycoord.clear();
		rot.clear();
	}
	
	public static void create(float x, float y, float t) {
		Xcoord.add(x);
		Ycoord.add(y);
		rot.add(t);
	}
	
	public void refresh() {
		Xcoord.clear();
		Ycoord.clear();
		rot.clear();
	}
	
	public static void remove(int i) {
		Xcoord.remove(i);
		Ycoord.remove(i);
		rot.remove(i);
	}
}
