package rayTracing.objects;

import java.awt.Color;
import java.awt.image.BufferedImage;

import rayTracing.tools.Ray;
import rayTracing.tools.VectorDouble;

public class TriangleHittableTextured extends Hittable {
	
	private HitRecord record;
	private VectorDouble A;
	private VectorDouble B;
	private VectorDouble C;
	private VectorDouble normal;
	private BufferedImage texture;
	
	public TriangleHittableTextured() {
		record = new HitRecord();
		A = new VectorDouble();
		B = new VectorDouble();
		C = new VectorDouble();
		texture = null;
	}
	
	public TriangleHittableTextured(VectorDouble Pa, VectorDouble Pb, VectorDouble Pc, BufferedImage Ptexture) {
		record = new HitRecord();
		A = new VectorDouble(Pa.getX(), Pa.getY(), Pa.getZ()*-1.0d);
		B = new VectorDouble(Pb.getX(), Pb.getY(), Pb.getZ()*-1.0d);
		C = new VectorDouble(Pc.getX(), Pc.getY(), Pc.getZ()*-1.0d);
		texture = Ptexture;
	}

	@Override
	public HitRecord getHit() {
		return record;
	}

	@Override
	public boolean hit(Ray ray, double tmin, double tmax) {
		Ray r = new Ray(ray.getOrigin(), new VectorDouble(ray.getDirection().getX(), ray.getDirection().getY(), ray.getDirection().getZ()*-1.0d));
		VectorDouble AB = B.sub(A);
		VectorDouble AC = C.sub(A);
		VectorDouble BC = C.sub(B);
		VectorDouble CA = A.sub(C);
		normal = AB.cross(AC);
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
		record.point.setX(normal.dot(U));
		VectorDouble vp1 = P.sub(B);
		U = BC.cross(vp1);
		if (normal.dot(U) < 0) return false;
		record.point.setY(normal.dot(U));
		VectorDouble vp2 = P.sub(C);
		U = CA.cross(vp2);
		if (normal.dot(U) < 0) return false;
		record.point.setZ(normal.dot(U));
		return true;
	}

	@Override
	public VectorDouble getColor(HitRecord rec) {
		Color color = new Color(texture.getRGB((int)modulo(rec.point.getY()*C.sub(B).getLength()*5,texture.getWidth()), (int)modulo(rec.point.getX()*B.sub(A).getLength()*5,texture.getHeight())));
		return new VectorDouble(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	public double modulo(double value, double div) {
		return value%div;
	}

	@Override
	public double getVHitBox(VectorDouble pos) {
		return -5.0d;
	}

}
