package rwk51_mvc_beer3.model.beer;

public class BeerImageDTO {

	private int i_id;
	
	private String i_file_name;
	private String i_original_name;
	private String i_thumbnail_name;
	private long i_file_size;
	private int i_f_id;
	
	public BeerImageDTO() {
		super();
	}
	
	public BeerImageDTO(int i_id, String i_file_name, String i_original_name, String i_thumbnail_name, long i_file_size,
			int i_f_id) {
		super();
		this.i_id = i_id;
		this.i_file_name = i_file_name;
		this.i_original_name = i_original_name;
		this.i_thumbnail_name = i_thumbnail_name;
		this.i_file_size = i_file_size;
		this.i_f_id = i_f_id;
	}
	
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public String getI_file_name() {
		return i_file_name;
	}
	public void setI_file_name(String i_file_name) {
		this.i_file_name = i_file_name;
	}
	public String getI_original_name() {
		return i_original_name;
	}
	public void setI_original_name(String i_original_name) {
		this.i_original_name = i_original_name;
	}
	public String getI_thumbnail_name() {
		return i_thumbnail_name;
	}
	public void setI_thumbnail_name(String i_thumbnail_name) {
		this.i_thumbnail_name = i_thumbnail_name;
	}
	public long getI_file_size() {
		return i_file_size;
	}
	public void setI_file_size(long i_file_size) {
		this.i_file_size = i_file_size;
	}
	public int getI_f_id() {
		return i_f_id;
	}
	public void setI_f_id(int i_f_id) {
		this.i_f_id = i_f_id;
	}
	
}
