import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameBackground4 extends JPanel {

	private static final long serialVersionUID = 6514646313541351L;
	private int pos = 0;
	private int timer = 0;
	private int displayScore = 0;
	private Dimension oldPos = new Dimension(0,0);
	private float coef = 1.0f;
	private int maxDim = 0;
	
	public GameBackground4() {
	}
	public void paintComponent(Graphics g) {
		if (this.getHeight() >= this.getWidth()) {
			maxDim = this.getWidth();
		}
		else {
			maxDim = this.getHeight();
		}
		coef = ((float)(maxDim))/584.0f;
		g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,this.getWidth(),this.getHeight());
		g.setColor(Color.decode("#FFFFFF"));
	    g.fillRect((((this.getWidth()-maxDim)/2)), (((this.getHeight()-maxDim)/2)), maxDim, maxDim);
	    if (Game4.menu == 0) {
	    	g.setColor(Color.decode("#333366"));
	    	fillNewRect(g, 150, 170, 300, 70);
	    	fillNewRect(g, 150, 270, 300, 70);
	    	fillNewRect(g, 150, 370, 300, 70);
	    	g.setColor(Color.decode("#9999CC"));
	    	if (Game4.menuPos == 0) {
	    		fillNewRect(g, 150, 170, 300, 70);
	    	}
	    	if (Game4.menuPos == 1) {
	    		fillNewRect(g, 150, 270, 300, 70);
	    	}
	    	if (Game4.menuPos == 2) {
	    		fillNewRect(g, 150, 370, 300, 70);
	    	}
	    	g.setColor(Color.decode("#000000"));
	    	g.setFont(new Font("Dialog", Font.BOLD, (int)(40.0f*coef)));
	    	g.drawString("Platform Game", (((this.getWidth()-maxDim)/2))+(int)(160*coef), (((this.getHeight() -maxDim)/2))+(int)(100*coef));
	    	g.setFont(new Font("Dialog", Font.PLAIN, (int)(22.0f*coef)));
	    	g.drawString("Start", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(213*coef));
	    	g.drawString("Stages", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(313*coef));
	    	g.drawString("Exit", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(413*coef));
	    }
	    else if (Game4.menu == 1) {
	    	g.setColor(Color.decode("#000000"));
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		    g.setColor(Color.decode("#FFFFFF"));
		    g.setFont(new Font("Dialog", Font.PLAIN, (int)(22.0f*coef)));
		    g.drawString("Stage - "+(Game4.stage+1), (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(250*coef));
	    }
	    else if (Game4.menu == 2 || Game4.menu == 4) {
	    g.setFont(new Font("Dialog", Font.PLAIN, (int)(12.0f*coef)));
	    if (Game4.pos.width+100 > pos + 50) {
	    	pos = pos + 3;
	    }
	    if (Game4.pos.width+100 < pos - 50 && pos > 0) {
	    	pos = pos - 3;
	    }
	    for (int i = 0; i < Game4.solX[Game4.stage].length; i++) {
	    	if (Game4.solX[Game4.stage][i]+300 > pos-350 && Game4.solX[Game4.stage][i]-300 < pos+350) {
	    		g.setColor(Color.decode(Game4.solC[Game4.stage][i] == 0 ? "#116611" : (Game4.solC[Game4.stage][i] == 1 ? "#AAAA00" : (Game4.solC[Game4.stage][i] == 2 ? "#AAAAAA" : (Game4.solC[Game4.stage][i] == 3 ? "#00AAAA" : "#FF0000")))));
	    		fillNewOval(g,410+Game4.solX[Game4.stage][i]-pos-(Game4.solL[Game4.stage][i]), 410+Game4.solY[Game4.stage][i]-(Game4.solL[Game4.stage][i]), Game4.solL[Game4.stage][i]*2-20, Game4.solL[Game4.stage][i]*2-20);
	    		g.setColor(Color.decode("#000000"));
	    		fillNewRect(g, Game4.solX[Game4.stage][i]-pos+398, Game4.solY[Game4.stage][i]+398,4,4);
	    	}
	    }

	    if (Game4.stageEX[Game4.stage]+300 > pos-350 && Game4.stageEX[Game4.stage]-300 < pos+350) {
	    	drawNewImage(g,Game4.door, 300+Game4.stageEX[Game4.stage]-pos, 236+Game4.stageEY[Game4.stage]);
	    }
	    
	    g.setColor(Game4.bonus == 0 ? (Game4.deadTimer == 0 ? Color.red : Color.black) : (Game4.bonus == 1 ? Color.BLUE : Color.red));
	    DrawRotatedimage(Game4.rot, (float)(Game4.pos.getWidth())-pos-(Game4.car.getWidth()/2), (float)(Game4.pos.getHeight())-(Game4.car.getHeight()/2),Game4.car,g);
	    g.setColor(Color.BLACK);
	    g.drawString("Lifes :"+Game4.lifes, (((this.getWidth()-maxDim)/2))+(int)(30*coef), (((this.getHeight() -maxDim)/2))+(int)(30*coef));
	    g.drawString("Score :"+Game4.score, (((this.getWidth()-maxDim)/2))+(int)(30*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
	    for (int i = 0; i < Game4.bigCoinB[Game4.stage].length; i++) {
	    	if (Game4.bigCoinB[Game4.stage][i]) {
	    		drawNewImage(g,Game4.BigCoin, 270+(30*i), 30);
	    	}
	    	else {
	    		drawNewImage(g,Game4.BigCoinVoid, 270+(30*i), 30);
	    	}
	    }
	    if (Game4.show) {
	    	g.drawString("Times :"+Game4.speed, (((this.getWidth()-maxDim)/2))+(int)(30*coef), (((this.getHeight() -maxDim)/2))+(int)(70*coef));
		    g.drawString("FPS :"+Game4.fps, (((this.getWidth()-maxDim)/2))+(int)(130*coef), (((this.getHeight() -maxDim)/2))+(int)(30*coef));
		    g.drawString("PosX :"+Game4.pos.width, (((this.getWidth()-maxDim)/2))+(int)(130*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
		    g.drawString("PosY :"+Game4.pos.height, (((this.getWidth()-maxDim)/2))+(int)(130*coef), (((this.getHeight() -maxDim)/2))+(int)(70*coef));
		    g.drawString("Debug mode, press K to switch", (((this.getWidth()-maxDim)/2))+(int)(230*coef), (((this.getHeight() -maxDim)/2))+(int)(70*coef));
	    }
	    if (Game4.menu == 2) {
	    	if (Game4.scoreS > 0) {
		    	timer = 70;
		    	displayScore = Game4.scoreS;
		    	oldPos.width = 300+(int)Game4.pos.getWidth()-pos;
		    	oldPos.height = 300+(int)Game4.pos.getHeight();
		    	oldPos.height = oldPos.height - 30;
		    	Game4.scoreS = 0;
		    }
		    if (timer > 1) {
		    	timer = timer - 1;
		    	oldPos.height = oldPos.height - 1;
		    	g.setColor(Color.BLACK);
		    	g.drawString(""+displayScore, (((this.getWidth()-maxDim)/2))+(int)((oldPos.width-10)*coef), (((this.getHeight() -maxDim)/2))+(int)((oldPos.height)*coef));
		    }
		    if (timer == 1) {
		    	timer = 0;
		    	displayScore = 0;
		    	oldPos.height = 0;
		    	oldPos.width = 0;
		    }
	    }
	    if (Game4.menu == 4) {
	    	g.setColor(Color.decode("#999999"));
	    	fillNewRect(g,142, 135, 300, 300);
	    	g.setColor(Color.decode("#000000"));
	    	g.drawRect((((this.getWidth()-maxDim)/2))+(int)(142*coef), (((this.getHeight() -maxDim)/2))+(int)(135*coef), (int)(300*coef), (int)(300*coef));
	    	g.setFont(new Font("Dialog", Font.PLAIN, (int)(22.0f*coef)));
		    g.drawString("Stage - "+(Game4.stage+1), (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(200*coef));
		    g.setColor(Color.decode("#333366"));
		    fillNewRect(g,185, 260, 220, 50);
		    fillNewRect(g,185, 350, 220, 50);
		    g.setColor(Color.decode("#9999CC"));
		    if (Game4.menuPos == 0) {
		    	fillNewRect(g,185, 260, 220, 50);
		    }
		    else {
		    	fillNewRect(g,185, 350, 220, 50);
		    }
		    g.setColor(Color.decode("#000000"));
		    g.drawString("Back", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(292*coef));
		    g.drawString("Quit", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(382*coef));
	    }
	    }
	    else if (Game4.menu == 3) {
	    	g.setColor(Color.decode("#000000"));
		    g.fillRect(0, 0, this.getWidth(), this.getHeight());
		    g.setColor(Color.decode("#FFFFFF"));
		    g.setFont(new Font("Dialog", Font.PLAIN, (int)(22.0f*coef)));
		    g.drawString("Game Over", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(250*coef));
	    }
	    if (Game4.menu == 5) {
	    	g.setColor(Color.decode("#333366"));
	    	fillNewRect(g,127, 180, 50, 50);
	    	fillNewRect(g,197, 180, 50, 50);
	    	fillNewRect(g,267, 180, 50, 50);
	    	fillNewRect(g,337, 180, 50, 50);
	    	fillNewRect(g,407, 180, 50, 50);
	    	fillNewRect(g,127, 250, 50, 50);
	    	fillNewRect(g,197, 250, 50, 50);
	    	fillNewRect(g,267, 250, 50, 50);
	    	fillNewRect(g,337, 250, 50, 50);
	    	fillNewRect(g,407, 250, 50, 50);
	    	fillNewRect(g,127, 320, 50, 50);
	    	fillNewRect(g,197, 320, 50, 50);
	    	fillNewRect(g,267, 320, 50, 50);
	    	fillNewRect(g,337, 320, 50, 50);
	    	fillNewRect(g,407, 320, 50, 50);
	    	fillNewRect(g,187, 420, 210, 50);
	    	g.setColor(Color.decode("#9999CC"));
	    	switch (Game4.menuPos) {
	    	case 0:
	    		fillNewRect(g,127, 180, 50, 50);
	    		break;
	    	case 1 :
	    		fillNewRect(g,197, 180, 50, 50);
	    		break;
	    	case 2:
	    		fillNewRect(g,267, 180, 50, 50);
	    		break;
	    	case 3:
	    		fillNewRect(g,337, 180, 50, 50);
	    		break;
	    	case 4:
	    		fillNewRect(g,407, 180, 50, 50);
	    		break;
	    	case 5:
	    		fillNewRect(g,127, 250, 50, 50);
	    		break;
	    	case 6:
	    		fillNewRect(g,197, 250, 50, 50);
	    		break;
	    	case 7:
	    		fillNewRect(g,267, 250, 50, 50);
	    		break;
	    	case 8:
	    		fillNewRect(g,337, 250, 50, 50);
	    		break;
	    	case 9:
	    		fillNewRect(g,407, 250, 50, 50);
	    		break;
	    	case 10:
	    		fillNewRect(g,127, 320, 50, 50);
	    		break;
	    	case 11:
	    		fillNewRect(g,197, 320, 50, 50);
	    		break;
	    	case 12:
	    		fillNewRect(g,267, 320, 50, 50);
	    		break;
	    	case 13:
	    		fillNewRect(g,337, 320, 50, 50);
	    		break;
	    	case 14:
	    		fillNewRect(g,407, 320, 50, 50);
	    		break;
	    	case 15:
	    		fillNewRect(g,187, 420, 210, 50);
	    		break;
	    	}
	    	if (Game4.maxStage < 14) {
	    		drawNewImage(g,Game4.lock, 407, 320);
	    	}
	    	if (Game4.maxStage < 13) {
	    		drawNewImage(g,Game4.lock, 337, 320);
	    	}
	    	if (Game4.maxStage < 12) {
	    		drawNewImage(g,Game4.lock, 267, 320);
	    	}
	    	if (Game4.maxStage < 11) {
	    		drawNewImage(g,Game4.lock, 197, 320);
	    	}
	    	if (Game4.maxStage < 10) {
	    		drawNewImage(g,Game4.lock, 127, 320);
	    	}
	    	if (Game4.maxStage < 9) {
	    		drawNewImage(g,Game4.lock, 407, 250);
	    	}
	    	if (Game4.maxStage < 8) {
	    		drawNewImage(g,Game4.lock, 337, 250);
	    	}
	    	if (Game4.maxStage < 7) {
	    		drawNewImage(g,Game4.lock, 267, 250);
	    	}
	    	if (Game4.maxStage < 6) {
	    		drawNewImage(g,Game4.lock, 197, 250);
	    	}
	    	if (Game4.maxStage < 5) {
	    		drawNewImage(g,Game4.lock, 127, 250);
	    	}
	    	if (Game4.maxStage < 4) {
	    		drawNewImage(g,Game4.lock, 407, 180);
	    	}
	    	if (Game4.maxStage < 3) {
	    		drawNewImage(g,Game4.lock, 337, 180);
	    	}
	    	if (Game4.maxStage < 2) {
	    		drawNewImage(g,Game4.lock, 267, 180);
	    	}
	    	if (Game4.maxStage < 1) {
	    		drawNewImage(g,Game4.lock, 197, 180);
	    	}
	    	g.setColor(Color.decode("#000000"));
	    	g.setFont(new Font("Dialog", Font.PLAIN, (int)(22.0f*coef)));
	    	g.drawString("1", (((this.getWidth()-maxDim)/2))+(int)(146*coef), (((this.getHeight() -maxDim)/2))+(int)(212*coef));
	    	g.drawString("2", (((this.getWidth()-maxDim)/2))+(int)(216*coef), (((this.getHeight() -maxDim)/2))+(int)(212*coef));
	    	g.drawString("3", (((this.getWidth()-maxDim)/2))+(int)(286*coef), (((this.getHeight() -maxDim)/2))+(int)(212*coef));
	    	g.drawString("4", (((this.getWidth()-maxDim)/2))+(int)(356*coef), (((this.getHeight() -maxDim)/2))+(int)(212*coef));
	    	g.drawString("5", (((this.getWidth()-maxDim)/2))+(int)(426*coef), (((this.getHeight() -maxDim)/2))+(int)(212*coef));
	    	g.drawString("6", (((this.getWidth()-maxDim)/2))+(int)(146*coef), (((this.getHeight() -maxDim)/2))+(int)(282*coef));
	    	g.drawString("7", (((this.getWidth()-maxDim)/2))+(int)(216*coef), (((this.getHeight() -maxDim)/2))+(int)(282*coef));
	    	g.drawString("8", (((this.getWidth()-maxDim)/2))+(int)(286*coef), (((this.getHeight() -maxDim)/2))+(int)(282*coef));
	    	g.drawString("9", (((this.getWidth()-maxDim)/2))+(int)(356*coef), (((this.getHeight() -maxDim)/2))+(int)(282*coef));
	    	g.drawString("10", (((this.getWidth()-maxDim)/2))+(int)(420*coef), (((this.getHeight() -maxDim)/2))+(int)(282*coef));
	    	g.drawString("11", (((this.getWidth()-maxDim)/2))+(int)(140*coef), (((this.getHeight() -maxDim)/2))+(int)(352*coef));
	    	g.drawString("12", (((this.getWidth()-maxDim)/2))+(int)(210*coef), (((this.getHeight() -maxDim)/2))+(int)(352*coef));
	    	g.drawString("13", (((this.getWidth()-maxDim)/2))+(int)(280*coef), (((this.getHeight() -maxDim)/2))+(int)(352*coef));
	    	g.drawString("14", (((this.getWidth()-maxDim)/2))+(int)(350*coef), (((this.getHeight() -maxDim)/2))+(int)(352*coef));
	    	g.drawString("15", (((this.getWidth()-maxDim)/2))+(int)(420*coef), (((this.getHeight() -maxDim)/2))+(int)(352*coef));
	    	g.drawString("Back", (((this.getWidth()-maxDim)/2))+(int)(265*coef), (((this.getHeight() -maxDim)/2))+(int)(452*coef));
	    }
	    if (Game4.menu == 6) {
		    g.setColor(Color.decode("#000000"));
		    g.setFont(new Font("Dialog", Font.PLAIN, (int)(22.0f*coef)));
		    g.drawString("Stage Clear !", (((this.getWidth()-maxDim)/2))+(int)(230*coef), (((this.getHeight() -maxDim)/2))+(int)(180*coef));
		    for (int i = 0; i < Game4.bigCoinB[Game4.stage].length; i++) {
		    	if (Game4.bigCoinM[Game4.stage][i]) {
		    		drawNewImage(g,Game4.BigCoin, 250+(30*i), 200);
		    	}
		    	else {
		    		drawNewImage(g,Game4.BigCoinVoid, 250+(30*i), 200);
		    	}
		    }
		    g.setColor(Color.decode("#333366"));
		    fillNewRect(g,185, 280, 220, 50);
		    fillNewRect(g,185, 370, 220, 50);
		    g.setColor(Color.decode("#9999CC"));
		    if (Game4.menuPos == 0) {
		    	fillNewRect(g,185, 280, 220, 50);
		    }
		    else {
		    	fillNewRect(g,185, 370, 220, 50);
		    }
		    g.setColor(Color.decode("#000000"));
		    g.drawString("Menu", (((this.getWidth()-maxDim)/2))+(int)(260*coef), (((this.getHeight() -maxDim)/2))+(int)(312*coef));
		    g.drawString("Next Stage", (((this.getWidth()-maxDim)/2))+(int)(240*coef), (((this.getHeight() -maxDim)/2))+(int)(402*coef));
		    
	    }
	    g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,(this.getWidth()-maxDim)/2,this.getHeight());
	    g.fillRect((this.getWidth()-maxDim)/2+maxDim,0,((this.getWidth()-maxDim)/2)+1,this.getHeight());
	    g.fillRect(0,0,this.getWidth(),(this.getHeight()-maxDim)/2);
	    g.fillRect(0,(this.getHeight()-maxDim)/2+maxDim,this.getWidth(),((this.getHeight()-maxDim)/2)+1);
	  }
	public void fillNewRect(Graphics a, int x, int y, int l, int w) {
		a.fillRect((((this.getWidth()-maxDim)/2))+(int)(x*coef), (((this.getHeight() -maxDim)/2))+(int)(y*coef), (int)(l*coef), (int)(w*coef));
	}
	public void fillNewOval(Graphics a, int x, int y, int l, int w) {
		a.fillOval((((this.getWidth()-maxDim)/2))+(int)(x*coef), (((this.getHeight() -maxDim)/2))+(int)(y*coef), (int)(l*coef), (int)(w*coef));
	}
	public void drawNewImage(Graphics a, BufferedImage img, int x, int y) {
		a.drawImage(img,(((this.getWidth()-maxDim)/2))+(int)(x*coef), (((this.getHeight() -maxDim)/2))+(int)(y*coef), (int)(img.getWidth() *coef), (int)(img.getHeight()*coef), this);
	}
	public void fillRectWithImg(Graphics a, BufferedImage img, int x, int y, int w, int h) {
		x = (((this.getWidth()-maxDim)/2))+(int)(x*coef);
		y = (((this.getHeight() -maxDim)/2))+(int)(y*coef);

		for (int l = 0; l*img.getHeight() < h; l++) {
			for (int i = 0; i*img.getWidth() < w; i++) {
				if (l*img.getHeight() < h-img.getHeight()) {
					if (i*img.getWidth() < w-img.getWidth()) {
						a.drawImage(img, x+(int)(img.getWidth()*coef*i), y+(int)(img.getHeight()*coef*l), (int)((img.getWidth()*coef)+0.8f), (int)((img.getHeight()*coef)+0.8f), this);
					}
					else {
						a.drawImage(img, x+(int)(img.getWidth()*coef*i), y+(int)(img.getHeight()*coef*l), x+(int)(img.getWidth()*coef*i)+(int)(((w-(i*img.getWidth()))*coef)+0.8f), y+(int)(img.getHeight()*coef*l)+(int)((img.getHeight()*coef)+0.8f), img.getWidth()-(int)(w-(i*img.getWidth())), 0, img.getWidth(), img.getHeight(), this);
						// System.out.println("X1 : "+(x+(int)(img.getWidth()*coef*i))+"\tY1 :"+(y+(int)(img.getHeight()*coef*l))+"\tX2 :"+(int)(((w-(i*img.getWidth()))*coef)+0.8f)+"\tY2 :"+(int)((img.getHeight()*coef)+0.8f));
					}
					
				}
				else {
					if (i*img.getWidth() < w-img.getWidth()) {
						a.drawImage(img, x+(int)(img.getWidth()*coef*i), y+(int)(img.getHeight()*coef*l), x+(int)(img.getWidth()*coef*i)+(int)((img.getWidth()*coef)+0.8f), y+(int)(img.getHeight()*coef*l)+(int)(((h-(l*img.getHeight()))*coef)+0.8f), 0, img.getHeight()-(int)(h-(l*img.getHeight())), img.getWidth(), img.getHeight(), this);
					}
					else {
						a.drawImage(img, x+(int)(img.getWidth()*coef*i), y+(int)(img.getHeight()*coef*l), x+(int)(img.getWidth()*coef*i)+(int)(((w-(i*img.getWidth()))*coef)+0.8f), y+(int)(img.getHeight()*coef*l)+(int)(((h-(l*img.getHeight()))*coef)+0.8f), img.getWidth()-(int)(w-(i*img.getWidth())), img.getHeight()-(int)(h-(l*img.getHeight())), img.getWidth(), img.getHeight(), this);
					}
				}
			}
		}
	}
	public void DrawRotatedimage(float rot, float x, float y, BufferedImage image, Graphics g) {
		double rotationRequired = Math.toRadians(rot);
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, image.getWidth()/2, image.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		drawNewImage(g, op.filter(image, null), (int) (400+x), (int) (400+y));
	}
	public void setPos(int position) {
		pos = position;
	}
}
