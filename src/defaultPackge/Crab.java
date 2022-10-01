package defaultPackge;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Crab {

	public double x,y,dX,dY;
	public double speed = 4;
	
	public static BufferedImage [] crabSprite;
	
	public int curFrames = 0, maxFrames = 10, maxAnim = 2, curAnim = 0; 
	
	public Crab (int x, int y) {
		this.x = x;
		this.y = y;
		
		double radius = Math.atan2((Game.HEIGHT/2 - 20) - y, (Game.WIDTH/2 - 20) - x);
		this.dX = Math.cos(radius);
		this.dY = Math.sin(radius);
		
		crabSprite    = new BufferedImage[2];
		crabSprite[0] = Game.spritesheet.getSprite(0, 0, 16, 16);
		crabSprite[1] = Game.spritesheet.getSprite(17, 0, 16, 16);
		
	}
	
	public void update() {
		x+= (dX * speed);
		y+= (dY * speed);
			
			if(new Rectangle((int)x, (int) y, 40, 40).intersects(Game.maskBuraco)) {
					
					Game.crabs.remove(this);
					Game.vida -= 1;
					return;
			}
			
			curFrames++;
				if(curFrames == maxFrames) {
					curAnim++;
						if(curAnim == maxAnim) {
							curAnim = 0;
						}
					curFrames = 0;	
				}
				
		verificaColisao();		
	}
	
	public void verificaColisao() {
		if(Game.isPressed) {
			Game.isPressed = false;
					if(Game.mX >= x && Game.mX <= x + 40) {
							if(Game.mY >= y && Game.mY <= y + 40) {
									Game.crabs.remove(this);
									Game.Score++;
									Game.smoke.add(new Smoke((int) x, (int) y));
									return;
							}
					}
		}
		
	}

	public void render(Graphics g) {
		
		g.drawImage(crabSprite[curAnim], (int)x, (int)y, 40, 40, null);
		
		
		/*	g.setColor(Color.red);
			g.fillRect((int)x, (int)y, 40, 40);
		*/	
	}
	
}