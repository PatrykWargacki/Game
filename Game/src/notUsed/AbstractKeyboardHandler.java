package notUsed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import input.extras.KeyAndObject;

public abstract class AbstractKeyboardHandler implements KeyboardHandler {
	private Map<Integer, List<KeyAndObject>> hm;
	private int[] keyCodes;
	
	public AbstractKeyboardHandler(int... keyCodes){
		hm = new HashMap<Integer, List<KeyAndObject>>();
		this.keyCodes = keyCodes;
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
				if(keyCodes[i] == 0){
					b = false;
					break;
				}
			}
			if(b){
				kao.callMethod();
			}
		}
	}
	
	public void setCodes(int... keyCodes){
		this.keyCodes = keyCodes;
	}
}
