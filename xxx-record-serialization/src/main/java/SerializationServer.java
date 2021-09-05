import static java.net.StandardProtocolFamily.UNIX;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Files;

public class SerializationServer {

	public static void main(String[] args) throws Exception {
		var address = UnixDomainSocketAddress.of("mnt/server");
		try (var serverChannel = ServerSocketChannel.open(UNIX)) {
			serverChannel.bind(address);
			try (var clientChannel = serverChannel.accept()) {

				ByteBuffer buffer = ByteBuffer.allocate(1024);
				clientChannel.read(buffer);

				ByteArrayInputStream byteInput = new ByteArrayInputStream(buffer.flip().array());
				ObjectInputStream in = new ObjectInputStream(byteInput);
				record MessageRecord(String message) {};
				MessageRecord message = (MessageRecord) in.readObject();

				System.out.println(message.message().toUpperCase());
			}
		} finally {
			Files.deleteIfExists(address.getPath());
		}
	}
}
