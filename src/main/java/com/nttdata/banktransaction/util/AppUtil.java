package com.nttdata.banktransaction.util;

import lombok.SneakyThrows;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class defines methods the global use.
 *
 * @author Alcibar Vasquez
 * @version 1.0
 */
public class AppUtil {

    /**
     * This method set format to date
     *
     * @param date date
     * @return date formatted
     */
    @SneakyThrows(ParseException.class)
    public static Date dateFormat(Date date) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String newDate = simpleDateFormat.format(date);
        return simpleDateFormat.parse(newDate);
    }

    public static Float decimalFormat(Float request) {
        return Float.valueOf(String.format(Locale.getDefault(),
                "%.2f", request).replace(",", "."));
    }

}
