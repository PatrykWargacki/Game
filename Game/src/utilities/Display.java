package utilities;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Objects;

import Handler.Singleton;
import Handler.annotations.DefaultConstructor;
import Handler.annotations.Single;
import render.MyJFrame;

@Single
public class Display {
	
	private DisplayMode displayMode;
	private GraphicsDevice graphicsDevice;
	
	@DefaultConstructor
	public Display(){
		graphicsDevice = GraphicsEnvironment
							.getLocalGraphicsEnvironment()
							.getDefaultScreenDevice();
		displayMode = graphicsDevice.getDisplayMode();
	}
	
	public Display(DisplayMode displayMode, GraphicsDevice graphicsDevice){
		this.displayMode = displayMode;
		this.graphicsDevice = graphicsDevice;
	}
	
	public Display[] getDisplayModes(){
		ArrayList<Display> list = new ArrayList<Display>();
		GraphicsDevice[] gds = GraphicsEnvironment
								.getLocalGraphicsEnvironment()
								.getScreenDevices();
		for(GraphicsDevice gd : gds){
			DisplayMode[] dms = gd.getDisplayModes();
	    	for( DisplayMode dm : dms ) {
	    		Display d  = new Display( dm, gd );
	            if( !list.contains( d ) ) {
	            	list.add( d );
	            }
	    	}
		}
	    return list.toArray(new Display[0]);
	}
	
	// i should make an annotation for DI for Singleton job
	public void toFullScreenMode(){
		if(graphicsDevice.isFullScreenSupported()){
			Singleton.getInstance(MyJFrame.class).setUndecorated(true);
			Singleton.getInstance(MyJFrame.class).setIgnoreRepaint(true);
			graphicsDevice.setFullScreenWindow(Singleton.getInstance(MyJFrame.class));
			graphicsDevice.setDisplayMode(displayMode);
		}
	}
	
	public void exitFullScreenMode(){
		graphicsDevice.setDisplayMode(displayMode);
		graphicsDevice.setFullScreenWindow(null);
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(displayMode, graphicsDevice);
	}
}
