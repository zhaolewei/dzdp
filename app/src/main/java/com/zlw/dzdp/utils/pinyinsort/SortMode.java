package com.zlw.dzdp.utils.pinyinsort;

public class SortMode {
	private String name;
	private String sorteLtter;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSorteLtter() {
		return sorteLtter;
	}
	public void setSorteLtter(String sorteLtter) {
		this.sorteLtter = sorteLtter;
	}

	public SortMode(String name, String sorteLtter) {
		this.name = name;
		this.sorteLtter = sorteLtter;
	}

	public SortMode() {
	}
}
