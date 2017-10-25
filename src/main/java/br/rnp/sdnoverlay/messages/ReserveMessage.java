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

package br.rnp.sdnoverlay.messages;

import br.rnp.sdnoverlay.types.ReserveCriteriaType;

import javax.xml.soap.*;

/**
 *
 */
final public class ReserveMessage {

    /**
     *
     */
    final private static String SOAP_ACTION = "http://schemas.ogf.org/nsi/2013/12/connection/service/reserve";

    /**
     *
     * @param globalReservationId
     * @param description
     * @param criteria
     * @param providerNSA
     * @param requesterNSA
     * @param replyTo
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage createMessage (String globalReservationId, String description, ReserveCriteriaType criteria, String providerNSA, String requesterNSA, String replyTo) throws SOAPException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        soapMessage = MessageUtil.configureMimeHeader(soapMessage, SOAP_ACTION);
        soapMessage = MessageUtil.configureNameSpaces(soapMessage);
        soapMessage = MessageUtil.createHeader(soapMessage, requesterNSA, providerNSA, replyTo);

        soapMessage = createBody(soapMessage, globalReservationId, description, criteria);

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     *
     * @param soapMessage
     * @param globalReservationId
     * @param description
     * @param criteria
     * @return
     * @throws SOAPException
     */
    private static SOAPMessage createBody (SOAPMessage soapMessage, String globalReservationId, String description, ReserveCriteriaType criteria) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        SOAPBody soapBody = soapEnvelope.getBody();

        soapBody.setPrefix("soapenv");
        SOAPElement reserve = soapBody.addChildElement("reserve", "type");

        //Do nothing if its empty string
        SOAPElement globalId = reserve.addChildElement(soapEnvelope.createName("globalReservationId"));
        globalId.addTextNode(globalReservationId);

        if (!description.equals("")) {
            SOAPElement desc = reserve.addChildElement(soapEnvelope.createName("description"));
            desc.addTextNode(description);
        }

        if (criteria != null) {
            SOAPElement crite = reserve.addChildElement(soapEnvelope.createName("criteria"));
            crite.addAttribute(soapEnvelope.createName("version"), criteria.getVersion().toString());

            if (criteria.getSchedule() != null) {
                SOAPElement sched = crite.addChildElement(soapEnvelope.createName("schedule"));
                SOAPElement startTime = sched.addChildElement(soapEnvelope.createName("startTime"));
                startTime.addTextNode(criteria.getSchedule().getStartTimeString());
                SOAPElement endTime = sched.addChildElement(soapEnvelope.createName("endTime"));

                if (criteria.getSchedule().getEndTime() != null) {
                    endTime.addTextNode(criteria.getSchedule().getEndTimeString());
                } else {
                    endTime.addAttribute(soapEnvelope.createName("nil", "xsi", "http://www.w3.org/2001/XMLSchema-instance"), "true");
                }
            }

            SOAPElement servType = crite.addChildElement(soapEnvelope.createName("serviceType"));
            servType.addTextNode(criteria.getServiceType());

            if (criteria.getPointToPoint() != null) {
                SOAPElement p2p = crite.addChildElement("p2ps", "p2p", "http://schemas.ogf.org/nsi/2013/12/services/point2point");

                SOAPElement capacity = p2p.addChildElement(soapEnvelope.createName("capacity"));
                capacity.addTextNode(criteria.getPointToPoint().getCapacity().toString());

                SOAPElement direction = p2p.addChildElement(soapEnvelope.createName("directionality"));
                direction.addTextNode(criteria.getPointToPoint().getDirectionality());

                SOAPElement symmetric = p2p.addChildElement(soapEnvelope.createName("symmetricPath"));
                symmetric.addTextNode(criteria.getPointToPoint().getSymmetricPath().toString());

                SOAPElement source = p2p.addChildElement(soapEnvelope.createName("sourceSTP"));
                source.addTextNode(criteria.getPointToPoint().getSourceSTP());

                SOAPElement dest = p2p.addChildElement(soapEnvelope.createName("destSTP"));
                dest.addTextNode(criteria.getPointToPoint().getDestSTP());

                SOAPElement param1 = p2p.addChildElement(soapEnvelope.createName("parameter"));
                param1.addAttribute(soapEnvelope.createName("type"), "protection");

                if (criteria.getPointToPoint().getProtection()) {
                    param1.addTextNode("PROTECTED");
                } else {
                    param1.addTextNode("UNPROTECTED");
                }

                if (!criteria.getPointToPoint().getPathComputationAlgorithm().equals("DEFAULT")) {
                    SOAPElement param2 = p2p.addChildElement(soapEnvelope.createName("parameter"));
                    param2.addAttribute(soapEnvelope.createName("type"), "pathComputationAlgorithm");
                    param2.addTextNode(criteria.getPointToPoint().getPathComputationAlgorithm());
                }
            }
        }

        soapMessage.saveChanges();

        return soapMessage;
    }
}