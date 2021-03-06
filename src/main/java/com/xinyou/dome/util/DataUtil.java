package com.xinyou.dome.util;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.*;
import java.util.*;

public class DataUtil {


    public static long Date5Timestamp(String datestr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        try {
            date = dateFormat.parse(datestr);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        long timestamp = date.getTime();
        return timestamp;
    }

    private static Number addNumber(Number param1, Number param2) {
        if ((param1 == null) && (param2 == null)) {
            return null;
        }
        boolean haveNull = false;
        if (param2 == null) {
            param2 = 0;
            haveNull = true;
        }
        if (param1 == null) {
            param1 = 0;
            haveNull = true;
        }
        if (haveNull) {
            return new Double(param1.doubleValue() + param2.doubleValue());
        }
        if (param1 instanceof Integer) {
            return new Integer(param1.intValue() + param2.intValue());
        } else if (param1 instanceof Double) {
            return new Double(param1.doubleValue() + param2.doubleValue());
        } else if (param1 instanceof Long) {
            return new Long(param1.longValue() + param2.longValue());
        } else if (param1 instanceof Float) {
            return new Float(param1.floatValue() + param2.floatValue());
        }
        return new Double(param1.doubleValue() + param2.doubleValue());
    }

    private static boolean gtNumber(Number param1, Number param2) {
        if (param1 == null || param2 == null) {
            return false;
        }
        return param1.doubleValue() > param2.doubleValue();
    }

    private static boolean geNumber(Number param1, Number param2) {
        if (param1 == null || param2 == null) {
            return false;
        }
        return param1.doubleValue() >= param2.doubleValue();
    }

    public static boolean parseBool(Object param) {
        return parseBool(param, false);
    }

    public static boolean parseBool(Object param, boolean defaultValue) {
        return parseBoolean(param, defaultValue);
    }

    public static Boolean parseBoolean(Object param) {
        return parseBoolean(param, null);
    }

    public static Boolean parseBoolean(Object param, Boolean defaultValue) {
        if (param == null) {
            return defaultValue;
        }
        try {
            return new Boolean(param.toString());
        } catch (Exception e) {
            return defaultValue;
        }
    }


    public static boolean eq(Number number1, Number number2) {
        if (number1 == null || number2 == null) {
            return false;
        }
        return Double.compare(number1.doubleValue(), number2.doubleValue()) == 0;
    }

    public static boolean gt(Number number1, Number number2) {
        if (number1 == null) {
            return false;
        }
        if (number2 == null) {
            return true;
        }
        return number1.doubleValue() > number2.doubleValue();
    }

    public static final String DATE_PATTEN_DEFAULT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTEN_DAY = "yyyy-MM-dd";
    public static final String DATE_PATTEN_DAY_M = "yyyyMMddHHmmssSSS";

    public static String formatDate(long timeMillis) {
        return formatDate(new Date(timeMillis));
    }

    public static String formatDate(Date date) {
        return formatDate(date, DATE_PATTEN_DEFAULT);
    }

    public static String formatDate(Date date, String patten) {
        if (date == null) {
            return null;
        }
        DateFormat fmt = getDateFormat(patten);
        synchronized (fmt) {
            return fmt.format(date);
        }
    }

    public static Date parseDate(Object param) {
        return parseDate(param, DATE_PATTEN_DEFAULT);
    }

    public static Date parseDate(Object param, String patten) {
        if (param == null) {
            return null;
        }
        try {
            DateFormat fmt = getDateFormat(patten);
            synchronized (fmt) {
                return fmt.parse(param.toString());
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean before(Date sourceDate, Date targetDate) {
        return targetDate.before(sourceDate);
    }

    public static Date getCurrentMonthFirstDat(Date date) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Integer getMonthOfThisYear() {
        return getMonthOfThisYear(new Date());
    }

    public static Integer getMonthOfThisYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;

    }

    public static Integer getWeekOfThisYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    public static Integer getTimeValue(Date date, int valueType) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(valueType);
    }

    public static Date setTimeValue(Date date, int valueType, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(valueType, value);
        return cal.getTime();
    }

    public static Date addTimeValue(Date date, int valueType, int value) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(valueType, value);
        return cal.getTime();
    }

    public static Date getTimeByMonth(Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MONTH, month - 1);
        return cal.getTime();
    }

    public static Date getMonthBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
        return cal.getTime();
    }

    public static Date getMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
        return cal.getTime();
    }

    public static Date getWeekBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Date mm = nDaysAgo(cal.get(Calendar.DAY_OF_WEEK) - 2, date);
        return getDayBegin(mm);
    }

    public static Date getWeekEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Date mm = nDaysAfter(cal.get(8 - Calendar.DAY_OF_WEEK), date);
        return getDayEnd(mm);

    }

    public static Date nDaysAfter(int n, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + n);
        return cal.getTime();
    }

    public static Date getDayBegin(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return cal.getTime();
    }

    public static Date getDayEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH) + 1, 0, 0, 0);
        cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
        return cal.getTime();
    }

    public static Date nMonthsAgo(Integer n, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - n);
        return cal.getTime();
    }

    public static Date nDaysAgo(Integer n, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - n);
        return cal.getTime();
    }

    public static String formatNumber(Number number, String patten) {
        return getNumberFormat(patten).format(number);
    }

    public static NumberFormat getNumberFormat(String patten) {
        if (numberFormatMap == null) {
            numberFormatMap = new HashMap<String, NumberFormat>();
        }
        if (!numberFormatMap.containsKey(patten)) {
            numberFormatMap.put(patten, new DecimalFormat(patten));
        }
        return numberFormatMap.get(patten);
    }

    private static Map<String, NumberFormat> numberFormatMap;

    public static DateFormat getDateFormat(String patten) {
        if (dateFormatMap == null) {
            dateFormatMap = new HashMap<String, DateFormat>();
        }
        if (!dateFormatMap.containsKey(patten)) {
            dateFormatMap.put(patten, new SimpleDateFormat(patten));
            // dateFormatMap.put(patten, new
            // SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
        }
        return dateFormatMap.get(patten);
    }

    private static Map<String, DateFormat> dateFormatMap;

    public static Date minuteToDate(long minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(minute * 60000);
        return calendar.getTime();
    }

    public static int dateToMinute(Date date) {
        if (date == null) {
            return 0;
        }
        return (int) (date.getTime() / 60000);
    }

    public static Set<String> getDateArray(Date startDate, Date endDate) {
        DateFormat df = getDateFormat(DATE_PATTEN_DAY);
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            return null;
        }
        Set<String> result = new LinkedHashSet<String>();
        String endStr = df.format(endDate);
        String startStr = null;
        do {
            startStr = df.format(startDate);
            result.add(startStr);
            startDate = DateUtils.addDays(startDate, 1);
        } while (!startStr.equals(endStr));

        return result;
    }

    /**
     * 杩斿洖浠庡紑濮嬫椂闂村埌缁撴潫鏃堕棿鐨勬墍鏈夋湀浠?
     */
    public static Set<String> getDateMonthArray(Date startDate, Date endDate) {
        DateFormat df = getDateFormat("yyyy-MM");
        if (startDate == null || endDate == null || startDate.after(endDate)) {
            return null;
        }
        Set<String> result = new LinkedHashSet<String>();
        String endStr = df.format(endDate);
        String startStr = null;
        do {
            startStr = df.format(startDate);
            result.add(startStr);
            startDate = DateUtils.addMonths(startDate, 1);
        } while (!startStr.equals(endStr));

        return result;
    }

    public static String getShortDate(Date date) {
        return FastDateFormat.getInstance("yyyyMMdd").format(date);
    }

    public static Date nextMonthFirstDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static int addPriviledge(int priValue, int pri) {
        if (pri > 30 || pri < 1) {
            return priValue;
        }
        return priValue | (int) Math.pow(2, (double) (pri - 1));
    }

    public static int deletePriviledge(int priValue, int pri) {
        if (pri > 30 || pri < 1) {
            return priValue;
        }
        return priValue & ~(int) Math.pow(2, (double) (pri - 1));
    }

    public static boolean havePriviledge(int priValue, int pri) {
        if (pri > 30 || pri < 1) {
            return false;
        }
        return (priValue & (int) Math.pow(2, (double) (pri - 1))) > 0;
    }

    public static Set<Integer> getAllPriviledge(int priValue) {
        Set<Integer> result = new LinkedHashSet<Integer>();
        for (int i = 1; i <= 30; i++) {
            if (havePriviledge(priValue, i)) {
                result.add(i);
            }
        }
        return result;
    }

    public static Map<String, Object> toMap(Collection collection) {
        Map result = new HashMap();
        if (collection != null && collection.size() > 0) {
            for (Iterator iterator = collection.iterator(); iterator.hasNext(); ) {
                Object object = (Object) iterator.next();
                result.put(object.toString(), object);
            }
        }
        return result;
    }

    // Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map
    public static Map<String, Object> transBean2Map(Object obj) {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }

            }
        } catch (Exception e) {
            System.out.println("transBean2Map Error " + e.getMessage());
        }
        return map;
    }

    public static byte[] getIpByteArrayFromString(String ip) {
        byte[] ret = new byte[4];
        StringTokenizer st = new StringTokenizer(ip, ".");
        try {
            ret[0] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[1] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[2] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
            ret[3] = (byte) (Integer.parseInt(st.nextToken()) & 0xFF);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public static String getString(String s, String srcEncoding,
                                   String destEncoding) {
        try {
            return new String(s.getBytes(srcEncoding), destEncoding);
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    public static String getString(byte[] b, String encoding) {
        try {
            return new String(b, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b);
        }
    }

    public static String getString(byte[] b, int offset, int len,
                                   String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException e) {
            return new String(b, offset, len);
        }
    }

    public static String getIpStringFromBytes(byte[] ip) {
        StringBuffer sb = new StringBuffer();
        sb.append(ip[0] & 0xFF);
        sb.append('.');
        sb.append(ip[1] & 0xFF);
        sb.append('.');
        sb.append(ip[2] & 0xFF);
        sb.append('.');
        sb.append(ip[3] & 0xFF);
        return sb.toString();
    }

    public static String getIpStringFromLong(long ip) {
        String ipstr = "";
        ipstr = (ip % 256) + ipstr;
        ip = ip / 256;
        ipstr = "." + ipstr;
        ipstr = (ip % 256) + ipstr;
        ip = ip / 256;
        ipstr = "." + ipstr;
        ipstr = (ip % 256) + ipstr;
        ip = ip / 256;
        ipstr = "." + ipstr;
        ipstr = (ip % 256) + ipstr;
        return ipstr;
    }

    public static long getLongFromIp(String ip) {
        if (ip == null || ip.trim().length() == 0) {
            return 0;
        }
        String[] ipSeg = ip.split("\\.");
        if (ipSeg.length != 4) {
            return 0;
        }
        try {
            int p0 = Integer.parseInt(ipSeg[0]);
            int p1 = Integer.parseInt(ipSeg[1]);
            int p2 = Integer.parseInt(ipSeg[2]);
            int p3 = Integer.parseInt(ipSeg[3]);
            long ipValue = p0 * 256L * 256L * 256L + p1 * 256L * 256L + p2
                    * 256L + p3;
            return ipValue;
        } catch (Exception e) {
        }
        return 0;
    }

    public static long getLongFromIpCSeg(String ip) {
        if (ip == null || ip.trim().length() == 0) {
            return 0;
        }
        String[] ipSeg = ip.split("\\.");
        if (ipSeg.length != 4) {
            return 0;
        }
        try {
            int p0 = Integer.parseInt(ipSeg[0]);
            int p1 = Integer.parseInt(ipSeg[1]);
            int p2 = Integer.parseInt(ipSeg[2]);
            // int p3 = Integer.parseInt(ipSeg[3]);
            int p3 = 0;
            long ipValue = p0 * 256L * 256L * 256L + p1 * 256L * 256L + p2
                    * 256L + p3;
            return ipValue;
        } catch (Exception e) {
        }
        return 0;
    }

    public static String replaceNoPrint(String input) {
        if (input == null || input == "") {
            return input;
        }
        char[] chs = input.toCharArray();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chs.length; i++) {
            if ((chs[i] <= 8) || (chs[i] >= 11 && chs[i] <= 12)
                    || (chs[i] >= 14 && chs[i] <= 31)) {
                sb.append("&#x" + Integer.toHexString((char) chs[i]));
            } else {
                sb.append((char) chs[i]);
            }
        }
        return sb.toString();
    }

    public static Double roundDown2(Double v) {
        if (v == null) {
            return 0.00;
        }
        BigDecimal bd1 = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        double v1 = bd1.divide(one, 2, BigDecimal.ROUND_DOWN).doubleValue();
        return v1;
    }

    public static Double roundUp2(Double v) {
        if (v == null) {
            return 0.00;
        }
        BigDecimal bd1 = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        double v1 = bd1.divide(one, 2, BigDecimal.ROUND_UP).doubleValue();
        return v1;
    }
}
