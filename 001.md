# Switch Expressions

Switch Expressions, added in Java 14 ([JEP 361](https://openjdk.java.net/jeps/361)) are a great way of handling evaluations that have _n_ paths like in this example: 

```
String input = args[0];
if (input.equals("1")) {
	System.out.println("Monday");
} else if (input.equals("2")) {
	System.out.println("Tuesday");
} else if (input.equals("3")) {
	System.out.println("Wednesday");
} else if (input.equals("4")) {
	System.out.println("Thursday");
} else if (input.equals("5")) {
	System.out.println("Friday");
} else if (input.equals("6")) {
	System.out.println("Saturday");
} else if (input.equals("7")) {
	System.out.println("Sunday");
} else {
	System.out.println("Invalid selection, valid choices 1-7");
}
```

The above can be expressed more succinctly, and less error prone, with a switch expression: 

```
switch (args[0]) {
case "1" -> System.out.println("Sunday");
case "2" -> System.out.println("Monday");
case "3" -> System.out.println("Tuesday");
case "4" -> System.out.println("Wednesday");
case "5" -> System.out.println("Thursday");
case "6" -> System.out.println("Friday");
case "7" -> System.out.println("Saturday");
default -> System.out.println("Invalid selection, valid choices 1-7");
}
``` 

## Returning Value

A switch expression can also return a value:

```
String result = switch (args[0]) {
case "1" -> "Sunday";
case "2" -> "Monday";
case "3" -> "Tuesday";
case "4" -> "Wednesday";
case "5" -> "Thursday";
case "6" -> "Friday";
case "7" -> "Saturday";
default -> "Invalid Choice";
};
	
System.out.println(result);
```

## Block Cases

A case can also be expressed in a code block if multiple statements need to be executed for a case:

```
String result = switch (args[0]) {
case "1" -> "Sunday";
case "2" -> "Monday";
case "3" -> "Tuesday";
case "4" -> "Wednesday";
case "5" -> "Thursday";
case "6" -> "Friday";
case "7" -> "Saturday";
default -> {
	System.out.println("Invalid selection, valid choices 1-7");
	yield "Invalid Choice";
}
};
```

### Yield

When a switch expression is returning a value and a case is defined with a block, that block must include a `yield` statement as the final line:

```
default -> {
	System.out.println("Invalid selection, valid choices 1-7");
	yield "Invalid Choice";
}
```

### Exhaustiveness and enums 

A switch expression must be exhaustive. In most cases this will mean a `default` case must be defined for the expression. However in the below example using a `enum` a default case is not required as ever enum value has a case mapped to it:

```
enum DaysOfWeek {
	SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY ;
}
	
public static void main(String[] args) {
	
	DaysOfWeek dayOfWeek = DaysOfWeek.SUNDAY;
	String result = switch (dayOfWeek) {
	case SUNDAY -> "Sunday";
	case MONDAY -> "Monday";
	case TUESDAY -> "Tuesday";
	case WEDNESDAY -> "Wednesday";
	case THURSDAY -> "Thursday";
	case FRIDAY -> "Friday";
	case SATURDAY -> "Saturday";
	};
	
	System.out.println(result);
}
```
