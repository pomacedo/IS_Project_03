
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
    private final static QName _CheckLogIn_QNAME = new QName("http://connector/", "checkLogIn");
    private final static QName _AddClient_QNAME = new QName("http://connector/", "addClient");
    private final static QName _AddClientResponse_QNAME = new QName("http://connector/", "addClientResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client.artefact
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddClient }
     * 
     */
    public AddClient createAddClient() {
        return new AddClient();
    }

    /**
     * Create an instance of {@link CheckLogIn }
     * 
     */
    public CheckLogIn createCheckLogIn() {
        return new CheckLogIn();
    }

    /**
     * Create an instance of {@link ClientInfo }
     * 
     */
    public ClientInfo createClientInfo() {
        return new ClientInfo();
    }

    /**
     * Create an instance of {@link CheckLogInResponse }
     * 
     */
    public CheckLogInResponse createCheckLogInResponse() {
        return new CheckLogInResponse();
    }

    /**
     * Create an instance of {@link AddClientResponse }
     * 
     */
    public AddClientResponse createAddClientResponse() {
        return new AddClientResponse();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckLogIn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "checkLogIn")
    public JAXBElement<CheckLogIn> createCheckLogIn(CheckLogIn value) {
        return new JAXBElement<CheckLogIn>(_CheckLogIn_QNAME, CheckLogIn.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link AddClientResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://connector/", name = "addClientResponse")
    public JAXBElement<AddClientResponse> createAddClientResponse(AddClientResponse value) {
        return new JAXBElement<AddClientResponse>(_AddClientResponse_QNAME, AddClientResponse.class, null, value);
    }

}
