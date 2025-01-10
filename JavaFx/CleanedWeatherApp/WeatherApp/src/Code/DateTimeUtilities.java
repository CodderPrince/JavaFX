package Code;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Contains functions to manipulate date and time values.
 */
public class DateTimeUtilities {

    /**
     * Extracts the date portion from a date and time string.
     * @param dateTime A string containing a date and time.
     * @return Returns the extracted date.
     */
    public static String extractDate(String dateTime) {
        String dateNTime [] = dateTime.split(" ", 2);
        return dateNTime[0];
    }

    /**
     * Extracts the time portion of a date and time string.
     * @param dateTime A string containing a date and time.
     * @return Returns the extracted time.
     */
    public static String extractTime(String dateTime) {
        String dateNTime [] = dateTime.split(" ", 2);
        return dateNTime[1];
    }

    /**
     * Converts a unix timestamp to a date in yyyy-MM-dd.
     * @param unixTime A unix timestamp.
     * @param timezone Contains a timezone.
     * @return A date in yyyy-MM-dd.
     */
    public static String formatDate(long unixTime, String timezone) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = Instant.ofEpochSecond(unixTime).atZone(ZoneId.of(timezone)).format(formatter);
        return formattedDate;
    }

    /**
     * Computes the current date as per local system in yyyy-mm-dd format.
     * @return Returns a date in yyyy-mm-dd.
     */
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String currentDate = LocalDateTime.now().format(formatter);
        return currentDate;
    }

    /**
     * Extracts the day portion from a date.
     * For example, from 2018-09-10, 10 is extracted.
     * @param date A string containing a date in yyyy-mm-dd format.
     * @return Returns the day portion of a date.
     */
    public static int getDay(String date) {
        String fullDate [] = date.split("-", 3);
        return Integer.parseInt(fullDate[2]);
    }

    /**
     * Computes the week day name from a date.
     * For example, from 2018-09-10, MONDAY is computed.
     * @param date A string containing a date in yyyy-mm-dd format.
     * @return Returns the week day name of the specified date.
     */
    public static String dayOfWeek(String date) {
        int day = getDay(date);
        int month = getMonth(date);
        int year = getYear(date);
        LocalDate localDate = LocalDate.of(year,month,day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return dayOfWeek.toString();
    }

    /**
     * Computes the three letter equivalent of a week day name.
     * For example, THURSDAY is converted to THU.
     * @param dayName The week day name.
     * @return Returns the three letter equivalent of the week day name.
     */
    public static String getDayOfWeekInitial(String dayName) {
        String initial = dayName.substring(0, 3);
        return initial;
    }

    /**
     * Formats the input time string to a hh:mm:ss format.
     * @param time A string containing a time.
     * @return Returns a time string in hh:mm:ss format.
     */
    public static String getFormattedTime(String time) {
        time = String.valueOf(LocalTime.parse(time, DateTimeFormatter.ofPattern("hh a")));
        String formattedTime = time + ":00";
        return formattedTime;
    }

    /**
     * Extracts the month portion from a date.
     * For example, from 2018-09-10, 09 is extracted.
     * @param date A string containing a date in yyyy-mm-dd format.
     * @return Returns the month portion of a date.
     */
    public static int getMonth(String date) {
        String fullDate [] = date.split("-", 3);
        return Integer.parseInt(fullDate[1]);
    }

    /**
     * Formats a time string to a 12 hour format.
     * For example 15:00:00 is converted to 3pm.
     * @param time A string containing a time.
     * @return Returns a time string in a 12 hour format.
     */
    public static String getTwelveHourFormat(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh a");
        LocalTime localTime = LocalTime.parse(time);
        String formattedTime = localTime.format(formatter);
        return formattedTime;
    }

    /**
     * Extracts the year portion from a date.
     * For example, from 2018-09-10, 2018 is extracted.
     * @param date A string containing a date in yyyy-mm-dd format.
     * @return Returns the year portion of a date.
     */
    public static int getYear(String date) {
        String fullDate [] = date.split("-", 3);
        return Integer.parseInt(fullDate[0]);
    }
}
