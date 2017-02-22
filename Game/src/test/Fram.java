package test;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

public class Fram extends JFrame{
	public Fram(int i){
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("TESTs");
		setFocusable(true);
		setAutoRequestFocus(true);
		
		setSize( 480, 640 );
		setVisible(true);
		
		String cancelName = "cancel";
	    InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
	    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F, 0), cancelName);
	    ActionMap actionMap = getRootPane().getActionMap();
	    actionMap.put(cancelName, new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            System.out.println("dziala");
	        }
	    });
	    System.out.println("powinn");
	}
}
