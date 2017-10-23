package br.rnp.sdnoverlay.types;


/**
 *
 */
class PointToPointType {
    private Integer capacity;
    private String directionality;
    private Boolean symmetricPath;
    private String sourceSTP;
    private String destSTP;
    private Boolean protection;
    private String pathComputationAlgorithm;

    /**
     *
     */
    protected PointToPointType() {
        setProtection(true);
        setPathComputationAlgorithm("Default");
        setDirectionality("Bidirectional");
    }

    /**
     *
     * @param capacity
     */
    protected void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     *
     * @return
     */
    protected Integer getCapacity() {
        return capacity;
    }

    /**
     *
     * @param directionality
     */
    protected void setDirectionality(String directionality) {

        if (directionality.equalsIgnoreCase("Bidirectional")) {
            this.directionality = "Bidirectional";
        }
        if (directionality.equalsIgnoreCase("Unidirectional")) {
            this.directionality = "Unidirectional";
        }
    }

    /**
     *
     * @return
     */
    protected String getDirectionality() {
        return directionality;
    }

    /**
     *
     * @param symmetricPath
     */
    protected void setSymmetricPath(Boolean symmetricPath) {
        this.symmetricPath = symmetricPath;
    }

    /**
     *
     * @return
     */
    protected Boolean getSymmetricPath() {
        return symmetricPath;
    }

    /**
     *
     * @param sourceSTP
     */
    protected void setSourceSTP(String sourceSTP) {
        this.sourceSTP = sourceSTP;
    }

    /**
     *
     * @return
     */
    protected String getSourceSTP() {
        return sourceSTP;
    }

    /**
     *
     * @param destSTP
     */
    protected void setDestSTP(String destSTP) {
        this.destSTP = destSTP;
    }

    /**
     *
     * @return
     */
    protected String getDestSTP() {
        return destSTP;
    }

    /**
     *
     * @param protection
     */
    protected void setProtection(Boolean protection) {
        this.protection = protection;
    }

    /**
     *
     * @return
     */
    protected Boolean getProtection() {
        return protection;
    }

    /**
     *
     * @return
     */
    protected String getPathComputationAlgorithm() {
        return pathComputationAlgorithm;
    }

    /**
     *
     * @param pathComputationAlgorithm
     */
    protected void setPathComputationAlgorithm(String pathComputationAlgorithm) {
        if (pathComputationAlgorithm.equalsIgnoreCase("default")) {
            this.pathComputationAlgorithm = pathComputationAlgorithm.toUpperCase();
        }
        if (pathComputationAlgorithm.equalsIgnoreCase("chain")) {
            this.pathComputationAlgorithm = pathComputationAlgorithm.toUpperCase();
        }
        if (pathComputationAlgorithm.equalsIgnoreCase("sequential")) {
            this.pathComputationAlgorithm = pathComputationAlgorithm.toUpperCase();
        }
        if (pathComputationAlgorithm.equalsIgnoreCase("tree")) {
            this.pathComputationAlgorithm = pathComputationAlgorithm.toUpperCase();
        }
    }
}
