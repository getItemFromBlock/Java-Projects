import java.util.LinkedList;
import java.util.List;

public class Game3Bloc {

	public static List<Integer> Xcoord = new LinkedList<Integer>();
	public static List<Integer> Ycoord = new LinkedList<Integer>();
	public static List<Integer> type = new LinkedList<Integer>();
	
	public Game3Bloc() {
		Xcoord.clear();
		Ycoord.clear();
		type.clear();
	}
	
	public void create(int x, int y, int t) {
		Xcoord.add(x);
		Ycoord.add(y);
		type.add(t);
	}
	
	public boolean refresh() {
		boolean bol = false;
		for (int i = 0; i < Xcoord.size(); i++) {
			if (type.get(i) != -1) {
				for (int n = 0; n < Game3Projectile.type.size(); n++) {
					if (type.get(i) == 0 || type.get(i) == 1) {
					if (Game3Projectile.Xcoord.get(n) < Xcoord.get(i)*24 + 12 && Game3Projectile.Xcoord.get(n) > Xcoord.get(i)*24 - 12) {
						if (Game3Projectile.Ycoord.get(n) < Ycoord.get(i)*24 -10 && Game3Projectile.Ycoord.get(n) > Ycoord.get(i)*24 - 12) {
							Game3Projectile.rotation.set(n, -Game3Projectile.rotation.get(n)+180);
							Game3Projectile.timer.set(n, Game3Projectile.timer.get(n)+1);
							bol = true;
						}
						if (Game3Projectile.Ycoord.get(n) < Ycoord.get(i)*24 +12 && Game3Projectile.Ycoord.get(n) > Ycoord.get(i)*24 + 10) {
							Game3Projectile.rotation.set(n, -Game3Projectile.rotation.get(n)+180);
							Game3Projectile.timer.set(n, Game3Projectile.timer.get(n)+1);
							bol = true;
						}
						if (Game3Projectile.Ycoord.get(n) < Ycoord.get(i)*24 +12 && Game3Projectile.Ycoord.get(n) > Ycoord.get(i)*24 - 12 && Game3Projectile.Xcoord.get(n) < Xcoord.get(i)*24 - 10) {
							Game3Projectile.rotation.set(n, -Game3Projectile.rotation.get(n));
							Game3Projectile.timer.set(n, Game3Projectile.timer.get(n)+1);
							bol = true;
						}
						if (Game3Projectile.Ycoord.get(n) < Ycoord.get(i)*24 +12 && Game3Projectile.Ycoord.get(n) > Ycoord.get(i)*24 - 12 && Game3Projectile.Xcoord.get(n) > Xcoord.get(i)*24 + 10) {
							Game3Projectile.rotation.set(n, -Game3Projectile.rotation.get(n));
							Game3Projectile.timer.set(n, Game3Projectile.timer.get(n)+1);
							bol = true;
						}
					}
				}
				}
				if (Game3.posX < Xcoord.get(i)*24 + 21 && Game3.posX > Xcoord.get(i)*24 - 21) {
					if (Game3.posY < Ycoord.get(i)*24 -18 && Game3.posY > Ycoord.get(i)*24 - 21) {
						Game3.posY = Game3.posY - Game3.velosY;
					}
					if (Game3.posY < Ycoord.get(i)*24 +21 && Game3.posY > Ycoord.get(i)*24 + 18) {
						Game3.posY = Game3.posY - Game3.velosY;
					}
					if (Game3.posY < Ycoord.get(i)*24 +21 && Game3.posY > Ycoord.get(i)*24 - 21 && Game3.posX < Xcoord.get(i)*24 - 18) {
						Game3.posX = Game3.posX - Game3.velosX;
					}
					if (Game3.posY < Ycoord.get(i)*24 +21 && Game3.posY > Ycoord.get(i)*24 - 21 && Game3.posX > Xcoord.get(i)*24 + 18) {
						Game3.posX = Game3.posX - Game3.velosX;
					}
				}
			}
		}
		return bol;
	}
	
	public static void remove(int i) {
		Xcoord.remove(i);
		Ycoord.remove(i);
		type.remove(i);
	}
}
