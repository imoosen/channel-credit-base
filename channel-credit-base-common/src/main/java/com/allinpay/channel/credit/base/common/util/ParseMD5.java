package com.allinpay.channel.credit.base.common.util;

import java.security.MessageDigest;

/**
 * Created by mengsen on 2017/8/28.
 *
 * @Description: [一句话描述该类的功能]
 * @UpdateUser: mengsen on 2017/8/28.
 */
public class ParseMD5 {
    /**
     * @param str
     * @return
     * @Date: 2017-8-17
     * @Author: mengsen
     * @Description: 32位小写MD5
     */
    public static String parseStrToMd5L32(String str,String charset) {
        String reStr = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes;
            if(charset!=null){
                bytes= md5.digest(str.getBytes(charset));
            }else {
                bytes=md5.digest(str.getBytes());
            }
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            reStr = stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }

    /**
     * @param str
     * @return
     * @Date: 2017-8-17
     * @Author: mengsen
     * @Description: 32位大写MD5
     */
    public static String parseStrToMd5U32(String str,String charset) {
        String reStr = parseStrToMd5L32(str,charset);
        if (reStr != null) {
            reStr = reStr.toUpperCase();
        }
        return reStr;
    }

    /**
     * @param str
     * @return
     * @Date: 2017-8-17
     * @Author: mengsen
     * @Description: 16位小写MD5
     */
    public static String parseStrToMd5U16(String str,String charset) {
        String reStr = parseStrToMd5L32(str,charset);
        if (reStr != null) {
            reStr = reStr.toUpperCase().substring(8, 24);
        }
        return reStr;
    }

    /**
     * @param str
     * @return
     * @Date: 2017-8-17
     * @Author: mengsen
     * @Description: 16位大写MD5
     */
    public static String parseStrToMd5L16(String str,String charset) {
        String reStr = parseStrToMd5L32(str,charset);
        if (reStr != null) {
            reStr = reStr.substring(8, 24);
        }
        return reStr;
    }

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        String test = "mid=1611280001&txnDate=1480063100010&cardNo=998877665544332211&name=李四&certNo=112233445566778899&phone=13888888888&key=123456789";

        System.out.println(parseStrToMd5U32(test,"utf-8"));

    }
}
