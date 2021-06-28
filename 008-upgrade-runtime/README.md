# Upgrading Java Runtime	

A new version of the JDK is being released every six-months, and with it come improved performance and new runtime features. Upgrading to a runtime might seem daunting, but did you know you can run code built using older versions of Java on the newest runtimes? And that you can still benefit from these performance and runtime improvements? 

## Performance Improvements

The JVM is continuously seeing performance improvements with every new JDK release. With upgrading to a newer runtime you can expected to see improvements across: 

* Memory footprint

* Startup

* Ramp up

* Throughput

All without having to recompile code or make any configuration changes.

### Load Test

A demonstration of these improvements can be seen with the below example of a load test using the Spring Boot Petclinic App:

* Requests: 500 (serial)
* Heap size: 48 MB
* Garbage collector: G1

#### Java 8 Results (1.8.0_291-b10)
Application startup: 8.665 seconds

Load test execution time: 137 seconds

#### Java 16 Results (build 16.0.1+9-24)
Application startup: 6.452 seconds

Load test execution time: 130 seconds

JFR recordings of load tests can be viewed here: [link](https://github.com/wkorando/sip-of-java/tree/main/008-upgrade-runtime)

## Security

Security is always a top priority with every JDK release. In the Java Bugs System, you can see here the security vulnerabilities being patches and improvements made from the release of JDK 9 on [link](https://bugs.openjdk.java.net/browse/JDK-8159528?jql=text%20~%20%22security%22%20and%20resolution%20%3D%20Fixed%20and%20status%20%3D%20Resolved%20and%20fixVersion%20%3E%3D%209).

## New Runtime Features and Improvements

There are a number of other runtime improvements and features that can also be taken advantage of by moving to the latest JDK runtime including:

* ZGC 
* New features for JDK Flight Recorder
* Better container support 
* Helpful NullPointerExceptions

  Java 8:
  
	```
	Exception in thread "main" java.lang.NullPointerException
		at NPEService.main(NPEService.java:7)
	```

  Java 16:
  
	```
	Exception in thread "main" java.lang.NullPointerException: 
	Cannot invoke "String.length()" because "<local1>" is null
	at NPEService.main(NPEService.java:7)
	```

* Improved AppCDS support
