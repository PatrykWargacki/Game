package input;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Handler.Singleton;
import Handler.annotations.Single;
import render.MyJFrame;

@Single
public class KeyboardBinding {
	/*
	 * String z lokalizacja pliku jako argument
	 */
	public static void addKeyBindings(JComponent contentPane){
		InputMap im = contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap am = contentPane.getActionMap();
		
		Action a = new AbstractAction(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("wcisniete");				
			}
		};
		
		im.put(KeyStroke.getKeyStroke("F"), "pressed");
		am.put("pressed", a);
		System.out.println("zzz");	
	}
}
