package br.rnp.sdnoverlay.types;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * ScheduleType is a class to store the start and
 * end time of a reserve request.
 *
 * @author Daniel Neto
 * @version %I%, %G%
 * @since 2017-10-23
 */
public class ScheduleType {

    /**
     * Date format expected by the provider.
     */
    final private static String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    private XMLGregorianCalendar startTime;
    private XMLGregorianCalendar endTime;

    /**
     * Constructor of the class that stores the default
     * value in startTime using the current date.
     *
     * @throws DatatypeConfigurationException
     */
    public ScheduleType() throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date()); //return now
        setStartTime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar));
    }

    /**
     * Get the startTime variable value in
     * XMLGregorianCalendar type.
     *
     * @return startTime as XMLGregorianCalendar.
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Set the startTime variable value.
     *
     * @param startTime
     */
    public void setStartTime(XMLGregorianCalendar startTime) {
        this.startTime = startTime;
    }

    /**
     * Get the endTime variable value in
     * XMLGregorianCalendar type.
     *
     * @return endTime as XMLGregorianCalendar.
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Set the endTime variable value.
     *
     * @param endTime
     */
    public void setEndTime(XMLGregorianCalendar endTime) {
        this.endTime = endTime;
    }

    /**
     * Get the startTime variable value in
     * String type.
     *
     * @return startTime as String.
     */
    public String getStartTimeString() {

        Calendar calendar = startTime.toGregorianCalendar();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }

    /**
     * Get the endTime variable value in
     * String type.
     *
     * @return endTime as String.
     */
    public String getEndTimeString() {

        Calendar calendar = endTime.toGregorianCalendar();

        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        formatter.setTimeZone(calendar.getTimeZone());
        String dateString = formatter.format(calendar.getTime());

        return dateString;
    }
}
