package br.rnp.sdnoverlay.types;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 */
public class ScheduleType {

    /**
     *
     */
    final private static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private XMLGregorianCalendar startTime;
    private XMLGregorianCalendar endTime;

    /**
     *
     * @throws DatatypeConfigurationException
     */
    public ScheduleType() throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date()); //return now
        setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
    }

    /**
     *
     * @return
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     *
     * @param startTime
     */
    public void setStartTime(XMLGregorianCalendar startTime) {
        this.startTime = startTime;
    }

    /**
     *
     * @return
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     *
     * @param endTime
     */
    public void setEndTime(XMLGregorianCalendar endTime) {
        this.endTime = endTime;
    }

    /**
     *
     * @return
     */
    public String getStartTimeString() {

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
    public String getEndTimeString() {

        Calendar calendar = endTime.toGregorianCalendar();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }
}
