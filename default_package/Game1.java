import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Game1 extends JFrame implements KeyListener {

	private static final long serialVersionUID = 5675267421756356254L;


public static void main(String[] args) {
    new Game1();
  }

  private GameBackground1 pan = new GameBackground1();
  Calendar cal = Calendar.getInstance();
  public static short clock = 0;
  public static short second = 0;
  public static short fps = 0;
  private GameSound1 pop = new GameSound1("pop"); // sons du jeu
  private GameSound1 death = new GameSound1("death");
  private GameSound1 coin = new GameSound1("coin");
  private GameSound1 bigCoin = new GameSound1("bigCoin");
  private GameSound1 clear = new GameSound1("clear");
  public static boolean left = false; // boolean utilisees pour les touches
  public static boolean right = false;
  public static boolean up = false;
  public static boolean down = false;
  public static boolean enter = false;
  public static boolean esc = false;
  public static boolean s = false; // (s_l:)
  public static boolean T = false;
  public static boolean l = false;
  public static boolean slash = false;
  public static boolean debug = false;
  public static boolean show = false;
  public static boolean ground = true; // boolean utilisee pour detecter si le joueur est au sol
  public static short timer = 0; // boolean utilisee pour divers timers
  public static short end = 0;
  public static Dimension pos = new Dimension(-285,0); // position du joueur
  public static float velosX = 0; // velocite du joueur
  public static float velosY = 0;
  public static final byte movSpeed = 2; // constantes de deplacement
  public static final byte jumpHeight = 7;
  public static BufferedImage lock, BigCoin, BigCoinVoid, door, red_flag, yellow_flag,bloc_0,bloc_1,bloc_2,enemy_0,enemy_1,bonus_0,sol_0;
  /*
   * Variables de declaration du terrain :
   * Les premiers crochets determinent le niveau
   * Les deuxiemes contiennent les elements.
   * Dans l ordre :
   * sol1 a solS, determine les elements fixes du niveau. sol1 = X1, sol2 = X2, solP1 = Y1, solP2 = Y2, solC = couleur,
   * solS = type de sol (0 = solide, 1 = plateforme, 2 = pics)
   */
  public static short sol1[][] = {{-330,-220,-160,-130,-30,100,130,170,260,340,630,800,930,940,1030,1130,1230,1300,1400,1630,2030},
		  {-330}};
  public static short sol2[][] = {{-190,-160,-50,-80,60,190,160,220,310,600,770,900,1000,990,1090,1180,1280,1370,1600,2000,2300},
		  {-190}};
  public static short solP1[][] = {{8,-70,-8,-30,8,15,10,8,30,10,10,0,-10,-70,-75,-65,-40,-10,10,10,10},
		  {8}};
  public static short solP2[][] = {{50,-50,30,8,70,80,31,100,80,350,350,50,40,-50,-55,-45,-20,10,350,350,350},
		  {50}};
  public static short solC[][] = {{0, 2, 1, 3, 0, 1, 4, 0, 0, 0, 0, 1, 0, 2, 2, 2, 2, 2, 1, 0, 0},
		  {0}};
  public static short solS[][] = {{0,1,0,0,0,0,2,0,0,0,0,0,0,1,1,1,1,1,0,0,0},
		  {0}};
  /*
   * Variables de declaration des ennemis :
   * Idem terrain
   * Dans l ordre :
   * enemyX a enemyZB, determine les entites du niveau. enemyX = X, enemyY = Y, enemyT = type d enemi, enemyVX et enemyVY sont la velocite,
   * enemyO = direction (true = droite, false = gauche), enemyD = si ennemi mort (ou doit disparaitre), enemyZA et enemyZB = zone x ou l ennemi se deplace
   */
  public static float enemyX[][] = {{0,440,710,850,1800,1850},{}};
  public static float enemyY[][] = {{0,-65,-5,10,20,20},{}};
  public static short enemyT[][] = {{0,2,1,0,0,1},{}};
  public static float enemyVX[][] = {{0,0,0,0,0,0},{}};
  public static float enemyVY[][] = {{0,0,0,0,0,0},{}};
  public static boolean enemyO[][] = {{false,false,true,true,true,true},{}};
  public static boolean enemyD[][] = {{false,true,false,false,false,false},{}};
  public static short enemyZA[][] = {{-30,340,630,800,1630,1630},{}};
  public static short enemyZB[][] = {{60,600,770,900,2000,2000},{}};
  /*
   * Variables de declaration des effets :
   * Idem terrain
   * Dans l ordre :
   * effectX a effectT, determine les effets du niveau. effectX = X, effectY = Y, effectC = couleur, effectT = type d'effet (0 = piece)
   */
  public static short effectX[][] = {{420,470,570},{}};
  public static short effectY[][] = {{-100,-50,-50},{}};
  public static short effectC[][] = {{0,0,0},{}};
  public static short effectT[][] = {{0,0,0},{}};
  public static final float enemyV = 0.5f; // constante de vitesse ennemi
  /*
   * Variables de declaration des effets :
   * Idem terrain
   * Dans l'ordre :
   * blocX a blocT, blocs du niveau. blocX = X, blocY = Y,
   * blocT = type (0 = bloc donnant bonus, 2 = bloc se cassant, 4 = bloc donnant piece, 6 = bloc invisible)
   */
  public static short blocX[][] = {{404,420,420,436,470,570,1550},{}};
  public static short blocY[][] = {{-50,-100,-50,-50,-50,-50,-50},{}};
  public static short blocT[][] = {{2,4,0,2,6,6,2},{}};
  public static boolean result = false;
  public static short lifes = 5; // vies du joueur par defaut
  public static boolean dead = false; // boolean utilisee pour mort du joueur
  public static short deadTimer = 0; // timer de mort
  public static short menu = 0; // determine le menu 0 = menu principal, 1 = ecran de chargement, 2 = stage, 3 = mort, 4 = pause, 5 = choix stage, 6 = fin niveau
  public static short menuPos = 0; // bouton selectionne du menu
  public static short stage = 0; // niveau actuel
  public static short maxStage = 0; // niveau max debloque
  public static short stageEX[] = {2150,3000};
  public static short stageEY[] = {10,10};
  public static short checkpointX[][] = {{960},{}};
  public static short checkpointY[][] = {{-10},{}};
  public static boolean checkpointB[][][] = {{{true,true,false}},{{},{}}};
  public static byte lastCheckpoint = -1;
  public static short bonus = 0; // transformation du joueur : 0 = normal, 1 = bonus bleu
  public static int score = 0; // score actuel
  public static short scoreS = 0; // augmentation du score, pour affichage des points
  public static short bigCoinX[][] = {{-240,435,1549},{}};
  public static short bigCoinY[][] = {{-120,-150,-100},{}};
  public static byte bigCoinS = 0;
  public static boolean bigCoinB[][] = {{false,false,false},{}};
  public static boolean bigCoinM[][] = {{false,false,false},{}};
  public static byte speed = 10;
  

  public Game1() {
    this.setTitle("Game 1");
    this.setSize(600, 623);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setContentPane(pan);
    this.setVisible(true);
    pan.setFocusable(true);
    pan.requestFocus();
    pan.addKeyListener(this);
    this.setMinimumSize(new Dimension(600,623));
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
	} catch (IOException e) {
		e.printStackTrace();
	}

    while (menu >= 0) {
        refresh();
    }
    if (lifes < 1) {
        System.out.println("Game Over");
    }
    System.out.println("End");
    this.dispose();
  }
  private void refresh() {
	  if (menu == 0) {
		  while (true) {
		  if (up) {
			  menuPos--;
			  up = false;
			  if (menuPos < 0) {
				  menuPos = 2;
			  }
		  }
		  if (down) {
			  down = false;
			  menuPos++;
			  if (menuPos > 2) {
				  menuPos = 0;
			  }
		  }
		  if (enter) {
			  enter = false;
			  if (menuPos == 0) {
				  stage = maxStage;
				  menu = 1;
				  timer = 200;
				  break;
			  }
			  if (menuPos == 1) {
				  menuPos = 0;
				  menu = 5;
				  break;
			  }
			  if (menuPos == 2) {
				  menu = -1;
				  break;
			  }
		  }
		  if (esc) {
			  menu = -1;
			  break;
		  }
		  if (s && T && l && slash) {
			  s = false;
			  T = false;
			  l = false;
			  slash = false;
			  maxStage = 14;
			  System.out.println("Unlocked all stages");
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
	  else if (menu == 1) {
		  while (true) {
			  if (timer == 1) {
				  menu = 2;
				  timer = 0;
				  break;
			  }
			  if (timer > 1) {
				  timer--;
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
	  else if (menu == 2) {
	  while (true) {
		  if (ground) {
			  velosY = 0;
		  }
		  else {
			  if (velosY < 9) {
				  velosY = velosY-0.4f;
			  }
		  }
		  velosX = 0;
		  if (left) {
			  if (right == false) {
				  if (pos.width > -290) {
					  velosX = movSpeed;
				  }
			  }
		  }
		  if (right) {
			  if (left == false) {
				  if (pos.width < 10000) {
					  velosX = -movSpeed;
				  }
			  }
		  }
		  if (up) {
			  if (ground) {
				  if (velosY == 0) {
					  velosY = jumpHeight;
					  ground = false;
				  }
			  }
		  }
		  if (esc) {
			  coin.playSound();
			  menu = 4;
			  esc = false;
			  break;
		  }
		  if (debug) {
			  debug = false;
			  if (show) {
				  show = false;
			  }
			  else {
				  show = true;
			  }
		  }
		  if (speed > 11) {
			  speed = 2;
		  }
		  if (speed < 2) {
			  speed = 11;
		  }
		  if (timer == 1) {
			  bonus = 0;
			  timer = 0;
		  }
		  if (timer > 1) {
			  timer--;
		  }
		  if (end > 1) {
			  end--;
		  }
		  if (end == 1) {
			  end = 0;
			  if (stage < 14) {
				  maxStage++;
			  }
			  pos.height = -10;
			  pos.width = -285;
			  velosX = 0;
			  velosY = 0;
			  pan.setPos(0);
			  for (int i = 0; i < enemyT[stage].length; i++) {
				  if (enemyT[stage][i] != 2) {
					  enemyD[stage][i] = false;
				  }
				  else {
					  enemyD[stage][i] = true;
				  }
			  }
			  for (int i = 0; i < bigCoinB[stage].length; i++) {
				  if (bigCoinB[stage][i]) {
					  bigCoinM[stage][i] = true;
					  bigCoinB[stage][i] = false;
				  }
			  }
			  for (int i = 0; i < blocT[stage].length; i++) {
				  if (blocT[stage][i] == 1) {
					  blocT[stage][i] = 0;
				  }
				  if (blocT[stage][i] == 3) {
					  blocT[stage][i] = 2;
				  }
				  if (blocT[stage][i] == 5) {
					  blocT[stage][i] = 4;
				  }
				  if (blocT[stage][i] == 7) {
					  blocT[stage][i] = 6;
				  }
			  }
			  lastCheckpoint = -1;
			  menu = 6;
			  menuPos = 0;
			  break;
		  }
		  if (pos.height > 350) {
			  if (deadTimer == 0) {
				  timer = 0;
				  dead = true;
				  bonus = 0;
			  }
		  }
		  if (dead) {
			  if (deadTimer == 0) {
				  dead = false;
				  if (bonus == 0) {
					  death.playSound();
					  if (lifes == 0) {
						  lastCheckpoint = -1;
						  result = false;
						  menuPos = 0;
						  menu = 3;
						  timer = 500;
						  break;
					  }
					  else {
						  lifes--;
						  deadTimer = 300;
					  }
				  }
				  else {
					  bonus = 2;
					  timer = 150;
				  }
			  }
		  }
		  if (deadTimer > 1) {
			  deadTimer--;
			  if (pos.height < 320) {
				  if (deadTimer < 210 && deadTimer > 200) {
					  pos.height = pos.height-3;
				  }
				  if (deadTimer < 195) {
					  pos.height = pos.height+3;
				  }
				  if (deadTimer < 150) {
					  pos.height = pos.height+2;
				  }
			  }
			  velosX = 0;
			  velosY = 0;
		  }
		  if (deadTimer == 1) {
			  deadTimer = 0;
			  if (lastCheckpoint > -1) {
				  pos.height = checkpointY[stage][lastCheckpoint];
				  pos.width = checkpointX[stage][lastCheckpoint];
				  pan.setPos(checkpointX[stage][lastCheckpoint]+30);
			  }
			  else {
				  pos.height = -10;
				  pos.width = -285;
				  pan.setPos(0);
			  }
			  velosX = 0;
			  velosY = 0;
			  for (int i = 0; i < enemyT[stage].length; i++) {
				  if (enemyT[stage][i] != 2) {
					  enemyD[stage][i] = false;
				  }
				  else {
					  enemyD[stage][i] = true;
				  }
			  }
			  for (int i = 0; i < bigCoinB[stage].length; i++) {
				  if (lastCheckpoint > -1) {
					  if (!checkpointB[stage][lastCheckpoint][i]) {
						  if (bigCoinB[stage][i]) {
							  bigCoinB[stage][i] = false;
						  }
					  }
				  }
				  else {
					  if (bigCoinB[stage][i]) {
						  bigCoinB[stage][i] = false;
					  }
				  }
			  }
			  for (int i = 0; i < blocT[stage].length; i++) {
				  if (blocT[stage][i] == 1) {
					  blocT[stage][i] = 0;
				  }
				  if (blocT[stage][i] == 3) {
					  blocT[stage][i] = 2;
				  }
				  if (blocT[stage][i] == 5) {
					  blocT[stage][i] = 4;
				  }
				  if (blocT[stage][i] == 7) {
					  blocT[stage][i] = 6;
				  }
			  }
		  }
		  if (deadTimer == 0 && end == 0) {
			  pos.height = (int) (pos.height - velosY);
			  pos.width = (int) (pos.width - velosX);
			  ground = this.onGround(pos);
			  bloc();
			  enemy();
			  testEnemy();
			  effect();
			  bigCoin();
			  checkpoint();
			  if (door()) {
				  if (end == 0) {
					  if (bonus == 2) {
						  bonus = 0;
						  timer = 0;
					  }
					  clear.playSound();
					  pos.height = stageEY[stage];
					  pos.width = stageEX[stage]+32;
					  end = 150;
				  }
			  }
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
	  else if (menu == 3) {
		  while (true) {
			  if (timer == 1) {
				  for (int i = 0; i < enemyT[stage].length; i++) {
					  if (enemyT[stage][i] != 2) {
						  enemyD[stage][i] = false;
					  }
					  else {
						  enemyD[stage][i] = true;
					  }
				  }
				  for (int i = 0; i < bigCoinB[stage].length; i++) {
					  if (bigCoinB[stage][i]) {
						  bigCoinB[stage][i] = false;
					  }
				  }
				  for (int i = 0; i < blocT[stage].length; i++) {
					  if (blocT[stage][i] == 1) {
						  blocT[stage][i] = 0;
					  }
					  if (blocT[stage][i] == 3) {
						  blocT[stage][i] = 2;
					  }
					  if (blocT[stage][i] == 5) {
						  blocT[stage][i] = 4;
					  }
					  if (blocT[stage][i] == 7) {
						  blocT[stage][i] = 6;
					  }
				  }
				  menu = 0;
				  timer = 0;
				  lifes = 5;
				  pos.height = -10;
				  pos.width = -285;
				  velosX = 0;
				  velosY = 0;
				  pan.setPos(0);
				  break;
			  }
			  if (timer > 1) {
				  timer--;
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
	  else if (menu == 4) {
		  while (true) {
			  if (esc) {
				  menuPos = 0;
				  menu = 2;
				  esc = false;
				  break;
			  }
			  if (up) {
				  menuPos--;
				  up = false;
				  if (menuPos < 0) {
					  menuPos = 1;
				  }
			  }
			  if (down) {
				  down = false;
				  menuPos++;
				  if (menuPos > 1) {
					  menuPos = 0;
				  }
			  }
			  if (enter) {
				  enter = false;
				  if (menuPos == 0) {
					  menuPos = 0;
					  menu = 2;
					  break;
				  }
				  if (menuPos == 1) {
					  deadTimer = 0;
					  pos.height = -10;
					  pos.width = -285;
					  velosX = 0;
					  velosY = 0;
					  pan.setPos(0);
					  lastCheckpoint = -1;
					  bonus = 0;
					  timer = 0;
					  for (int i = 0; i < enemyT[stage].length; i++) {
						  if (enemyT[stage][i] != 2) {
							  enemyD[stage][i] = false;
						  }
						  else {
							  enemyD[stage][i] = true;
						  }
					  }
					  for (int i = 0; i < blocT[stage].length; i++) {
						  if (blocT[stage][i] == 1) {
							  blocT[stage][i] = 0;
						  }
						  if (blocT[stage][i] == 3) {
							  blocT[stage][i] = 2;
						  }
						  if (blocT[stage][i] == 5) {
							  blocT[stage][i] = 4;
						  }
						  if (blocT[stage][i] == 7) {
							  blocT[stage][i] = 6;
						  }
					  }
					  menuPos = 0;
					  menu = 0;
					  break;
				  }
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
	  else if (menu == 5) {
		  while (true) {
			  if (esc) {
				  menuPos = 0;
				  menu = 0;
				  esc = false;
				  break;
			  }
			  if (up) {
				  menuPos = (short) (menuPos-5);
				  up = false;
				  if (menuPos < 0) {
					  menuPos = 0;
				  }
			  }
			  if (down) {
				  menuPos = (short) (menuPos+5);
				  down = false;
				  if (menuPos > 15) {
					  menuPos = 15;
				  }
			  }
			  if (right) {
				  menuPos++;
				  right = false;
				  if (menuPos > 15) {
					  menuPos = 0;
				  }
			  }
			  if (left) {
				  menuPos--;
				  left = false;
				  if (menuPos < 0) {
					  menuPos = 15;
				  }
			  }
			  if (enter) {
				  enter = false;
				  if (menuPos == 15) {
					  menuPos = 0;
					  menu = 0;
					  break;
				  }
				  else if (menuPos <= maxStage) {
					  stage = menuPos;
					  menuPos = 0;
					  timer = 200;
					  menu = 1;
					  break;
				  }
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
	  else if (menu == 6) {
		  while (true) {
			  if (up) {
				  menuPos++;
				  up = false;
				  if (menuPos > 1) {
					  menuPos = 0;
				  }
			  }
			  if (down) {
				  down = false;
				  menuPos--;
				  if (menuPos < 0) {
					  menuPos = 1;
				  }
			  }
			  if (enter) {
				  enter = false;
				  if (menuPos == 0) {
					  menuPos = 0;
					  menu = 0;
					  break;
				  }
				  if (menuPos == 1) {
					  stage++;
					  menuPos = 0;
					  menu = 1;
					  timer = 200;
					  break;
				  }
			  }
			  if (esc) {
				  menuPos = 0;
				  menu = 0;
				  break;
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
  }
  
  private void enemy() {
	  for (int i = 0; i < enemyT[stage].length; i++) {
		  if (!enemyD[stage][i]) {
		  if (enemyT[stage][i] == 0) {
			  if (enemyGround(i)) {
				  enemyVY[stage][i] = 0;
			  }
			  else {
				  if (enemyVY[stage][i] < 9) {
					  enemyVY[stage][i] = enemyVY[stage][i]-0.4f;
				  }
			  }
			  enemyVX[stage][i] = 0;
			  if (enemyO[stage][i]) {
				  enemyVX[stage][i] = enemyV;
			  }
			  else {
				  enemyVX[stage][i] = -enemyV;
			  }
			  enemyX[stage][i] = enemyX[stage][i] - enemyVX[stage][i];
			  enemyY[stage][i] = enemyY[stage][i] - enemyVY[stage][i];
			  if (enemyX[stage][i] < enemyZA[stage][i]) {
				  enemyX[stage][i] = enemyX[stage][i] + enemyVX[stage][i];
				  enemyO[stage][i] = !enemyO[stage][i];
			  }
			  if (enemyX[stage][i] > enemyZB[stage][i]-2) {
				  enemyX[stage][i] = enemyX[stage][i] + enemyVX[stage][i];
				  enemyO[stage][i] = !enemyO[stage][i];
			  }
		  }
		  if (enemyT[stage][i] == 1) {
			  if (enemyGround(i)) {
				  enemyVY[stage][i] = 0;
			  }
			  else {
				  if (enemyVY[stage][i] < 9) {
					  enemyVY[stage][i] = enemyVY[stage][i]-0.4f;
				  }
			  }
			  enemyVX[stage][i] = 0;
			  if (enemyO[stage][i]) {
				  enemyVX[stage][i] = enemyV;
			  }
			  else {
				  enemyVX[stage][i] = -enemyV;
			  }
			  enemyX[stage][i] = enemyX[stage][i] - enemyVX[stage][i];
			  enemyY[stage][i] = enemyY[stage][i] - enemyVY[stage][i];
			  if (enemyX[stage][i] < enemyZA[stage][i]) {
				  enemyX[stage][i] = enemyX[stage][i] + enemyVX[stage][i];
				  enemyO[stage][i] = !enemyO[stage][i];
			  }
			  if (enemyX[stage][i] > enemyZB[stage][i]-2) {
				  enemyX[stage][i] = enemyX[stage][i] + enemyVX[stage][i];
				  enemyO[stage][i] = !enemyO[stage][i];
			  }
		  }
		  if (enemyT[stage][i] == 2) {
			  if (enemyGround(i)) {
				  enemyVY[stage][i] = 0;
			  }
			  else {
				  if (enemyVY[stage][i] < 9) {
					  enemyVY[stage][i] = enemyVY[stage][i]-0.4f;
				  }
			  }
			  enemyVX[stage][i] = 0;
			  if (enemyO[stage][i]) {
				  enemyVX[stage][i] = enemyV;
			  }
			  else {
				  enemyVX[stage][i] = -enemyV;
			  }
			  enemyX[stage][i] = enemyX[stage][i] - enemyVX[stage][i];
			  enemyY[stage][i] = enemyY[stage][i] - enemyVY[stage][i];
			  if (enemyX[stage][i] < enemyZA[stage][i]) {
				  enemyX[stage][i] = enemyX[stage][i] + enemyVX[stage][i];
				  enemyO[stage][i] = !enemyO[stage][i];
			  }
			  if (enemyX[stage][i] > enemyZB[stage][i]-2) {
				  enemyX[stage][i] = enemyX[stage][i] + enemyVX[stage][i];
				  enemyO[stage][i] = !enemyO[stage][i];
			  }
		  }
		  }
	  }
  }
  private void testEnemy() {
	  for (int i = 0; i < enemyT[stage].length; i++) {
		  if (!enemyD[stage][i]) {
			  if (enemyT[stage][i] == 0) {
		  if (pos.width > enemyX[stage][i]-15 && pos.width < enemyX[stage][i]+15 && pos.height < enemyY[stage][i]-10 && pos.height > enemyY[stage][i]-20) {
			  enemyD[stage][i] = true;
			  velosY = (up == true ? 8 : 4);
			  scoreS = 200;
			  score = score + 200;
		  }
		  }
		  if (pos.width > enemyX[stage][i]-18 && pos.width < enemyX[stage][i]+18 && pos.height < enemyY[stage][i]+36 && pos.height > enemyY[stage][i]-10) {
			  if (enemyT[stage][i] != 2) {
				  if (timer == 0) {
					  if (deadTimer == 0) {
						  dead = true;
					  }
				  }
			  }
			  else {
				  if (bonus == 0) {
					  bonus = 1;
					  scoreS = 1000;
					  score = score + 1000;
				  }
				  enemyD[stage][i] = true;
				  pop.playSound();
			  }
		  }
		  }
	  }
  }
  private void bloc() {
	  if (!ground) {
	  for (int i = 0; i < blocX[stage].length; i++) {
		  if (blocT[stage][i] != 3) {
		  if (pos.width <= blocX[stage][i] + 32 && pos.width >= blocX[stage][i]) {
		  if (pos.height >= blocY[stage][i]) {
			  if  (pos.height <= blocY[stage][i]+45) {
				  if (pos.height < blocY[stage][i]+11) {
					  pos.height = blocY[stage][i];
						  ground = true;
						  break;
				  }
					  boolean bol = false;
					  if (left) {
						  pos.width = (int) (pos.getWidth()+velosX);
						  bol = true;
					  }
					  if (right) {
						  pos.width = (int) (pos.getWidth()+velosX);
						  bol = true;
					  }
					  if (velosY > 0) {
						  	pos.height = (int) (pos.getHeight()-velosY);
						  	velosY = 0;
						  	if (pos.width <= blocX[stage][i] + 25 && pos.width >= blocX[stage][i] - 9) {
						  		if (blocT[stage][i] == 0) {
						  			blocT[stage][i] = 1;
							  		enemyVY[stage][1] = 5;
							  		enemyVX[stage][1] = 5;
							  		enemyX[stage][1] = 440;
							  		enemyY[stage][1] = -65;
							  		enemyO[stage][1] = false;
							  		enemyD[stage][1] = false;
						  		}
						  		else if (blocT[stage][i] == 2) {
						  			blocT[stage][i] = 3;
						  		}
						  		else if (blocT[stage][i] == 4) {
						  			blocT[stage][i] = 5;
						  			effectT[stage][0] = 1;
						  			coin.playSound();
						  			scoreS = 100;
						  			score = score + 100;
						  		}
						  		else if (blocT[stage][i] == 6) {
						  			blocT[stage][i] = 7;
						  			if (i == 4) {
							  			effectT[stage][1] = 1;
						  			}
						  			else if (i == 5) {
							  			effectT[stage][2] = 1;
						  			}
						  			coin.playSound();
						  			scoreS = 100;
						  			score = score + 100;
						  		}
						  		
						  	}
					  }
					  if (bol) {
						  velosX = 0;
				  }
			  }
		  }
			  
		  }
		  }
	  }
	  }
	  }
private void effect() {
	for (int i = 0; i < effectT[stage].length; i++) {
		if (effectT[stage][i] == 0) {
			if (effectC[stage][i] > 0) {
				effectY[stage][i] = (short) (effectY[stage][i] + effectC[stage][i]);
				effectC[stage][i] = 0;
			}
		}
		else {
			if (effectC[stage][i] < 50) {
				effectC[stage][i]++;
				effectY[stage][i]--;
			}
			else {
				effectC[stage][i] = 50;
				effectT[stage][i] = 0;
			}
		}
	}
}
private void bigCoin() {
	for (int i = 0; i < bigCoinB[stage].length; i++) {
		if (!bigCoinB[stage][i]) {
			if (pos.width < bigCoinX[stage][i]+17 && pos.width > bigCoinX[stage][i]-17 && pos.height < bigCoinY[stage][i]+37 && pos.height > bigCoinY[stage][i]-1) {
				bigCoin.playSound();
				scoreS = 3000;
	  			score = score + 3000;
				bigCoinB[stage][i] = true;
			}
		}
	}
	bigCoinS++;
	if (bigCoinS > 99) {
		bigCoinS = 0;
	}
}
private void checkpoint() {
	for (byte i = 0; i < checkpointX[stage].length; i++) {
		if (lastCheckpoint < i) {
			if (pos.width < checkpointX[stage][i]+17 && pos.width > checkpointX[stage][i]-17 && pos.height < checkpointY[stage][i]+37 && pos.height > checkpointY[stage][i]-1) {
				bigCoin.playSound();
				scoreS = 2000;
	  			score = score + 2000;
				lastCheckpoint = i;
			}
		}
	}
	bigCoinS++;
	if (bigCoinS > 99) {
		bigCoinS = 0;
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
private boolean door() {
	if (pos.width > stageEX[stage]+10 && pos.width < stageEX[stage]+54 && pos.height < stageEY[stage]+20 && pos.height > stageEY[stage]-32) {
		return true;
	}
	else {
		return false;
	}
}
private boolean enemyGround(int i) {
	boolean result = false;
	  for (int x = 0; x < sol1[stage].length; x++) {
		  for (int a = sol1[stage][x]; a < sol2[stage][x]; a++) {
		  if (enemyX[stage][i] == a) {
		  if (enemyY[stage][i] >= solP1[stage][x]) {
			  if  (enemyY[stage][i] <= solP2[stage][x]) {
				  if (enemyY[stage][i] < solP1[stage][x]+15) {
					  enemyY[stage][i] = solP1[stage][x];
					  if (solS[stage][x] == 1) {
						  if (enemyVY[stage][i] <= 0) {
							  result = true;
						  }
					  }
					  else {
						  result = true;
					  }
					  break;
				  }
			  }
		  }
			  
		  }
	  }
	  }
	  return result;
}
  
private boolean onGround(Dimension position) {
	  boolean result = false;
	  for (int i = 0; i < sol1[stage].length; i++) {
		  for (int a = sol1[stage][i]; a < sol2[stage][i]; a++) {
		  if (position.width == a) {
		  if (position.height >= solP1[stage][i]) {
			  if  (position.height <= solP2[stage][i]) {
				  if (solS[stage][i] == 2) {
					  if (timer == 0) {
						  if (deadTimer == 0) {
							  dead = true;
						  }
					  }
				  }
				  if (position.height < solP1[stage][i]+11) {
					  position.height = solP1[stage][i];
					  if (solS[stage][i] == 1) {
						  if (velosY <= 0) {
							  result = true;
						  }
					  }
					  else {
						  result = true;
					  }
					  break;
				  }
				  else if (solS[stage][i] != 1) {
					  boolean bol = false;
					  if (left) {
						  pos.width = (int) (pos.getWidth()+velosX);
						  bol = true;
					  }
					  if (right) {
						  pos.width = (int) (pos.getWidth()+velosX);
						  bol = true;
					  }
					  if (up) {
						  	pos.height = (int) (pos.getHeight()-velosY);
						  	velosY = 0;
					  }
					  if (bol) {
						  velosX = 0;
					  }
				  }
			  }
		  }
			  
		  }
	  }
	  }
	  return result;
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
	  if (event.getKeyCode() == 83) {
			s = true;
	  }
	  if (event.getKeyCode() == 56) {
			T = true;
	  }
	  if (event.getKeyCode() == 76) {
			l = true;
	  }
	  if (event.getKeyCode() == 513) {
			slash = true;
	  }
	  if (event.getKeyCode() == 27) {
			esc = true;
	  }
	  if (event.getKeyCode() == 75) {
		  debug = true;
	  }
	  // Toolkit.getDefaultToolkit().beep();
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
	  if (event.getKeyCode() == 83) {
			s = false;
	  }
	  if (event.getKeyCode() == 56) {
			T = false;
	  }
	  if (event.getKeyCode() == 76) {
			l = false;
	  }
	  if (event.getKeyCode() == 513) {
			slash = false;
	  }
	  if (event.getKeyCode() == 27) {
			esc = false;
	  }
	  if (event.getKeyCode() == 75) {
		  debug = false;
	  }
	  // System.out.println(""+event.getKeyCode());
  }
  @Override
  public void keyTyped(KeyEvent event) {
	  
  }
}