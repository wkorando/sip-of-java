```java
String aPaddedString = "   a padded string   ";
System.out.println(
  "stripLeading {" + aPaddedString.stripLeading() + "}");
System.out.println(
  "stripTrailing {" + aPaddedString.stripTrailing() + "}");
System.out.println(
  "strip {" + aPaddedString.strip() + "}");
```

## OUTPUT

```
stripLeading {a padded string   }
stripTrailing {   a padded string}
strip {a padded string}
```