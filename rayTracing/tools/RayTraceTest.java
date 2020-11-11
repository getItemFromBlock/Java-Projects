package rayTracing.tools;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import rayTracing.RayTraceCore;
import rayTracing.objects.HittableList;

public class RayTraceTest extends JPanel {

	/**
	 * Oof
	 */
	private static final long serialVersionUID = -8642521895649138246L;
	
	public static VectorDouble origin = RayTraceCore.pos;
	
	private static int samples_per_pixel = 5;
	public double aspect_ratio = 16.0d/9.0d;
	public int timer = 0;
	public static boolean antialiasing = false;
	
	Camera cam = new Camera(new VectorDouble(-2,2,1), new VectorDouble(0,0,-1), new VectorDouble(0,1,0), 90, aspect_ratio);
	

	public RayTraceTest() {
		
	}
	
	public void paintComponent(Graphics g) {
		drawScene(g);
		timer++;
		if (RayTraceCore.show) {
			g.drawString("FPS : "+RayTraceCore.fps, 5, 13);
		}
	}
	
	public VectorDouble rayColor(Ray r, HittableList world) {
		if (world.hit(r, 0.00001d, 10000.0d)) {
			return world.getColor();
		}

	    VectorDouble unit_direction = r.getDirection().unitVector();
	    double t = 0.5d*(unit_direction.getY() + 1.0d);
	    return new VectorDouble((int)(((1.0d-t) + t*0.5d)*255.0d), (int)(((1.0d-t) + t*0.7d)*255.0d), (int)(((1.0d-t) + t*1.0d)*255.0d));
	}
	
	public void drawScene(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(-2, -2, 1, 1);
		if (RayTraceCore.menu == 1) {
			int maxX = RayTraceCore.res.width;
			int maxY = RayTraceCore.res.height;
			for (int j = maxY; j >= 0; j--) {
				for (int i = 0; i < maxX; i++) {
					VectorDouble pixel_color = new VectorDouble(0,0,0);
					if (antialiasing) {
						for (int s = 0; s < samples_per_pixel; s++) {
							double u = (i + Math.random())/maxX;
							double v = (j + Math.random())/maxY;
							Ray r = cam.getRay(u, v);
							pixel_color = pixel_color.add(rayColor(r, RayTraceCore.world));
						}
					} else {
						double u = (i + 0.5d)/maxX;
						double v = (j + 0.5d)/maxY;
						Ray r = cam.getRay(u, v);
						pixel_color = rayColor(r, RayTraceCore.world);
					}
					setColor(g, pixel_color, samples_per_pixel, i, j, maxY);
					
				}
			}
		}
		
		//System.out.println(g.getColor().toString());
	}
	
	public double getAngle(double fov, double posX, double posY) {
		
		return fov;
	}
	
	public Color getPixelValue(double fov, double posX, double posY) {
		
		return Color.BLACK;
	}
	
	public double clamp(double x, double min, double max) {
		if (x < min) return min;
		if (x > max) return max;
		return x;
	}
	
	public void setColor(Graphics G, VectorDouble color, int samples_per_pixel, int i, int j, int maxY) {
		double r = color.getX();
		double g = color.getY();
		double b = color.getZ();
		
		if (antialiasing) {
			double scale = 1.0d/samples_per_pixel;
			r = r*scale;
			g = g*scale;
			b = b*scale;
		}
		
		
		G.setColor(new Color((int)(clamp(r, 0.0d, 254.999d)), (int)(clamp(g, 0.0d, 254.999d)), (int)(clamp(b, 0.0d, 254.999d))));
		G.fillRect(2*i, 2*(maxY-j), 2, 2);
	}
	
	public void refreshCam(VectorDouble newPos, double[] rot, double fov) {
		cam.refresh(newPos, new VectorDouble(Math.cos(Math.toRadians(rot[0]))*Math.cos(Math.toRadians(rot[1])),Math.sin(Math.toRadians(rot[1])),Math.sin(Math.toRadians(rot[0]))*Math.cos(Math.toRadians(rot[1]))), new VectorDouble(0,1,0), fov, aspect_ratio);
	}
	
}
