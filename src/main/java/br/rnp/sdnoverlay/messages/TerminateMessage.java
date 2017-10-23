package br.rnp.sdnoverlay.messages;

import javax.xml.soap.*;

/**
 *
 */
final public class TerminateMessage {

    /**
     *
     */
    final private static String SOAP_ACTION = "http://schemas.ogf.org/nsi/2013/12/connection/service/terminate";

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
        SOAPElement reserveCommit = soapBody.addChildElement("terminate", "type");

        SOAPElement connId = reserveCommit.addChildElement(soapEnvelope.createName("connectionId"));
        connId.addTextNode(connectionId);

        soapMessage.saveChanges();

        return soapMessage;
    }
}