package com.qishon.common.utils;

import com.qishon.common.common.DateFormatType;
import com.qishon.common.common.RandomType;
import com.qishon.common.common.TimeUnitType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author kexia.lu on 2017/8/31.
 */
public class BaseUtils {

    private final static Logger log = LoggerFactory.getLogger(BaseUtils.class);

    /**
     * 将对象转为x-www-form-urlencoded兼容的字符串格式（属性必须有get方法）
     * @param object 要转换的对象
     * @param class_ 对象的Class
     * @return
     */
    public static String generateFormParams(Object object, Class class_) {
        if (null == object) {
            return null;
        }

        Field[] fields = object.getClass().getDeclaredFields();
        if (fields.length == 0) {
            try {
                object = class_.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return "";
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fields.length; i++) {
            sb.append(fields[i].getName()).append("=").append(fetchFieldValueByName(fields[i].getName(), object)).append("&");
        }
        String resultStr = sb.toString();
        return resultStr.substring(0, resultStr.length() - 1);
    }

    /**
     * 生成随机字符串
     * @param length  随机字符串长度
     * @param type	  1:数字    2:字母   3及其他:数字和字符串类型
     * @return
     */
    public static String generateRandom(int length, RandomType type) {

        if (length < 1) {
            return "";
        }
        String base = null;
        if (RandomType.Number == type) {
            base = "0123456789";
        } else if (RandomType.Chars == type) {
            base = "abcdefghijklmnopqrstuvwxyz";
        } else {
            base = "abcdefghijklmnopqrstuvwxyz0123456789";
        }
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        if (sb.substring(0,1).equals("0")) {
            return (new Random().nextInt(9) + 1) + sb.substring(1);
        }
        return sb.toString();
    }

    /**
     * 以date为基础，生成之前或之后的时间，
     * @param date  当前时间点
     * @param unitType  1:分钟    2:小时    3:天   4:月 为单位
     * @param outDegree  超时时间
     * @param ifAfter  true:生成之后的时间   false:生成之前的时间
     * @return  生成相应的时间
     */
    public static Date generateTimeOut(Date date, TimeUnitType unitType, int outDegree, boolean ifAfter) {
        Calendar cld = Calendar.getInstance();
        cld.setTime(date);
        int degree = unitType.ordinal();
        if (ifAfter) {
            if (0 == degree) {
                int minute =  cld.get(Calendar.MINUTE) + outDegree;
                cld.set(Calendar.MINUTE, minute);
            } else if (1 == degree) {
                int hour = cld.get(Calendar.HOUR) + outDegree;
                cld.set(Calendar.HOUR, hour);
            } else if (2 == degree) {
                int day = cld.get(Calendar.DATE) + outDegree;
                cld.set(Calendar.DATE, day);
            } else if (3 == degree) {
                int month = cld.get(Calendar.MONTH) + outDegree;
                cld.set(Calendar.MONTH, month);
            }
        } else {
            if (0 == degree) {
                int minute =  cld.get(Calendar.MINUTE) - outDegree;
                cld.set(Calendar.MINUTE, minute);
            } else if (1 == degree) {
                int hour = cld.get(Calendar.HOUR) - outDegree;
                cld.set(Calendar.HOUR, hour);
            } else if (2 == degree) {
                int day = cld.get(Calendar.DATE) - outDegree;
                cld.set(Calendar.DATE, day);
            } else if (3 == degree) {
                int month = cld.get(Calendar.MONTH) - outDegree;
                cld.set(Calendar.MONTH, month);
            }
        }
        return cld.getTime();
    }


    /**
     * @return 当前整点小时数的时间磋
     */
    public static long generateCurrentIntegralHourMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    /**
     * long型时间转字符串
     * @param formatType 见DateFormatType
     * @param createTime 时间磋
     * @return
     */
    public static String longToString(DateFormatType formatType, long createTime) {
        if (0 == createTime) {
            return "/";
        }
        return new SimpleDateFormat(formatType.getFormat()).format(new Date(createTime));
    }


    /**
     * 用这个方法的话，属性必须有get方法，否则值为""
     */
    private static Object fetchFieldValueByName(String fieldName, Object object) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = object.getClass().getMethod(getter);
            Object value = method.invoke(object);
            return null == value ? "" : value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        }
    }
}
