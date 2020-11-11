import java.util.LinkedList;
import java.util.List;

public class Game2Asteroid {

	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Float> rotation = new LinkedList<Float>();
	public static List<Float> velosX = new LinkedList<Float>();
	public static List<Float> velosY = new LinkedList<Float>();
	public static List<Float> spin = new LinkedList<Float>();
	public static int timer = 200;
	
	public Game2Asteroid() {
		Xcoord.clear();
		Ycoord.clear();
		rotation.clear();
		velosX.clear();
		velosY.clear();
		spin.clear();
	}
	
	public void create(float x, float y, float vx, float vy, float sp) {
		Xcoord.add(x);
		Ycoord.add(y);
		rotation.add((float) (Math.random()*360));
		spin.add(sp);
		velosX.add(vx);
		velosY.add(vy);
	}
	
	public void refresh() {
		for (int i = 0; i < Xcoord.size(); i++) {
			if (Xcoord.get(i) < -1024) {
				Xcoord.set(i, 1024.0f);
			}
			if (Xcoord.get(i) > 1024) {
				Xcoord.set(i, -1024.0f);
			}
			if (Ycoord.get(i) < -1024) {
				Ycoord.set(i, 1024.0f);
			}
			if (Ycoord.get(i) > 1024) {
				Ycoord.set(i, -1024.0f);
			}
			Xcoord.set(i, (Xcoord.get(i)+(velosX.get(i))));
			Ycoord.set(i, (Ycoord.get(i)-(velosY.get(i))));
			rotation.set(i, rotation.get(i)+spin.get(i));
			if (Game2Laser.laserTest(Xcoord.get(i), Ycoord.get(i))) {
				remove(i);
				Game2.score++;
			}
		}
		timer--;
		if (timer == 0) {
			if (Xcoord.size() < 50) {
				timer = (int) (100+Math.random()*200);
				float a = (float) (-500+Math.random()*1000);
				float b = (float) (-500+Math.random()*1000);
				create((a < Game2.posX-300 && a > Game2.posX+300) ? a : (float) (-500+Math.random()*1000),(b < Game2.posY-300 && b > Game2.posY+300) ? b : (float) (-500+Math.random()*1000), (float) (-2+Math.random()*4), (float) (-2+Math.random()*4),(float) (-2+Math.random()*4));
			}
		}
	}
	
	public static boolean asteroidTest(float x, float y) {
		for (int i = 0; i < Xcoord.size(); i++) {
			if (Xcoord.get(i) < x+24 && Xcoord.get(i) > x-40) {
				if (Ycoord.get(i) < y+24 && Ycoord.get(i) > y-40) {
					remove(i);
					return true;
				}
			}
		}
		return false;
	}
	
	public static void remove(int i) {
		Xcoord.remove(i);
		Ycoord.remove(i);
		rotation.remove(i);
		velosX.remove(i);
		velosY.remove(i);
		spin.remove(i);
	}
}
