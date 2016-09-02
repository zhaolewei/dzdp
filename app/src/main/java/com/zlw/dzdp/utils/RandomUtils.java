package com.zlw.dzdp.utils;

/**
 * 产生随机验证码
 * Created by zlw on 2016/8/23 0023.
 */
public class RandomUtils {

    public static final String RANDOMS = "1234567890qwertyuiopasdfhjklzxbnmQWERTIOPASDFGHJKLZXCVBNM";

    /**
     * 获取随机验证码
     *
     * @param num 验证码长度
     * @return
     */
    public static String getRandom(int num) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < num; i++) {
            int random = (int) (Math.random() * RANDOMS.length());
            buffer.append(RANDOMS.charAt(random));
        }
        return buffer.toString();
    }


}
