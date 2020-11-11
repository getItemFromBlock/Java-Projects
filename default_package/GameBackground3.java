import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GameBackground3 extends JPanel {
	
	private static final long serialVersionUID = -1172543725982478065L;
	
	public float coef = 1.0f;
	public int maxDim = 0;
	
	public GameBackground3() {
	}
	public void paintComponent(Graphics g) {
		if (this.getHeight() >= this.getWidth()) {
			maxDim = this.getWidth();
		}
		else {
			maxDim = this.getHeight();
		}
		coef = ((float)(maxDim))/650.0f;
		//System.out.println(coef);
		g.setColor(Color.decode("#000000"));
	    g.fillRect(0,0,this.getWidth(),this.getHeight());
		g.setColor(Color.decode("#FFFFFF"));
	    g.fillRect((((this.getWidth()-maxDim)/2)), (((this.getHeight()-maxDim)/2)), maxDim, maxDim);
	    g.setColor(Color.decode("#000000"));
	    if (Game3.tank != null) {
	    	for (int i = 0; i < Game3Mine.timer.size(); i++) {
	    		drawNewImage(g, Game3.mine, (int)(394+Game3Mine.Xcoord.get(i)), (int)(394+Game3Mine.Ycoord.get(i)));
	    	}
	    	for (int i = 0; i < Game3Trace.rot.size(); i++) {
	    		drawRotatedImage(Game3Trace.rot.get(i), Game3Trace.Xcoord.get(i)-8, Game3Trace.Ycoord.get(i)-8, Game3.trace, g);
	    	}
	    	for (int i = 0; i < Game3Bloc.type.size(); i++) {
	    		if (Game3Bloc.type.get(i) != -1) {
	    			drawNewImage(g, Game3Bloc.type.get(i) == 0 ? Game3.bloc_0 : Game3.bloc_1, 388+Game3Bloc.Xcoord.get(i)*24, 388+Game3Bloc.Ycoord.get(i)*24);
	    		}
	    	}
	    	for (int i = 0; i < Game3Projectile.type.size(); i++) {
		    	drawRotatedImage(Game3Projectile.rotation.get(i), Game3Projectile.Xcoord.get(i)-4, Game3Projectile.Ycoord.get(i)-4, Game3.rocket_0, g);
		    }
	    	for (int i = 0; i < Game3AI.rot.size(); i++) {
	    		drawRotatedImage(Game3AI.rot3.get(i),Game3AI.Xcoord.get(i)-11, Game3AI.Ycoord.get(i)-11, Game3.tank, g);
			    drawRotatedImage(Game3AI.rot.get(i),Game3AI.Xcoord.get(i)-16, Game3AI.Ycoord.get(i)-16, Game3.turret, g);
	    	}
	    	for (int i = 0; i < Game3Effect.timer.size(); i++) {
	    		if (Game3Effect.timer.get(i) > 25) {
	    			drawNewImage(g, Game3.explode_0, (int)(360+Game3Effect.Xcoord.get(i)), (int)(360+Game3Effect.Ycoord.get(i)));
	    		}
	    		else if (Game3Effect.timer.get(i) > 10) {
	    			drawNewImage(g, Game3.explode_1, (int)(375+Game3Effect.Xcoord.get(i)), (int)(375+Game3Effect.Ycoord.get(i)));
	    		}
	    		else {
	    			drawNewImage(g, Game3.explode_2, (int)(390+Game3Effect.Xcoord.get(i)), (int)(390+Game3Effect.Ycoord.get(i)));
	    		}
	    	}
	    	drawRotatedImage(Game3.rot2,Game3.posX-11, Game3.posY-11, Game3.tank, g);
		    drawRotatedImage(Game3.rot,Game3.posX-16, Game3.posY-16, Game3.turret, g);
		    g.fillRect((int)(Game3.a)-1, (int)(Game3.b)-8, 2, 16);
		    g.fillRect((int)(Game3.a)-8, (int)(Game3.b)-1, 16, 2);
	    }
		
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
