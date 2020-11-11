import java.util.LinkedList;
import java.util.List;

public class Game3AI {

	public static List<Float> Xcoord = new LinkedList<Float>();
	public static List<Float> Ycoord = new LinkedList<Float>();
	public static List<Float> Xv = new LinkedList<Float>();
	public static List<Float> Yv = new LinkedList<Float>();
	public static List<Float> rot = new LinkedList<Float>();
	public static List<Integer> rot2 = new LinkedList<Integer>();
	public static List<Integer> rot3 = new LinkedList<Integer>();
	public static List<Integer> timer = new LinkedList<Integer>();
	public static List<Integer> shootTimer = new LinkedList<Integer>();
	public static List<Integer> type = new LinkedList<Integer>();
	public static List<Integer> tracetimer = new LinkedList<Integer>();
	public static float movspeed = 0.4f;
	
	public Game3AI() {
		Xcoord.clear();
		Ycoord.clear();
		Xv.clear();
		Yv.clear();
		timer.clear();
		shootTimer.clear();
		rot.clear();
		rot2.clear();
		rot3.clear();
		type.clear();
		tracetimer.clear();
	}
	
	public void create(float x, float y, int t) {
		Xcoord.add(x*24);
		Ycoord.add(y*24);
		Xv.add(0.0f);
		Yv.add(0.0f);
		type.add(t);
		timer.add(1);
		shootTimer.add(100);
		rot.add(0.0f);
		rot2.add(0);
		rot3.add(0);
		tracetimer.add(0);
	}
	
	public void refresh() {
		for (int n = 0; n < type.size(); n++) {
			if (type.get(n) != 0) {
				int d = dir(n);
				if (d == -1) {
					if (timer.get(n) == 1) {
						timer.set(n, 30+(int)(Math.random()*50));
						d = (int) (Math.random()*10);
						if (d == 8 || d == 9 || d == 10) {
							d = 8;
						}
					}
					timer.set(n, timer.get(n)-1);
				}
				rot2.set(n, d);
				if (rot2.get(n) != -1 && rot2.get(n) != 8) {
					rot3.set(n,rot2.get(n)*45);
				}
				if (rot2.get(n) == 2) {
					Xv.set(n, movspeed);
					Yv.set(n, 0.0f);
				}
				if (rot2.get(n) == 1 || rot2.get(n)== 3) {
					Xv.set(n, (float) Math.sqrt(movspeed*movspeed/2));
				}
				if (rot2.get(n) == 6) {
					Xv.set(n, -movspeed);
					Yv.set(n, 0.0f);
				}
				if (rot2.get(n) == 5 || rot2.get(n)== 7) {
					Xv.set(n, (float) -Math.sqrt(movspeed*movspeed/2));
				}
				if (rot2.get(n) == 0) {
					Yv.set(n, -movspeed);
					Xv.set(n, 0.0f);
				}
				if (rot2.get(n) == 7 || rot2.get(n)== 1) {
					Yv.set(n, (float) -Math.sqrt(movspeed*movspeed/2));
				}
				if (rot2.get(n) == 4) {
					Yv.set(n, movspeed);
					Xv.set(n, 0.0f);
				}
				if (rot2.get(n) == 3 || rot2.get(n)== 5) {
					Yv.set(n, (float) Math.sqrt(movspeed*movspeed/2));
				}
				if (rot2.get(n) == 8) {
					Yv.set(n, 0.0f);
					Xv.set(n, 0.0f);
				}
				if (Xv.get(n) != 0.0f || Yv.get(n) != 0.0f) {
					tracetimer.set(n,tracetimer.get(n)-1);
					if (tracetimer.get(n) < 0) {
						tracetimer.set(n, 10);
						Game3Trace.create(Xcoord.get(n), Ycoord.get(n), rot3.get(n));
					}
				}
				Xcoord.set(n, Xcoord.get(n)+Xv.get(n));
				Ycoord.set(n, Ycoord.get(n)+Yv.get(n));
			}
			float a = Game3.posX-Xcoord.get(n);
			float b = Game3.posY-Ycoord.get(n);
			if (a < 0) {
				rot.set(n, 270.0f + (float) Math.toDegrees(Math.atan(b/a)));
			}
			if (a > 0) {
				rot.set(n, 90.0f + (float) Math.toDegrees(Math.atan(b/a)));
			}
			if (!test(n)) {
				if (shootTimer.get(n) < 1) {
					shootTimer.set(n, 200+(int)(Math.random()*100));
					Game3.playFire();
					Game3Projectile.create(Xcoord.get(n), Ycoord.get(n), rot.get(n), 0);
				}
				shootTimer.set(n, shootTimer.get(n)-1);
			}
		}
	}
	
	public static void remove(int i) {
		Xcoord.remove(i);
		Ycoord.remove(i);
		Xv.remove(i);
		Yv.remove(i);
		rot.remove(i);
		rot2.remove(i);
		rot3.remove(i);
		type.remove(i);
		timer.remove(i);
		shootTimer.remove(i);
		tracetimer.remove(i);
	}
	public static int dir(int n) {
		for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
			if (Game3Projectile.Xcoord.get(i) > Xcoord.get(n)-50 && Game3Projectile.Xcoord.get(i) < Xcoord.get(n) && Game3Projectile.Ycoord.get(i) > Ycoord.get(n)-25 && Game3Projectile.Ycoord.get(i) < Ycoord.get(n)+25) {
				if (Game3Projectile.rotation.get(i) > 45 && Game3Projectile.rotation.get(i) < 135) {
					return 2;
				}
			}
		}
		for (int i = 0; i < Game3Bloc.type.size(); i++) {
			if (Game3Bloc.Xcoord.get(i)*24 > Xcoord.get(n)-20 && Game3Bloc.Xcoord.get(i)*24 < Xcoord.get(n) && Game3Bloc.Ycoord.get(i)*24 > Ycoord.get(n)-11 && Game3Bloc.Ycoord.get(i)*24 < Ycoord.get(n)+11) {
				if (Game3Bloc.type.get(i) != -1) {
					return 2;
				}
			}
		}
		for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
			if (Game3Projectile.Xcoord.get(i) > Xcoord.get(n) && Game3Projectile.Xcoord.get(i) < Xcoord.get(n)+50 && Game3Projectile.Ycoord.get(i) > Ycoord.get(n)-25 && Game3Projectile.Ycoord.get(i) < Ycoord.get(n)+25) {
				if (Game3Projectile.rotation.get(i) > 225 && Game3Projectile.rotation.get(i) < 315) {
					return 6;
				}
			}
		}
		for (int i = 0; i < Game3Bloc.type.size(); i++) {
			if (Game3Bloc.Xcoord.get(i)*24 > Xcoord.get(n) && Game3Bloc.Xcoord.get(i)*24 < Xcoord.get(n)+20 && Game3Bloc.Ycoord.get(i)*24 > Ycoord.get(n)-11 && Game3Bloc.Ycoord.get(i)*24 < Ycoord.get(n)+11) {
				if (Game3Bloc.type.get(i) != -1) {
					return 6;
				}
			}
		}
		for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
			if (Game3Projectile.Ycoord.get(i) > Ycoord.get(n)-50 && Game3Projectile.Ycoord.get(i) < Ycoord.get(n) && Game3Projectile.Xcoord.get(i) > Xcoord.get(n)-25 && Game3Projectile.Xcoord.get(i) < Xcoord.get(n)+25) {
				if (Game3Projectile.rotation.get(i) > 315 && Game3Projectile.rotation.get(i) < 45) {
					return 4;
				}
			}
		}
		for (int i = 0; i < Game3Bloc.type.size(); i++) {
			if (Game3Bloc.Ycoord.get(i)*24 > Ycoord.get(n)-20 && Game3Bloc.Ycoord.get(i)*24 < Ycoord.get(n) && Game3Bloc.Xcoord.get(i)*24 > Xcoord.get(n)-11 && Game3Bloc.Xcoord.get(i)*24 < Xcoord.get(n)+11) {
				if (Game3Bloc.type.get(i) != -1) {
					return 4;
				}
			}
		}
		for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
			if (Game3Projectile.Ycoord.get(i) >Ycoord.get(n) && Game3Projectile.Ycoord.get(i) < Ycoord.get(n)+50 && Game3Projectile.Xcoord.get(i) > Xcoord.get(n)-25 && Game3Projectile.Xcoord.get(i) < Xcoord.get(n)+25) {
				if (Game3Projectile.rotation.get(i) > 135 && Game3Projectile.rotation.get(i) < 225) {
					return 0;
				}
			}
		}
		for (int i = 0; i < Game3Bloc.type.size(); i++) {
			if (Game3Bloc.Ycoord.get(i)*24 > Ycoord.get(n) && Game3Bloc.Ycoord.get(i)*24 < Ycoord.get(n)+20 && Game3Bloc.Xcoord.get(i)*24 > Xcoord.get(n)-11 && Game3Bloc.Xcoord.get(i)*24 < Xcoord.get(n)+11) {
				if (Game3Bloc.type.get(i) != -1) {
					return 0;
				}
			}
		}
		return -1;
	}
	public static boolean test(int n) {
		for (int i = 0; i < Game3Projectile.rotation.size(); i++) {
			if (Game3Projectile.Xcoord.get(i) > Xcoord.get(n) - 12 && Game3Projectile.Xcoord.get(i) < Xcoord.get(n) + 12 && Game3Projectile.Ycoord.get(i) > Ycoord.get(n) - 12 && Game3Projectile.Ycoord.get(i) < Ycoord.get(n) + 12) {
				remove(n);
				Game3Projectile.remove(i);
				return true;
			}
		}
		return false;
	}
}
