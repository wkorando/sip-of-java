import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class PersonClass implements Serializable {

	private int id;
	private String firstName;
	private String lastName;

	public PersonClass(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

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
