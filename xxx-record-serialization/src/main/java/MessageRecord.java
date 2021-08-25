import java.io.IOException;
import java.io.ObjectInputStream;

import javax.naming.OperationNotSupportedException;

public record MessageRecord(String message) {


	
	public MessageRecord(String message) {
		if (message == null || message.isEmpty()) {
			throw new IllegalArgumentException("Message cannot be null or empty!");
		}
		this.message = message;
	}
	
	private final void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		System.out.println("In MessageRecord#readObject");
	}
}
