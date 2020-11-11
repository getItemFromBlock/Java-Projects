package rayTracing.objects;

import rayTracing.tools.Ray;
import rayTracing.tools.VectorDouble;

public class HitRecord {
	
	public VectorDouble point;
	public VectorDouble normal;
	public double t;
	public boolean front_face;

	public HitRecord() {
		point = new VectorDouble();
		normal = new VectorDouble();
		t = 0.0d;
	}
	
	public HitRecord(VectorDouble pointT, VectorDouble normalT, double T) {
		point = pointT;
		normal = normalT;
		t = T;
	}
	
	public HitRecord(boolean front) {
		front_face = front;
	}
	
	public void setFaceNormal(Ray r, VectorDouble outward_normal) {
		front_face = r.getDirection().dot(outward_normal) < 0;
		normal = front_face ? outward_normal : outward_normal.mul(-1.0d);
	}

}
