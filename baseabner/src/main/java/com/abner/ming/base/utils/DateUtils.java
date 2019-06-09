/**
 *
 */
package com.abner.ming.base.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author tongzhui.peng
 */
@SuppressLint("SimpleDateFormat")
public final class DateUtils {

    public static String format(Date date, String format) {

        if (date == null || Utils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String format(long timeMillion, String format) {
        if (timeMillion < 31507200000L) {
            timeMillion *= 1000;
        }
        return format(new Date(timeMillion), format);
    }

    /**
     * @param date   long 20140507
     * @param format
     * @return
     */
    public static String format(long date, long time, String format) {
        try {
            Date d = getDateFromNumber(date, time);
            return format(d, format);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 处理符合时间格式字符串
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parser(String dateStr, String format) {

        if (Utils.isEmpty(dateStr) || Utils.isEmpty(format)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }


    public static String getAmonthAgo(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.MONTH, -1);
        return format(calendar.getTime(), format);
    }

    public static String getAmonthAgo(Date endDate, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.MONTH, -1);
        return format(calendar.getTime(), format);
    }

    /**
     * 处理非时间格式字符串如：date=20141010 time=163022
     *
     * @param date
     * @param time
     * @return
     */
    public static Date getDateFromNumber(long date, long time) {

        long year = date / 10000;
        long month = (date % 10000) / 100;
        long day = date % 100;
        long hour = time / 10000;
        long minute = (time % 10000) / 100;
        long second = time % 100;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, (int) year);
        calendar.set(Calendar.MONTH, (int) month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, (int) day);

        calendar.set(Calendar.HOUR_OF_DAY, (int) hour);
        calendar.set(Calendar.MINUTE, (int) minute);
        calendar.set(Calendar.SECOND, (int) second);

        return calendar.getTime();
    }

    public static String getDateFromNumber(long date, long time, String format) {

        return DateUtils.format(getDateFromNumber(date, time), format);

    }

    public static boolean isInOneMonth(String startDateStr, String endDateStr) {

        Date start = parser(startDateStr, "yyyyMMdd");
        if (start == null) {
            return false;
        }
        Date end = parser(endDateStr, "yyyyMMdd");
        if (end == null) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.MONTH, -1);

        Date monthAgo = calendar.getTime();

        if (start.compareTo(monthAgo) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean isInOneMonth(Date startDate, Date endDate) {

        if (startDate == null || endDate == null) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(endDate);
        calendar.add(Calendar.MONTH, -1);

        Date monthAgo = calendar.getTime();

        if (startDate.compareTo(monthAgo) >= 0) {
            return true;
        }
        return false;
    }

    /**
     * @return null 时间格式错误
     */
    public static String getTimeAgoString(String dateStr, String paramFormat) {
        Date date = parser(dateStr, paramFormat);
        if (date == null) {
            return dateStr;
        }
        long timeMillion = date.getTime();
        String result = getTimeAgoString(timeMillion, paramFormat);
        if (Utils.isEmpty(result)) {
            return dateStr;
        }
        return result;
    }

    /**
     * @return null 时间格式错误
     */
    public static String getTimeAgoString(long timeMillion, String resultFormat) {

        if (timeMillion < 31507200000L) {
            timeMillion *= 1000;
        }
        long currTime = System.currentTimeMillis();

        long timeSub = currTime - timeMillion;
        if (timeSub < 0) {
            return "刚刚";
        }

        if (timeSub < (3 * 60 * 1000)) {
            return "刚刚";
        }

        if (timeSub < (60 * 60 * 1000)) {
            long t = timeSub / (60 * 1000);
            return t + "分钟前";
        }

//		if(timeSub < (60 * 60 * 1000)){
//			return "半小时前";
//		}

        if (timeSub < (24 * 60 * 60 * 1000)) {
            long t = timeSub / (60 * 60 * 1000);
            return t + "小时前";
        }

        Calendar yesterday = Calendar.getInstance();
        yesterday.setTimeInMillis(System.currentTimeMillis());
        yesterday.add(Calendar.DAY_OF_MONTH, -1);
        yesterday.set(Calendar.HOUR, 0);
        yesterday.set(Calendar.MINUTE, 0);
        yesterday.set(Calendar.SECOND, 0);
        yesterday.set(Calendar.MILLISECOND, 0);

        if (timeMillion > yesterday.getTimeInMillis()) {

            return "昨天" + format(timeMillion, "HH:mm");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeMillion);
        int defYear = calendar.get(Calendar.YEAR);

        Calendar currCalendar = Calendar.getInstance();
        currCalendar.setTimeInMillis(System.currentTimeMillis());
        int currYear = currCalendar.get(Calendar.YEAR);
        if (defYear == currYear) {
            return format(timeMillion, "MM-dd HH:mm");
        } else {
            return format(timeMillion, "yyyy-MM-dd");
        }

    }


    public static Date getDateByMillis(long time) {
        if (time <= 0) {
            return null;
        }
        Date dat = new Date(time);
        return dat;
    }

    public static String getStringDate(Date date) {

        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);


    }

    public static String getStringDateByMillis(long time) {

        return getStringDate(getDateByMillis(time));


    }

    public static boolean isToday(long time) {
        Calendar calendar = Calendar.getInstance();


        return true;

    }

    /**
     * 知我
     */

    private static final SimpleDateFormat FORMATTER_INPUT = new SimpleDateFormat(
            "EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
    private static final SimpleDateFormat FORMATTER_OUTPUT = new SimpleDateFormat(
            "yyyy年MM月dd日 HH:mm");
    private static final SimpleDateFormat FORMATTER_INPUT2 = new SimpleDateFormat(
            "yyyyMMddHHmmss");
    private static final SimpleDateFormat FORMATTER_INPUT3 = new SimpleDateFormat(
            "yyyy-MM-dd");

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String paserDate(String date) {
        try {
            Date myDate = FORMATTER_INPUT2.parse(date);
            Date curDate = new Date();
            long dif = curDate.getTime() - myDate.getTime();

            long temp = 0l;
            if (isToday(myDate)) {
                if (dif < 0) {
                    return "1秒前";
                }

                temp = dif / (60L * 60L * 1000L);
                if (temp > 0) {

                    return temp + "小时前";
                }
                temp = dif / (60 * 1000L);
                if (temp > 0) {

                    return temp + "分钟前";
                }
                temp = dif / (1000L);
                if (temp > 0) {
                    return temp + "秒前";
                }
            }

            return FORMATTER_OUTPUT.format(myDate);

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断传入的日期是否是今天
     *
     * @param mDate
     * @return
     */
    public static boolean isToday(Date mDate) {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String todayStr = format.format(date);
        String mStr = format.format(mDate);

        SimpleDateFormat mm = new SimpleDateFormat("MM");
        SimpleDateFormat yy = new SimpleDateFormat("yyyy");
        if (!(yy.format(date).equals(yy.format(mDate)))) {
            return false;
        } else {

            if (!(mm.format(date).equals(mm.format(mDate)))) {
                return false;
            } else {
                if (todayStr.equals(mStr)) {
                    return true;
                } else {
                    return false;
                }

            }
        }
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        Date date = new Date();
        return paserDate(FORMATTER_INPUT2.format(date));
    }

    public static String getCurrentTime2() {
        Date date = new Date();
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        return ymd.format(date);
    }

    /**
     * 由手机时间判断是否是周末
     */
    public static boolean isWeekEnd() {

        int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        // Log.d("Time", "week="+week);
        if (week == Calendar.SATURDAY || week == Calendar.SUNDAY)
            return true;
        else
            return false;

    }

    /**
     * 判断当前日期是星期几
     *
     * @param
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static String dayForWeek(Calendar c) {
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        switch (dayForWeek) {
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            case 6:
                return "周六";
            case 7:
                return "周日";
            default:
                return "";
        }
    }


}
