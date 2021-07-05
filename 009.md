# Join APIs for String

Taking a list of items and transforming them into a String with delimiters between each item and possibly prefixes and suffixes, is a common need in software development. It's also a frequent source of frustration as the below code example demonstrates.

```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
StringBuilder devAdvocateList = new StringBuilder();

devAdvocateList.append("{ ");
for(String devAdvocate : devAdvocates) {
	devAdvocateList.append(devAdvocate);
	devAdvocateList.append(", ");
}

devAdvocateList.append(" }");

System.out.println(devAdvocateList);
```

## OUTPUT

```
{ Billy, David, Denys, José, Nicolai,  }
```


Manually having to add the prefix and suffix before and after a loop statement is messy. There is also the issue of dealingwith the final delimiter instance, which often requires adding logic to a loop to skip adding the delimiter after the last time, or messy editing of the String to remove the final delimiter. 

Luckily there is a much easier way of handling this, the Join APIs, all of which are avaiable in JDK 8!

## StringJoiner

`StringJoiner` has two constructors, one taking only a delimiter, a second that takes a prefix and suffix. Items can be add to a `StringJoiner` individually using `add()` like in the example below: 


```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");

		
StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
for(String devAdvocate : devAdvocates) {

	joiner.add(devAdvocate);
}
	
System.out.println(joiner.toString());
```

## OUTPUT

```
{ Billy, David, Denys, José, Nicolai }
```

### Adding with forEach

If the items being transformed to a String are already in an `Iterable` instance, like in the example below using `List` (which extends `Iterable`), then all the items can be added in a single call with `forEach` and using the `StringJoiner::add` method reference like in the below example:

```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
		
StringJoiner joiner = new StringJoiner(", ", "{ ", " }");

devAdvocates.forEach(joiner::add);
	
System.out.println(joiner.toString());
```

## Collectors.joining()

If you prefer to use a `Stream` consider using `Collectors.joining()`. Like with `StringJoiner`, `Collectors.joining()` can either take just a delimiter, or a prefix and suffix. The below demonstrates using `Collectors.joining()`:

```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
String devAdvocatesList = devAdvocates.stream().collect(Collectors.joining(", ", "{ ", " }"));
		
System.out.println(devAdvocatesList);
```

## String.join()

If you only need to use a delimiter in the String list being built, `String.join()` might be the best option. `String.join()` can accept an `Iterable` like in the below example:

```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
				
System.out.println(String.join(", ", devAdvocates));
```

## OUTPUT

```
Billy, David, Denys, José, Nicolai
```

### Varargs

`String.join()` can also take a varargs of items like shown here:

```java	
System.out.println(String.join(", ", "Billy", "David", "Denys", "José", "Nicolai"));

```

Happy coding!
