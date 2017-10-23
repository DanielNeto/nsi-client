package br.rnp.sdnoverlay.types;


/**
 *
 */
//TODO: add ERO parameter (nodes in the path)
public class PointToPointType {
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
    public PointToPointType() {
        setCapacity(0); //minimum value
        setProtection(true);
        setSymmetricPath(true);
        setPathComputationAlgorithm("Default");
        setDirectionality("Bidirectional");
    }

    /**
     *
     * @param capacity
     */
    public void setCapacity(Integer capacity) {

        if (capacity >= 0) {
            this.capacity = capacity;
        }
    }

    /**
     *
     * @return
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     *
     * @param directionality
     */
    public void setDirectionality(String directionality) {

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
    public String getDirectionality() {
        return directionality;
    }

    /**
     *
     * @param symmetricPath
     */
    public void setSymmetricPath(Boolean symmetricPath) {
        this.symmetricPath = symmetricPath;
    }

    /**
     *
     * @return
     */
    public Boolean getSymmetricPath() {
        return symmetricPath;
    }

    /**
     *
     * @param sourceSTP
     */
    public void setSourceSTP(String sourceSTP) {
        this.sourceSTP = sourceSTP;
    }

    /**
     *
     * @return
     */
    public String getSourceSTP() {
        return sourceSTP;
    }

    /**
     *
     * @param destSTP
     */
    public void setDestSTP(String destSTP) {
        this.destSTP = destSTP;
    }

    /**
     *
     * @return
     */
    public String getDestSTP() {
        return destSTP;
    }

    /**
     *
     * @param protection
     */
    public void setProtection(Boolean protection) {
        this.protection = protection;
    }

    /**
     *
     * @return
     */
    public Boolean getProtection() {
        return protection;
    }

    /**
     *
     * @return
     */
    public String getPathComputationAlgorithm() {
        return pathComputationAlgorithm;
    }

    /**
     *
     * @param pathComputationAlgorithm
     */
    public void setPathComputationAlgorithm(String pathComputationAlgorithm) {
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
