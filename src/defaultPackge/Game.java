package defaultPackge;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,MouseListener {
	
	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;
	
	public static double vida  = 5;

	public static List<Crab> crabs;
	public static List<Smoke> smoke;
	
	public Spawner spawner;
	
	public static Spritesheet spritesheet;
	
	public static Rectangle maskBuraco;
	
	public static int mX, mY;
	
	public  static boolean isPressed = false;
	
	public static int Score = 0;
	
	public static BufferedImage  Heart;
	
	
	public Game(){
		
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.addMouseListener(this);
		spritesheet = new Spritesheet("/spritesheet.png");
		Heart       = Game.spritesheet.getSprite(144, 144,16,16);
		crabs       = new ArrayList<>();
		smoke       = new ArrayList<>();
		spawner     = new Spawner();
		maskBuraco  = new Rectangle(WIDTH/2 - 20, HEIGHT/2 - 20, 40, 40);
	}
	
	public static void main(String[] args){
		Game game    = new Game();
		JFrame frame = new JFrame();
		frame.setTitle("Catch The Crab");
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		new Thread(game).start();
	}
	
	public void update(){
		spawner.update();
			for(int i = 0; i < crabs.size(); i++) {
				crabs.get(i).update();
			}
			
			for(int i = 0; i < smoke.size(); i++) {
				smoke.get(i).update();
			}
			
			if(Game.vida <= 0)
			{
				//Game Over!
				System.exit(1);
			}
		
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(new Color(255,229,102));
		g.fillRect(0, 0,WIDTH,HEIGHT);
	    g.setColor(Color.black);
	    g.fillOval(WIDTH/2 - 20, HEIGHT/2 - 20, 40, 40);
	    
	    for(int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}
	    
	    for(int i = 0; i < smoke.size(); i++) {
			smoke.get(i).render(g);
		}
	    
	    for(int i = 0 ; i < (int)(Game.vida); i++) {
			g.drawImage(Heart, 259 + (i*39),10,35,35,null);;
		}
	    
	    
	    g.setColor(Color.black);
	    g.setFont(new Font("arial",Font.BOLD,22));
	    g.drawString("Score: "+Score, 10, 30);
	    
		g.dispose();
		bs.show();
	}
	
	public void run() {
		
		double fps = 60.0;

		while(true){
		
				update();
				render();
					try {
						Thread.sleep((int)(1000/fps));
					}catch(InterruptedException e) {}
			}
			
		}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
			mX = e.getX();
			mY = e.getY();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}


