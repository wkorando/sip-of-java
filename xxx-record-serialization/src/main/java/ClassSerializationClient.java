import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClassSerializationClient {

    public static void main(String[] args) throws Exception {
        var address = UnixDomainSocketAddress.of("/mnt/server");
        try (var clientChannel = SocketChannel.open(address)) {
            ByteBuffer buf = ByteBuffer.wrap("Hello world".getBytes());
            clientChannel.write(buf);
        }
    }

}
