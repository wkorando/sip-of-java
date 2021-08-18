import static java.net.StandardProtocolFamily.UNIX;

import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Files;

public class ClassSerializationServer {

    public static void main(String[] args) throws Exception {
        var address = UnixDomainSocketAddress.of("/mnt/server");
        try (var serverChannel = ServerSocketChannel.open(UNIX)) {
            serverChannel.bind(address);
            try (var clientChannel = serverChannel.accept()) {
                ByteBuffer buf = ByteBuffer.allocate(64);
                clientChannel.read(buf);
                buf.flip();
                System.out.printf("Read %d bytes\n", buf.remaining());
            }
        } finally {
            Files.deleteIfExists(address.getPath());
        }
    }
}
