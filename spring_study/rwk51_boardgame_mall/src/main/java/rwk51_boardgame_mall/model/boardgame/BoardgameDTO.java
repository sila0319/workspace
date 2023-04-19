package rwk51_boardgame_mall.model.boardgame;

public class BoardgameDTO {
	int b_id; //상품 id
	String b_code; //상품코드
	String b_category; //카테고리 (전략,파티,신상,선주문,베스트100) 
	String b_name;//게임이름
	int b_price;//게임 가격 
	int b_age; // 적정연령 
	String b_agestate; //연령 범위
	String b_player; //플레이 수
	String b_time; //플레이시간
	String b_content; //내용
	int b_charge; //배송비
	String b_image; //기본사진
	public int getB_id() {
		return b_id;
	}
	public void setB_id(int b_id) {
		this.b_id = b_id;
	}
	public String getB_code() {
		return b_code;
	}
	public void setB_code(String b_code) {
		this.b_code = b_code;
	}
	public String getB_category() {
		return b_category;
	}
	public void setB_category(String b_category) {
		this.b_category = b_category;
	}
	public String getB_name() {
		return b_name;
	}
	public void setB_name(String b_name) {
		this.b_name = b_name;
	}
	public int getB_price() {
		return b_price;
	}
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	public int getB_age() {
		return b_age;
	}
	public void setB_age(int b_age) {
		this.b_age = b_age;
	}
	public String getB_agestate() {
		return b_agestate;
	}
	public void setB_agestate(String b_agestate) {
		this.b_agestate = b_agestate;
	}
	public String getB_player() {
		return b_player;
	}
	public void setB_player(String b_player) {
		this.b_player = b_player;
	}
	public String getB_time() {
		return b_time;
	}
	public void setB_time(String b_time) {
		this.b_time = b_time;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public int getB_charge() {
		return b_charge;
	}
	public void setB_charge(int b_charge) {
		this.b_charge = b_charge;
	}
	public String getB_image() {
		return b_image;
	}
	public void setB_image(String b_image) {
		this.b_image = b_image;
	}
	public BoardgameDTO(int b_id, String b_code, String b_category, String b_name, int b_price, int b_age, String b_agestate,
			String b_player, String b_time, String b_content, int b_charge, String b_image) {
		super();
		this.b_id = b_id;
		this.b_code = b_code;
		this.b_category = b_category;
		this.b_name = b_name;
		this.b_price = b_price;
		this.b_age = b_age;
		this.b_agestate = b_agestate;
		this.b_player = b_player;
		this.b_time = b_time;
		this.b_content = b_content;
		this.b_charge = b_charge;
		this.b_image = b_image;
	}
	public BoardgameDTO() {
		super();
	}
	
	public String toString() {
		String s = 
				this.b_id +" "+
				this.b_code+" "+
				this.b_category+" "+
				this.b_name+" "+
				this.b_price+" "+
				this.b_age+" "+
				this.b_agestate+" "+
				this.b_player+" "+
				this.b_time+" "+
				this.b_content+" "+
				this.b_charge+" "+
				this.b_image;
		return s;
	}

}
