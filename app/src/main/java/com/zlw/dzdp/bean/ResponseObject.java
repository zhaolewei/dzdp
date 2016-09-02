package com.zlw.dzdp.bean;

import java.util.List;

public class ResponseObject<T> {

	private String msg;
	private int state = 1; // 0:失败 ； 1：成功；

	private T datas;
	private int page;
	private int size;
	private int count;

	public ResponseObject() {
		// TODO Auto-generated constructor stub
	}

	public ResponseObject(String msg, int state, T datas, int page, int size, int count) {
		this.msg = msg;
		this.state = state;
		this.datas = datas;
		this.page = page;
		this.size = size;
		this.count = count;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
