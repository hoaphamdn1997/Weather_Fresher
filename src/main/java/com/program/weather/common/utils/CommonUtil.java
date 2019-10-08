package com.program.weather.common.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;


public class CommonUtil {

    /**
     * Convert F --> C
     *
     * @param F
     * @return formatter
     */
    public static String toCelsius(double F) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format((F - 32) * 5 / 9);
    }

    /**
     * Format Key Search ex: đà nẵng=danang
     *
     * @param seach
     * @return name
     */
    public static String removeAccent(String seach) {
        String name = Normalizer.normalize(seach, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(name).replaceAll("").replace('đ', 'd').replace('Đ', 'D').replaceAll("\\s+", "");
    }

    /**
     * Format TimeStamp to String yyyymmdd
     *
     * @param ts
     * @return
     */
    public static String formatToString(Timestamp ts) {
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        return formattedDate;
    }

    /**
     * Parse curTime to String
     *
     * @return
     */
    public static String curTimeToString() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        return formattedDate;
    }




}
