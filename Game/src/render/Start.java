package render;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.SwingUtilities;

import Handler.Singleton;

public class Start {

	public static void main(String[] args) {
		
		Singleton.init();
		
		final MyJFrame frame = Singleton.getInstance(MyJFrame.class);
		
		frame.addWindowListener( new WindowAdapter() {
	         public void windowClosing( WindowEvent e ) {
	             frame.onWindowClosing();
	          }
	       });
		
		
		SwingUtilities.invokeLater( new Runnable() {
	         public void run() {
	            frame.getShitDone();
	         }
	      });

	}

}
