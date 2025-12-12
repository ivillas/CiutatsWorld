package model;

import java.util.Objects;

public class Ciutat {
	
	
	//Atributs
	
	
	private String ID;
	private String Name;
	private String CountryCode;
	private String District;
	private int population;
	
	
	//Getters & Setters
	
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCountryCode() {
		return CountryCode;
	}
	public void setCountryCode(String countryCode) {
		CountryCode = countryCode;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	@Override
	public int hashCode() {
		return Objects.hash(CountryCode, District, ID, Name, population);
	}
	
	
	//Hashcode & equals
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciutat other = (Ciutat) obj;
		return Objects.equals(CountryCode, other.CountryCode) && Objects.equals(District, other.District)
				&& ID == other.ID && Objects.equals(Name, other.Name) && population == other.population;
	}
	
	
	//Tostring
	
	@Override
	public String toString() {
		return "Ciutat [ID=" + ID + ", Name=" + Name + ", CountryCode=" + CountryCode + ", District=" + District
				+ ", population=" + population + "]";
	}
	

	
	
	
	
	

}
