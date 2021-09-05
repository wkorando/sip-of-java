import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SerializationClient {

	public static void main(String[] args) throws Exception {
		var address = UnixDomainSocketAddress.of("mnt/server");
		try (var clientChannel = SocketChannel.open(address)) {
			
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			record MessageRecord(String message) implements Serializable {};
			MessageRecord message = new MessageRecord("Hello World!");
			ObjectOutputStream objectStream = new ObjectOutputStream(out);
			objectStream.writeObject(message);
			ByteBuffer buf = ByteBuffer.wrap(out.toByteArray());

			clientChannel.write(buf);
		}
	}

}
