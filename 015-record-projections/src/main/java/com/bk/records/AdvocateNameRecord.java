package com.bk.records;

public record AdvocateNameRecord(String fName, String lName) {

	public String toString() {
		return lName + ", " + fName;
	}
}
