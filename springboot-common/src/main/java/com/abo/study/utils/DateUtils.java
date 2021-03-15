package com.abo.study.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author lnb
 * @date 2021/3/15 15:16
 * @description
 */
public class DateUtils {

    /**
     * 根据日期判断本月有多少天
     * @author 半知半行
     */
    public static int dayByMonth(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH)+1;

        switch (month) {
            case 1: case 3: case 5:case 7:  case 8:  case 10:  case 12:
                return 31;
            case 4:  case 6: case 9:  case 11:
                return 30;
            //对于2月份需要判断是否为闰年
            case 2:
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }

            default:
                return 0;
        }
    }

}
