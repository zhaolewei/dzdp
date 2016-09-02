package com.zlw.dzdp.utils.pinyinsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zlw on 2016/8/18 0018.
 */
public class PinyinSortHelper {

    /**
     * 将String[] 转换成List<SortMode>集合 以便排序
     *
     * @param data
     * @return
     */
    public static List<SortMode> convertToSortMode(String[] data) {

        List<SortMode> mLists = new ArrayList<SortMode>();

        CharacterParser characterParser = CharacterParser.getInstance();

        for (int i = 0; i < data.length; i++) {
            SortMode mode = new SortMode();
            mode.setName(data[i]);
            String pinyin = characterParser.getSelling(data[i]);
            // Log.i("INFO", pinyin);
            String sortLetter = pinyin.substring(0, 1).toUpperCase();
            // Log.i("INFO", sortLetter);
            mode.setSorteLtter(sortLetter);
            mLists.add(mode);
        }

        return mLists;
    }

    public static List<SortMode> makeSort(String[] data) {
        List<SortMode> mLists = convertToSortMode(data);
        return makeSort(mLists);
    }

    public static List<SortMode> makeSort(List<SortMode> mLists) {
        // 排序
        Collections.sort(mLists, new PinyinComparator());
        return mLists;
    }
}
