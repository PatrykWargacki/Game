package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Handler.annotations.Single;
import input.extras.KeyAndObject;

@Single
public class KeyboardInput implements KeyListener{
	private Map<Integer, List<KeyAndObject>> hm;
	private boolean[] keys;
	private int[] poll;
	
	public KeyboardInput(){
		keys = new boolean[256];
		poll = new int[256];
		// wczytaj konfiguracje w pliku json
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
	public void keyTyped(KeyEvent e) {}

}
