package com.hfbh.yuecheng.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author：Libin on 2016/12/19 10:11
 * Email：1993911441@qq.com
 * Describe：时间戳
 */
public class DateUtils {

    /**
     * @param type
     * @param time
     * @return 获取日期
     */
    public static String getDate(String type, int time) {
        SimpleDateFormat sdf = new SimpleDateFormat(type, Locale.CHINA);
        return sdf.format(new Date(time));
    }

    /**
     * @param time
     * @return 字符串转时间戳毫秒
     */
    public static long getTime(String time) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}
