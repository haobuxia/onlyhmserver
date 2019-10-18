package com.tianyi.helmet.server.util;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

/**
 * 日期新旧api转换
 *
 * Created by liuhanc on 2017/10/13.
 */
public class Dates {
    private static final Logger logger = LoggerFactory.getLogger(Dates.class);

    public static final long ONE_SEC_MILLIES = 1000;
    public static final long ONE_MIN_MILLIES = 60 * ONE_SEC_MILLIES;
    public static final long ONE_HOUR_MILLIES = 60 * ONE_MIN_MILLIES;

    public static long duration(LocalDateTime d1,LocalDateTime d2){
        Duration d = Duration.between(d1,d2);
        return d.toMillis();
    }

    public static Date toDate(LocalDateTime ldt){
        if(ldt == null) return  null;
        Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date toDate(LocalDate ld){
        if(ld == null) return  null;
        Instant instant = ld.atStartOfDay(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate toLocalDate(Date d){
        if(d == null) return  null;
        return toLocalDateTime(d).toLocalDate();
    }

    public static LocalTime toLocalTime(Date d){
        if(d == null) return  null;
        return toLocalDateTime(d).toLocalTime();
    }

    public static LocalDateTime toLocalDateTime(Date d){
        if(d == null) return  null;
        return LocalDateTime.ofInstant(d.toInstant(),ZoneId.systemDefault());
    }

    public static String format(Date d,String format){
        return new SimpleDateFormat(format).format(d);
    }

    public static String format(LocalDate d,String format){
        return new SimpleDateFormat(format).format(toDate(d));
    }

    public static String format(LocalDateTime d,String format){
        return new SimpleDateFormat(format).format(toDate(d));
    }

    public static Date parse(String str,String format){
        try{
            return new SimpleDateFormat(format).parse(str);
        }catch(Exception e){
            logger.error("解析日期失败.str:"+str+",format:"+format);
            return null;
        }
    }

    public static String formatSeconds(int seconds){
        int min = seconds/60;
        int leftSecond = seconds%60;
        int hour = min/60;
        int leftMin = min%60;
        return Strings.padStart(""+hour,2,'0')+":"+Strings.padStart(""+leftMin,2,'0')+":"+Strings.padStart(""+leftSecond,2,'0');
    }

    public static String formatMillieseconds(long millies,char milliesSplit){
        int hour = (int)(millies / ONE_HOUR_MILLIES);
        int leftMillies = (int)(millies % ONE_HOUR_MILLIES);
        int min = (int)(leftMillies / ONE_MIN_MILLIES);
        leftMillies = (int)(leftMillies % ONE_MIN_MILLIES);
        int second = (int)(leftMillies / ONE_SEC_MILLIES);
        leftMillies = (int)(leftMillies % ONE_SEC_MILLIES);
        //format : 00:00:01.878
        return Strings.padStart(""+hour,2,'0') + ":" + Strings.padStart(""+min,2,'0') + ":" + Strings.padStart(""+second,2,'0') + milliesSplit + Strings.padStart(""+leftMillies,3,'0');
    }

    public static void main(String[] a){
        System.out.println(formatSeconds(20));
        System.out.println(formatSeconds(55));
        System.out.println(formatSeconds(75));
        System.out.println(formatSeconds(89));
    }
}
