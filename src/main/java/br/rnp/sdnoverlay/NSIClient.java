package br.rnp.sdnoverlay;

import br.rnp.sdnoverlay.messages.*;
import br.rnp.sdnoverlay.types.ReserveCriteriaType;
import br.rnp.sdnoverlay.types.ResponseHandlerType;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 */
public class NSIClient {

    /**
     *
     */
    final private static String ENDPOINT_URL_DEFAULT = "http://10.128.12.16:9000/nsi-v2/ConnectionServiceProvider";
    final private static String REQUESTER_DEFAULT = "urn:ogf:network:cipo.rnp.br:2014:nsa:nsi-requester";
    final private static String PROVIDER_DEFAULT = "urn:ogf:network:cipo.rnp.br:2014:nsa:safnari";

    private String requesterNSA;
    private String providerNSA;
    private URL endpoint;
    private SOAPConnection soapConnection;

    /**
     *
     * @throws MalformedURLException
     * @throws SOAPException
     */
    public NSIClient() throws MalformedURLException, SOAPException {
        setEndpoint(new URL(ENDPOINT_URL_DEFAULT));
        setRequesterNSA(REQUESTER_DEFAULT);
        setProviderNSA(PROVIDER_DEFAULT);

        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        this.soapConnection = soapConnectionFactory.createConnection();
    }

    /**
     *
     * @throws SOAPException
     */
    public void close() throws SOAPException {
        this.soapConnection.close();
    }

    /**
     *
     * @return
     */
    public String getRequesterNSA() {
        return requesterNSA;
    }

    /**
     *
     * @param requesterNSA
     */
    public void setRequesterNSA(String requesterNSA) {
        this.requesterNSA = requesterNSA;
    }

    /**
     *
     * @return
     */
    public String getProviderNSA() {
        return providerNSA;
    }

    /**
     *
     * @param providerNSA
     */
    public void setProviderNSA(String providerNSA) {
        this.providerNSA = providerNSA;
    }

    /**
     *
     * @return
     */
    public URL getEndpoint() {
        return endpoint;
    }

    /**
     *
     * @param endpoint
     */
    public void setEndpoint(URL endpoint) {
        this.endpoint = endpoint;
    }

    /**
     *
     * @param description
     * @param criteria
     * @return
     * @throws SOAPException
     */
    public String reserve(String description, ReserveCriteriaType criteria) throws SOAPException {
        return reserve("", description, criteria, "");
    }

    /**
     *
     * @param globalReservationId
     * @param description
     * @param criteria
     * @param replyTo
     * @return
     * @throws SOAPException
     */
    public String reserve(String globalReservationId, String description, ReserveCriteriaType criteria, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = ReserveMessage.createMessage(globalReservationId, description, criteria, getProviderNSA(), getRequesterNSA(), replyTo);
        SOAPMessage soapResponse = this.soapConnection.call(soapMessage,getEndpoint());

        ResponseHandlerType response = new ResponseHandlerType(soapResponse);

        if (!response.itsOk()) {
            System.out.println(response.getFaultString());
            throw new SOAPException();
        }

        return response.getConnectionId();
    }

    /**
     *
     * @param connectionId
     * @throws SOAPException
     */
    public void reserveCommit(String connectionId) throws SOAPException {
        reserveCommit(connectionId, "");
    }

    /**
     *
     * @param connectionId
     * @param replyTo
     * @throws SOAPException
     */
    public void reserveCommit(String connectionId, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = ReserveCommitMessage.createMessage(connectionId, getProviderNSA(), getRequesterNSA(), replyTo);
        SOAPMessage soapResponse = this.soapConnection.call(soapMessage,getEndpoint());

        ResponseHandlerType response = new ResponseHandlerType(soapResponse);

        if (!response.itsOk()) {
            System.out.println(response.getFaultString());
            throw new SOAPException();
        }
    }

    /**
     *
     * @param connectionId
     * @throws SOAPException
     */
    public void reserveAbort(String connectionId) throws SOAPException {
        reserveAbort(connectionId, "");
    }

    /**
     *
     * @param connectionId
     * @param replyTo
     * @throws SOAPException
     */
    public void reserveAbort(String connectionId, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = ReserveAbortMessage.createMessage(connectionId, getProviderNSA(), getRequesterNSA(), replyTo);
        this.soapConnection.call(soapMessage,getEndpoint());

    }

    /**
     *
     * @param connectionId
     * @param criteria
     * @throws SOAPException
     */
    public void reserveModify(String connectionId, ReserveCriteriaType criteria) throws SOAPException {
        reserveModify(connectionId, criteria, "");
    }

    /**
     *
     * @param connectionId
     * @param criteria
     * @param replyTo
     * @throws SOAPException
     */
    public void reserveModify(String connectionId, ReserveCriteriaType criteria, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = ReserveModificationMessage.createMessage(connectionId, criteria, getProviderNSA(), getRequesterNSA(), replyTo);
        SOAPMessage soapResponse = this.soapConnection.call(soapMessage,getEndpoint());

        ResponseHandlerType response = new ResponseHandlerType(soapResponse);

        if (!response.itsOk()) {
            System.out.println(response.getFaultString());
            throw new SOAPException();
        }

    }

    /**
     *
     * @param connectionId
     * @throws SOAPException
     */
    public void provision(String connectionId) throws SOAPException {
        provision(connectionId, "");
    }

    /**
     *
     * @param connectionId
     * @param replyTo
     * @throws SOAPException
     */
    public void provision(String connectionId, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = ProvisionMessage.createMessage(connectionId, getProviderNSA(), getRequesterNSA(), replyTo);
        this.soapConnection.call(soapMessage,getEndpoint());

    }

    /**
     *
     * @param connectionId
     * @throws SOAPException
     */
    public void release(String connectionId) throws SOAPException {
        release(connectionId, "");
    }

    /**
     *
     * @param connectionId
     * @param replyTo
     * @throws SOAPException
     */
    public void release(String connectionId, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = ReleaseMessage.createMessage(connectionId, getProviderNSA(), getRequesterNSA(), replyTo);
        this.soapConnection.call(soapMessage,getEndpoint());

    }

    /**
     *
     * @param connectionId
     * @throws SOAPException
     */
    public void terminate(String connectionId) throws SOAPException {
        terminate(connectionId, "");
    }

    /**
     *
     * @param connectionId
     * @param replyTo
     * @throws SOAPException
     */
    public void terminate(String connectionId, String replyTo) throws SOAPException {

        SOAPMessage soapMessage = TerminateMessage.createMessage(connectionId, getProviderNSA(), getRequesterNSA(), replyTo);
        this.soapConnection.call(soapMessage,getEndpoint());

    }
}