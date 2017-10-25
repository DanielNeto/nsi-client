package br.rnp.sdnoverlay.types;


/**
 * PointToPointType is a type to store the
 * parameters of a point to point circuit
 * reserve request. It includes the
 * capacity of the link (in mbps), the
 * directionality (uni or bi), the source
 * and destination endpoints, the
 * algorithm used to compute the path, and
 * two Boolean variables to indicate if the
 * path is symmetrical (for bidirectional
 * circuits) and if the path is protected.
 *
 *
 * @author Daniel Neto
 * @version %I%, %G%
 * @since 2017-10-23
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
     * Constructor of the class that stores some
     * default values in the internal variables.
     */
    public PointToPointType() {
        setCapacity(0); //minimum value
        setProtection(true);
        setSymmetricPath(true);
        setPathComputationAlgorithm("Default");
        setDirectionality("Bidirectional");
    }

    /**
     * Set the capacity variable value.
     * Only Integer values greater or equal than
     * 0 are allowed (0 means no restriction on
     * bandwidth).
     *
     * @param capacity
     */
    public void setCapacity(Integer capacity) {

        if (capacity >= 0) {
            this.capacity = capacity;
        }
    }

    /**
     * Get the capacity variable value.
     *
     * @return capacity as Integer.
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Set the directionality variable value.
     * Values allowed: Unidirectional and Bidirectional.
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
     * Get the directionality variable value.
     *
     * @return directionality as String.
     */
    public String getDirectionality() {
        return directionality;
    }

    /**
     * Set the symmetricPath variable value.
     * True indicates the path is symmetric and
     * false otherwise.
     *
     * @param symmetricPath
     */
    public void setSymmetricPath(Boolean symmetricPath) {
        this.symmetricPath = symmetricPath;
    }

    /**
     * Get the symmetricPath variable value.
     *
     * @return symmetricPath as Boolean.
     */
    public Boolean getSymmetricPath() {
        return symmetricPath;
    }

    /**
     * Set the sourceSTP variable value.
     *
     * @param sourceSTP
     */
    public void setSourceSTP(String sourceSTP) {
        this.sourceSTP = sourceSTP;
    }

    /**
     * Get the sourceSTP variable value.
     *
     * @return sourceSTP as String.
     */
    public String getSourceSTP() {
        return sourceSTP;
    }

    /**
     * Set the destSTP variable value.
     *
     * @param destSTP
     */
    public void setDestSTP(String destSTP) {
        this.destSTP = destSTP;
    }

    /**
     * Get the destSTP variable value.
     *
     * @return destSTP as String.
     */
    public String getDestSTP() {
        return destSTP;
    }

    /**
     * Set the protection variable value.
     * True means the path is protected and
     * false otherwise.
     *
     * @param protection
     */
    public void setProtection(Boolean protection) {
        this.protection = protection;
    }

    /**
     * Get the protection variable value.
     *
     * @return protection as Boolean.
     */
    public Boolean getProtection() {
        return protection;
    }

    /**
     * Get the pathComputationAlgorithm variable
     * value.
     *
     * @return pathComputationAlgorithm as String.
     */
    public String getPathComputationAlgorithm() {
        return pathComputationAlgorithm;
    }

    /**
     * Set the pathComputationAlgorithm variable
     * value.
     * The values allowed are: default, chain,
     * sequential and tree.
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
