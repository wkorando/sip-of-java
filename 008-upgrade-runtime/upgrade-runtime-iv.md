## New Runtime Features and Improvements

* ZGC
* New and improved AArch64 support across OSs
* More events types tracked by JDK Flight Recorder
* Better container support 
* Improved AppCDS support
* Modular runtime and custom Java runtime image support with `jlink`
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