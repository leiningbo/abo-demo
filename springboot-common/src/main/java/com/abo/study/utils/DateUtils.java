package com.abo.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

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

    /**
     * 获得近几个月的开始和结束时间
     * month 1:表示当月
     * 例如：2021-05-01 00:00:00  2021-05-31 23:59:59
     */
    public static Map<String, Date> getStartToEndTimeByMonth(int month) throws ParseException {
        Map<String,Date> dateMap = new LinkedHashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar start = Calendar.getInstance();
        start.add(Calendar.MONTH, -(month - 1));
        start.set(Calendar.DAY_OF_MONTH, 1);
        String startTime = sdf.format(start.getTime()) + " 00:00:00";
        dateMap.put("startTime", simpleDateFormat.parse(startTime));

        // 当月最后一天
        Calendar end = Calendar.getInstance();
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endTime = sdf.format(end.getTime()) + " 23:59:59";
        dateMap.put("endTime", simpleDateFormat.parse(endTime));
        return dateMap;
    }

    public static Map<String, Date> getLastYearAugustToThisYearJuly(int month) throws ParseException {
        Map<String,Date> dateMap = new LinkedHashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 去年8月第一天
        Calendar start = Calendar.getInstance();
        if (month < 8) {
            start.add(Calendar.YEAR, -1);
        }
        start.add(Calendar.MONTH, 7);
        start.set(Calendar.DAY_OF_MONTH, 1);
        String startTime = sdf.format(start.getTime()) + " 00:00:00";
        dateMap.put("startTime", simpleDateFormat.parse(startTime));

        // 今年7月最后一天
        Calendar end = Calendar.getInstance();
        if (month > 7) {
            end.add(Calendar.YEAR, 1);
        }
        end.set(Calendar.MONTH, 6);
        end.set(Calendar.DAY_OF_MONTH, end.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endTime = sdf.format(end.getTime()) + " 23:59:59";
        dateMap.put("endTime", simpleDateFormat.parse(endTime));
        return dateMap;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Date> threeMonthStartToEndTime = getLastYearAugustToThisYearJuly(8);

    }

}
