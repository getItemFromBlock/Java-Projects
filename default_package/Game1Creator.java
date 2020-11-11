import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;


import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game1Creator extends JFrame implements KeyListener {
	private static final long serialVersionUID = -5176190419623991152L;
	
	public static void main(String[] args) {
	    new Game1Creator();
	  }
	
	private Game1CreatorR pan = new Game1CreatorR();
	public static boolean reinit = false;
	public static boolean debug = false;
	public static boolean show = false;
	public static boolean up = false;
	public static boolean down = false;
	public static boolean right = false;
	public static boolean left = false;
	public static boolean clic_R = false;
	public static boolean clic_L = false;
	public static boolean clic_M = false;
	public static boolean e = false;
	public static boolean a = false;
	public static short clock = 0;
	public static short second = 0;
	public static short fps = 0;
	public static byte speed = 10;
	public static Dimension pos = new Dimension(-285,0);
	public static float mPosX = 0;
	public static float mPosY = 0;
	public static List<Integer> sol1 = new LinkedList<Integer>();
	public static List<Integer> sol2 = new LinkedList<Integer>();
	public static List<Integer> solP1 = new LinkedList<Integer>();
	public static List<Integer> solP2 = new LinkedList<Integer>();
	public static List<Integer> solC = new LinkedList<Integer>();
	public static List<Integer> solS = new LinkedList<Integer>();
	public static List<Integer> blocX = new LinkedList<Integer>();
	public static List<Integer> blocY = new LinkedList<Integer>();
	public static List<Integer> blocT = new LinkedList<Integer>();
	public static BufferedImage lock, BigCoin, BigCoinVoid, door, red_flag, yellow_flag,bloc_0,bloc_1,bloc_2,enemy_0,enemy_1,bonus_0,sol_0,sol_1,sol_2,sol_3,sol_4;

	public Game1Creator() {
	    this.setTitle("Game 1 Level Creator");
	    this.setSize(618, 647);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setContentPane(pan);
	    this.setVisible(true);
	    pan.setFocusable(true);
	    pan.requestFocus();
	    pan.addKeyListener(this);
	    pan.addMouseListener(new java.awt.event.MouseAdapter() {
	        public void mousePressed(MouseEvent e){
	          game_mousePressed(e);
	        }
	        public void mouseReleased(MouseEvent e) {
	        	game_mouseReleased(e);
	        }
	      });
	    this.setMinimumSize(new Dimension(618,647));
	    try {
			this.setIconImage(ImageIO.read(this.getClass().getResource("/pictures/my_block.png")));
			lock = ImageIO.read(this.getClass().getResource("/pictures/lock.png"));
			BigCoin = ImageIO.read(this.getClass().getResource("/pictures/BigCoin.png"));
			BigCoinVoid = ImageIO.read(this.getClass().getResource("/pictures/BigCoinVoid.png"));
			door = ImageIO.read(this.getClass().getResource("/pictures/door.png"));
			red_flag = ImageIO.read(this.getClass().getResource("/pictures/red_flag.png"));
			yellow_flag = ImageIO.read(this.getClass().getResource("/pictures/yellow_flag.png"));
			bloc_0 = ImageIO.read(this.getClass().getResource("/pictures/bloc_0.png"));
			bloc_1 = ImageIO.read(this.getClass().getResource("/pictures/bloc_1.png"));
			bloc_2 = ImageIO.read(this.getClass().getResource("/pictures/bloc_2.png"));
			enemy_0 = ImageIO.read(this.getClass().getResource("/pictures/enemy_0.png"));
			enemy_1 = ImageIO.read(this.getClass().getResource("/pictures/enemy_1.png"));
			bonus_0 = ImageIO.read(this.getClass().getResource("/pictures/bonus.png"));
			sol_0 = ImageIO.read(this.getClass().getResource("/pictures/grass.png"));
			sol_1 = ImageIO.read(this.getClass().getResource("/pictures/dirt.png"));
			sol_2 = ImageIO.read(this.getClass().getResource("/pictures/my_block.png"));
			sol_3 = ImageIO.read(this.getClass().getResource("/pictures/sol_3.png"));
			sol_4 = ImageIO.read(this.getClass().getResource("/pictures/spike.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    addSol(-330,-190,8,50,0,0);
	    while (true) {
	    	if (up) {
	    		pos.height = (int) pos.getHeight() - 2;
	    	}
	    	if (down) {
	    		pos.height = (int) pos.getHeight() + 2;
	    	}
	    	if (right) {
	    		pos.width = (int) pos.getWidth() - 3;
	    	}
	    	if (left) {
	    		pos.width = (int) pos.getWidth() + 3;
	    	}
	    	if (reinit) {
	    		reinit = false;
	    		pos.width = -285;
	    		pos.height = 0;
	    	}
	    	if (debug) {
	    		debug = false;
	    		if (show) {
	    			show = false;
	    		} else {
	    			show = true;
	    		}
	    	}
	    	if (e) {
	    		e = false;
	    		int x = pos.width;
	    		int y = pos.height;
	    		if (x < -300) x = -300;
	    		if (y > 300) y = 300;
	    		if (y < -300) y = -300;
	    		addSol(x-30, x+30, y-30, y+30, 0, 0);
	    	}
	    	if (clic_L || clic_M || clic_R) {
	    		mPosX = (float) ((MouseInfo.getPointerInfo().getLocation().getX()-this.getLocationOnScreen().getX())-(this.getWidth()-pan.getMaxDim())/2)/pan.getCoef()-300 + pan.getPos();
	    		mPosY = (float) ((MouseInfo.getPointerInfo().getLocation().getY()-this.getLocationOnScreen().getY())-14-(this.getHeight()-pan.getMaxDim())/2)/pan.getCoef() - 300;
	    		testBloc();
	    	}
	    	if (a) {
	    		a = false;
	    		printT();
	    	}
	    	process();
	    	pan.repaint();
	    	try {
	    		Thread.sleep(speed);
	    		} catch (InterruptedException e) {
	    			e.printStackTrace();
	    			}
	    	}
	    }
	private void process() {
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
	private void addSol(int x, int x2, int y, int y2, int color, int type) {
		sol1.add(x);
		sol2.add(x2);
		solP1.add(y);
		solP2.add(y2);
		solC.add(color);
		solS.add(type);
	}
	private void removeSol(int i) {
		sol1.remove(i);
		sol2.remove(i);
		solP1.remove(i);
		solP2.remove(i);
		solC.remove(i);
		solS.remove(i);
	}
	private void testBloc() {
		if (clic_R) {
			clic_R = false;
			for (int i = 0; i < solC.size(); i++) {
				if (mPosX > sol1.get(i) + 10 && mPosX < sol2.get(i) - 10 && mPosY > solP1.get(i) + 3 && mPosY < solP2.get(i) - 18) {
					//System.out.println("test");
					solC.set(i, solC.get(i)+1);
					if (solC.get(i) > 4) {
						solC.set(i, 0);
					}
					if (solC.get(i) == 2) {
						solS.set(i, 1);
					}
					else if (solC.get(i) == 4) {
						solS.set(i, 2);
					}
					else {
						solS.set(i, 0);
					}
				}
			}
		}
		else if (clic_L) {
			for (int i = 0; i < solC.size(); i++) {
				if (mPosX > sol1.get(i) + 19 && mPosX < sol2.get(i) - 19 && mPosY > solP1.get(i) + 8 && mPosY < solP2.get(i) - 23) {
					int difX = sol2.get(i) - sol1.get(i);
					int difY = solP2.get(i) - solP1.get(i);
					sol1.set(i, (int) (mPosX-difX/2));
					sol2.set(i, sol1.get(i)+difX);
					solP1.set(i, (int) (mPosY-difY/2)+7);
					solP2.set(i, solP1.get(i)+difY);
				}
				else {
					if (mPosX > sol1.get(i) + 10 && mPosX < sol2.get(i) - 10 && mPosY > solP1.get(i) && mPosY < solP1.get(i) + 7) {
						solP1.set(i, (int) (mPosY - 3));
					}
					if (mPosX > sol1.get(i) + 10 && mPosX < sol2.get(i) - 10 && mPosY > solP2.get(i) - 21 && mPosY < solP2.get(i) - 15) {
						solP2.set(i, (int) (mPosY + 18));
					}
					if (mPosX > sol1.get(i) + 8 && mPosX < sol1.get(i) + 17 && mPosY > solP1.get(i) + 3 && mPosY < solP2.get(i) - 18) {
						sol1.set(i, (int) (mPosX - 12));
					}
					if (mPosX > sol2.get(i) - 17 && mPosX < sol2.get(i) - 8 && mPosY > solP1.get(i) + 3 && mPosY < solP2.get(i) - 18) {
						sol2.set(i, (int) (mPosX + 12));
					}
				}
			}
		}
		else if (clic_M) {
			clic_M = false;
			for (int i = 0; i < solC.size(); i++) {
				if (mPosX > sol1.get(i) + 8 && mPosX < sol2.get(i) - 8 && mPosY > solP1.get(i) + 1 && mPosY < solP2.get(i) - 15) {
					removeSol(i);
					break;
				}
			}
		}
	}
	
	public void printT() {
		System.out.print("\n\nLevel code :\n{");
		for (int i = 0; i < solS.size(); i++) {
			if (i != 0) System.out.print(",");
			System.out.print(""+sol1.get(i));
		}
		System.out.print("}\n{");
		for (int i = 0; i < solS.size(); i++) {
			if (i != 0) System.out.print(",");
			System.out.print(""+sol2.get(i));
		}
		System.out.print("}\n{");
		for (int i = 0; i < solS.size(); i++) {
			if (i != 0) System.out.print(",");
			System.out.print(""+solP1.get(i));
		}
		System.out.print("}\n{");
		for (int i = 0; i < solS.size(); i++) {
			if (i != 0) System.out.print(",");
			System.out.print(""+solP2.get(i));
		}
		System.out.print("}\n{");
		for (int i = 0; i < solS.size(); i++) {
			if (i != 0) System.out.print(",");
			System.out.print(""+solC.get(i));
		}
		System.out.print("}\n{");
		for (int i = 0; i < solS.size(); i++) {
			if (i != 0) System.out.print(",");
			System.out.print(""+solS.get(i));
		}
		System.out.print("}\n");
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		//System.out.println(""+event.getKeyCode());
		if (event.getKeyCode() == 75) {
			debug = true;
		}
		if (event.getKeyCode() == 90) {
			up = true;
		}
		if (event.getKeyCode() == 81) {
			right = true;
		}
		if (event.getKeyCode() == 83) {
			down = true;
		}
		if (event.getKeyCode() == 68) {
			left = true;
		}
		if (event.getKeyCode() == 82) {
			reinit = true;
		}
		if (event.getKeyCode() == 69) {
			e = true;
		}
		if (event.getKeyCode() == 65) {
			a = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == 75) {
			debug = false;
		}
		if (event.getKeyCode() == 90) {
			up = false;
		}
		if (event.getKeyCode() == 81) {
			right = false;
		}
		if (event.getKeyCode() == 83) {
			down = false;
		}
		if (event.getKeyCode() == 68) {
			left = false;
		}
		if (event.getKeyCode() == 82) {
			reinit = false;
		}
		if (event.getKeyCode() == 69) {
			e = false;
		}
		if (event.getKeyCode() == 65) {
			a = false;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent event) {
		
	}
	
	public void game_mousePressed(MouseEvent e) {
	    int buttonDown = e.getButton();
	 
	    if (buttonDown == MouseEvent.BUTTON1) {
	    	clic_L = true;
	    } else if(buttonDown == MouseEvent.BUTTON2) {
	    	clic_M = true;
	    } else if(buttonDown == MouseEvent.BUTTON3) {
	    	clic_R = true;
	    }
	 }
	
	public void game_mouseReleased(MouseEvent e) {
	    int buttonDown = e.getButton();
	 
	    if (buttonDown == MouseEvent.BUTTON1) {
	    	clic_L = false;
	    } else if(buttonDown == MouseEvent.BUTTON2) {
	    	clic_M = false;
	    } else if(buttonDown == MouseEvent.BUTTON3) {
	    	clic_R = false;
	    }
	 }

}
