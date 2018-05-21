package kz.crtr.app.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.logging.Level;
import java.util.logging.Logger;

import static kz.crtr.app.utils.Util.getCalendar;
import static kz.crtr.app.utils.Util.isNullOrEmpty;

/**
 * @author a.kussein
 */
public class DateUtils {

    public static String dateToString(Date date, String f) {
        if (date != null) {
            return new SimpleDateFormat(f, Locale.ENGLISH).format(date);
        }
        return "";
    }

    /*
     * @desc добавляет день в дату
     */
    public static Date getCalendarDateAddDay(String dt, int day) {
        if (!isNullOrEmpty(dt)) {
            Calendar date = getCalendar(stringToDate(dt));
            date.add(Calendar.DATE, +day);
            return date.getTime();
        }
        return null;
    }

    public static String dateToString(Date date) {
        return dateToString(date, "dd.MM.yyyy");
    }

    public static String dateTimeToString(Date date, String f) {
        if (date != null) {
            return new SimpleDateFormat(f, Locale.ENGLISH).format(date);
        }
        return null;
    }

    public static String dateTimeToString(Date date) {
        if (date != null) {
            return new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ENGLISH).format(date);
        }
        return null;
    }

    public static java.sql.Date stringToSqlDate(String date) {
        if (!isNullOrEmpty(date)) {
            if (date.length() < 10) {
                date = getDateToBeginOrEnd(date);
            }

            Date date1 = stringToDate(date);
            if (date1 != null) {
                return new java.sql.Date(date1.getTime());
            }
        }
        return null;
    }

    public static java.sql.Date stringToSqlDate(String date, String format) {
        if (date != null && !date.isEmpty()) {
            return new java.sql.Date(stringToDateTime(date, format).getTime());
        }
        return null;
    }

    public static Date stringToDateTime(String date, String format) {
        if (!isNullOrEmpty(date)) {
            try {
                return new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @param str;
     * @return date format or null
     * @author a.kussein
     * mmyyyy to dd.mm.yyyy
     * @since 03-02-2017
     */
    public static String getDateToBeginOrEnd(String str) {
        if (!isNullOrEmpty(str)) {
            return str.length() > 6 ? "01." + str : null;
        }
        return null;
    }

    /**
     * @desc Формирования даты с точкой
     * @autor beljerin;
     */
    public static Date generateCorrectDateWithDote(String sDate) {
        StringJoiner stringJoiner = new StringJoiner(".");
        stringJoiner.add(sDate.substring(0, 2))
                .add(sDate.substring(2, 4))
                .add(sDate.substring(4));

        return stringToDate(stringJoiner.toString());
    }

    public static Timestamp stringToTimestamp(String dateTime) {
        if (!isNullOrEmpty(dateTime)) {
            try {
                return new Timestamp(stringToDate(dateTime, "dd.MM.yyyy HH:mm").getTime());
            } catch (NullPointerException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static Date stringToDate(String date, String format) {
        try {
            return new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date stringToDate(String date) {
        if (date != null && !date.isEmpty()) {
            try {
                date = date.substring(0, 10);

                if (date.contains(".")) {
                    return new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(date);
                } else if (date.contains("-")) {
                    return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date);
                }
                return new SimpleDateFormat("ddMMyyyy", Locale.ENGLISH).parse(date);
            } catch (ParseException ex) {

            }
        }
        return null;
    }

    public static String parserToDate(String begindate) throws ParseException {
        String toDate = "";
        if (begindate != null) {
            try {
                begindate = begindate.replace("-", ".");
                DateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.ENGLISH);
                Date date = format.parse(begindate);
                DateFormat formats = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                toDate = formats.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return toDate;
        }
        return null;
    }

    public static Date addDays(Date date, int days) {
        if (date != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days);
            return cal.getTime();
        }
        return null;
    }

    public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }

    public static Date truncate(Date dte) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
            Date clearDate;
            clearDate = formatter.parse(formatter.format(dte));
            return clearDate;
        } catch (ParseException ex) {
            Logger.getLogger(DateUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dte;
    }

    public static int getYear(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);        
        return calendar.get(Calendar.YEAR);
    }
    
    private static String getMonthName(Integer monthNum) {
        switch (monthNum) {
            case 0:
                return "янв.";
            case 1:
                return "фев.";
            case 2:
                return "мар.";
            case 3:
                return "апр.";
            case 4:
                return "май";
            case 5:
                return "июн.";
            case 6:
                return "июл.";
            case 7:
                return "авг.";
            case 8:
                return "сен.";
            case 9:
                return "окт.";
            case 10:
                return "ноя.";
            case 11:
                return "дек.";
        }
        return "";
    }

    public static boolean betweenDates(Date sysdate, Date begDate, Date endDate) {
        return begDate.compareTo(sysdate) * sysdate.compareTo(endDate) > 0;
    }

    /**
     *
     * @param begDate
     * @param endDate
     * @return Long daysCount = endDate - begDate
     */
    public static Integer getDaysBetweenDates(Date begDate, Date endDate) {
        if (begDate != null && endDate != null) {
            Calendar currentCalendar = Calendar.getInstance();
            currentCalendar.setTime(stringToDate(dateToString(begDate, "yyyy-MM-dd"), "yyyy-MM-dd"));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(stringToDate(dateToString(endDate, "yyyy-MM-dd"), "yyyy-MM-dd"));

            long diff = calendar.getTimeInMillis() - currentCalendar.getTimeInMillis();

            return (int) (diff / 1000 / 60 / 60 / 24);
        }

        return null;
    }

}
