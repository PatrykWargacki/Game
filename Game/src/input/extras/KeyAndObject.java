package input.extras;

import java.util.Arrays;

public class KeyAndObject {
	private ObjectAndMethod objectAndMethod;
	private int[] keySchema;
	
	public KeyAndObject(ObjectAndMethod objectAndMethod, int... keySchema){
		this.objectAndMethod = objectAndMethod;
		this.keySchema = keySchema;
	}

	public void callMethod(){
		objectAndMethod.callMethod();
	}
	
	public ObjectAndMethod getObjectAndMethod() {
		return objectAndMethod;
	}

	public int[] getKeySchema() {
		return keySchema;
	}

	public void setObjectAndMethod(ObjectAndMethod objectAndMethod) {
		this.objectAndMethod = objectAndMethod;
	}

	public void setKeySchema(int[] keySchema) {
		this.keySchema = keySchema;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(keySchema);
		result = prime * result + ((objectAndMethod == null) ? 0 : objectAndMethod.hashCode());
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
		if (!(obj instanceof KeyAndObject)) {
			return false;
		}
		KeyAndObject other = (KeyAndObject) obj;
		if (!Arrays.equals(keySchema, other.keySchema)) {
			return false;
		}
		if (objectAndMethod == null) {
			if (other.objectAndMethod != null) {
				return false;
			}
		} else if (!objectAndMethod.equals(other.objectAndMethod)) {
			return false;
		}
		return true;
	}
	
}
