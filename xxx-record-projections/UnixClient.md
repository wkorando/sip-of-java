# Unix Socket "Client"

```java
var address = UnixDomainSocketAddress.of("/mnt/server");
try (var clientChannel = SocketChannel.open(address)) {
    ByteBuffer buf =
       ByteBuffer.wrap("Hello world".getBytes());
    clientChannel.write(buf);
}
```