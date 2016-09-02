package com.zlw.dzdp.utils.pinyinsort;

import java.util.Comparator;

public class PinyinComparator implements Comparator<SortMode> {

	/**
	 * 排序
	 * Collections.sort(data, new PinyinComparator());
	 * @author zlw QQ:739043667
	 * @param 
	 * @return 
	 */


	@Override
	public int compare(SortMode lhs, SortMode rhs) {
		// TODO Auto-generated method stub
		return lhs.getSorteLtter().compareTo(rhs.getSorteLtter());
	}

}
