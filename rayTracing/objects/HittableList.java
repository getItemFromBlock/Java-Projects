package rayTracing.objects;

import java.util.ArrayList;

import rayTracing.tools.Ray;
import rayTracing.tools.VectorDouble;

public class HittableList {

	ArrayList<Hittable> o = new ArrayList<Hittable>();
	
	HitRecord hit;
	VectorDouble color;
	
	public HittableList() {
		o.clear();
	}
	
	public void add(Hittable object) {
		o.add(object);
	}
	
	public void clear() {
		o.clear();
	}
	
	public boolean hit(Ray r, double tmin, double tmax) {
		boolean hit_anything = false;
		double closest_so_far = tmax;
		
		for (int i = 0; i < o.size(); i++) {
			if (o.get(i).hit(r, tmin, closest_so_far)) {
				HitRecord temp = o.get(i).getHit();
				hit_anything = true;
				closest_so_far = temp.t;
				setHit(temp);
				setColor(o.get(i).getColor(hit));
			}
		}
		return hit_anything;
	}
	
	public void setHit(HitRecord value) {
		hit = value;
	}

	public HitRecord getHit() {
		return hit;
	}
	
	public VectorDouble getColor() {
		return color;
	}
	
	public void setColor(VectorDouble value) {
		color = value;
	}
	
	public double getVHitBox(VectorDouble pos) {
		double closest_so_far = -5.0d;
		
		for (int i = 0; i < o.size(); i++) {
			if (o.get(i).getVHitBox(pos) > closest_so_far && o.get(i).getVHitBox(pos) >= pos.getY()) {
				closest_so_far = o.get(i).getVHitBox(pos);
			}
		}
		return closest_so_far;
	}

}
