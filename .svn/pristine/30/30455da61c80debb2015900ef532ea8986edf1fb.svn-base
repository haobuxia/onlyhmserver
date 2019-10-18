package com.tianyi.helmet.server.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;

/**
 *  编码相关工具
 *
 * Created by liuhanc on 2017/10/15.
 */
public class EncoderUtil {

    public static String urlEncode(String str, String charset) {
        try {
            return java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
        }
        return str;
    }

    public static String urlDecode(String str, String charset) {
        try {
            return java.net.URLDecoder.decode(str, charset);
        } catch (Exception e) {
        }
        return str;
    }

    /**
     * Hex编码.
     */
    public static String hexEncode(byte[] input) {
        return Hex.encodeHexString(input);
    }

    /**
     * Hex解码.
     */
    public static byte[] hexDecode(String input) {
        try {
            return Hex.decodeHex(input.toCharArray());
        } catch (DecoderException e) {
            throw new IllegalStateException("Hex Decoder exception", e);
        }
    }

    /**
     * Base64编码.
     */
    public static String base64Encode(byte[] input) {
        return Base64.encodeBase64String(input);
    }

    /**
     * Base64编码, URL安全(将Base64中的URL非法字符�?,/=转为其他字符, 见RFC3548).
     */
    public static String base64UrlSafeEncode(byte[] input) {
        return Base64.encodeBase64URLSafeString(input);
    }

    /**
     * Base64解码.
     */
    public static byte[] base64Decode(String input) {
        return Base64.decodeBase64(input);
    }

    /**
     * 将1个字节转成对应bit字符串。bit字符串的长度是8，每个字符表示对应的1个bit。
     * @param by
     * @return
     */
    public static String byteToBits(byte by){
        StringBuffer sb = new StringBuffer();
        sb  .append((by>>7)&0x1)
                .append((by>>6)&0x1)
                .append((by>>5)&0x1)
                .append((by>>4)&0x1)
                .append((by>>3)&0x1)
                .append((by>>2)&0x1)
                .append((by>>1)&0x1)
                .append((by>>0)&0x1);
        return sb.toString();
    }

    /**
     * 将1个bit字符串转成对应的1个字节。
     *  bitStr的长度必须是4位或者8位
     *
     * @param bitStr
     * @return
     */
    public static byte bitsToByte(String bitStr){
        if (null == bitStr) {
            return 0;
        }
        int len = bitStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        int  re;
        if (len == 8) {// 8 bit处理
            if (bitStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(bitStr, 2);
            } else {// 负数
                re = Integer.parseInt(bitStr, 2) - 256;
            }
        } else {// 4 bit处理
            re = Integer.parseInt(bitStr, 2);
        }
        return (byte) re;
    }

    public static String md5(String str){
        return encode("md5",str);
    }

    public static String sha1(String str){
        return encode("sha1",str);
    }

    /**
     * 根据某个算法编码,支持md5,sha1
     * @param algorithm
     * @param value
     * @return
     */
    public static String encode(String algorithm, String value) {
        if (value == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(value.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 字节转16进制字符
     * @param bytes
     * @return
     */
    public static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }


    private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}
