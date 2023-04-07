package main.java.com.kiki.utils;

public class ArrayUtils {
    public static boolean isValidIntArray(String[] arr) {
        for (String s : arr) {
            try {
                Integer.parseInt(s);
            } catch (NumberFormatException e) {
                return false;
            }
        }
        return true;
    }

    public static String[] removeLastElement(String[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        String[] newArray = new String[arr.length - 1];
        System.arraycopy(arr, 0, newArray, 0, arr.length - 1);
        return newArray;
    }
}
