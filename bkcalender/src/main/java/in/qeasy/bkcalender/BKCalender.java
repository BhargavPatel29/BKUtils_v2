package in.qeasy.bkcalender;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BKCalender {
    public static final int DIFF_MILLIS = 0;
    public static final int DIFF_SECONDS = 1;
    public static final int DIFF_MINUTES = 2;
    public static final int DIFF_HOURS = 3;
    public static final int DIFF_DAYS = 4;

    public static String getCurrentDate(String dateFormat) {
        if (dateFormat == null || dateFormat.isEmpty())
            dateFormat = "dd-MM-yyyy";

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Instant instant = Instant.now();
            ZoneId zoneId = ZoneId.of("Asia/Kolkata");
            ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, zoneId);
            return zonedDateTime.format(DateTimeFormatter.ofPattern(dateFormat));
        } else {
            TimeZone timeZone = TimeZone.getTimeZone("GMT+05:30");
            TimeZone.setDefault(timeZone); //Not Working
            return new SimpleDateFormat(dateFormat, Locale.US).format(Calendar.getInstance(timeZone).getTime());
        }
    }

    public static String convertFormat(String inputFormat, String outputFormat, String string) {
        if (inputFormat == null || inputFormat.isEmpty())
            inputFormat = "dd-MM-yyyy";
        if (outputFormat == null || outputFormat.isEmpty())
            outputFormat = "dd-MM-yyyy";
        if (string == null || string.isEmpty())
            return string;

        SimpleDateFormat inputSdf = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat outputSdf = new SimpleDateFormat(outputFormat, Locale.getDefault());
        try {
            string = outputSdf.format(inputSdf.parse(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    //==============================================================================================
    //==============================================================================================

    public static DatePickerDialog datePickerDialog(Context context, String inputDate, String inputFormat, DatePickerDialog.OnDateSetListener listener) {
        try {
            String date = (inputDate != null && !inputDate.isEmpty())
                    ? BKCalender.convertFormat(inputFormat, "yyyyMMdd", inputDate)
                    : BKCalender.getCurrentDate("yyyyMMdd");
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(4, 6));
            int day = Integer.parseInt(date.substring(6, 8));
            return new DatePickerDialog(context, listener, year, month - 1, day);
        } catch (Exception e) {
            e.printStackTrace();
            return new DatePickerDialog(context, listener, 2021, 0, 1);
        }
    }

    public static TimePickerDialog timePickerDialog(Context context, String inputTime, String inputFormat, TimePickerDialog.OnTimeSetListener listener) {
        try {
            String time = (inputTime != null && !inputTime.isEmpty())
                    ? BKCalender.convertFormat(inputFormat, "HHmmss", inputTime)
                    : BKCalender.getCurrentDate("HHmmss");
            int hour = Integer.parseInt(time.substring(0, 2));
            int minute = Integer.parseInt(time.substring(2, 4));
            int second = Integer.parseInt(time.substring(4, 6));
            return new TimePickerDialog(context, listener, hour, minute, false);
        } catch (Exception e) {
            e.printStackTrace();
            return new TimePickerDialog(context, listener, 12, 0, false);
        }
    }

    //==============================================================================================
    //==============================================================================================

    public static long getTimeDifference(String dateFromat, String dateStart, String dateEnd, int outputType) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFromat, Locale.US);
        Date date1, date2;

        try {
            date1 = simpleDateFormat.parse(dateStart);
            date2 = simpleDateFormat.parse(dateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }

        if (date1 == null || date2 == null) return 0;

        //1 minute = 60 seconds
        //1 hour = 60 x 60 = 3600
        //1 day = 3600 x 24 = 86400

        //milliseconds
        long different = date2.getTime() - date1.getTime();

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long diffInMilli = different;
        long diffInSecond = diffInMilli / secondsInMilli;
        long diffInMinute = diffInMilli / minutesInMilli;
        long diffInHours = diffInMilli / hoursInMilli;
        long diffInDays = diffInMilli / daysInMilli;

        //==============================================

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        /*
        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
         */

        //==============================================

        switch (outputType) {
            case DIFF_MILLIS:
                return diffInMilli;
            case DIFF_SECONDS:
                return diffInSecond;
            case DIFF_MINUTES:
                return diffInMinute;
            case DIFF_HOURS:
                return diffInHours;
            case DIFF_DAYS:
                return diffInDays;
            default:
                return diffInMilli;
        }
    }
}
