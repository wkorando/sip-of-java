```java
lines.skip(titleLine) //
		.map(s -> s.split(",")).takeWhile(filterToCurrentMonth)//
		.mapMulti(mapElectricProject) //
		.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
				Collectors.maxBy(compareExpectedKwhProduction) )) //
		.values().stream().map(Optional::get).forEach(printResults);
```