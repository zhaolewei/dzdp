package com.zlw.dzdp.utils;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class SharedUtils {

	private static final String FILE_NAME = "dianping";
	private static final String MODE_NAME = "welcome";

	/**
	 * 
	 * 设置isFirst属性
	 * 
	 * 
	 * @param context
	 * @param isFirst
	 */
	public static void putIsFirstBoolean(Context context, boolean isFirst) {
		Editor edit = context.getSharedPreferences(FILE_NAME, Context.MODE_APPEND).edit();
		edit.putBoolean(MODE_NAME, isFirst);
		edit.commit();
	}

	/**
	 * 是否是第一次进入
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isFirst(Context context) {
		return context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).getBoolean(MODE_NAME, true);
	}

}
