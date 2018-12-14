/**
 * Copyright 2008 - 2011 Simcore.org.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chao.ssmvue.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期、时间工具类
 * <p/>
 * 集成Commons-Lang对时间、日期的操作方法.
 */
public class DateUtil extends DateUtils {
    /**
     * 比较两个日期是否相同，不包含时间
     *
     * @param start 日期
     * @param end   日期
     * @return 负数：start<end；0：start=end；整数：start>end
     */
    public static int compare(Date start, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int year1 = c1.get(Calendar.YEAR);
        int year2 = c2.get(Calendar.YEAR);
        if (year1 != year2) {
            return year1 - year2;
        }
        int month1 = c1.get(Calendar.MONTH);
        int month2 = c2.get(Calendar.MONTH);
        if (month1 != month2) {
            return month1 - month2;
        }
        int day1 = c1.get(Calendar.DAY_OF_MONTH);
        int day2 = c2.get(Calendar.DAY_OF_MONTH);
        return day1 - day2;
    }

    /**
     * 获得系统当前时间
     *
     * @return
     */
    public static Date getSystemTime() {
        return new Date();
    }

    /**
     * 将日期类型转为字符串,按照 yyyy-MM-dd HH:mm:ss 格式转换
     *
     * @param date   字符串型日期
     * @param format 转换格
     * @return
     */
    public static String dateStringFormat(Date date, String format) {
        if (date == null)
            return "";
        SimpleDateFormat sdf = null;
        if (StringUtils.isNotBlank(format))
            sdf = new SimpleDateFormat(format);
        else
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 将日期类型转为字符串,按照 yyyy-MM-dd HH:mm:ss 格式转换
     *
     * @param strDate 字符串型日期
     * @return
     */
    public static String dateStringFormat(Date strDate) {
        if (strDate == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(strDate);
    }

    /**
     * 将日期类型转为字符串,按照 yyyyMMddHHmmss 格式转换
     *
     * @param strDate 字符串型日期
     * @return
     */
    public static String dateStringFormatF(Date strDate) {
        if (strDate == null)
            return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(strDate);
    }

    /**
     * 将字符串转为日期类型,format 为null时,按照 yyyy-MM-dd HH:mm:ss 格式转换
     *
     * @param strDate 字符串型日期
     * @param format  转换格
     * @return
     */
    public static Date stringDateFormat(String strDate, String format) {
        if (StringUtils.isBlank(strDate)) {
            return null;
        }
        SimpleDateFormat sdf = null;
        if (format == null)
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        else
            sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获得参考日期前一天日期.
     *
     * @param date 参考日期
     * @return Date 参考日期前一天日期
     */
    public static Date getYesterday(Date date) {
        return addDays(date, -1);
    }

    /**
     * 获得参考日期后一天的日期.
     *
     * @param date 参考日期
     * @return Date 参考日期后一天日期
     */
    public static Date getTomorrow(Date date) {
        return addDays(date, 1);
    }

    /**
     * 获得参考日期前两天的日期.
     *
     * @param date 参考日期
     * @return Date 参考日期前两天日期
     */
    public static Date getBeforeYesterday(Date date) {
        return addDays(date, -2);
    }

    /**
     * 获得参考日期后两天的日期.
     *
     * @param date 参考日期
     * @return Date 参考日期后两天日期
     */
    public static Date getAfertTomorrow(Date date) {
        return addDays(date, 2);
    }

    /**
     * 获得参考日期月初的日期.
     *
     * @param date 参考日期
     * @return Date 参考日期月初日期
     */
    public static Date getMonthFistDay(Date date) {
        return setDays(date, 1);
    }

    /**
     * 获得参考日期月末日期.
     *
     * @param date 参考日期
     * @return Date 参考日期月末日期
     */
    public static Date getMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int actualMaximum = calendar.getActualMaximum(Calendar.DATE);
        return setDays(date, actualMaximum);
    }

    /**
     * 获得参考日期前一个交易日(周六日不计).
     *
     * @param date 参考日期
     * @return Date 参考日期前一个交易日
     */
    public static Date getBeforeTrad(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        do {
            calendar.roll(Calendar.DATE, false);
        } while (calendar.get(Calendar.DAY_OF_WEEK) == 1
                || calendar.get(Calendar.DAY_OF_WEEK) == 7);

        return calendar.getTime();
    }

    /**
     * 获得参考日期后一个交易日(周六日不计)
     *
     * @param date 参考日期
     * @return Date 参考日期后一个交易日
     */
    public static Date getAfterTrad(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        do {
            calendar.roll(Calendar.DATE, true);
        } while (calendar.get(Calendar.DAY_OF_WEEK) == 1
                || calendar.get(Calendar.DAY_OF_WEEK) == 7);

        return calendar.getTime();
    }

    /**
     * 获得参考日期月初第一个交易日(周六日不计)
     *
     * @param date 参考日期
     * @return Date 参考日期月初第一个交易日
     */
    public static Date getMonthFirstTrad(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMonthFistDay(date));

        while (calendar.get(Calendar.DAY_OF_WEEK) == 1
                || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            calendar.roll(Calendar.DATE, true);
        }

        return calendar.getTime();
    }

    /**
     * 获得参考日期月末最后一个交易日(周六日不计)
     *
     * @param date 参考日期
     * @return Date 参考日起月末最后一个交易日
     */
    public static Date getMonthLastTrad(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getMonthLastDay(date));

        while (calendar.get(Calendar.DAY_OF_WEEK) == 1
                || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
            calendar.roll(Calendar.DATE, false);
        }

        return calendar.getTime();
    }

    /**
     * 返回两个时间相减的分钟数
     *
     * @param start
     * @param end
     * @return
     */
    public static long dateReduction(Date start, Date end) {
        long l = end.getTime() - start.getTime();
        return l / 1000 / 60;
    }

    /**
     * 取得参考时间（若为空则取当前时间）加上指定秒
     *
     * @param date
     * @param second
     * @return date
     * @authorJiaYunqi
     */
    public static Date getAddSecond(Date date, int second) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(Calendar.SECOND, second);
        return calendar.getTime();
    }

    /**
     * 取得当前时间之前分钟的时间
     *
     * @param time
     * @return
     */
    public static Date getBeforeDate(int time) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, (-1 * time));
        return c.getTime();
    }

    /**
     * 得到日期是本周的第几天
     *
     * @param date
     * @return
     */
    public static int getWeek(String date) {
        Date d = stringDateFormat(date, "yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        int weekDay = cal.get(Calendar.DAY_OF_WEEK);
        return weekDay;
    }

    /**
     * 本年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);// 获得周数
    }

    /**
     * 年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获得周的第一天和最后一天日期
     *
     * @param date
     * @return
     */
    public static String[] getWeekFirstAndLast(Date date) {

        String[] arr = new String[2];

        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);
        // 所在周开始日期
        arr[0] = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        // 所在周结束日期
        arr[1] = new SimpleDateFormat("yyyyMMdd").format(cal.getTime());

        return arr;
    }

    /**
     * 获得旬的第一天和最后一天日期
     *
     * @param date
     * @return
     */
    public static String[] getXunFirstAndLast(Date date) {

        String[] arr = new String[2];

        int xun = getXunByMonth(date);
        String yyyyMM = DateUtil.dateStringFormat(date, "yyyyMM");

        if (xun == 1) {
            arr[0] = yyyyMM + "01";
            arr[1] = yyyyMM + "10";
        } else if (xun == 2) {
            arr[0] = yyyyMM + "11";
            arr[1] = yyyyMM + "20";
        } else {
            arr[0] = yyyyMM + "21";
            arr[1] = DateUtil.dateStringFormat(getMonthLastDay(date),
                    "yyyyMMdd");
        }

        return arr;
    }

    /**
     * 得到本世纪最后一天
     *
     * @return
     * @author tsx
     * @update 2016年11月18日 下午1:25:18
     */
    public static Date getMaxDate() {
        return stringDateFormat("2099-12-31 23:59:59", null);
    }

    /**
     * 获得本月的第几旬,上旬1,中旬2,下旬3
     *
     * @param date
     * @return
     */
    public static int getXunByMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int value = cal.get(Calendar.DAY_OF_MONTH);

        if (value <= 10) {
            return 1;
        } else if (value > 10 && value <= 20) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * 获取当月最后一天
     *
     * @return
     * @author tsx
     * @update 2016年12月19日 下午4:05:42
     */
    public static int getLastDayOfMonth() {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH,
                cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(cal.getTime());
        return Integer.parseInt(last);
    }

    /**
     * 得到日期所在本年季度.分别为1,2,3,4
     *
     * @param date
     * @return
     */
    public static int getQuarterByYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);

        int jd = 0;
        switch (month) {
            case 0:
            case 1:
            case 2:
                jd = 1;
                break;
            case 3:
            case 4:
            case 5:
                jd = 2;
                break;
            case 6:
            case 7:
            case 9:
                jd = 3;
                break;
            default:
                jd = 4;
                break;
        }

        return jd;
    }

    /**
     * -1：fmtDate < date；0：fmtDate = date；1：fmtDate > date
     * 用于数据库中读出的时间（格式化了的时间）与未格式化的时间比较
     *
     * @return
     * @author tsx
     * @update 2016年12月22日 上午10:40:42
     */
    public static int compareTwoDate(Date fmtDate, Date date) {

        if (fmtDate.getTime() < date.getTime()) {
            return -1;
        } else if (fmtDate.getTime() > date.getTime()) {
            return 1;
        } else {
            return 0;
        }

    }

    /**
     * 得到日期所属季度的第一天和最后一天
     *
     * @param date
     * @return
     */
    public static String[] getQuarterFirstAndLast(Date date) {

        String[] arr = new String[2];

        int jd = getQuarterByYear(date);

        switch (jd) {
            case 1:
                arr[0] = DateUtil.getYear(date) + "0101";
                arr[1] = DateUtil.getYear(date) + "0331";
                break;
            case 2:
                arr[0] = DateUtil.getYear(date) + "0401";
                arr[1] = DateUtil.getYear(date) + "0630";
                break;
            case 3:
                arr[0] = DateUtil.getYear(date) + "0701";
                arr[1] = DateUtil.getYear(date) + "0930";
                break;
            case 4:
                arr[0] = DateUtil.getYear(date) + "1001";
                arr[1] = DateUtil.getYear(date) + "1231";
                break;
            default:
                break;
        }

        return arr;
    }

    public static String[] getTwoMonthFirstAndLast(Date nowDate) {
        String[] arr = new String[2];
        String month = dateStringFormat(nowDate, "MM");
        if (month.equals("01")) {
            arr[0] = DateUtil.getYear(nowDate) + "01";
            arr[1] = DateUtil.getYear(nowDate) + "02";
            return arr;
        }
        if (month.equals("03")) {
            arr[0] = DateUtil.getYear(nowDate) + "03";
            arr[1] = DateUtil.getYear(nowDate) + "04";
            return arr;
        }
        if (month.equals("05")) {
            arr[0] = DateUtil.getYear(nowDate) + "05";
            arr[1] = DateUtil.getYear(nowDate) + "06";
            return arr;
        }
        if (month.equals("07")) {
            arr[0] = DateUtil.getYear(nowDate) + "07";
            arr[1] = DateUtil.getYear(nowDate) + "08";
            return arr;
        }
        if (month.equals("09")) {
            arr[0] = DateUtil.getYear(nowDate) + "09";
            arr[1] = DateUtil.getYear(nowDate) + "10";
            return arr;
        }
        if (month.equals("11")) {
            arr[0] = DateUtil.getYear(nowDate) + "11";
            arr[1] = DateUtil.getYear(nowDate) + "12";
            return arr;
        }
        return arr;
    }

    public static String[] getHalfYearFirstAndLast(Date nowDate) {
        String[] arr = new String[2];
        String month = dateStringFormat(nowDate, "MM");
        if (month.equals("01")) {
            arr[0] = DateUtil.getYear(nowDate) + "01";
            arr[1] = DateUtil.getYear(nowDate) + "06";
            return arr;
        }
        if (month.equals("07")) {
            arr[0] = DateUtil.getYear(nowDate) + "07";
            arr[1] = DateUtil.getYear(nowDate) + "12";
            return arr;
        }
        return null;
    }

}
