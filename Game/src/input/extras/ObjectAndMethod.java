package input.extras;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ObjectAndMethod {
	private Object object;
	private Object[] args;
	private Method method;

	public ObjectAndMethod(Object o, Method m, Object... args){
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(args);
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((object == null) ? 0 : object.hashCode());
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
		if (!(obj instanceof ObjectAndMethod)) {
			return false;
		}
		ObjectAndMethod other = (ObjectAndMethod) obj;
		if (!Arrays.equals(args, other.args)) {
			return false;
		}
		if (method == null) {
			if (other.method != null) {
				return false;
			}
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (object == null) {
			if (other.object != null) {
				return false;
			}
		} else if (!object.equals(other.object)) {
			return false;
		}
		return true;
	}
	
}
