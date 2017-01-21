package model.basket;

import java.sql.Date;

public class Basket {
	private String userId = null;
	private int product_id = 0;
	private Date basket_date = null;
	private String product_name = null;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public Date getBasket_date() {
		return basket_date;
	}

	public void setBasket_date(Date basket_date) {
		this.basket_date = basket_date;
	}
	
	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}
