package br.com.solutis.desafio.helper;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberHelper {

    public static int major(int value1, int value2) {
        return Integer.compare(value1, value2) == -1 ? value2 : value1;
    }

    public static int toInt(String value) {
        return NumberUtils.toInt(value);
    }

    public static int toInt(String value, int defaultValue) {
        return NumberUtils.toInt(value, defaultValue);
    }

    public static Long toLong(String value, long defaultValue) {
        return NumberUtils.toLong(value, defaultValue);
    }

    public static Long toLong(String value) {
        return NumberUtils.toLong(value);
    }
}
