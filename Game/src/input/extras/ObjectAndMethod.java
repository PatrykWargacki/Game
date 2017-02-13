package input.extras;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ObjectAndMethod {
	private Object object;
	private Object[] args;
	private Method method;

	private ObjectAndMethod(Object o, Method m, Object... args){
		this.object = o;
		this.method = m;
		this.args = args;
	}

	public void callMethod(){
		try {
			method.invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			System.out.println(e.toString() + " " + e.getMessage());
		}
	}

	public Object getObject() {
		return object;
	}

	public Object[] getArgs() {
		return args;
	}

	public Method getMethod() {
		return method;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
}
