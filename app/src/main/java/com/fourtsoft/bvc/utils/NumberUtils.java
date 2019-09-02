package com.fourtsoft.bvc.utils;

public class NumberUtils {
    public static String addLastNumber(String textNumberCurrent, String number){
        if (textNumberCurrent.length() < 20){
            return textNumberCurrent + number;
        }
        return textNumberCurrent;
    }

    public static String backSpase(String textNumberCurrent){
        if (textNumberCurrent != null){
            StringBuilder sb = new StringBuilder(textNumberCurrent);
            if (sb.length() > 0){
                sb.deleteCharAt(sb.length() - 1);
                return sb.toString();
            }
        }
        return "";
    }
}
