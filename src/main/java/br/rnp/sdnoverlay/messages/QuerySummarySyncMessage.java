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

import javax.xml.soap.*;

/**
 *
 */
final public class QuerySummarySyncMessage {

    /**
     *
     */
    final private static String SOAP_ACTION = "http://schemas.ogf.org/nsi/2013/12/connection/service/querySummarySync";

    /**
     *
     * @param connectionId
     * @param providerNSA
     * @param requesterNSA
     * @param replyTo
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage createMessage (String connectionId, String providerNSA, String requesterNSA, String replyTo) throws SOAPException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        soapMessage = MessageUtil.configureMimeHeader(soapMessage, SOAP_ACTION);
        soapMessage = MessageUtil.configureNameSpaces(soapMessage);
        soapMessage = MessageUtil.createHeader(soapMessage, requesterNSA, providerNSA, replyTo);

        soapMessage = createBody(soapMessage, connectionId);

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     *
     * @param soapMessage
     * @param connectionId
     * @return
     * @throws SOAPException
     */
    private static SOAPMessage createBody (SOAPMessage soapMessage, String connectionId) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        SOAPBody soapBody = soapEnvelope.getBody();

        soapBody.setPrefix("soapenv");
        SOAPElement reserveCommit = soapBody.addChildElement("querySummarySync", "type");

        SOAPElement connId = reserveCommit.addChildElement(soapEnvelope.createName("connectionId"));
        connId.addTextNode(connectionId);

        soapMessage.saveChanges();

        return soapMessage;
    }
}