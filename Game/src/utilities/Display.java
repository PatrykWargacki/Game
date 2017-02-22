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
		System.out.println("weszlo");
		if(graphicsDevice.isFullScreenSupported()){
			System.out.println("weszlo2");
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayMode == null) ? 0 : displayMode.hashCode());
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
		if (!(obj instanceof Display)) {
			return false;
		}
		Display other = (Display) obj;
		if (displayMode == null) {
			if (other.displayMode != null) {
				return false;
			}
		} else if (!displayMode.equals(other.displayMode)) {
			return false;
		}
		return true;
	}
	
}
