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
    private ConnectionStatesType connectionStatesType;
    private long sourceVlan;
    private long destVlan;

    /**
     * Constructor of the class that parses the received
     * message and stores some information in internal
     * variables.
     *
     * @param response The SOAPMessage received
     * @throws SOAPException
     */
    public ResponseHandlerType(SOAPMessage response) throws SOAPException {

        //Initializing intern variables
        connectionId = null;
        faultCode = null;
        faultString = null;
        itsOk = false;
        connectionStatesType = null;
        sourceVlan = 0;
        destVlan = 0;

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
            if (bodyFirstChild.getElementName().getLocalName().equalsIgnoreCase("querySummarySyncConfirmed")) {

                if (bodyFirstChild.getChildElements(new QName("reservation")).hasNext()) {

                    SOAPElement reservation = (SOAPElement) bodyFirstChild.getChildElements(new QName("reservation")).next();

                    if (reservation.getChildElements(new QName("criteria")).hasNext()) {

                        SOAPElement criteria = (SOAPElement) reservation.getChildElements(new QName("criteria")).next();

                        SOAPElement p2ps = (SOAPElement) criteria.getChildElements(new QName("http://schemas.ogf.org/nsi/2013/12/services/point2point", "p2ps", "nsi_p2p")).next();

                        SOAPElement sourceStp = (SOAPElement) p2ps.getChildElements(new QName("sourceSTP")).next();
                        SOAPElement destStp = (SOAPElement) p2ps.getChildElements(new QName("destSTP")).next();

                        String sourceUrn = sourceStp.getTextContent();
                        String destUrn = destStp.getTextContent();

                        if (sourceUrn.contains("vlan=")) {
                            String vlanS = sourceUrn.substring(sourceUrn.lastIndexOf("vlan=") + 5);
                            setSourceVlan(Long.parseLong(vlanS));
                        }
                        if (destUrn.contains("vlan=")) {
                            String vlanD = destUrn.substring(destUrn.lastIndexOf("vlan=") + 5);
                            setDestVlan(Long.parseLong(vlanD));
                        }
                    }

                    if (reservation.getChildElements(new QName("connectionStates")).hasNext()) {

                        SOAPElement connStates = (SOAPElement) reservation.getChildElements(new QName("connectionStates")).next();

                        SOAPElement reservState = (SOAPElement) connStates.getChildElements(new QName("reservationState")).next();
                        SOAPElement provState = (SOAPElement) connStates.getChildElements(new QName("provisionState")).next();
                        SOAPElement lifeCycleState = (SOAPElement) connStates.getChildElements(new QName("lifecycleState")).next();
                        SOAPElement dataPlaneStatus = (SOAPElement) connStates.getChildElements(new QName("dataPlaneStatus")).next();

                        ReservationStateEnumType reservationStateEnumType = ReservationStateEnumType.fromValue(reservState.getTextContent());
                        ProvisionStateEnumType provisionStateEnumType = ProvisionStateEnumType.fromValue(provState.getTextContent());
                        LifecycleStateEnumType lifecycleStateEnumType = LifecycleStateEnumType.fromValue(lifeCycleState.getTextContent());

                        SOAPElement dataPlaneActive = (SOAPElement) dataPlaneStatus.getChildElements(new QName("active")).next();
                        SOAPElement dataPlaneVersion = (SOAPElement) dataPlaneStatus.getChildElements(new QName("version")).next();
                        SOAPElement dataPlaneConsist = (SOAPElement) dataPlaneStatus.getChildElements(new QName("versionConsistent")).next();

                        DataPlaneStatusType dataPlaneStatusType = new DataPlaneStatusType();

                        dataPlaneStatusType.setActive(dataPlaneActive.getTextContent().equalsIgnoreCase("true"));
                        dataPlaneStatusType.setVersion(Integer.parseInt(dataPlaneVersion.getTextContent()));
                        dataPlaneStatusType.setVersionConsistent(dataPlaneConsist.getTextContent().equalsIgnoreCase("true"));

                        this.connectionStatesType = new ConnectionStatesType();
                        setConnectionStatesType(reservationStateEnumType, provisionStateEnumType, lifecycleStateEnumType, dataPlaneStatusType);
                    }

                    setItsOk(true);
                }
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

    private void setSourceVlan(long sourceVlan) {
        this.sourceVlan = sourceVlan;
    }

    public long getSourceVlan() {
        return sourceVlan;
    }

    private void setDestVlan(long destVlan) {
        this.destVlan = destVlan;
    }

    public long getDestVlan() {
        return destVlan;
    }

    private void setConnectionStatesType(ConnectionStatesType connectionStatesType) {
        this.connectionStatesType = connectionStatesType;
    }

    private void setConnectionStatesType(ReservationStateEnumType reserv, ProvisionStateEnumType prov, LifecycleStateEnumType life, DataPlaneStatusType data) {
        this.connectionStatesType.setReservationState(reserv);
        this.connectionStatesType.setProvisionState(prov);
        this.connectionStatesType.setLifeCycleState(life);
        this.connectionStatesType.setDataPlaneStatus(data);
    }

    public ConnectionStatesType getConnectionStatesType() {
        return connectionStatesType;
    }
}
