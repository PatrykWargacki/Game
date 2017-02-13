package notUsed;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import utilities.FrameRate;

public class MyJPanel extends JPanel{
	
	FrameRate fps;
	
	public MyJPanel(){
		setPreferredSize(new Dimension(400,400));
		setBackground(Color.WHITE);
		
		fps = new FrameRate();
		fps.initialize();
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		onPaint(g);
	}
	
	protected void onPaint( Graphics g ) {
		fps.calculate();
	    g.setColor( Color.BLACK );
	    g.drawString(String.valueOf(fps.getFrameRate()), 30, 30);
	    repaint();
	}
}
