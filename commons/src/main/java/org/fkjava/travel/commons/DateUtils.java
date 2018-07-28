package org.fkjava.travel.commons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LuoWenqiang
 */
public class DateUtils {

    private static final SimpleDateFormat[] FORMATERS = new SimpleDateFormat[]{
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
        new SimpleDateFormat("yyyy-MM-dd HH:mm"),
        new SimpleDateFormat("yyyy-MM-dd")
    };

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Date对象转换为yyyy-MM-dd格式的字符串
     *
     * @param date
     * @return
     */
    public static String dataToString(Date date) {
        return dateFormat.format(date);
    }

    /**
     * Date对象转换为yyyy-MM-dd HH:mm:ss格式的字符串
     *
     * @param date
     * @return
     */
    public static String dataTimeToString(Date date) {
        return dateTimeFormat.format(date);
    }

    /**
     * 把yyyy-MM-dd格式的字符串转换为Date对象
     *
     * @param date
     * @return
     */
    public static Date toDate(String date) {
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * 把yyyy-MM-dd HH:mm:ss格式的字符串转换为Date对象
     *
     * @param date
     * @return
     */
    public static Date toDateTime(String date) {
        for (SimpleDateFormat sdf : FORMATERS) {
            try {
                Date d = sdf.parse(date);
                return d;
            } catch (ParseException ex) {
                //Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        throw new IllegalArgumentException("字符串 " + date + " 转换为Date对象");
        //return null;
    }
}
