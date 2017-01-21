package model.elist;

import java.sql.Date;

public class Elist {
	private String elist_id = null;//이벤트리스트 번호
	private String event_id = null;//이벤트 종류 번호
	private String brand_id = null;
	private String rec_id = null;
	private String elist_content = null;//이벤트 명
	private Date start_date = null;
	private Date end_date = null;
	
	private String event_name = null;//이벤트 종류명
	private String content = null;//이벤트 받아오는거
	private int discount = 0;
	private String brand_name = null;
	private String product_name = null;
	
	public String getElist_id() {
		return elist_id;
	}

	public void setElist_id(String elist_id) {
		this.elist_id = elist_id;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(String brand_id) {
		this.brand_id = brand_id;
	}

	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

	public String getElist_content() {
		return elist_content;
	}

	public void setElist_content(String elist_content) {
		this.elist_content = elist_content;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	
	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
}
