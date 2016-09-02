package com.zlw.dzdp.bean;

import java.util.ArrayList;
import java.util.List;

public class LocalInfo {
	// ---------用户信息--------
	private int user_id;
	private String username;
	private String other;// 备注

	// ---------定位信息---------
	private String time;
	/**
	 * error_code : 61 ： GPS定位结果，GPS定位成功。 62 ：
	 * 无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。 63 ：
	 * 网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。 65 ： 定位缓存的结果。 66 ：
	 * 离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。 67 ：
	 * 离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。 68 ： 网络连接失败时，查找本地离线定位时对应的返回结果。
	 * 161： 网络定位结果，网络定位定位成功。 162：
	 * 请求串密文解析失败，一般是由于客户端SO文件加载失败造成，请严格参照开发指南或demo开发，放入对应SO文件。 167：
	 * 服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。 502： key参数错误，请按照说明文档重新申请KEY。 505：
	 * key不存在或者非法，请按照说明文档重新申请KEY。 601： key服务被开发者自己禁用，请按照说明文档重新申请KEY。 602： key
	 * mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；且包名是您当前运行应用的包名，
	 * 请按照说明文档重新申请KEY。 501～700：key验证失败，请按照说明文档重新申请KEY。
	 */
	private int error_code;

	/**
	 * 纬度
	 */
	private double latitude;
	/**
	 * 经度
	 */
	private double longitude;
	/**
	 * 识别半径
	 */
	private float radius;
	/**
	 * 地址
	 */
	private String addr;
	/**
	 * 操作
	 */
	private int operationers;
	/**
	 * 描述：网络定位成功
	 */
	private String describe;
	/**
	 * 位置描述
	 */
	private String locationdescribe;
	/**
	 * 定位点的多少
	 */
	private int size;
	/**
	 * 定位点集合
	 */
	private List<String> pois = new ArrayList<String>();

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getOperationers() {
		return operationers;
	}

	public void setOperationers(int operationers) {
		this.operationers = operationers;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getLocationdescribe() {
		return locationdescribe;
	}

	public void setLocationdescribe(String locationdescribe) {
		this.locationdescribe = locationdescribe;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<String> getPois() {
		return pois;
	}

	public void setPois(List<String> pois) {
		this.pois = pois;
	}

	public LocalInfo() {
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public LocalInfo(int user_id, String username, String other, String time, int error_code, double latitude,
			double longitude, float radius, String addr, int operationers, String describe, String locationdescribe,
			int size, List<String> pois) {
		super();
		this.user_id = user_id;
		this.username = username;
		this.other = other;
		this.time = time;
		this.error_code = error_code;
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		this.addr = addr;
		this.operationers = operationers;
		this.describe = describe;
		this.locationdescribe = locationdescribe;
		this.size = size;
		this.pois = pois;
	}

	@Override
	public String toString() {
		return "LocalInfo [time=" + time + "\n" + ", error_code=" + error_code + "\n" + ", latitude=" + latitude + "\n"
				+ ", lontitude=" + longitude + "\n" + ", radius=" + radius + "\n" + ", addr=" + addr + "\n"
				+ ", operationers=" + operationers + "\n" + ", describe=" + describe + "\n" + ", locationdescribe="
				+ locationdescribe + "\n" + ", size=" + size + "\n" + ", pois=" + pois + "\n" + "]" + "\n";
	}

}
