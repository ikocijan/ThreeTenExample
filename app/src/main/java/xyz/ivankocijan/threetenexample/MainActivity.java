package xyz.ivankocijan.threetenexample;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.Month;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "koc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        instant();

        localDate();

        localTime();

        localDateTime();

        zonedDateTime();

        offsetDateTime();

        parseDate();

        createADate();

        compareDate();

        localizedDate();

        between();


    }

    private void between() {
        LocalDate localDate1 = LocalDate.now();
        LocalDate localDate2 = localDate1.plusDays(10);

        long daysBetween = ChronoUnit.DAYS.between(localDate1, localDate2);

        //Days between localDate1 and localDate2: 10
        Log.d(TAG, "Days between localDate1 and localDate2: " + daysBetween);
    }

    private void localizedDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        String formattedDateWithSlash = zonedDateTime.format(DateTimeFormatter.ofPattern("dd/MM/YYYY"));
        String formattedDateWithDash = zonedDateTime.format(DateTimeFormatter.ofPattern("dd-MM-YYYY"));

        //24/01/2016
        Log.d(TAG, formattedDateWithSlash);

        //24-01-2016
        Log.d(TAG, formattedDateWithDash);


        LocalDate localDate = LocalDate.of(2016, Month.MAY, 31);
        String localFormattedDate = localDate.format(DateTimeFormatter.ofPattern("dd MMM", Locale.getDefault()));

        //31 May
        Log.d(TAG, localFormattedDate);


        String localFormattedDateTaiwan = localDate.format(DateTimeFormatter.ofPattern("dd MMM", Locale.PRC));

        //31 5月
        Log.d(TAG, localFormattedDateTaiwan);

        String localFormattedDateDutch = localDate.format(DateTimeFormatter.ofPattern("dd MMM", new Locale("nl", "NL")));

        //31 mei
        Log.d(TAG, localFormattedDateDutch);
    }

    private void compareDate() {
        String date1 = "2016-01-23T20:00:00+01:00";
        String date2 = "2016-01-23T20:00:00+02:00";

        ZonedDateTime firstDate = ZonedDateTime.parse(date1);
        ZonedDateTime secondDate = ZonedDateTime.parse(date2);

        //False
        Log.d(TAG, "is first date after second: " + firstDate.isAfter(secondDate));

        //True
        Log.d(TAG, "is first date before second: " + firstDate.isBefore(secondDate));

        //False
        Log.d(TAG, "is first date equal to second: " + firstDate.isEqual(secondDate));

        String localDate1 = "2016-01-23";
        String locatDate2 = "2016-01-23";

        LocalDate firstLocalDate = LocalDate.parse(localDate1);
        LocalDate secondLocalDate = LocalDate.parse(locatDate2);

        //true
        Log.d(TAG, "is first date after second: " + firstLocalDate.isAfter(secondLocalDate));

        //false
        Log.d(TAG, "is first date before second: " + firstLocalDate.isBefore(secondLocalDate));

        //true
        Log.d(TAG, "is first date equal to second: " + firstLocalDate.isEqual(secondLocalDate));
    }

    private void createADate() {
        //year, month, dayOfMonth, hour, minute, second, nanoOfSecond, ZoneId zone
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2016, Month.APRIL.getValue(), 24, 12, 0, 0, 0, ZoneId.systemDefault());

        //2016-04-24T12:00+02:00[Europe/Zagreb]
        Log.d(TAG, zonedDateTime.toString());

        //prefix, offset
        ZoneId zoneId = ZoneId.ofOffset("GMT", ZoneOffset.ofHours(10));
        ZonedDateTime zonedDateTimeWithOffset = ZonedDateTime.of(2016, Month.APRIL.getValue(), 24, 12, 0, 0, 0, zoneId);

        //2016-04-24T12:00+10:00[GMT+10:00]
        Log.d(TAG, zonedDateTimeWithOffset.toString());

        ZonedDateTime zonedDateTimeWithYear = zonedDateTime.withYear(2017);

        //2017-04-24T12:00+02:00[Europe/Zagreb]
        Log.d(TAG, zonedDateTimeWithYear.toString());
    }

    private void parseDate() {
        String iso8061Date = "2016-01-23T20:00:00+01:00";
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(iso8061Date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        //2016-01-23T20:00+01:00
        Log.d(TAG, zonedDateTime.toString());

        LocalDate localDate = LocalDate.parse(iso8061Date, DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        //2016-01-23
        Log.d(TAG, localDate.toString());

        LocalTime localTime = LocalTime.parse(iso8061Date, DateTimeFormatter.ISO_TIME.ISO_OFFSET_DATE_TIME);

        //20:00
        Log.d(TAG, localTime.toString());
    }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/Instant.html</a>
     */
    private void instant() {
        Log.d(TAG, "Instant example");

        //Uses UTC clock to obtain the current instant
        Instant instant = Instant.now();

        //2016-01-23T20:24:38.294Z
        Log.d(TAG, instant.toString());

        //In milliseconds: 1453580678294
        Log.d(TAG, "In milliseconds: " + instant.toEpochMilli());

        instant.plusMillis(1000);
        instant.minusMillis(1000);

    }


    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html</a>
     */
    private void localDate() {

        Log.d(TAG, "LocalDate example");

        LocalDate localDate = LocalDate.now();

        //2016-01-23
        Log.d(TAG, localDate.toString());

        LocalDate formattedISODate = LocalDate.parse("2016-01-25T20:24:38.294+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME);

        //2016-01-25
        Log.d(TAG, formattedISODate.toString());

        LocalDate formattedBasicIsoDate = LocalDate.parse("2016-01-30",
                DateTimeFormatter.ISO_LOCAL_DATE);

        //2016-01-30
        Log.d(TAG, formattedBasicIsoDate.toString());

    }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalTime.html</a>
     */
    private void localTime() {

        Log.d(TAG, "LocalTime example");

        LocalTime localTime = LocalTime.now();

        //22:39:48.030
        Log.d(TAG, localTime.toString());

        LocalTime formattedIsoLocalTime = LocalTime.parse("22:00", DateTimeFormatter.ISO_TIME);

        //22:00
        Log.d(TAG, formattedIsoLocalTime.toString());
    }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/LocalDateTime.html</a>
     */
    private void localDateTime() {

        Log.d(TAG, "LocalDateTime example");

        LocalDateTime localDateTime = LocalDateTime.now();

        //2016-01-23T22:45:43.007
        Log.d(TAG, localDateTime.toString());
    }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/ZonedDateTime.html</a>
     */
    private void zonedDateTime() {

        Log.d(TAG, "ZonedDateTime example");

        ZonedDateTime zonedDateTime = ZonedDateTime.now();

        //2016-01-23T22:51:55.301+01:00[Europe/Zagreb]
        Log.d(TAG, zonedDateTime.toString());
    }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/time/OffsetDateTime.html</a>
     */
    private void offsetDateTime() {

        Log.d(TAG, "OffsetDateTime example");

        LocalDateTime localDate = LocalDateTime.now();

        //2016-01-23T23:11:25.184
        Log.d(TAG, localDate.toString());

        ZoneOffset zoneOffset = ZoneOffset.of("+12:00");
        OffsetDateTime dateWithOffset = OffsetDateTime.of(localDate, zoneOffset);

        //2016-01-23T23:11:25.184+12:00
        Log.d(TAG, dateWithOffset.toString());
    }


}
