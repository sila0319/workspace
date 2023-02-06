package rwk51_mvc_beer3.model.beer;

public class BeerCountryDTO {

private String b_country;
private String country_code;

public String getB_country() {
	return b_country;
}
public void setB_country(String b_category) {
	this.b_country = b_category;
}
public String getCountry_code() {
	return country_code;
}
public void setCountry_code(String country_code) {
	this.country_code = country_code;
}

public BeerCountryDTO(){
	super();	
}
public BeerCountryDTO(String b_country, String country_code) {
	super();
	this.b_country = b_country;
	this.country_code = country_code;
}
}
