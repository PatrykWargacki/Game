package render;

import javax.swing.SwingUtilities;

import Handler.Singleton;

public class Start2 {

	public static void main(String[] args) {
		Singleton.init();
		
		final MyJFrame frame = Singleton.getInstance(MyJFrame.class);
		frame.init();
		SwingUtilities.invokeLater( new Runnable() {
			public void run() {
				frame.getShitDone();;
			}
		});

	}

}
