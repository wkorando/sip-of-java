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
