package rwk51_boardgame_mall.model.cart;

public class CartDTO {
	private int c_id;
	private int c_cnt;
	private int user_num;
	private int b_id;
	private int b_price;
	private int b_charge;
	private String b_name;
	public CartDTO(int c_id, int c_cnt, int user_num, int b_id, int b_price, int b_charge, String b_name) {
		super();
		this.c_id = c_id;
		this.c_cnt = c_cnt;
		this.user_num = user_num;
		this.b_id = b_id;
		this.b_price = b_price;
		this.b_charge = b_charge;
		this.b_name = b_name;
	}
	public CartDTO() {
		super();
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getC_cnt() {
		return c_cnt;
	}
	public void setC_cnt(int c_cnt) {
		this.c_cnt = c_cnt;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public int getB_price() {
		return b_price;
	}
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	public int getB_charge() {
		return b_charge;
	}
	public void setB_charge(int b_charge) {
		this.b_charge = b_charge;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	
	public String toString() {
		String s = 
		this.c_id +" "+
		this.c_cnt +" "+
		this.user_num +" "+
		this.b_id +" "+
		this.b_price +" "+
		this.b_charge +" "+
		this.b_name +" ";
				
				return s;
	}
	

}
