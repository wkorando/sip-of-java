import static java.net.StandardProtocolFamily.UNIX;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Files;

import javax.naming.OperationNotSupportedException;

public class ClassSerializationServer {
	public static void main(String[] args) throws Exception {
		var address = UnixDomainSocketAddress.of("mnt/server");
		try (var serverChannel = ServerSocketChannel.open(UNIX)) {
			serverChannel.bind(address);
			try (var clientChannel = serverChannel.accept()) {

				ByteBuffer buffer = ByteBuffer.allocate(1024);
				clientChannel.read(buffer);

				ByteArrayInputStream byteInput = new ByteArrayInputStream(buffer.flip().array());
				ObjectInputStream in = new ObjectInputStream(byteInput);
				in.setObjectInputFilter(new SerializationFilter());
				MessageClass message = (MessageClass) in.readObject();

				System.out.println(message.toString().toUpperCase());
			}
		} finally {
			Files.deleteIfExists(address.getPath());
		}
	}

	
	static class MessageClass implements Serializable {

		private static final long serialVersionUID = 1L;
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
		
		@Override
		public String toString() {
			return message;
		}
	}
	
	static class SerializationFilter implements ObjectInputFilter {

		public Status checkInput(FilterInfo filterInfo) {
			for (Class typeInterface : filterInfo.serialClass().getInterfaces()) {
				if (typeInterface == Runnable.class) {
					return Status.REJECTED;
				}
			}
			for (Class classField : filterInfo.serialClass().getDeclaredClasses()) {
				for (Class typeInterface : classField.getInterfaces()) {
					if (typeInterface == Runnable.class) {
						return Status.REJECTED;
					}
				}
			}
			return Status.ALLOWED;
		}
	}
}
