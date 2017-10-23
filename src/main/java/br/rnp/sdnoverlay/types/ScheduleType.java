package br.rnp.sdnoverlay.types;

import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 */
class ScheduleType {

    /**
     *
     */
    final private static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private XMLGregorianCalendar startTime;
    private XMLGregorianCalendar endTime;

    /**
     *
     * @return
     */
    protected XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     */
    protected void setStartTime(XMLGregorianCalendar startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     */
    protected XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime
     */
    protected void setEndTime(XMLGregorianCalendar endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return
     */
    protected String getStartTimeString() {

        Calendar calendar = startTime.toGregorianCalendar();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }

    /**
     *
     * @return
     */
    protected String getEndTimeString() {

        Calendar calendar = endTime.toGregorianCalendar();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }
}
