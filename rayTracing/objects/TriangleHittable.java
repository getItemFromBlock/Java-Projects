package rayTracing.objects;

import rayTracing.tools.Ray;
import rayTracing.tools.VectorDouble;

public class TriangleHittable extends Hittable {
	
	private HitRecord record;
	private VectorDouble A;
	private VectorDouble B;
	private VectorDouble C;
	private VectorDouble normal;
	private VectorDouble sign;
	private double origin;
	
	public TriangleHittable() {
		record = new HitRecord();
		A = new VectorDouble();
		B = new VectorDouble();
		C = new VectorDouble();
	}
	
	public TriangleHittable(VectorDouble Pa, VectorDouble Pb, VectorDouble Pc) {
		origin = Pa.getY();
		record = new HitRecord();
		A = new VectorDouble(Pa.getX(), Pa.getY(), Pa.getZ());
		B = new VectorDouble(Pb.getX(), Pb.getY(), Pb.getZ());
		C = new VectorDouble(Pc.getX(), Pc.getY(), Pc.getZ());
		VectorDouble AB = B.sub(A);
		VectorDouble AC = C.sub(A);
		sign = AB.cross(AC);
		sign = new VectorDouble(sign.getX() > 0 ? -1.0d : 1.0d, sign.getY() > 0 ? -1.0d : 1.0d, sign.getZ() < 0 ? -1.0d : 1.0d);
		A = A.mul(sign);
		B = B.mul(sign);
		C = C.mul(sign);
		AB = B.sub(A);
		AC = C.sub(A);
		normal = AB.cross(AC);
	}

	@Override
	public HitRecord getHit() {
		return record;
	}

	@Override
	public boolean hit(Ray ray, double tmin, double tmax) {
		Ray r = new Ray(ray.getOrigin(), ray.getDirection().mul(sign));
		VectorDouble AB = B.sub(A);
		record.setFaceNormal(r, normal);
		if (record.front_face) return false;
		double NdotRay = normal.dot(r.getDirection());
		if (Math.abs(NdotRay) < 0.000001) return false;
		double d = normal.dot(A);
		record.t = (normal.dot(r.getOrigin())+d) / NdotRay;
		if (record.t<tmin || record.t > tmax) return false;
		VectorDouble P = r.getOrigin().add(r.getDirection().mul(record.t));
		VectorDouble vp0 = P.sub(A);
		VectorDouble U = AB.cross(vp0);
		if (normal.dot(U) < 0) return false;
		record.point.setX(normal.dot(C));
		VectorDouble BC = C.sub(B);
		VectorDouble vp1 = P.sub(B);
		U = BC.cross(vp1);
		if (normal.dot(U) < 0) return false;
		record.point.setY(normal.dot(C));
		VectorDouble CA = A.sub(C);
		VectorDouble vp2 = P.sub(C);
		U = CA.cross(vp2);
		if (normal.dot(U) < 0) return false;
		record.point.setZ(normal.dot(C));
		return true;
	}

	@Override
	public VectorDouble getColor(HitRecord rec) {
		return new VectorDouble((int) (160.0d*(0.5d*rec.normal.getX()+1.0d)), (int) (160.0d*(0.5d*rec.normal.getY()+1.0d)), (int) (160.0d*(0.5d*rec.normal.getZ()+1.0d)));
	}

	@Override
	public double getVHitBox(VectorDouble pos) {
		return normal.getY() > 0.5d ? getHitBox(pos) : -5.0d;
	}
	
	public double getHitBox(VectorDouble pos) {
		pos = new VectorDouble(pos.getX(), A.getY(), pos.getZ());
		VectorDouble vp0 = pos.sub(A);
		VectorDouble U = B.sub(A).cross(vp0);
		if (normal.dot(U) < 0.2d) return -5.0d;
		record.point.setX(normal.dot(C));
		VectorDouble BC = C.sub(B);
		VectorDouble vp1 = pos.sub(B);
		U = BC.cross(vp1);
		if (normal.dot(U) < 0.2d) return -5.0d;
		return origin+1.0d;
	}

}
