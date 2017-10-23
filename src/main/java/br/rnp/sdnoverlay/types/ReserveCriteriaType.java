package br.rnp.sdnoverlay.types;

/**
 *
 */
class ReserveCriteriaType {

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
    protected ReserveCriteriaType() {
        setServiceType(SERVICE_DEFAULT);
    }

    /**
     *
     * @return
     */
    protected Integer getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    protected void setVersion(Integer version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    protected ScheduleType getSchedule() {
        return schedule;
    }

    /**
     *
     * @param schedule
     */
    protected void setSchedule(ScheduleType schedule) {
        this.schedule = schedule;
    }

    /**
     *
     * @return
     */
    protected String getServiceType() {
        return serviceType;
    }

    /**
     *
     * @param serviceType
     */
    protected void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     *
     * @return
     */
    protected PointToPointType getPointToPoint() {
        return pointToPoint;
    }

    /**
     *
     * @param pointToPoint
     */
    protected void setPointToPoint(PointToPointType pointToPoint) {
        this.pointToPoint = pointToPoint;
    }
}
