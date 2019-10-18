package com.tianyi.helmet.server.util;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * 常用工具方法
 * <p>
 * Created by liuhanc on 2017/10/25.
 */
public class Commons {
    /**
     * stream 的distinct方法本身没有参数，针对自定义类如果要distinct就无法实现，此方法变通实现。
     * stream调用filter方法时传入此函数
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 判断字符和攒是否是int数字
     *
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    /**
     * 是否正整数
     *
     * @param ii
     * @return
     */
    public static boolean isPlusInt(Integer ii) {
        return ii != null && ii.intValue() >= 0;
    }

    /**
     * 是否数字
     *
     * @param s
     * @return
     */
    public final static boolean isNumeric(String s) {
        if (s != null && !"".equals(s.trim()))
            return s.matches("^[0-9]*$");
        else
            return false;
    }

    /**
     * 是否手机号
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (StringUtils.isEmpty(mobile) || mobile.trim().length() != 11) {
            return false;
        }
        if (!isNumeric(mobile)) {
            return false;
        }
        if (!mobile.startsWith("1")) {
            return false;
        }
        return true;
    }

    /**
     * 手机号码打掩码避免泄露
     *
     * @param mobile
     * @return
     */
    public static String maskMobile(String mobile) {
        if (isMobile(mobile)) {
            return mobile.substring(0, 4) + "***" + mobile.substring(7, 11);
        }
        return mobile;
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerFirst(String str) {
        if (StringUtils.isEmpty(str)) return str;
        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperFirst(String str) {
        if (StringUtils.isEmpty(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 将基准url和接口具体uri连接成一个完整的url
     *
     * @param baseUrl
     * @param uri
     * @return
     */
    public static String concatUrl(String baseUrl, String uri) {
        if (baseUrl.endsWith("/")) {
            if (uri.startsWith("/"))
                return baseUrl + uri.substring(1);
            else
                return baseUrl + uri;
        } else {
            if (uri.startsWith("/"))
                return baseUrl + uri;
            else
                return baseUrl + "/" + uri;
        }
    }

    /**
     * 字符串截取
     *
     * @param src
     * @param start
     * @param end
     * @return
     */
    public static String subString(String src, int start, int end) {
        if (src.length() <= start || src.length() < end) {
            return null;
        }
        return src.substring(start, end);//2个字节
    }

    /**
     * 左侧补齐方式使字符串达到某个长度
     *
     * @param srcStr
     * @param toLength
     * @return
     */
    public static String leftToLength(String srcStr, int toLength, char fullfillChar) {
        if (srcStr == null) srcStr = "";
        int len = srcStr.length();
        if (len > toLength) return srcStr;
        int fulfillLen = toLength - len;
        char[] charArray = new char[fulfillLen];
        Arrays.fill(charArray, fullfillChar);
        return new String(charArray) + srcStr;
    }
}
