Hey java developers,

Needed to define a collection, but getting cramps from all the typing?

Same. 

Good news, there the Convenience Factory Methods for Collections added in Java 9

With the List factory method, of, you can quickly define a List instance, like in this example of Strings

The same syntax is also available for Sets, with Set.of

When needing to define a map instance, of can also be used, but the provided values must match key and value types of the map in an alternating pattern, like in this example

Alternatively Map.ofEntries can be used with the java.util.Map.entry static method, for better readability, at the cost of a little extra typing

Happy coding!