package Handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import Handler.annotations.DefaultConstructor;
import Handler.annotations.Single;
 
public class Singleton {
	private static HashMap<Class<?>, Object> hm;
	
	public static void init(){
		hm = new HashMap<Class<?>, Object>();
		/*
		 * call createNewInstance for every class with @Single
		 * getConstructor annotated with @DefaultConstructor
		 */
	}
	
	private static <T> T createNewInstance(Class<T> c){
		T t = null;
		try {
		if(!c.isAnnotationPresent(Single.class)){
			return c.newInstance();
		}
		
			Constructor[] allConstructors = c.getDeclaredConstructors();
			
			Constructor defaultConstructor = allConstructors[0];
			
			for(Constructor<?> con : allConstructors){
				if(con.isAnnotationPresent(DefaultConstructor.class)){
					defaultConstructor = con;
					break;
				}
			}

			Class<?>[] parameterTypes = defaultConstructor.getParameterTypes();
			List<Object> list = new LinkedList<Object>();
			
			for(Class cla : parameterTypes){
				list.add(getInstance(cla));
			}

			t = (T) defaultConstructor.newInstance(list.toArray());
			
			hm.put(c, t);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | SecurityException e) {
			System.out.println("Singleton " + e.toString() + e.getMessage() + c.toGenericString());
		}
		return t;
	}
	
	public static <T> T getInstance(Class<T> c){
		if(hm.isEmpty()){
			return createNewInstance(c);
		}
		Object o = hm.get(c);
		return o==(null) ? createNewInstance(c) : c.cast(o);
	}
	
}
