```java
public record ElectricProject(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city,
		String county, String state, String zipCode, String sector, String programType, String solicitation,
		String electricUtility, String purchaseType, LocalDate dateApplicationReceived, LocalDate dateCompleted,
		String projectStatus, String contractor, String primaryInverterManufacturer, String primaryInverterModelNumber,
		BigDecimal totalInverterQuantity, String primaryPVModuleManufacturer, String pvModuleModelNumber,
		BigDecimal totalPVModuleQuantity, BigDecimal projectCost, BigDecimal incentive, BigDecimal totalNameplatekWDC,
		BigDecimal expectedKWhAnnualProduction, String remoteNetMetering, String affordableSolar,
		String communityDistributed, String generationGreenJobsGreenNewYorkParticipant, String georeference) {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	boolean reportingPeriodMatches(Month month) {
		return this.reportingPeriod.getMonth().equals(month);
	}

	static BigDecimal handleNumeric(String value) {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			return BigDecimal.valueOf(0);
		}
	}

	static LocalDate handleDate(String value) {
		if (value.isBlank()) {
			return null;
		} else {
			return LocalDate.parse(value, formatter);
		}
	}

	static ElectricProject map(String[] values) {
		return new ElectricProject(handleDate(values[0]), values[1], values[2], values[3], values[4], values[5],
				values[6], values[7], values[8], values[9], values[10], values[11], handleDate(values[12]),
				handleDate(values[13]), values[14], values[15], values[16], values[17], handleNumeric(values[18]),
				values[19], values[20], handleNumeric(values[21]), handleNumeric(values[22]), handleNumeric(values[23]),
				handleNumeric(values[24]), handleNumeric(values[25]), values[26], values[27], values[28], values[29],
				values[30]);
	}
}
```