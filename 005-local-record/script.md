Hey java developers,

Needed to transform data within a method, but frustrated at having to define an entire class to do this?

We've all been there 

In this situation consider defining a local record

Records, added in Java 16, can be defined with the record keyword, the name of the record, and within the parentheses; the types and names of the fields 

This allows a record to be defined within a method, while preserving the meaning and flow of the method

Records achieve this with a tradeoff; less freedom in the definition of a data record in return for the compiler automatically generating a canonical constructor, accessors, equals, hashCode, and toString, along with a few other restrictions

Explicit implementation of the generated methods is allowed; like in this example using toString

And additional methods can also be defined, like here with toJSON

Happy coding!