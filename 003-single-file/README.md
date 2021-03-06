# Launch Single-File Source-Code Programs

Launch Single-File Source Code Programs, added in JDK 11 ([JEP 330](https://openjdk.java.net/jeps/330)), allows a single-file Java source-code program to be directly launched by the `java` launcher. 

## Requirements for to Launch a Source-Code file

When using the `java` launcher to launch a source-code file program, the launcher will check for the first top-level class defined in the source-code file, in the example below `SingleFileI`, and that that class contains a `public static void main(String[] args)` method. If those are present the launcher will attempt to compile and execute the program. 

```
public class SingleFileI {
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
}
```

## Single File, Multiple Classes

In my cases Java developers follow a one class to one source-file idiom. This is good for readability, but not a requirement in Java. A Java source-file can contain multiple top level classes, though only one can be public, as well inner classes, classes defined within another class. 

Multiple classes can allow for encapsulation of logic and behavior and defining of data carrier(s), for data processing purposes.   

The below code when executed:

```
public class SingleFileII {
	public static void main(String[] args) {
		AnotherClass anotherClass = new AnotherClass();
		YetAnotherClass yetAnotherClass = new YetAnotherClass();
		
		System.out.println(anotherClass.printAnotherMessage());
		System.out.println(yetAnotherClass.printYetAnotherMessage());
	}
}

class AnotherClass{
	public String printAnotherMessage() {
		return "anotherMessage";
	}
}

class YetAnotherClass{
	public String printYetAnotherMessage() {
		return "yetAnotherMessage";
	}
}
```

Prints out the following: 

```
anotherMessage
yetAnotherMessage
```

## Referencing non-JDK Classes

Classes that are not in the core-JDK can also be referenced in a single-file source code application, like in the example below using the `RandomUtils` located i nthe `commons-lang3` library, however a classpath argument (`-cp` or `-classpath`) to the class or library must be provided.  

In the below example, to successfully launch the application the command would look something like this:

```
java -cp /path/to/commons-lang3-3.12.0.jar SingleFileIII.jar
```

```
import org.apache.commons.lang3.RandomUtils;

public class SingleFileIII {

	public static void main(String[] args) {
		System.out.println(RandomUtils.nextInt());
	}

}
```

**Note:** This behavior is consistent with how a normally compiled and executed Java program would work, but as most Java developers typically use build tools and frameworks, this behavior is often hidden.