```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
StringBuilder devAdvocateList = new StringBuilder();

devAdvocateList.append("{ ");
for(String devAdvocate : devAdvocates) {
	devAdvocateList.append(devAdvocate);
	devAdvocateList.append(", ");
}
devAdvocateList = devAdvocateList.delete(devAdvocateList.length()-2, devAdvocateList.length());
devAdvocateList.append(" }");

System.out.println(devAdvocateList);
```java
