package br.rnp.sdnoverlay.types;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

/**
 *
 */
public class ResponseHandlerType {

    private String connectionId;
    private String faultCode;
    private String faultString;
    private Boolean itsOk;

    /**
     *
     * @param response
     * @throws SOAPException
     */
    public ResponseHandlerType(SOAPMessage response) throws SOAPException {

        SOAPPart soapPart = response.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        SOAPBody soapBody = soapEnvelope.getBody();

        SOAPElement bodyFirstChild = (SOAPElement) soapBody.getFirstChild();
        if (bodyFirstChild.getElementName().getLocalName().equalsIgnoreCase("Fault")) {
            SOAPElement faultText = (SOAPElement) bodyFirstChild.getChildElements(new QName("faultstring")).next();
            setFaultString(faultText.getTextContent());

            if (bodyFirstChild.getChildElements(new QName("detail")).hasNext()) {
                SOAPElement details = (SOAPElement) bodyFirstChild.getChildElements(new QName("detail")).next();
                SOAPElement detailsChild = (SOAPElement) details.getFirstChild();
                SOAPElement errorId = (SOAPElement) detailsChild.getChildElements(new QName("errorId")).next();
                setFaultCode(errorId.getTextContent());
            }

            setItsOk(false);
        }
        if (bodyFirstChild.getElementName().getLocalName().equalsIgnoreCase("reserveResponse")) {
            setConnectionId(bodyFirstChild.getTextContent());
            setItsOk(true);
        }

    }

    /**
     *
     * @return
     */
    public String getConnectionId() {
        return connectionId;
    }

    /**
     *
     * @param connectionId
     */
    private void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    /**
     *
     * @return
     */
    public String getFaultCode() {
        return faultCode;
    }

    /**
     *
     * @param faultCode
     */
    private void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    /**
     *
     * @return
     */
    public String getFaultString() {
        return faultString;
    }

    /**
     *
     * @param faultString
     */
    private void setFaultString(String faultString) {
        this.faultString = faultString;
    }

    /**
     *
     * @return
     */
    public Boolean itsOk () {
        return itsOk;
    }

    /**
     *
     * @param itsOk
     */
    private void setItsOk(Boolean itsOk) {
        this.itsOk = itsOk;
    }
}
