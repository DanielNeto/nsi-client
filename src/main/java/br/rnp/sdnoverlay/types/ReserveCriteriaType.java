package br.rnp.sdnoverlay.types;

/**
 *
 */
public class ReserveCriteriaType {

    /**
     *
     */
    final private static String SERVICE_DEFAULT = "http://services.ogf.org/nsi/2013/12/descriptions/EVTS.A-GOLE";

    private Integer version;
    private ScheduleType schedule;
    private String serviceType;
    private PointToPointType pointToPoint;

    /**
     *
     */
    public ReserveCriteriaType() {
        setServiceType(SERVICE_DEFAULT);
    }

    /**
     *
     * @return
     */
    public Integer getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    public ScheduleType getSchedule() {
        return schedule;
    }

    /**
     *
     * @param schedule
     */
    public void setSchedule(ScheduleType schedule) {
        this.schedule = schedule;
    }

    /**
     *
     * @return
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     *
     * @param serviceType
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     *
     * @return
     */
    public PointToPointType getPointToPoint() {
        return pointToPoint;
    }

    /**
     *
     * @param pointToPoint
     */
    public void setPointToPoint(PointToPointType pointToPoint) {
        this.pointToPoint = pointToPoint;
    }
}
