```java
public void switchOnLife(Life life) {
	switch (life) {
		case Archaea a -> System.out.println("Is Archaea");
		case Bacteria b -> System.out.println("Is Bacteria");
		case Eukaryota e -> System.out.println("Is Eukaryota");
	}
}
```