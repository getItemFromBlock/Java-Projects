package rayTracing;
import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import rayTracing.objects.HittableList;
import rayTracing.objects.SphereHittable;
import rayTracing.objects.TriangleHittable;
import rayTracing.objects.TriangleHittableMirror;
import rayTracing.objects.TriangleHittableTextured;
import rayTracing.tools.RayTraceTest;
import rayTracing.tools.VectorDouble;

public class RayTraceCore extends JFrame implements KeyListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6965671228102872718L;
	
	private static Robot robo;

	public static void main(String[] args) {
		
		try {
			robo = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.out.println("U are Fucked");
		}
		new RayTraceCore();

	}
	
	public static boolean left = false; // boolean utilisees pour les touches
	  public static boolean right = false;
	  public static boolean up = false;
	  public static boolean down = false;
	  public static boolean tr = false;
	  public static boolean tl = false;
	  public static boolean enter = false;
	  public static boolean esc = false;
	  public static boolean space = false;
	  public static boolean debug = false;
	  public static boolean show = false;
	  public static Dimension res = new Dimension(480,270);
	  public static double fov = 90.0d;
	  public static int sizeY = 540;
	  
	  public static double[] mousePos = {0,0};
	  public static double[] fixedPos = {0,0};
	  public static boolean grabMouse = false;
	  public static boolean dragMouse = false;
	  
	  public static short clock = 0;
	  public static short second = 0;
	  public static short fps = 0;
	  public static VectorDouble pos = new VectorDouble(0, 0, 0);
	  public static double[] rot = {90, 0};
	  public static double[] velocity = {0,0,0};
	  public static int menu = 0;
	  
	  public static BufferedImage brick;
	  
	  public static HittableList world = new HittableList();

	  
	  
	private RayTraceTest pan = new RayTraceTest();
	  Calendar cal = Calendar.getInstance();

	  public RayTraceCore() {
		    this.setTitle("Ray Tracing Test");
		    this.setSize(976,580);
		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setContentPane(pan);
		    this.setVisible(true);
		    pan.setFocusable(true);
		    pan.requestFocus();
		    pan.addKeyListener(this);
		    pan.addMouseMotionListener(this);
		    this.setMinimumSize(new Dimension(208,148));
		    
		    try {
		    	brick = ImageIO.read(this.getClass().getResource("/pictures/bloc_2.png"));
		    } catch (IOException e) {
		    	e.printStackTrace();
			}
		    
		    BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		    Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		        cursorImg, new Point(0, 0), "blank cursor");
		    Cursor defaultCursor = this.getContentPane().getCursor();
		    
		    
		    
		    
		    //world.add(new SphereHittable(new VectorDouble(0, -100.5, 0), 100));
		    world.add(new TriangleHittable(new VectorDouble(-100, -1, 100), new VectorDouble(100, -1, -100), new VectorDouble(-100, -1, -100)));
		    world.add(new TriangleHittable(new VectorDouble(-100, -1, 100), new VectorDouble(100, -1, 100), new VectorDouble(100, -1, -100)));
		    world.add(new TriangleHittableTextured(new VectorDouble(0, 0, -1.5), new VectorDouble(1.5, 0, -1.5), new VectorDouble(1.5, 1, -1.5), brick));
		    world.add(new TriangleHittableTextured(new VectorDouble(1.5, 1, -1.5), new VectorDouble(0, 1, -1.5), new VectorDouble(0, 0, -1.5), brick));
		    world.add(new SphereHittable(new VectorDouble(0, 0, -1), 0.5));
		    //world.add(new SphereHittable(new VectorDouble(1.5, 0, -1), 0.5));
		    world.add(new TriangleHittable(new VectorDouble(-1, -1, 1.5), new VectorDouble(-2, -1, 1.5), new VectorDouble(-2, 0, 1.5)));
		    world.add(new TriangleHittable(new VectorDouble(-1, -1, 1.5), new VectorDouble(-2, 0, 1.5), new VectorDouble(-1, 0, 1.5)));
		    world.add(new TriangleHittable(new VectorDouble(-1, -1, 2.5), new VectorDouble(-1, -1, 1.5), new VectorDouble(-1, 0, 1.5)));
		    world.add(new TriangleHittable(new VectorDouble(-1, -1, 2.5), new VectorDouble(-1, 0, 1.5), new VectorDouble(-1, 0, 2.5)));
		    world.add(new TriangleHittable(new VectorDouble(-2, 0, 2.5), new VectorDouble(-1, 0, 2.5), new VectorDouble(-1, 0, 1.5)));
		    world.add(new TriangleHittable(new VectorDouble(-2, 0, 2.5), new VectorDouble(-1, 0, 1.5), new VectorDouble(-2, 0, 1.5)));
		    
		    while (true) {
		    	if (menu == 0) {
		    		if (esc) break;
		    		if (enter) {
		    			enter = false;
		    			menu = 1;
		    			this.getContentPane().setCursor(blankCursor); 
		    			grabMouse = true;
		    		}
		    	}
		    	if (menu == 1) {
		    		if (esc) {
		    			esc = false;
		    			menu = 2;
		    			fov = 90.0d;
		    			this.getContentPane().setCursor(defaultCursor);
		    			grabMouse = false;
		    		}
			    	if (debug) {
			    		show = !show;
			    		debug = false;
			    	}
			    	if (up) {
			    		pos.setX(pos.getX()+Math.cos(Math.toRadians(rot[0]))*-0.05d);
			    		pos.setZ(pos.getZ()+Math.sin(Math.toRadians(rot[0]))*-0.05d);
			    	}
			    	if (down) {
			    		pos.setX(pos.getX()+Math.cos(Math.toRadians(rot[0]))*0.05d);
			    		pos.setZ(pos.getZ()+Math.sin(Math.toRadians(rot[0]))*0.05d);
			    	}
			    	if (left) {
			    		pos.setX(pos.getX()+Math.cos(Math.toRadians(rot[0]+90))*+0.05d);
			    		pos.setZ(pos.getZ()+Math.sin(Math.toRadians(rot[0]+90))*+0.05d);
			    	}
			    	if (right) {
			    		pos.setX(pos.getX()+Math.cos(Math.toRadians(rot[0]+90))*-0.05d);
			    		pos.setZ(pos.getZ()+Math.sin(Math.toRadians(rot[0]+90))*-0.05d);
			    	}
			    	if (space) {
			    		if (testGround() != -5.0d) {
				    		velocity[1] = 0.11d;
			    		}
			    	}
			    	if (!dragMouse) {
				    	rot[0] = mousePos[0]/5.0d + rot[0];
				    	rot[1] = cut(mousePos[1]/5.0d + rot[1], -90.0d, 90.0d);
				    	mousePos[0] = 0.0d;
				    	mousePos[1] = 0.0d;
			    	} else {
			    		dragMouse = false;
			    		fov = cut(mousePos[1]/7.0d + fov, 0.2, 360.0d);
			    		mousePos[0] = 0.0d;
				    	mousePos[1] = 0.0d;
			    	}
			    	if (testGround() != -5.0d && !space) {
			    		velocity[1] = 0.0d;
	    				pos.setY(testGround());
			    	} else {

			    		pos.setY(1.0);
			    	}
		    	}
		    	if (menu == 2) {
		    		if (esc) {
		    			esc = false;
		    			menu = 1;
		    			this.getContentPane().setCursor(blankCursor);
		    			grabMouse = true;
		    		}
		    	}
		    	int a = this.getHeight() - 40;
		    	if (a != sizeY) {
		    		sizeY = a;
		    		res.height = (int) (sizeY/2.0d);
		    		res.width = (int) (res.height * 16.0d/9.0d);
		    		
		    	}
		    	fixedPos[0] = this.getWidth()/2.0d;
		    	fixedPos[1] = this.getHeight()/2.0d;
		    	
		    	pan.refreshCam(pos, rot, fov);
		    	pan.repaint();
		    	process();
		    	try {
			        Thread.sleep(10, 0);
			      } catch (InterruptedException e) {
			        e.printStackTrace();
			      }
		    }
		    System.out.println("IT'S TIME TO STOP");
		    this.dispose();
	  }
	  
	  private void process() {
			clock++;
			  Calendar cal = calendar();
			  if (second != cal.get(Calendar.SECOND)) {
				  fps = (short) pan.timer;
				  clock = 0;
				  pan.timer = 0;
			  }
			  second = (short) cal.get(Calendar.SECOND);
		}
	  
	  public void moveMouse(MouseEvent arg0, boolean drag) {
		  if (arg0.getX() == fixedPos[0] && arg0.getY() == fixedPos[1]) return;
		  if (grabMouse) robo.mouseMove((int)fixedPos[0] + this.getX()+8, (int)fixedPos[1] + this.getY()+31);
		  dragMouse = drag;
		  mousePos[0] = arg0.getX() - fixedPos[0];
		  mousePos[1] = arg0.getY() - fixedPos[1];
	  }
	  
	  public double cut(double value, double min, double max) {
		  if (value < min) return min;
		  if (value > max) return max;
		  return value;
	  }
	  
	  public double testGround() {
		  return world.getVHitBox(pos);
	  }
	  
	  public static Calendar calendar() {
		  return Calendar.getInstance();
	  }
	  
	  @Override
	  public void keyPressed(KeyEvent event) {
		  if (event.getKeyCode() == 68) {
				right = true;
		  }
		  if (event.getKeyCode() == 81) {
				left = true;
		  }
		  if (event.getKeyCode() == 90) {
				up = true;
		  }
		  if (event.getKeyCode() == 83) {
				down = true;
		  }
		  if (event.getKeyCode() == 10) {
				enter = true;
		  }
		  if (event.getKeyCode() == 27) {
				esc = true;
		  }
		  if (event.getKeyCode() == 75) {
			  debug = true;
		  }
		  if (event.getKeyCode() == 65) {
				tl = true;
		  }
		  if (event.getKeyCode() == 69) {
			  tr = true;
		  }
		  if (event.getKeyCode() == 32) {
			  space = true;
		  }
	  }
	  @Override
	  public void keyReleased(KeyEvent event) {
		  if (event.getKeyCode() == 68) {
				right = false;
		  }
		  if (event.getKeyCode() == 81) {
				left = false;
		  }
		  if (event.getKeyCode() == 90) {
				up = false;
		  }
		  if (event.getKeyCode() == 83) {
				down = false;
		  }
		  if (event.getKeyCode() == 10) {
				enter = false;
		  }
		  if (event.getKeyCode() == 27) {
				esc = false;
		  }
		  if (event.getKeyCode() == 75) {
			  debug = false;
		  }
		  if (event.getKeyCode() == 65) {
				tl = false;
		  }
		  if (event.getKeyCode() == 69) {
			  tr = false;
		  }
		  if (event.getKeyCode() == 32) {
			  space = false;
		  }
		  //System.out.println(""+event.getKeyCode());
	  }

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		moveMouse(arg0, true);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		moveMouse(arg0, false);
	}

}
