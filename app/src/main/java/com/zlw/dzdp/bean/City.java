package com.zlw.dzdp.bean;

public class City {

	private int city_id;
	private String city_name;
	private String city_sortkey;

	public int getCity_id() {
		return city_id;
	}

	public void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}

	public String getCity_sortkey() {
		return city_sortkey;
	}

	public void setCity_sortkey(String city_sortkey) {
		this.city_sortkey = city_sortkey;
	}

	public City() {
	}

	public City(int city_id, String city_name, String city_sortkey) {
		super();
		this.city_id = city_id;
		this.city_name = city_name;
		this.city_sortkey = city_sortkey;
	}

}
