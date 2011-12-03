
package client.artefact;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client.artefact package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CheckLogInResponse_QNAME = new QName("http://connector/", "checkLogInResponse");
    private final static QName _GetClientInfoResponse_QNAME = new QName("http://connector/", "getClientInfoResponse");
    private final static QName _GetPost_QNAME = new QName("http://connector/", "getPost");
    private final static QName _CheckLogIn_QNAME = new QName("http://connector/", "checkLogIn");
    private final static QName _SendMessage_QNAME = new QName("http://connector/", "sendMessage");
    private final static QName _AddClient_QNAME = new QName("http://connector/", "addClient");
    private final static QName _GetClientInfo_QNAME = new QName("http://connector/", "getClientInfo");
    private final static QName _SendMessageResponse_QNAME = new QName("http://connector/", "sendMessageResponse");
    private final static QName _AddClientResponse_QNAME = new QName("http://connector/", "addClientResponse");
    private final static QName _GetPostResponse_QNAME = new QName("http://connector/", "getPostResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.artefact
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendMessage }
     * 
     */
    public SendMessage createSendMessage() {
        return new SendMessage();
    }

    /**
     * Create an instance of {@link AddClientResponse }
     * 
     */
    public AddClientResponse createAddClientResponse() {
        return new AddClientResponse();
    }

    /**
     * Create an instance of {@link CheckLogInResponse }
     * 
     */
    public CheckLogInResponse createCheckLogInResponse() {
        return new CheckLogInResponse();
    }

    /**
     * Create an instance of {@link GetPostResponse }
     * 
     */
    public GetPostResponse createGetPostResponse() {
        return new GetPostResponse();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link AddClient }
     * 
     */
    public AddClient createAddClient() {
        return new AddClient();
    }

    /**
     * Create an instance of {@link GetClientInfoResponse }
     * 
     */
    public GetClientInfoResponse createGetClientInfoResponse() {
        return new GetClientInfoResponse();
    }

    /**
     * Create an instance of {@link GetClientInfo }
     * 
     */
    public GetClientInfo createGetClientInfo() {
        return new GetClientInfo();
    }

    /**
     * Create an instance of {@link CheckLogIn }
     * 
     */
    public CheckLogIn createCheckLogIn() {
        return new CheckLogIn();
    }

    /**
     * Create an instance of {@link GetPost }
     * 
     */
    public GetPost createGetPost() {
        return new GetPost();
    }

    /**
     * Create an instance of {@link ClientInfo }
     * 
     */
    public ClientInfo createClientInfo() {
        return new ClientInfo();
    }

    /**
     * Create an instance of {@link Client }
     * 
     */
    public Client createClient() {
        return new Client();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLogInResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "checkLogInResponse")
    public JAXBElement<CheckLogInResponse> createCheckLogInResponse(CheckLogInResponse value) {
        return new JAXBElement<CheckLogInResponse>(_CheckLogInResponse_QNAME, CheckLogInResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "getClientInfoResponse")
    public JAXBElement<GetClientInfoResponse> createGetClientInfoResponse(GetClientInfoResponse value) {
        return new JAXBElement<GetClientInfoResponse>(_GetClientInfoResponse_QNAME, GetClientInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPost }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "getPost")
    public JAXBElement<GetPost> createGetPost(GetPost value) {
        return new JAXBElement<GetPost>(_GetPost_QNAME, GetPost.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLogIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "checkLogIn")
    public JAXBElement<CheckLogIn> createCheckLogIn(CheckLogIn value) {
        return new JAXBElement<CheckLogIn>(_CheckLogIn_QNAME, CheckLogIn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "sendMessage")
    public JAXBElement<SendMessage> createSendMessage(SendMessage value) {
        return new JAXBElement<SendMessage>(_SendMessage_QNAME, SendMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "addClient")
    public JAXBElement<AddClient> createAddClient(AddClient value) {
        return new JAXBElement<AddClient>(_AddClient_QNAME, AddClient.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetClientInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "getClientInfo")
    public JAXBElement<GetClientInfo> createGetClientInfo(GetClientInfo value) {
        return new JAXBElement<GetClientInfo>(_GetClientInfo_QNAME, GetClientInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "sendMessageResponse")
    public JAXBElement<SendMessageResponse> createSendMessageResponse(SendMessageResponse value) {
        return new JAXBElement<SendMessageResponse>(_SendMessageResponse_QNAME, SendMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "addClientResponse")
    public JAXBElement<AddClientResponse> createAddClientResponse(AddClientResponse value) {
        return new JAXBElement<AddClientResponse>(_AddClientResponse_QNAME, AddClientResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPostResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "getPostResponse")
    public JAXBElement<GetPostResponse> createGetPostResponse(GetPostResponse value) {
        return new JAXBElement<GetPostResponse>(_GetPostResponse_QNAME, GetPostResponse.class, null, value);
    }

}
