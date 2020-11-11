package rayTracing.objects;

import rayTracing.tools.Ray;
import rayTracing.tools.VectorDouble;

public class SphereHittable extends Hittable {
	
	public VectorDouble center;
	public double radius;
	private HitRecord hit_record;
	
	public SphereHittable() {
		center = new VectorDouble();
		radius = 0.0d;
		hit_record = new HitRecord();
	}
	
	public SphereHittable(VectorDouble origin, double rad) {
		center = origin;
		radius = rad;
		hit_record = new HitRecord();
	}

	/*
	 * (non-Javadoc)
	 * @see rayTracing.objects.Hittable#hit(rayTracing.tools.Ray, double, double)
	 * 
	 * Fonction permettant de déterminer si le rayon va toucher la sphère, et retourne vrai dans ce cas.
	 */
	@Override
	public boolean hit(Ray r, double tmin, double tmax) {
		HitRecord rec = new HitRecord();
		VectorDouble oc = r.getOrigin().sub(center);
		double a = r.getDirection().length_squared();
		double half_b = oc.dot(r.getDirection());
		double c = oc.length_squared() - radius*radius;
		double discriminant = half_b*half_b - a*c;
		if (discriminant > 0) {
			double root = Math.sqrt(discriminant);
			double temp = (-half_b - root)/a;
			if (temp < tmax && temp > tmin) {
				//System.out.println(tmin);
				rec.t = temp;
				rec.point = r.at(rec.t);
				VectorDouble outward_normal = (rec.point.sub(center)).div(radius);
				rec.setFaceNormal(r, outward_normal);
				if (rec.front_face) {
					hit_record = rec;
					return true;
				}
			}
			temp = (-half_b + root)/a;
			if (temp < tmax && temp > tmin) {
				rec.t = temp;
				rec.point = r.at(rec.t);
				VectorDouble outward_normal = (rec.point.sub(center)).div(radius);
				rec.setFaceNormal(r, outward_normal);
				if (rec.front_face) {
					hit_record = rec;
					return true;
				}
			}
		}
		
		return false;
	}

	@Override
	public HitRecord getHit() {
		return hit_record;
	}

	@Override
	public VectorDouble getColor(HitRecord rec) {
		return new VectorDouble((int) (160.0d*(0.5d*rec.normal.getX()+1.0d)), (int) (160.0d*(0.5d*rec.normal.getY()+1.0d)), (int) (160.0d*(0.5d*rec.normal.getZ()+1.0d)));
		
	}

	@Override
	public double getVHitBox(VectorDouble pos) {
		if (pos.getX() < center.getX() + radius + 0.2d && pos.getX() > center.getX() - radius - 0.2d && pos.getZ() < center.getZ() + radius + 0.2d && pos.getZ() > center.getZ() - radius - 0.2d && pos.getY() >= center.getY() + 1.0d) return center.getY() + radius + 1.0d;
		return -5.0d;
	}

}
