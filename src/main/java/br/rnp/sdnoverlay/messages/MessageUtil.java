package br.rnp.sdnoverlay.messages;

import javax.xml.soap.*;
import java.util.UUID;

/**
 *
 */
final public class MessageUtil {

    final private static String PROTOCOL_VERSION = "application/vnd.ogf.nsi.cs.v2.provider+soap";

    /**
     *
     * @param soapMessage
     * @param soapAction
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage configureMimeHeader (SOAPMessage soapMessage, String soapAction) throws SOAPException {

        MimeHeaders mimeHeaders = soapMessage.getMimeHeaders();
        mimeHeaders.addHeader("SOAPAction", soapAction);

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     *
     * @param soapMessage
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage configureNameSpaces (SOAPMessage soapMessage) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        //Default namespaces for NSI
        soapEnvelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
        soapEnvelope.addNamespaceDeclaration("head", "http://schemas.ogf.org/nsi/2013/12/framework/headers");
        soapEnvelope.addNamespaceDeclaration("type", "http://schemas.ogf.org/nsi/2013/12/connection/types");
        soapEnvelope.addNamespaceDeclaration("saml", "urn:oasis:names:tc:SAML:2.0:assertion");
        soapEnvelope.addNamespaceDeclaration("xsi", "http://www.w3.org/2001/XMLSchema-instance");
        soapEnvelope.addNamespaceDeclaration("xs", "http://www.w3.org/2001/XMLSchema");

        soapMessage.saveChanges();

        return soapMessage;
    }

    /**
     *
     * @param soapMessage
     * @param requesterNSA
     * @param providerNSA
     * @param replyTo
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage createHeader (SOAPMessage soapMessage, String requesterNSA, String providerNSA, String replyTo) throws SOAPException {
        return createHeader(soapMessage, PROTOCOL_VERSION, requesterNSA, providerNSA, replyTo);
    }

    /**
     *
     * @param soapMessage
     * @param protocolVersion
     * @param requesterNSA
     * @param providerNSA
     * @param replyTo
     * @return
     * @throws SOAPException
     */
    public static SOAPMessage createHeader (SOAPMessage soapMessage, String protocolVersion, String requesterNSA, String providerNSA, String replyTo) throws SOAPException {

        SOAPPart soapPart = soapMessage.getSOAPPart();
        SOAPEnvelope soapEnvelope = soapPart.getEnvelope();

        SOAPHeader soapHeader = soapEnvelope.getHeader();

        soapHeader.setPrefix("soapenv");
        SOAPElement nsiHeader = soapHeader.addChildElement("nsiHeader", "head");

        SOAPElement pv = nsiHeader.addChildElement(soapEnvelope.createName("protocolVersion"));
        pv.addTextNode(protocolVersion);

        SOAPElement correlationID = nsiHeader.addChildElement(soapEnvelope.createName("correlationId"));
        correlationID.addTextNode("urn:uuid:" + UUID.randomUUID().toString());

        SOAPElement req = nsiHeader.addChildElement(soapEnvelope.createName("requesterNSA"));
        req.addTextNode(requesterNSA);

        SOAPElement prov = nsiHeader.addChildElement(soapEnvelope.createName("providerNSA"));
        prov.addTextNode(providerNSA);

        if (!replyTo.equals("")) {
            SOAPElement reply = nsiHeader.addChildElement(soapEnvelope.createName("replyTo"));
            reply.addTextNode(replyTo);
        }

        soapMessage.saveChanges();

        return soapMessage;
    }
}