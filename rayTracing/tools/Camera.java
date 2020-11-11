package rayTracing.tools;

public class Camera {
	
	
	public VectorDouble origin;
	public VectorDouble horizontal;
	public VectorDouble vertical;
	public VectorDouble lower_left_corner;

	public Camera(VectorDouble lookfrom, VectorDouble direction, VectorDouble vup, double vfov, double aspect_ratio) {
		double theta = Math.toRadians(vfov);
		double h = Math.tan(theta/2.0d);
		double viewport_height = 2.0d*h;
		double viewport_width = aspect_ratio * viewport_height;
		
		VectorDouble w = direction.unitVector();
		VectorDouble u = vup.cross(w).unitVector();
		VectorDouble v = w.cross(u);
		
		origin = lookfrom;
		horizontal = u.mul(viewport_width);
		vertical = v.mul(viewport_height);
		lower_left_corner = origin.sub(horizontal.div(2.0d)).sub(vertical.div(2.0d)).sub(w);
	}
	
	public void refresh(VectorDouble lookfrom, VectorDouble direction, VectorDouble vup, double vfov, double aspect_ratio) {
		double theta = Math.toRadians(vfov);
		double h = Math.tan(theta/2.0d);
		double viewport_height = 2.0d*h;
		double viewport_width = aspect_ratio * viewport_height;
		
		VectorDouble w = direction.unitVector();
		VectorDouble u = vup.cross(w).unitVector();
		VectorDouble v = w.cross(u);
		
		origin = lookfrom;
		horizontal = u.mul(viewport_width);
		vertical = v.mul(viewport_height);
		lower_left_corner = origin.sub(horizontal.div(2.0d)).sub(vertical.div(2.0d)).sub(w);
	}
	
	public Ray getRay(double u, double v) {
		return new Ray(origin, lower_left_corner.add(horizontal.mul(u)).add(vertical.mul(v)).sub(origin));
	}
}
