/*
 * Copyright 2017 Brazilian National Research and Educational Network - RNP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.rnp.sdnoverlay.types;

import javax.xml.namespace.QName;
import javax.xml.soap.*;

/**
 * This class handles the response message received
 * by the SOAPConnection.call.
 *
 *
 * @author Daniel Neto
 * @version %I%, %G%
 * @since 2017-10-23
 */
public class ResponseHandlerType {

    private String connectionId;
    private String faultCode;
    private String faultString;
    private Boolean itsOk;

    /**
     * Constructor of the class that parses the received
     * message and stores some information in internal
     * variables.
     *
     * @param response The SOAPMessage received
     * @throws SOAPException
     */
    public ResponseHandlerType(SOAPMessage response) throws SOAPException {

        SOAPPart soapPart = response.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();
        SOAPBody soapBody = soapEnvelope.getBody();

        Object bodyFirstChildObj = soapBody.getFirstChild();

        if (bodyFirstChildObj instanceof javax.xml.soap.SOAPElement) {

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
            if (bodyFirstChild.getElementName().getLocalName().equalsIgnoreCase("reserveFailed")) {

                if (bodyFirstChild.getChildElements(new QName("serviceException")).hasNext()) {
                    SOAPElement serviceException = (SOAPElement) bodyFirstChild.getChildElements(new QName("serviceException")).next();
                    SOAPElement errorId = (SOAPElement) serviceException.getChildElements(new QName("errorId")).next();
                    setFaultCode(errorId.getTextContent());
                    SOAPElement errorText = (SOAPElement) serviceException.getChildElements(new QName("text")).next();
                    setFaultString(errorText.getTextContent());
                }

                setItsOk(false);
            }
            if (bodyFirstChild.getElementName().getLocalName().equalsIgnoreCase("reserveResponse")) {

                setConnectionId(bodyFirstChild.getTextContent());
                setItsOk(true);
            }
            if (bodyFirstChild.getElementName().getLocalName().equalsIgnoreCase("acknowledgment")) {

                setItsOk(true);
            }

        } else {

            if (bodyFirstChildObj instanceof com.sun.xml.internal.messaging.saaj.soap.impl.TextImpl) {

                setFaultString(soapBody.getTextContent());
                setItsOk(false);

            } else {

                setFaultString("Unknown Message Format!");
                setItsOk(false);
            }
        }
    }

    /**
     * Get the connectionId variable value.
     * This variable stores the connection identification
     * received in a success message of a reserve request.
     *
     * @return connectionId as String.
     */
    public String getConnectionId() {
        return connectionId;
    }

    /**
     * Set the connectionId variable value.
     *
     * @param connectionId
     */
    private void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * Get the faultCode variable value.
     * This variable stores the error code of a error
     * message.
     *
     * @return faultCode as String.
     */
    public String getFaultCode() {
        return faultCode;
    }

    /**
     * Set the faultCode variable value.
     *
     * @param faultCode
     */
    private void setFaultCode(String faultCode) {
        this.faultCode = faultCode;
    }

    /**
     * Get the faultString variable value.
     * This variable stores the text of a error message.
     *
     * @return faultString as String.
     */
    public String getFaultString() {
        return faultString;
    }

    /**
     * Set the faultString variable value.
     *
     * @param faultString
     */
    private void setFaultString(String faultString) {
        this.faultString = faultString;
    }

    /**
     * Get the itsOk variable value.
     * This variable indicates if the message received
     * is an error message (false) or a success message
     * (true).
     *
     * @return itsOk as Boolean.
     */
    public Boolean itsOk () {
        return itsOk;
    }

    /**
     * Set the itsOk variable value.
     *
     * @param itsOk
     */
    private void setItsOk(Boolean itsOk) {
        this.itsOk = itsOk;
    }
}
