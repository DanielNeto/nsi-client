package br.rnp.sdnoverlay.types;

/**
 * ReserveCriteriaType is a type to store the
 * criteria of a reserve request. It includes
 * the version of the request, a URI to the
 * service type description, the schedule
 * with start and end time, and the PointToPoint
 * information with specific parameters, like
 * source and destination endpoints.
 *
 * @author Daniel Neto
 * @version %I%, %G%
 * @since 2017-10-23
 */
public class ReserveCriteriaType {

    /**
     * Service type description default URI.
     */
    final private static String SERVICE_DEFAULT = "http://services.ogf.org/nsi/2013/12/descriptions/EVTS.A-GOLE";

    private Integer version;
    private ScheduleType schedule;
    private String serviceType;
    private PointToPointType pointToPoint;

    /**
     * Constructor of the class that stores default
     * values for service type and version.
     */
    public ReserveCriteriaType() {
        setServiceType(SERVICE_DEFAULT);
        setVersion(0); //minimum value
    }

    /**
     * Get the version variable value.
     * This variable represents the version of the
     * reserve profile requested.
     *
     * @return version as Integer.
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Set the version variable value.
     *
     * @param version
     */
    public void setVersion(Integer version) {

        if (version >= 0) {
            this.version = version;
        }
    }

    /**
     * Get the schedule variable value.
     * This is a composed type with the start
     * and end time of the reserve request.
     *
     * @return schedule as ScheduleType.
     */
    public ScheduleType getSchedule() {
        return schedule;
    }

    /**
     * Set the schedule variable value.
     *
     * @param schedule
     */
    public void setSchedule(ScheduleType schedule) {
        this.schedule = schedule;
    }

    /**
     * Get the serviceType variable value.
     * This variable stores the URI of the
     * description for the service.
     *
     * @return serviceType as String.
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Set the serviceType variable value.
     *
     * @param serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * Get the pointToPoint variable value.
     * This is a composed type with parameters
     * specific to the connection.
     *
     * @return pointToPoint as PointToPointType.
     */
    public PointToPointType getPointToPoint() {
        return pointToPoint;
    }

    /**
     * Set the pointToPoint variable value.
     *
     * @param pointToPoint
     */
    public void setPointToPoint(PointToPointType pointToPoint) {
        this.pointToPoint = pointToPoint;
    }
}
