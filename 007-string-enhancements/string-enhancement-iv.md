```java
String aStringToTransform = "Transform me!";
String transformedString 
	= aStringToTransform.transform(s -> s.toUpperCase());
System.out.println(transformedString);
```

## OUTPUT

```
TRANSFORM ME!
```