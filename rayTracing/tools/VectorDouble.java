package rayTracing.tools;


public class VectorDouble {
	
	private double x;
	private double y;
	private double z;
	
	public VectorDouble() {
		x = 0.0d;
		y = 0.0d;
		z = 0.0d;
	}
	
	public VectorDouble(double dx, double dy, double dz) {
		x = dx;
		y = dy;
		z = dz;
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getZ() {
		return z;
	}
	public void setX(double value) {
		x = value;
	}
	public void setY(double value) {
		y = value;
	}
	public void setZ(double value) {
		z = value;
	}
	
	public double getLength() {
		return Math.sqrt(x*x+y*y+z*z);
	}
	
	public VectorDouble add(VectorDouble v) {
		return new VectorDouble(x + v.x, y + v.y, z + v.z);
	}
	
	public VectorDouble sub(VectorDouble v) {
		return new VectorDouble(x - v.x, y - v.y, z - v.z);
	}
	
	public VectorDouble mul(VectorDouble v) {
		return new VectorDouble(x * v.x, y * v.y, z * v.z);
	}
	
	public VectorDouble mul(Double d) {
		return new VectorDouble(x * d, y * d, z * d);
	}
	
	public VectorDouble div(Double d) {
		if (d == 0.0d) return mul(Math.pow(10, 100));
		return mul(1.0d/d);
	}

	public double dot(VectorDouble v) {
		return x * v.x + y * v.y + z * v.z;
	}
	
	public VectorDouble unitVector() {
		return div(getLength());
	}
	
	public VectorDouble cross(VectorDouble v) {
		return new VectorDouble(y*v.z-z*v.y, z*v.x-x*v.z, x*v.y-y*v.x);
	}
	
	public double length_squared() {
		return x*x+y*y+z*z;
	}
}
