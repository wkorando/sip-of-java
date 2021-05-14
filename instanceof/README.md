# Instanceof Pattern Matching

Instanceof Pattern Matching, added in JDK 15 ([JEP 394](https://openjdk.java.net/jeps/394)), was added to help address the common issue when using the instance of operator of the test, assign, convert:

```
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String) {
	String nowImAString = (String) actuallyAString;
	
	System.out.println(nowImAString);
}
```

The above code, along with being verbose, creates opportunities for introduction of bugs in the code.

## Instanceof Pattern Matching

Instanceof pattern matching address this issue by extending the instanceof operator to take a predicate and a pattern variable that will be assigned when the predicate is true:

```
		Object actuallyAString = "I'm actually a string!";
		
		if(actuallyAString instanceof String nowImAString) {
			System.out.println(nowImAString);
		}
```

## Flow Scope

The pattern variable, in the below example `nowImAString`, is only in scope within the flow it was declared. For the below example `nowImAString` is only in scope within evaluation statement of the `if` statement and its corresponding code block, but out of scope outside of it. 

The same variable name, `nowImAString`, can be reused later in the code file, but is only inscope within the evaluation statement.


```
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String nowImAString) {
	System.out.println(nowImAString);
}
	
System.out.println(nowImAString);
	
boolean isAString = (actuallyAString instanceof String nowImAString);

System.out.println(nowImAString);
```

## AND (&&) and OR (||) Operators

A pattern variable would leave scope to the right of an OR `||` operator as the pattern variable may not had been assigned.

```
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String nowImAString || nowImAString.endsWith("!")) {
	System.out.println(nowImAString);
}
```

However a  pattern variable would still be in-scope to the right of an AND `&&` operator

```
Object actuallyAString = "I'm actually a string!";
	
if(actuallyAString instanceof String nowImAString && nowImAString.endsWith("!")) {
	System.out.println(nowImAString);
}
```		

## Using a Value Outside of Flow Scope

To use a value outside of flowscope, it will need to be assigned to a variable of the scope that is appropriate for your application, like i nthe below example of assigning the variable `aString` the value of `nowImAString` within the `if` block: 

```
Object actuallyAString = "I'm actually a string!";
String aString = null;
	
if(actuallyAString instanceof String nowImAString) {
	aString  = nowImAString;
}
	
System.out.println(aString);
```