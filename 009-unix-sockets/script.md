Hey Java developers,

Having trouble communicating between docker containers?

[Comedy bit - Me struggling with some coffee mugs in kitch tupperware containers]

Consider switching to unix sockets! 

Unix-domain sockets, added in Java 16, were designed to improve the security and performance when communicating between processes on the same system

To use a unix socket do this 

And this

Unix sockets also work on Windows Server 2019 or later

Happy coding!