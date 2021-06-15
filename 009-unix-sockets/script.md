Hey Java developers,

TCP/IP connections slowing down your intra-host communication?

[Old school modem tone and me look bored and frustrated in the background]

Consider switching to unix-domain socket channels! 

Unix-domain socket channels, added in Java 16, were designed to improve the security and performance for intra-host communication

Unlike TCP/IP connections, unix-domain sockets are defined using the system filepath 

This improves security, by not requiring applications to accept remote connections when only communicating with other processes on the same host

And also allows for OS enforced filesystem controls

To use a Unix-domain socket channel, define a path, create a UnixDomainSocketAddress, open a ServerSocketChannel using the Unix protocol, and bind this address, and set the channel to accept connections

On the client side, the steps are very similar, but instead you will connect to the channel

Unix-domain socket channels can be used for communication between containers using shared volumes

And even works on Windows 10 and Windows Server 2019

Happy coding!