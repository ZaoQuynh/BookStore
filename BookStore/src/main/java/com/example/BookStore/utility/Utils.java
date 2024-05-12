package com.example.BookStore.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    public static String formatCurrencyVietnamese(BigDecimal money){
        return NumberFormat
                .getCurrencyInstance(new Locale("vi", "VN"))
                .format(money);
    }

    public static boolean isValidVietnamesePhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("^0\\d{9}$");
    }

    public static boolean isNullOrEmpty(String string){
        return string == null || string.isEmpty() || string.isBlank();
    }

    public static int toInt(String str){
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.err.println("Error: Input string is not a valid integer.");
            e.printStackTrace();
            return 0;
        }
    }
}
