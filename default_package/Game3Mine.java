import java.util.LinkedList;
import java.util.List;

public class Game3Mine {

	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Integer> timer = new LinkedList<Integer>();
	
	public Game3Mine() {
		Xcoord.clear();
		Ycoord.clear();
		timer.clear();
	}
	
	public void create(float x, float y, int t) {
		Xcoord.add(x);
		Ycoord.add(y);
		timer.add(t);
	}
	
	public void refresh() {
		for (int n = 0; n < timer.size(); n++) {
			timer.set(n, timer.get(n)+1);
			if (timer.get(n) > 500) {
				remove(n);
			}
			else {
				for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
					if (Game3Projectile.Xcoord.get(i) > Xcoord.get(n) - 12 && Game3Projectile.Xcoord.get(i) < Xcoord.get(n) + 12 && Game3Projectile.Ycoord.get(i) > Ycoord.get(n) - 12 && Game3Projectile.Ycoord.get(i) < Ycoord.get(n) + 12) {
						remove(n);
						break;
					}
				}
			}
		}
	}
	
	public static void remove(int n) {
		for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
			if (Game3Projectile.Xcoord.get(i) > Xcoord.get(n) - 40 && Game3Projectile.Xcoord.get(i) < Xcoord.get(n) + 40 && Game3Projectile.Ycoord.get(i) > Ycoord.get(n) - 40 && Game3Projectile.Ycoord.get(i) < Ycoord.get(n) + 40) {
				Game3Projectile.remove(i);
			}
		}
		for (int i = 0; i < Game3AI.rot.size(); i++) {
			if (Game3AI.Xcoord.get(i) > Xcoord.get(n) - 40 && Game3AI.Xcoord.get(i) < Xcoord.get(n) + 40 && Game3AI.Ycoord.get(i) > Ycoord.get(n) - 40 && Game3AI.Ycoord.get(i) < Ycoord.get(n) + 40) {
				Game3AI.remove(i);
			}
		}
		for (int i = 0; i < Game3Bloc.type.size(); i++) {
			if (Game3Bloc.Xcoord.get(i) > Xcoord.get(n) - 40 && Game3Bloc.Xcoord.get(i) < Xcoord.get(n) + 40 && Game3Bloc.Ycoord.get(i) > Ycoord.get(n) - 40 && Game3Bloc.Ycoord.get(i) < Ycoord.get(n) + 40) {
				if (Game3Bloc.type.get(i) == 1) {
					Game3Bloc.type.set(i, -1);
				}
			}
		}
		if (Game3.posX > Xcoord.get(n) - 40 && Game3.posX < Xcoord.get(n) + 40 && Game3.posY > Ycoord.get(n) - 40 && Game3.posY < Ycoord.get(n) + 40) {
			Game3.dead = true;
		}
		Game3Effect.create(Xcoord.get(n), Ycoord.get(n), 0);
		Game3.playExplode();
		Xcoord.remove(n);
		Ycoord.remove(n);
		timer.remove(n);
	}
}
