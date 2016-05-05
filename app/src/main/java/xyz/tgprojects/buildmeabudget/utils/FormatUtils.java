package xyz.tgprojects.buildmeabudget.utils;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by tylor.garrett on 5/3/16.
 */
public class FormatUtils {
    public static String dollarFormatter(long input){
        return "$" + NumberFormat.getNumberInstance(Locale.US).format(input);
    }

    public static String percentFormatter(long input){
        return Long.toString(input) + "%";
    }
}
