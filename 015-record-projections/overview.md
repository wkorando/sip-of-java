# Records

* Added in Java 16

* Transparent carrier of data

* Can be defined in a single line: 

```java
record NameRecord(String fName, String lName) {}
```
* Unsuitable as a JPA entity, because Records are final and immutable*

\* A bit more complicated, see further reading section in article