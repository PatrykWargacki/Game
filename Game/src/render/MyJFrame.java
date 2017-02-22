package render;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import Handler.annotations.DefaultConstructor;
import Handler.annotations.Single;
import utilities.FrameRate;

@Single
public class MyJFrame extends JFrame implements Runnable{
	
	private FrameRate fps;
	private BufferStrategy bs;
	private volatile boolean running;
	private Thread gameThread;
	
	@DefaultConstructor
	public MyJFrame(FrameRate frameRate){
		fps = frameRate;
	}
	
	public void init(){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("My Game");
		setFocusable(true);
		setVisible(true);
		
		addWindowListener( new WindowAdapter() {
	         public void windowClosing( WindowEvent e ) {
	             onWindowClosing();
	         }
	       }
		);
		
		//addKeyBind((JComponent) getContentPane());
	}
	
	public void getShitDone(){
		JPanel panel = new JPanel();
		addKeyBind(panel);
		panel.setIgnoreRepaint(true);
		panel.setFocusable(true);
		panel.setVisible(true);
		
		getContentPane().add(panel);

		Canvas canvas = new Canvas();
		canvas.setSize( 320, 240 );
	    canvas.setBackground( Color.BLACK );
	    canvas.setIgnoreRepaint( true );
		
	    //panel.add(canvas);
		//getContentPane().add(canvas);
	    
		pack();
		
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
	
	private void addKeyBind(JComponent contentPane) {
		Action disableButtonAction = new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		      System.out.println("dzialaj");
		    }
		  };
	    InputMap iMap = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap aMap = contentPane.getActionMap();
	    iMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), "d");
	    aMap.put("d", disableButtonAction);
	    System.out.println("dodane");
	  }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bs == null) ? 0 : bs.hashCode());
		result = prime * result + ((fps == null) ? 0 : fps.hashCode());
		result = prime * result + ((gameThread == null) ? 0 : gameThread.hashCode());
		result = prime * result + (running ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MyJFrame)) {
			return false;
		}
		MyJFrame other = (MyJFrame) obj;
		if (bs == null) {
			if (other.bs != null) {
				return false;
			}
		} else if (!bs.equals(other.bs)) {
			return false;
		}
		if (fps == null) {
			if (other.fps != null) {
				return false;
			}
		} else if (!fps.equals(other.fps)) {
			return false;
		}
		if (gameThread == null) {
			if (other.gameThread != null) {
				return false;
			}
		} else if (!gameThread.equals(other.gameThread)) {
			return false;
		}
		if (running != other.running) {
			return false;
		}
		return true;
	}
	   
}
