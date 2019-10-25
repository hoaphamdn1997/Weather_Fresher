package com.program.weather.common.utils;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;


/**
 * The type Common util.
 */
public class CommonUtil {

    /**
     * Convert F --> C
     *
     * @param F the f
     * @return formatter string
     */
    public static String toCelsius(double F) {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format((F - 32) * 5 / 9);
    }

    /**
     * Format TimeStamp to String yyyymmdd
     *
     * @param ts //TIMESTAMP
     * @return formattedDate //yyyyMMdd
     */
    public static String formatToString(Timestamp ts) {
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        return formattedDate;
    }

    /**
     * Parse curTime to String
     *
     * @return formattedDate //yyyyMMdd
     */
    public static String curTimeToString() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String formattedDate = new SimpleDateFormat("yyyyMMdd").format(ts);
        return formattedDate;
    }


}
