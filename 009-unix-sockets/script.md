Hey Java developers,

TCP/IP connections slowing down your intra-system communication?

[Old school modem tone and me look bored and frustrated in the background]

Consider switching to unix-domain sockets! 

Unix-domain sockets, added in Java 16, were designed to improve the security and performance for intra-system communication

Unlike TCP/IP connections, unix-domain sockets are defined using a filepath like shown in this setting up the host

To communicate to the host from a client, you would do this:

Show it in action:

Unix-domain sockets Strictly 

Can work docker containers using shared volumes

Unix sockets also work on Windows Server 2019 or later

Happy coding!