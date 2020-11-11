import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game3 extends JFrame implements KeyListener {

	private static final long serialVersionUID = -2534563754003553176L;

	public static void main(String[] args) {
		new Game3();
	}
	
	private GameBackground3 pan = new GameBackground3();
	private static GameSound3 fire = new GameSound3("Tank_Assets/cannon"); // sons du jeu
	private static GameSound3 bound = new GameSound3("Tank_Assets/bound");
	private static GameSound3 explode = new GameSound3("Tank_Assets/explode");
	private Game3Bloc blocks = new Game3Bloc();
	private Game3Projectile projects = new Game3Projectile();
	private Game3Trace traces = new Game3Trace();
	private Game3Mine mines = new Game3Mine();
	private Game3AI ai = new Game3AI();
	private Game3Effect effects = new Game3Effect();
	
	public static final float movSpeed = 0.7f;
	
	public static short clock = 0;
	public static short second = 0;
	public static short fps = 0;
	public static byte speed = 10;
	public static boolean q = false; // boolean utilisees pour les touches
	public static boolean d = false;
	public static boolean z = false;
	public static boolean s = false;
	public static boolean enter = false;
	public static boolean esc = false;
	public static boolean f = false;
	public static boolean r = false;
	public static float rot = 0.0f;
	public static int rot2 = 0;
	public static float velosX = 0;
	public static float velosY = 0;
	public static float posX = 0;
	public static float posY = 0;
	public static short shootCounter = 0;
	public static short timer = 0;
	public static boolean dead = false;
	public static int score = 0;
	public static float a = 0.0f;
	public static float b = 0.0f;
	public static short tracetimer = 0;

	
	public static BufferedImage tank, turret, rocket_0, rocket_1 , bloc_0, bloc_1, hole, trace, mine, explode_0, explode_1, explode_2, background;
	
	public Game3() {
		this.setTitle("Tank game");
	    this.setSize(800, 800);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(pan);
	    this.setVisible(true);
	    pan.setFocusable(true);
	    pan.requestFocus();
	    pan.addKeyListener(this);
	    this.setMinimumSize(new Dimension(800,800));
	    try {
			tank = ImageIO.read(this.getClass().getResource("/Tank_Assets/tankBase.png"));
			turret = ImageIO.read(this.getClass().getResource("/Tank_Assets/tankTurret.png"));
			rocket_0 = ImageIO.read(this.getClass().getResource("/Tank_Assets/rocket_0.png"));
			bloc_0 = ImageIO.read(this.getClass().getResource("/Tank_Assets/bloc_0.png"));
			bloc_1 = ImageIO.read(this.getClass().getResource("/Tank_Assets/bloc_1.png"));
			trace = ImageIO.read(this.getClass().getResource("/Tank_Assets/trace.png"));
			mine = ImageIO.read(this.getClass().getResource("/Tank_Assets/mine.png"));
			explode_0 = ImageIO.read(this.getClass().getResource("/Tank_Assets/explode_0.png"));
			explode_1 = ImageIO.read(this.getClass().getResource("/Tank_Assets/explode_1.png"));
			explode_2 = ImageIO.read(this.getClass().getResource("/Tank_Assets/explode_2.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    blocks.create(-5, -2, 0);
	    blocks.create(-4, -2, 0);
	    blocks.create(-3, -2, 0);
	    blocks.create(-2, -2, 0);
	    blocks.create(-1, -2, 0);
	    blocks.create(0, -2, 0);
	    blocks.create(1, -2, 0);
	    blocks.create(2, -5, 0);
	    blocks.create(2, -6, 0);
	    blocks.create(2, -7, 0);
	    blocks.create(3, -5, 0);
	    blocks.create(4, -5, 0);
	    blocks.create(5, -5, 0);
	    blocks.create(2, -4, 0);
	    blocks.create(2, -3, 0);
	    blocks.create(2, -2, 0);
	    blocks.create(2, -1, 0);
	    blocks.create(2, 0, 1);
	    blocks.create(2, 1, 1);
	    blocks.create(-5, -8, 0);
	    blocks.create(-4, -8, 0);
	    blocks.create(-3, -8, 0);
	    blocks.create(-2, -8, 0);
	    blocks.create(-1, -8, 0);
	    blocks.create(0, -8, 0);
	    ai.create(5, -3, 0);
	    ai.create(-3, -4, 1);
	    ai.create(-2, -4, 1);
	    ai.create(-4, -4, 1);
	    ai.create(-7, -4, 1);
	    
	    traces.refresh();
	    
	    process();
	    try {
	        Thread.sleep(1000);
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	    System.out.println("End");
	    this.dispose();
	}
	
	public void process() {
		while (true) {
			if (z) {
				if (q || d) {
					velosY = velosY + (float) -Math.sqrt(movSpeed/2);
					rot2 = (q ? 315 : 45);
				} else {
					velosY = velosY -movSpeed;
					rot2 = 0;
				}
			}
			if (s) {
				if (q || d) {
					velosY = velosY + (float) Math.sqrt(movSpeed/2);
					rot2 = (q ? 225 : 135);
				} else {
					velosY = velosY + movSpeed;
					rot2 = 180;
				}
			}
			if (q) {
				if (z || s) {
					velosX = velosX + (float) -Math.sqrt(movSpeed/2);
				} else {
					velosX = velosX -movSpeed;
					rot2 = 270;
				}
			}
			if (d) {
				if (z || s) {
					velosX = velosX + (float) Math.sqrt(movSpeed/2);
				} else {
					velosX = velosX + movSpeed;
					rot2 = 90;
				}
			}
			if (f) {
				f = false;
				if (shootCounter < 6) {
					fire.playSound();
					Game3Projectile.create(posX, posY, rot, 0);
					shootCounter++;
				}
			}
			if (r) {
				r = false;
				if (Game3Mine.timer.size() < 2) {
					mines.create(posX, posY, 0);
				}
			}
			timer++;
			if (timer > 80) {
				timer = 0;
				if (shootCounter > 0) shootCounter --;
			}
			if (!dead) {
				posX = posX+velosX;
				posY = posY+velosY;
			}
			if (velosX != 0.0f || velosY != 0.0f) {
				tracetimer--;
				if (tracetimer < 0) {
					tracetimer = 10;
					Game3Trace.create(posX, posY, rot2);
				}
			}
			if (dead) {
				System.out.println("Game Over");
				break;
			}
			a = (float) (MouseInfo.getPointerInfo().getLocation().getX()-this.getLocationOnScreen().getX())-8;
			b = (float) (MouseInfo.getPointerInfo().getLocation().getY()-this.getLocationOnScreen().getY())-36;
			if (((posX+400)*pan.coef+((pan.getWidth()-pan.maxDim)/2)-a) < 0) {
				rot = 90.0f + (float) Math.toDegrees(Math.atan(((posY+400)*pan.coef+((pan.getHeight() -pan.maxDim)/2)-b)/((posX+400)*pan.coef+((pan.getWidth()-pan.maxDim)/2)-a)));
			}
			if (((posX+400)*pan.coef+((pan.getWidth()-pan.maxDim)/2)-a) > 0) {
				rot = 270.0f + (float) Math.toDegrees(Math.atan(((posY+400)*pan.coef+((pan.getHeight() -pan.maxDim)/2)-b)/((posX+400)*pan.coef+((pan.getWidth()-pan.maxDim)/2)-a)));
			}
			mines.refresh();
			if (projects.refresh()) {
				dead = true;
				bound.playSound();
			}
			if (blocks.refresh()) {
				bound.playSound();
			}
			ai.refresh();
			effects.refresh();
			
			//System.out.println(((posY+400)*pan.coef+((pan.getHeight() -pan.maxDim)/2)-b)+" "+((posX+400)*pan.coef+((pan.getWidth()-pan.maxDim)/2)-a)+" "+rot);
			calc();
			pan.repaint();
			velosX = 0.0f;
			velosY = 0.0f;
			
			try {
		        Thread.sleep(speed);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		}
	}
	
	private void calc() {
		clock++;
		  Calendar cal = Calendar.getInstance();
		  if (second != cal.get(Calendar.SECOND)) {
			  fps = clock;
			  clock = 0;
			  if (fps < 92) {
				  speed--;
			  }
			  if (fps > 108) {
				  speed ++;
			  }
		  }
		  second = (short) cal.get(Calendar.SECOND);
	}
	
	public static void playFire() {
		fire.playSound();
	}
	public static void playExplode() {
		explode.playSound();
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == 68) {
			d = true;
	  }
	  if (event.getKeyCode() == 81) {
			q = true;
	  }
	  if (event.getKeyCode() == 90) {
			z = true;
	  }
	  if (event.getKeyCode() == 83) {
			s = true;
	  }
	  if (event.getKeyCode() == 10) {
			enter = true;
	  }
	  if (event.getKeyCode() == 70) {
			f = true;
	  }
	  if (event.getKeyCode() == 82) {
			r = true;
	  }
	  //System.out.println(event.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == 68) {
			d = false;
	  }
	  if (event.getKeyCode() == 81) {
			q = false;
	  }
	  if (event.getKeyCode() == 90) {
			z = false;
	  }
	  if (event.getKeyCode() == 83) {
			s = false;
	  }
	  if (event.getKeyCode() == 10) {
			enter = false;
	  }
	  if (event.getKeyCode() == 70) {
			f = false;
	  }
	  if (event.getKeyCode() == 82) {
			r = false;
	  }
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}

}
