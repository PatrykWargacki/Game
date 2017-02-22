package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Handler.Singleton;
import Handler.annotations.Single;
import input.extras.KeyAndObject;
import input.extras.ObjectAndMethod;
import utilities.Display;

@Single
public class KeyboardInput implements KeyListener{
	private Map<Integer, List<KeyAndObject>> hm;
	private boolean[] keys;
	private int[] poll;
	
	public KeyboardInput(){
		keys = new boolean[256];
		poll = new int[256];
		hm   = new HashMap<Integer, List<KeyAndObject>>();
		// wczytaj konfiguracje w pliku json
		/*
		try {
			addKeyAndObject(KeyEvent.VK_F, new KeyAndObject(
											new ObjectAndMethod(
													Singleton.getInstance(MyJFrame.class), 
													Display.class.getMethod("toFullScreenMode")
													), 
											null));
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("KeyboardInput " + e.toString() + " " + e.getMessage());
		}
		*/
	}
	
	public void loadConfig(){
		try {
			System.out.println("laduje");
			addKeyAndObject(KeyEvent.VK_F, new KeyAndObject(
					new ObjectAndMethod(
							Singleton.getInstance(Display.class), 
							Display.class.getMethod("toFullScreenMode")
							), 
					null));
			System.out.println("zaladowane");
		} catch (NoSuchMethodException | SecurityException e) {
			System.out.println("KeyboardInput" + e.toString() + " " + e.getMessage());
		}
	}
	
	public synchronized void poll(){
		for(int i = 0; i<256 ;i++){
			if(keys[i]){
				poll[i]++;
			}else{
				poll[i]=0;
			}
		}
	}

	public void addKeyAndObject(int keyCode, KeyAndObject keyAndObject){
		if(!hm.containsKey(keyCode)){
			hm.put(keyCode, new ArrayList<KeyAndObject>());
		}
		hm.get(keyCode).add(keyAndObject);
	}	
	
	public void doAction(int keyCode) {
		List<KeyAndObject> list = hm.get(keyCode);
		if(list == null){return;}
		
		int[] schema;
		boolean b;
		
		for(KeyAndObject kao : list){
			b = true;
			schema = kao.getKeySchema();
			for(int i : schema){
				if(poll[i] == 0){
					b = false;
					break;
				}
			}
			if(b){
				kao.callMethod();
			}
		}
	}
	
	@Override
	public synchronized void keyPressed(KeyEvent e) {
		System.out.println("Wcisniete " + e.getKeyChar());
		int keyCode = e.getKeyCode();
		if(keyCode > -1 && keyCode < 256){
			keys[keyCode] = true;
			doAction(keyCode);
		}
	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > -1 && keyCode < 256){
			keys[keyCode] = false;
		}
	}
	
	@Override
	public synchronized void keyTyped(KeyEvent e) {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hm == null) ? 0 : hm.hashCode());
		result = prime * result + Arrays.hashCode(keys);
		result = prime * result + Arrays.hashCode(poll);
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
		if (!(obj instanceof KeyboardInput)) {
			return false;
		}
		KeyboardInput other = (KeyboardInput) obj;
		if (hm == null) {
			if (other.hm != null) {
				return false;
			}
		} else if (!hm.equals(other.hm)) {
			return false;
		}
		if (!Arrays.equals(keys, other.keys)) {
			return false;
		}
		if (!Arrays.equals(poll, other.poll)) {
			return false;
		}
		return true;
	}

}
