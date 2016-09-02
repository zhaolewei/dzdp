 package com.zlw.dzdp.bean;

/**
 * 
 * 商家信息
 * 
 * @author zlw
 */
public class Shop {
	private String id;
	private String name; // 商家名称
	private String tel; // 联系方式
	private String address; // 所在详细地址
	private String area; // 所属区域
	private String openTime; // 开业时间

	private double lon; // 经度
	private double lat; // 纬度

	private String traffic; // 交通信息

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public String getTraffic() {
		return traffic;
	}

	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}

	public Shop() {
		// TODO Auto-generated constructor stub
	}

	public Shop(String id, String name, String tel, String address, String area,
			String openTime, double lon, double lat, String traffic) {
		super();
		this.id = id;
		this.name = name;
		this.tel = tel;
		this.address = address;
		this.area = area;
		this.openTime = openTime;
		this.lon = lon;
		this.lat = lat;
		this.traffic = traffic;
	}
}
