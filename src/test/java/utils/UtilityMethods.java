package utils;

import globals.Product;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Random;

public class UtilityMethods {

    public static int getRandomIndex(int maxSize) {
        return new Random().nextInt(maxSize) + 1;
    }

    public static String numberFormating(float number) {
        NumberFormat formatter = NumberFormat.getNumberInstance(Locale.GERMANY);
        DecimalFormat decimalFormat = (DecimalFormat) formatter;
        decimalFormat.applyPattern("#,###.00");
        return decimalFormat.format(number);
    }

    public static void resetAllGlobal() {
        Product.getInstance().reset();
    }
}