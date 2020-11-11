package rayTracing.objects;

import rayTracing.tools.Ray;
import rayTracing.tools.VectorDouble;

public abstract class Hittable {

	public abstract HitRecord getHit();

	public abstract boolean hit(Ray r, double tmin, double tmax);
	
	public abstract VectorDouble getColor(HitRecord hit_record);
	
	public abstract double getVHitBox(VectorDouble pos);
}
