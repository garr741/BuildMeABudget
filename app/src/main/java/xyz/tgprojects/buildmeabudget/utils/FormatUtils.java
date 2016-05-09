package xyz.tgprojects.buildmeabudget.utils;

import android.util.Log;
import android.util.StringBuilderPrinter;
import java.text.NumberFormat;
import java.util.Locale;


public class FormatUtils {
    public static String dollarFormatter(long input){
        return "$" + NumberFormat.getNumberInstance(Locale.US).format(input);
    }

    public static String percentFormatter(long input){
        return Long.toString(input) + "%";
    }

    public static String textAppend(String current, String input){
        current = new StringBuilder().append(current).append(input).toString();
        return current;
    }

    public static String stripClean(String input){
        input = input.replace("$", "");
        input = input.replace(",", "");
        return input;
    }

    public static String backspace(String input){
        return input.substring(0, input.length()-1);
    }
}
