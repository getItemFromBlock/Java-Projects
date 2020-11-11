package rayTracing.tools;

public class Ray {
	
	private VectorDouble origin = new VectorDouble();
	private VectorDouble direction = new VectorDouble();

	public Ray() {
		
	}
	
	public Ray(VectorDouble originV, VectorDouble directionV) {
		origin = originV;
		direction = directionV;
	}
	
	public VectorDouble getOrigin() {
		return origin;
	}
	
	public VectorDouble getDirection() {
		return direction;
	}
	
	public VectorDouble at(double t) {
		return origin.add(direction.mul(t));
	}
}
