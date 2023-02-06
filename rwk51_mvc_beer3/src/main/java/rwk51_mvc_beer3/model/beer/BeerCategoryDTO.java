package rwk51_mvc_beer3.model.beer;

public class BeerCategoryDTO {
	
private String b_category;


private String country_code;

public String getB_category() {
	return b_category;
}
public void setB_category(String b_category) {
	this.b_category = b_category;
}
public String getCountry_code() {
	return country_code;
}
public void setCountry_code(String country_code) {
	this.country_code = country_code;
}

public BeerCategoryDTO(){
	super();
}
public BeerCategoryDTO(String b_category, String country_code) {
	super();
	this.b_category = b_category;
	this.country_code = country_code;
}
}
