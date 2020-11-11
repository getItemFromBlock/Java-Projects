import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game1CreatorR extends JPanel {
	private static final long serialVersionUID = -4067780344408712844L;

	private int pos = 0;
	private float coef = 1.0f;
	private int maxDim = 0;
	
	public Game1CreatorR() {
		
	}
	
	public void paintComponent(Graphics g) {
		if (this.getHeight() >= this.getWidth()) {
			maxDim = this.getWidth();
		}
		else {
			maxDim = this.getHeight();
		}
		coef = ((float)(maxDim))/600.0f;
		g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,this.getWidth(),this.getHeight());
		g.setColor(Color.decode("#FFFFFF"));
	    g.fillRect((((this.getWidth()-maxDim)/2)), (((this.getHeight()-maxDim)/2)), maxDim, maxDim);
	    //System.out.println(""+coef+" "+this.getWidth()+" "+this.getHeight());
	    g.setFont(new Font("Dialog", Font.PLAIN, (int)(12.0f*coef)));
	    if (Game1Creator.pos.width > pos + 50) {
	    	pos = Game1Creator.pos.width - 50;
	    }
	    if (Game1Creator.pos.width < pos - 50 && pos > 0) {
	    	pos = Game1Creator.pos.width + 50;
	    }
	    if (pos < 0) {
	    	pos = 0;
	    }
	    for (int i = 0; i < Game1Creator.sol1.size(); i++) {
	    	if (Game1Creator.sol1.get(i)+300 > pos-350 || Game1Creator.sol2.get(i)-300 < pos+350) {
	    		if (Game1Creator.solC.get(i) == 0) {
	    	    	fillRectWithImg(g, Game1Creator.sol_0, 300+Game1Creator.sol1.get(i)+8-pos, 300+Game1Creator.solP1.get(i), Game1Creator.sol2.get(i)-Game1Creator.sol1.get(i)-16, Game1Creator.solP2.get(i)-Game1Creator.solP1.get(i)-16);
	    		}
	    		if (Game1Creator.solC.get(i) == 1) {
	    	    	fillRectWithImg(g, Game1Creator.sol_1, 300+Game1Creator.sol1.get(i)+8-pos, 300+Game1Creator.solP1.get(i), Game1Creator.sol2.get(i)-Game1Creator.sol1.get(i)-16, Game1Creator.solP2.get(i)-Game1Creator.solP1.get(i)-16);
	    		}
	    		if (Game1Creator.solC.get(i) == 2) {
	    	    	fillRectWithImg(g, Game1Creator.sol_2, 300+Game1Creator.sol1.get(i)+8-pos, 300+Game1Creator.solP1.get(i), Game1Creator.sol2.get(i)-Game1Creator.sol1.get(i)-16, Game1Creator.solP2.get(i)-Game1Creator.solP1.get(i)-16);
	    		}
	    		if (Game1Creator.solC.get(i) == 3) {
	    	    	fillRectWithImg(g, Game1Creator.sol_3, 300+Game1Creator.sol1.get(i)+8-pos, 300+Game1Creator.solP1.get(i), Game1Creator.sol2.get(i)-Game1Creator.sol1.get(i)-16, Game1Creator.solP2.get(i)-Game1Creator.solP1.get(i)-16);
	    		}
	    		if (Game1Creator.solC.get(i) == 4) {
	    	    	fillRectWithImg(g, Game1Creator.sol_4, 300+Game1Creator.sol1.get(i)+8-pos, 300+Game1Creator.solP1.get(i), Game1Creator.sol2.get(i)-Game1Creator.sol1.get(i)-16, Game1Creator.solP2.get(i)-Game1Creator.solP1.get(i)-16);
	    		}
	    	}
	    }
	    for (int i = 0; i < Game1Creator.blocT.size(); i++) {
	    	if (Game1Creator.blocT.get(i) != 3) {
	    		if (Game1Creator.blocT.get(i) != 6) {
	    			if (Game1Creator.blocX.get(i)+300 > pos-350 && Game1Creator.blocX.get(i)-300 < pos+350) {
	    	    		drawNewImage(g, Game1Creator.blocT.get(i) == 0 ? Game1Creator.bloc_0 : (Game1Creator.blocT.get(i) == 1 ? Game1Creator.bloc_1 : (Game1Creator.blocT.get(i) == 2 ? Game1Creator.bloc_2 : (Game1Creator.blocT.get(i) == 4 ? Game1Creator.bloc_0 : (Game1Creator.blocT.get(i) == 7 ? Game1Creator.bloc_1 : Game1Creator.bloc_1)))), 300+Game1Creator.blocX.get(i)+8-pos, 300+Game1Creator.blocY.get(i));
	    	    	}
	    		}
	    	}
	    }
	    g.setColor(Color.decode("#FF0000"));
	    fillNewOval(g, 300+(int)Game1Creator.pos.getWidth()-10-pos, 300+(int)Game1Creator.pos.getHeight()-20, 20, 20);
	    if (Game1Creator.show) {
	    	g.drawString("Times :"+Game1Creator.speed, (((this.getWidth()-maxDim)/2))+(int)(30*coef), (((this.getHeight() -maxDim)/2))+(int)(70*coef));
		    g.drawString("FPS :"+Game1Creator.fps, (((this.getWidth()-maxDim)/2))+(int)(130*coef), (((this.getHeight() -maxDim)/2))+(int)(30*coef));
		    g.drawString("PosX :"+Game1Creator.pos.width, (((this.getWidth()-maxDim)/2))+(int)(130*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
		    g.drawString("PosY :"+Game1Creator.pos.height, (((this.getWidth()-maxDim)/2))+(int)(130*coef), (((this.getHeight() -maxDim)/2))+(int)(70*coef));
		    g.drawString("Mouse X :"+Game1Creator.mPosX, (((this.getWidth()-maxDim)/2))+(int)(230*coef), (((this.getHeight() -maxDim)/2))+(int)(30*coef));
		    g.drawString("Mouse Y :"+Game1Creator.mPosY, (((this.getWidth()-maxDim)/2))+(int)(230*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
		    g.drawString("Debug mode, press K to switch", (((this.getWidth()-maxDim)/2))+(int)(230*coef), (((this.getHeight() -maxDim)/2))+(int)(70*coef));
	    }
	    g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,(this.getWidth()-maxDim)/2,this.getHeight());
	    g.fillRect((this.getWidth()-maxDim)/2+maxDim,0,((this.getWidth()-maxDim)/2)+1,this.getHeight());
	    g.fillRect(0,0,this.getWidth(),(this.getHeight()-maxDim)/2);
	    g.fillRect(0,(this.getHeight()-maxDim)/2+maxDim,this.getWidth(),((this.getHeight()-maxDim)/2)+1);
	}
	public void fillNewRect(Graphics a, int x, int y, int l, int w) {
		a.fillRect(((this.getWidth()-maxDim)/2)+(int)(x*coef), ((this.getHeight() -maxDim)/2)+(int)(y*coef), (int)(l*coef), (int)(w*coef));
	}
	public void fillNewOval(Graphics a, int x, int y, int l, int w) {
		a.fillOval((((this.getWidth()-maxDim)/2))+(int)(x*coef), (((this.getHeight() -maxDim)/2))+(int)(y*coef), (int)(l*coef), (int)(w*coef));
	}
	public void drawNewImage(Graphics a, BufferedImage img, int x, int y, boolean reverse) {
		if (reverse) {
			a.drawImage(img,(((this.getWidth()-maxDim)/2))+(int)(x*coef)+(int)(img.getWidth()*coef), (((this.getHeight() -maxDim)/2))+(int)(y*coef), -(int)(img.getWidth() *coef), (int)(img.getHeight()*coef), this);
		}
		else {
			a.drawImage(img,(((this.getWidth()-maxDim)/2))+(int)(x*coef), (((this.getHeight() -maxDim)/2))+(int)(y*coef), (int)(img.getWidth() *coef), (int)(img.getHeight()*coef), this);
		}
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
	public float getCoef() {
		return coef;
	}
	public float getPos() {
		return pos;
	}
	public float getMaxDim() {
		return maxDim;
	}
}
