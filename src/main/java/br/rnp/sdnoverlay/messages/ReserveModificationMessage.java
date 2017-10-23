package br.rnp.sdnoverlay.messages;

import br.rnp.sdnoverlay.types.ReserveCriteriaType;

import javax.xml.soap.*;

/**
 *
 */
final public class ReserveModificationMessage {

    /**
     *
     */
    final private static String SOAP_ACTION = "http://schemas.ogf.org/nsi/2013/12/connection/service/reserve";

    /**
     *
     * @param connectionId
     * @param criteria
     * @param providerNSA
     * @param requesterNSA
     * @param replyTo
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage createMessage (String connectionId, ReserveCriteriaType criteria, String providerNSA, String requesterNSA, String replyTo) throws SOAPException {

        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();

        soapMessage = MessageUtil.configureMimeHeader(soapMessage, SOAP_ACTION);
        soapMessage = MessageUtil.configureNameSpaces(soapMessage);
        soapMessage = MessageUtil.createHeader(soapMessage, requesterNSA, providerNSA, replyTo);

        soapMessage = createBody(soapMessage, connectionId, criteria);

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     *
     * @param soapMessage
     * @param connectionId
     * @param criteria
     * @return
     * @throws SOAPException
     */
    //TODO: Validate optional parameters and exclude from final message
    private static SOAPMessage createBody (SOAPMessage soapMessage, String connectionId, ReserveCriteriaType criteria) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        SOAPBody soapBody = soapEnvelope.getBody();

        soapBody.setPrefix("soapenv");
        SOAPElement reserve = soapBody.addChildElement("reserve", "type");

        SOAPElement connId = reserve.addChildElement(soapEnvelope.createName("connectionId"));
        connId.addTextNode(connectionId);

        SOAPElement crite = reserve.addChildElement(soapEnvelope.createName("criteria"));
        crite.addAttribute(soapEnvelope.createName("version"), criteria.getVersion().toString());

        SOAPElement sched = crite.addChildElement(soapEnvelope.createName("schedule"));
        SOAPElement startTime = sched.addChildElement(soapEnvelope.createName("startTime"));
        startTime.addTextNode(criteria.getSchedule().getStartTimeString());
        SOAPElement endTime = sched.addChildElement(soapEnvelope.createName("endTime"));
        endTime.addTextNode(criteria.getSchedule().getEndTimeString());

        if (criteria.getPointToPoint() != null) {
            SOAPElement p2p = crite.addChildElement("p2ps", "p2p", "http://schemas.ogf.org/nsi/2013/12/services/point2point");

            SOAPElement capacity = p2p.addChildElement(soapEnvelope.createName("capacity"));
            capacity.addTextNode(criteria.getPointToPoint().getCapacity().toString());
        }

        soapMessage.saveChanges();

        return soapMessage;
    }
}