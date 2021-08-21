import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class MessageClass implements Serializable {

	private String message;


	
	
	
	private Runnable command = new Runnable() {

		@Override
		public void run() {
			System.out.println("Injected Code");
		}
	};

	private final void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		command.run();
	}
}
