import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game2 extends JFrame implements KeyListener {

	private static final long serialVersionUID = -2534563754003553176L;

	public static void main(String[] args) {
		new Game2();
	}
	
	private GameBackground2 pan = new GameBackground2();
	private Game2Laser lasers = new Game2Laser();
	private Game2Asteroid asteroids = new Game2Asteroid();
	private GameSound2 peow = new GameSound2("fire"); // sons du jeu
	
	public static short clock = 0;
	public static short second = 0;
	public static short fps = 0;
	public static byte speed = 10;
	public static boolean left = false; // boolean utilisees pour les touches
	public static boolean right = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean enter = false;
	public static boolean esc = false;
	public static boolean m = false;
	public static short rot = 0;
	public static float velos = 0;
	public static float posX = 0;
	public static float posY = 0;
	public static short shootTimer = 0;
	public static boolean dead = false;
	public static int score = 0;
	
	public static BufferedImage ship, laser, space, asteroid;
	
	public Game2() {
		this.setTitle("Game 2");
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
			ship = ImageIO.read(this.getClass().getResource("/Spaceship.png"));
			laser = ImageIO.read(this.getClass().getResource("/laser.png"));
			space = ImageIO.read(this.getClass().getResource("/space.png"));
			asteroid = ImageIO.read(this.getClass().getResource("/asteroid.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    process();
	    System.out.println("End");
	    this.dispose();
	}
	
	public void process() {
		while (true) {
			if (right) {
				if (!dead) {
					rot = (short) (rot+2);
					if (rot>360) {
						rot = (short) (rot-360);
					}
				}
			}
			if (left) {
				if (!dead) {
					rot = (short) (rot-2);
					if (rot<0) {
						rot = (short) (rot+360);
					}
				}
			}
			if (up) {
				velos = velos + 0.2f;
			}
			if (down) {
				velos = velos - 0.2f;
			}
			if (m) {
				m = false;
				if (!dead) {
					if (shootTimer == 0) {
						shootTimer = 30;
						peow.playSound();
						lasers.create(posX+11, posY+11, rot);
					}
				}
			}
			if (shootTimer > 0) {
				shootTimer--;
			}
			velos = velos/1.1f;
			if (velos < 0.1 && velos > -0.1) {
				velos = 0;
			}
			if (Game2Asteroid.asteroidTest(posX,posY)) {
				dead = true;
				shootTimer = 200;
			}
			if (!dead) {
				posX = (float) (posX+(Math.sin(Math.toRadians(rot))*velos));
				posY = (float) (posY-(Math.cos(Math.toRadians(rot))*velos));
				lasers.refresh();
				asteroids.refresh();
			}
			if (dead && shootTimer == 0) {
				break;
			}
			calc();
			pan.repaint();
			
			
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

	@Override
	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == 39) {
			right = true;
	  }
	  if (event.getKeyCode() == 37) {
			left = true;
	  }
	  if (event.getKeyCode() == 38) {
			up = true;
	  }
	  if (event.getKeyCode() == 40) {
			down = true;
	  }
	  if (event.getKeyCode() == 10) {
			enter = true;
	  }
	  if (event.getKeyCode() == 77) {
			m = true;
	  }
	  //System.out.println(event.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == 39) {
			right = false;
	  }
	  if (event.getKeyCode() == 37) {
			left = false;
	  }
	  if (event.getKeyCode() == 38) {
			up = false;
	  }
	  if (event.getKeyCode() == 40) {
			down = false;
	  }
	  if (event.getKeyCode() == 10) {
			enter = false;
	  }
	  if (event.getKeyCode() == 77) {
			m = false;
	  }
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}

}
