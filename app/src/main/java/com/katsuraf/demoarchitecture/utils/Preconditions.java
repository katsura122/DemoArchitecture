package com.katsuraf.demoarchitecture.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述：输入检查工具类
 */
public class Preconditions {
    public static boolean checkLength(String value, int minLength, int maxLength) {
        int length = value.trim().length();
        String chinese = "[\u4e00-\u9fa5]";
        for (int i = 0; i < value.length(); i++) {
            String temp = value.substring(i, i + 1);
            if (temp.matches(chinese)) {
                length += 1;
            }
        }
        return !checkNull(value) && 0 < length && length >= minLength
                && length <= maxLength;
    }

    public static boolean checkEmail(String value) {
        Pattern emailPattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher emailMatcher = emailPattern.matcher(value);
        return emailMatcher.matches();
    }

    public static boolean checkName(String value) {
        Pattern namePattern = Pattern.compile("^[a-zA-Z0-9\u4e00-\u9fa5]+");
        Matcher nameMatcher = namePattern.matcher(value);
        return nameMatcher.matches();
    }

    public static boolean checkPassword(String value) {
        Pattern namePattern = Pattern.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,10}$");
        Matcher nameMatcher = namePattern.matcher(value);
        return nameMatcher.matches();
    }

    public static boolean checkEquals(String value1, String value2) {
        return !checkNull(value1) && !checkNull(value2)
                && value1.equals(value2);
    }

    public static boolean checkNull(String value) {
        return null == value;
    }

    public static boolean isValidUrl(String url) {
        Pattern urlPattern = Pattern.compile("^(((file|gopher|news|nntp|telnet|http|ftp|https|ftps|sftp)://)|" +
                "(www\\.))+(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3})" +
                ")(/[a-zA-Z0-9\\&%_\\./-~-]*)?$");
        Matcher urlMatcher = urlPattern.matcher(url);
        return urlMatcher.matches();
    }

    public static boolean isImage(String url) {
        Pattern urlPattern = Pattern.compile("(?i).+?\\.(jpg|gif|bmp|png|jpeg)");
        Matcher urlMatcher = urlPattern.matcher(url);
        return urlMatcher.matches();
    }

    public static boolean isVideo(String url) {
        Pattern urlPattern = Pattern.compile("(?i).+?\\.(mp4|3gp|avi|mkv)");
        Matcher urlMatcher = urlPattern.matcher(url);
        return urlMatcher.matches();
    }
}
