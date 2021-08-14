# Records

* Added in Java 16

* Can be defined in a single line: 

	```java
	public record AdvocateNameRecord(String fName, String lName) {}
	```
* Unsuitable as a JPA entity, because Records are final and immutable*

\* A bit more complicated, see further reading section in article