# Unix-Domain Socket Channels

Unix-Domain Socket Channels, added in Java 16 ([JEP 380](https://openjdk.java.net/jeps/380)), are a Java feature designed to improve inter-processor communication on the same host, over the usage of TCP/IP.  

## Same Host Communication

Synchronous communication between two discreet processes that are located on the same host is a somewhat common need. For Java, this has primarily been done through TCP/IP connections. TCP/IP connections have relatively high overhead in setup and also limited throughput. For an application that only ever communicates with a local process, this also opens a security vulnerability as they will need to accept remote connections. 

### Unix-Domain Socket Channels

Unix-Domain Socket Channels (Unix Sockets), added in Java 16, make use of the `AF_UNIX` (`AF_LOCAL`) socket family. Unix Sockets are strictly limited to intra-host communication, but offer:

* Faster setup and higher throughput than TCP/IP loopback connections

* Allow OS enforced controls filesystem controls 

* Can work with containers via shared volumes

Also despite their name, Unix Sockets even work on Windows 10 and Windows Server 2019.

## Configuring Communication

Setting up a Unix Socket appropriately requires configuring a "server" and a "client" like in the below examples. 

### Server

Configuring the "server" is pretty simple:

```java
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
```

In the above example `/mnt/server` is the socket path. 

The channel must then be opened: `var serverChannel = ServerSocketChannel.open(UNIX)`

The server must then be told to listen to communication coming from the channel: `var clientChannel = serverChannel.accept()`

To pull data sent on the data, use `read()`:  `clientChannel.read(buf)`

### Client

Configuration on the client side is even simpler:

```java
var address = UnixDomainSocketAddress.of("/mnt/server");
try (var clientChannel = SocketChannel.open(address)) {
    ByteBuffer buf = ByteBuffer.wrap("Hello world".getBytes());
    clientChannel.write(buf);
}
```

The channel to the defined path `/mnt/server` must be opened: `var clientChannel = SocketChannel.open(address)`

To send data along the channel, use `write()`: `clientChannel.write(buf);`

Happy Coding!