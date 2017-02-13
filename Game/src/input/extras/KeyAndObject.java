package input.extras;

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
}
