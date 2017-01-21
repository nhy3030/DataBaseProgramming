package model.product;

public class Product {
	
	  /**
	   * 사용자 관리를 위하여 필요한 도메인 클래스.
	   * PRODUCT 테이블의 각 칼럼에 해당하는 setter와 getter를 가진다. 
	   */
	
	  	private int product_id;
	  	private String product_name;
	  	private int product_price;
	  	private int category_id;
	  	private int score;
	  	/**recommend 추천 기능을 위한 추가 getter/setter **/
	  	private String brand_name;
	  	private String category_name;
	  	private int event_id; //일반 이벤트 아이디
	  	private String elist_content; //진행중인 이벤트 내용
	  	private String content; //일반 이벤트 이름
	  	
	  	/** 가격비교를 위한 추가 변수 **/
	  	private int dc_price;
	  	
	  	
		public int getDc_price() {
			return dc_price;
		}
		public void setDc_price(int dc_price) {
			this.dc_price = dc_price;
		}
		public int getEvent_id() {
			return event_id;
		}
		public void setEvent_id(int event_id) {
			this.event_id = event_id;
		}
		public String getElist_content() {
			return elist_content;
		}
		public void setElist_content(String elist_content) {
			this.elist_content = elist_content;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
		}
		public int getProduct_id() {
			return product_id;
		}
		public void setProduct_id(int product_id) {
			this.product_id = product_id;
		}
		public String getProduct_name() {
			return product_name;
		}
		public void setProduct_name(String product_name) {
			this.product_name = product_name;
		}
		public int getProduct_price() {
			return product_price;
		}
		public void setProduct_price(int product_price) {
			this.product_price = product_price;
		}
		public int getCategory_id() {
			return category_id;
		}
		public void setCategory_id(int category_id) {
			this.category_id = category_id;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
		public String getBrand_name() {
			return brand_name;
		}
		public void setBrand_name(String brand_name) {
			this.brand_name = brand_name;
		}
		public String getCategory_name() {
			return category_name;
		}
		public void setCategory_name(String category_name) {
			this.category_name = category_name;
		}
	  	

	
}
