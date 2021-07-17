Hey Java developers,

TCP/IP connections slowing down your intra-host communication?

[Old school modem tone and me look bored and frustrated in the background]

Consider switching to unix-domain socket channels added in Java 16! 

To setup a Unix-domain socket, on the "server" define a path using the system filepath, create a UnixDomainSocketAddress, open a ServerSocketChannel using the Unix protocol, bind the address, and set the channel to accept connections

On the "client" side, the steps are similar, but instead you will connect to the channel

Unix-domain sockets improve security, as applications don't need to accept remote connections for intra-host communication and allow for OS enforced filesystem controls

They also offer faster setup and higher throughput than TCP/IP connections

Unix-domain sockets can be used for communication between containers using shared volumes

And even work on Windows 10 and Windows Server 2019

Happy coding!