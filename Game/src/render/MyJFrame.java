package render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.image.BufferStrategy;
import java.util.Objects;

import javax.swing.JFrame;

import Handler.Singleton;
import Handler.annotations.DefaultConstructor;
import Handler.annotations.Single;
import input.KeyboardInput;
import utilities.Display;
import utilities.FrameRate;

@Single
public class MyJFrame extends JFrame implements Runnable{
	
	private FrameRate fps;
	private BufferStrategy bs;
	private volatile boolean running;
	private Thread gameThread;
	private KeyboardInput keyboardInput;
	
	@DefaultConstructor
	public MyJFrame(FrameRate frameRate, KeyboardInput keyboardInput){
		fps = frameRate;
		this.keyboardInput = keyboardInput;
	}
	
	public void getShitDone(){
		Canvas canvas = new Canvas();
		canvas.setSize( 320, 240 );
	    canvas.setBackground( Color.BLACK );
	    canvas.setIgnoreRepaint( true );
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("My Game");
		
		getContentPane().add(canvas);
		pack();

		addKeyListener(keyboardInput);
		
		setVisible(true);
		
		canvas.createBufferStrategy( 2 );
	    bs = canvas.getBufferStrategy();
	      
	    gameThread = new Thread( this );
	    gameThread.start();
	}

	public void run() {
		running = true;
	    fps.initialize();
	    while( running ) {
	    	gameLoop();
	    }
	}
	
	public void gameLoop() {
		do {
			do {
				keyboardInput.poll();
				Graphics g = null;
	            try {
	            	g = bs.getDrawGraphics();
	                g.clearRect( 0, 0, getWidth(), getHeight() );
	                render( g );
	            } finally {
	            	if( g != null ) {
	                	g.dispose();
	                }
	            }
	         } while( bs.contentsRestored() );
	         bs.show();
	      } while( bs.contentsLost() );
	   }
	   
	private void render( Graphics g ) {
		fps.calculate();
		g.setColor( Color.GREEN );
		g.drawString( String.valueOf(fps.getFrameRate()), 30, 30 );
	}
	   
	protected void onWindowClosing() {
		try {
			running = false;
			gameThread.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		System.exit( 0 );
	}
	   
	@Override
	public int hashCode(){
		return Objects.hash(fps,bs,running,gameThread);
	}
	   
}
