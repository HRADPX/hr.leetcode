package com.hr.nums;

import com.hr.utils.ReflectUtils;

/**
 * 将数字转中文字符串
 */
public class Number2Chinese {

    private static final String[] CN_UPPER_NUMBER ={"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] CN_UPPER_UNIT = {"", "十", "百", "千", "万", "十", "百", "千", "亿"};

    public String number2Chinese(int number) {

        if (number == 0) {
            return CN_UPPER_NUMBER[0];
        }
        StringBuilder res = new StringBuilder();
        int unit = 0;
        int idx4 = 0;
        while (number > 0) {
            int mod = number % 10;
            if (mod == 0) {
                if (res.length() != 0 && !res.substring(0, 1).equals(CN_UPPER_NUMBER[0])) {
                    res.insert(0, CN_UPPER_NUMBER[0]);
                }
                if (unit == 4) {
                    idx4 = res.length();
                }
            } else {
                res.insert(0, CN_UPPER_UNIT[unit]).insert(0, CN_UPPER_NUMBER[mod]);
            }
            unit++;
            number /= 10;
        }
        if (idx4 > 0 && res.charAt(idx4 - 1) != CN_UPPER_UNIT[8].charAt(0)) {
            res.insert(res.length() - idx4, CN_UPPER_UNIT[4]);
        }
        return res.toString();
    }

    public static void main(String[] args){

        Number2Chinese instance = ReflectUtils.getInstance(Number2Chinese.class);
        System.out.println(instance.number2Chinese(1232));
        System.out.println(instance.number2Chinese(120001));
    }

}
