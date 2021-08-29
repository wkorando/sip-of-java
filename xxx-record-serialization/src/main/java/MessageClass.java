import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import javax.naming.OperationNotSupportedException;

public class MessageClass implements Serializable {

	private String message;
	private MessageClass() throws OperationNotSupportedException {
		throw new OperationNotSupportedException();
	}
	public MessageClass(String message) {
		if (message == null || message.isEmpty()) {
			throw new IllegalArgumentException("Message cannot be null or empty!");
		}
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return message;
	}
	
//	private final void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {		
//		try {
//			this.getClass().getDeclaredField("message").set(this, null);
//		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
