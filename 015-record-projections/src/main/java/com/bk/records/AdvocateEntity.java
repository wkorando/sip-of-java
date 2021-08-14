package com.bk.records;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

@Entity
@Table(name = "advocates")
@SqlResultSetMapping(
        name = "AdvocateNameRecordMapping",
        classes = @ConstructorResult(
            targetClass = AdvocateNameRecord.class,
            columns = { 
                        @ColumnResult(name = "f_name"), 
                        @ColumnResult(name = "l_name")}))
public class AdvocateEntity {
	@Id
	private int id;
	private String fName;
	private String lName;
	private String region;
	private int twitterFollowers;

	public AdvocateEntity() {
	}

	public AdvocateEntity(int id, String fName, String lName, String region, int twitterFollowers) {
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.region = region;
		this.twitterFollowers = twitterFollowers;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getTwitterFollowers() {
		return twitterFollowers;
	}

	public void setTwitterFollowers(int twitterFollowers) {
		this.twitterFollowers = twitterFollowers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fName == null) ? 0 : fName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lName == null) ? 0 : lName.hashCode());
		result = prime * result + ((region == null) ? 0 : region.hashCode());
		result = prime * result + twitterFollowers;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdvocateEntity other = (AdvocateEntity) obj;
		if (fName == null) {
			if (other.fName != null)
				return false;
		} else if (!fName.equals(other.fName))
			return false;
		if (id != other.id)
			return false;
		if (lName == null) {
			if (other.lName != null)
				return false;
		} else if (!lName.equals(other.lName))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (twitterFollowers != other.twitterFollowers)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AdvocateEntity [id=" + id + ", fName=" + fName + ", lName=" + lName + ", region=" + region
				+ ", twitterFollowers=" + twitterFollowers + "]";
	}

}