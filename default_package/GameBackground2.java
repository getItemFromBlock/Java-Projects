import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameBackground2 extends JPanel {

	private static final long serialVersionUID = -7867669486817616666L;
	
	private static int posX = 0;
	private static int posY = 0;
	private float coef = 1.0f;
	private int maxDim = 0;

	public GameBackground2() {
		
	}
	
	public void paintComponent(Graphics g) {
		if (this.getHeight() >= this.getWidth()) {
			maxDim = this.getWidth();
		}
		else {
			maxDim = this.getHeight();
		}
		if (Game2.posX > posX + 100) {
	    	posX = (int) (Game2.posX - 100);
	    }
		if (Game2.posX < posX - 150) {
	    	posX = (int) (Game2.posX + 150);
	    }
		if (Game2.posY > posY + 100) {
	    	posY = (int) (Game2.posY - 100);
	    }
		if (Game2.posY < posY - 150) {
	    	posY = (int) (Game2.posY + 150);
	    }
		coef = ((float)(maxDim))/800.0f;
		g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,this.getWidth(),this.getHeight());
		if (Game2.ship != null) {
			fillRectWithImg(g, Game2.space, 400-1024-posX,400-1024-posY,2048,2048);
			drawRotatedImage(Game2.rot, Game2.posX-posX, Game2.posY-posY, Game2.ship, g);
			for (int i = 0; i < Game2Laser.Xcoord.size(); i++) {
				drawRotatedImage(Game2Laser.rotation.get(i), Game2Laser.Xcoord.get(i)-posX, Game2Laser.Ycoord.get(i)-posY, Game2.laser, g);
			}
			for (int i = 0; i < Game2Asteroid.Xcoord.size(); i++) {
				drawRotatedImage(Game2Asteroid.rotation.get(i), Game2Asteroid.Xcoord.get(i)-posX, Game2Asteroid.Ycoord.get(i)-posY, Game2.asteroid, g);
			}
		}
		g.setColor(Color.decode("#FFFFFF"));
	    g.setFont(new Font("Dialog", Font.PLAIN, (int)(12.0f*coef)));
	    g.drawString("FPS: "+(Game2.fps), (((this.getWidth()-maxDim)/2))+(int)(50*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
	    g.drawString("Asteroids: "+(Game2Asteroid.Xcoord.size()), (((this.getWidth()-maxDim)/2))+(int)(150*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
	    g.drawString("Score: "+(Game2.score), (((this.getWidth()-maxDim)/2))+(int)(250*coef), (((this.getHeight() -maxDim)/2))+(int)(50*coef));
		g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,(this.getWidth()-maxDim)/2,this.getHeight());
	    g.fillRect((this.getWidth()-maxDim)/2+maxDim,0,((this.getWidth()-maxDim)/2)+1,this.getHeight());
	    g.fillRect(0,0,this.getWidth(),(this.getHeight()-maxDim)/2);
	    g.fillRect(0,(this.getHeight()-maxDim)/2+maxDim,this.getWidth(),((this.getHeight()-maxDim)/2)+1);
	}
	
	public void drawRotatedImage(float rot, float x, float y, BufferedImage image, Graphics g) {
		double rotationRequired = Math.toRadians(rot);
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, image.getWidth()/2, image.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
		drawNewImage(g, op.filter(image, null), (int) (400+x), (int) (400+y));
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
}
