# Instanceof Pattern Matching

Instanceof Pattern Matching, added in JDK 15 ([JEP 394](https://openjdk.java.net/jeps/394)), was added to help address the common idiom when using the `instanceof` operator of test, assign, convert:

```java
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String) {//Test if actuallyAString is a String
	String nowImAString = //Assign actuallyAString to a variable
	(String) actuallyAString; //Convert actuallyAString to a String
	
	System.out.println(nowImAString);
}
```

The above code, along with being verbose, creates several opportunities for introduction of bugs in the code.

## Instanceof Pattern Matching

Instanceof pattern matching address this issue by extending the `instanceof` operator to take a predicate and a pattern variable that will be assigned when the predicate is true:

```java
		Object actuallyAString = "I'm actually a string!";
		
		if(actuallyAString instanceof String nowImAString) {
			System.out.println(nowImAString);
		}
```

## Flow Scope

The pattern variable, in these examples `nowImAString`, is only available within the _flow scope_ where it was declared. What this means in practice is that `nowImAString` is available everywhere where the compiler *definitely* knows its value has been assigned (i.e. everywhere where `actuallyAString instanceof` is `true`)


### Normal Use Cases

Typically this will mean the pattern variable will be available in the evaluation block it was declared, and, if part of an `if` statement, the following code block, like in the below examples of an `if` statement and assigning a `boolean` value. 

```java
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String nowImAString) {
	System.out.println(nowImAString);
}
	
System.out.println(nowImAString); //Compiler error, nowImAString not in scope
	
boolean isAString = (actuallyAString instanceof String nowImAString);

System.out.println(nowImAString); //Compiler error, nowImAString not in scope
```

### AND (&&) and OR (||) Operators

However a pattern variable would leave scope to the right of an OR `||` operator as the pattern variable may not had been assigned:

```java
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String nowImAString 
	|| nowImAString.endsWith("!") //Compiler error, nowImAString not in scope after ||
) {
	System.out.println(nowImAString);
}
```

The pattern variable would still be in-scope thought to the right of an AND `&&` operator:

```java
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String nowImAString 
    && nowImAString.endsWith("!")) {
	System.out.println(nowImAString);
}
```		

### Unusual Use Cases 

There are some unusual ways to work with a pattern variable, like in the below examples of encapsulating `instanceof` in a NOT `!` so that the pattern variable can be referenced in an `else`, or throwing an exception in the body of the `if` so the pattern variable can be referenced outside of the evaluation and `if` code block.

```java
Object actuallyAString = "I'm actually a string!";

if (!(actuallyAString instanceof String nowImAString)) {
//...
} else {
	System.out.println(nowImAString);
}

if (!(actuallyAString instanceof String nowImAString)) {
	throw new IllegalArgumentException("Must be a string!");
}

System.out.println(nowImAString);
```

### Using the Value of a Pattern Variable Outside of Flow Scope

To use value of a pattern variable outside of flow scope, it will need to be assigned to a variable of the scope that is appropriate for your needs, like in the below example of assigning the local variable `aString` the value of `nowImAString` within the `if` block: 

```java
Object actuallyAString = "I'm actually a string!";
String aString = null;
	
if(actuallyAString instanceof String nowImAString) {
	aString  = nowImAString;
}
	
System.out.println(aString);
```