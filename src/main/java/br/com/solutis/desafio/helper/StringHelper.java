package br.com.solutis.desafio.helper;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class StringHelper {

    public static String substringBeforeLast(String str, String separator) {
        return StringUtils.substringBeforeLast(str, separator);
    }

    public static String unaccent(String value) {
        return StringUtils.stripAccents(value);
    }

    public static boolean isEmpty(String text) {
        return StringUtils.isEmpty(text);
    }

    public static boolean isNotEmpty(String text) {
        return StringUtils.isNotEmpty(text);
    }

    public static boolean isBlank(String text) {
        return StringUtils.isBlank(text);
    }

    public static boolean isNotBlank(String text) {
        return StringUtils.isNotBlank(text);
    }

    public static String rightPad(String str, int len) {
        return StringUtils.rightPad(str, len);
    }

    public static String leftPad(String str, int len) {
        return StringUtils.leftPad(str, len);
    }

    public static String randomString(int count, boolean letters, boolean numbers) {
        return RandomStringUtils.random(count, letters, numbers);
    }

    public static String left(String str, int len) {
        return StringUtils.left(str, len);
    }

    public static String right(String str, int len) {
        return StringUtils.right(str, len);
    }
}
