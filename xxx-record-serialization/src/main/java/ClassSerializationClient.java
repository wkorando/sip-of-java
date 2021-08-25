import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClassSerializationClient {

	public static void main(String[] args) throws Exception {
		var address = UnixDomainSocketAddress.of("mnt/server");
		try (var clientChannel = SocketChannel.open(address)) {

			ByteArrayOutputStream out = new ByteArrayOutputStream();
			MessageClass message = new MessageClass(null);
			ObjectOutputStream objectStream = new ObjectOutputStream(out);
			objectStream.writeObject(message);
			ByteBuffer buf = ByteBuffer.wrap(out.toByteArray());

			clientChannel.write(buf);
		}
	}

	static class MessageClass implements Serializable {
		private static final long serialVersionUID = 1L;
		private String message;

		public MessageClass(String message) {
			this.message = message;
		}

		@Override
		public String toString() {
			return message;
		}
	}
}
