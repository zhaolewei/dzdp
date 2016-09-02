package com.zlw.dzdp.bean;

public class Goods {

	private String id;   
	private String categoryId; // 类别ID
	private String shopId;    //商家ID
	private String cityId;  //城市Id
	private String title;   // 商品名称
	private String sortTitle; //商品描述
	private String imgUrl; //图片URL
	private String startTime; //开售时间
	private String value;  //原价
	private String price;  // 售价
	private String ribat; // 折扣
	private String bought; //购买量
	private String maxQuota; //最大购买量
	private String post; 
	private String soldOut;
	private String tip;
	private String endTime;//结束时间
	private String detail;  //描述详情
	private String minquota; //
	private String distance;
	private Shop shop;  //所属商家
	private boolean isRefund;  
	private boolean isOverTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSortTitle() {
		return sortTitle;
	}
	public void setSortTitle(String sortTitle) {
		this.sortTitle = sortTitle;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRibat() {
		return ribat;
	}
	public void setRibat(String ribat) {
		this.ribat = ribat;
	}
	public String getBought() {
		return bought;
	}
	public void setBought(String bought) {
		this.bought = bought;
	}
	public String getMaxQuota() {
		return maxQuota;
	}
	public void setMaxQuota(String maxQuota) {
		this.maxQuota = maxQuota;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public String getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(String soldOut) {
		this.soldOut = soldOut;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getMinquota() {
		return minquota;
	}
	public void setMinquota(String minquota) {
		this.minquota = minquota;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public Shop getShop() {
		return shop;
	}
	public void setShop(Shop shop) {
		this.shop = shop;
	}
	public boolean isRefund() {
		return isRefund;
	}
	public void setRefund(boolean isRefund) {
		this.isRefund = isRefund;
	}
	public boolean isOverTime() {
		return isOverTime;
	}
	public void setOverTime(boolean isOverTime) {
		this.isOverTime = isOverTime;
	}
	
	public Goods() {
		// TODO Auto-generated constructor stub
	}

	public Goods(String id, String categoryId, String shopId, String cityId,
			String title, String sortTitle, String imgUrl, String startTime,
			String value, String price, String ribat, String bought,
			String maxQuota, String post, String soldOut, String tip,
			String endTime, String detail, String minquota, String distance,
			Shop shop, boolean isRefund, boolean isOverTime) {
		super();
		this.id = id;
		this.categoryId = categoryId;
		this.shopId = shopId;
		this.cityId = cityId;
		this.title = title;
		this.sortTitle = sortTitle;
		this.imgUrl = imgUrl;
		this.startTime = startTime;
		this.value = value;
		this.price = price;
		this.ribat = ribat;
		this.bought = bought;
		this.maxQuota = maxQuota;
		this.post = post;
		this.soldOut = soldOut;
		this.tip = tip;
		this.endTime = endTime;
		this.detail = detail;
		this.minquota = minquota;
		this.distance = distance;
		this.shop = shop;
		this.isRefund = isRefund;
		this.isOverTime = isOverTime;
	}
}


