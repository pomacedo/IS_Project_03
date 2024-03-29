
package client.artefact;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "PhasebookMainWS", targetNamespace = "http://connector/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PhasebookMainWS {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addClient", targetNamespace = "http://connector/", className = "client.artefact.AddClient")
    @ResponseWrapper(localName = "addClientResponse", targetNamespace = "http://connector/", className = "client.artefact.AddClientResponse")
    public int addClient(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns client.artefact.ClientInfo
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkLogIn", targetNamespace = "http://connector/", className = "client.artefact.CheckLogIn")
    @ResponseWrapper(localName = "checkLogInResponse", targetNamespace = "http://connector/", className = "client.artefact.CheckLogInResponse")
    public ClientInfo checkLogIn(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg5
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @param arg6
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "editProfile", targetNamespace = "http://connector/", className = "client.artefact.EditProfile")
    @ResponseWrapper(localName = "editProfileResponse", targetNamespace = "http://connector/", className = "client.artefact.EditProfileResponse")
    public boolean editProfile(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        String arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        int arg5,
        @WebParam(name = "arg6", targetNamespace = "")
        float arg6);

    /**
     * 
     * @param arg0
     * @return
     *     returns client.artefact.Client
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getClientInfo", targetNamespace = "http://connector/", className = "client.artefact.GetClientInfo")
    @ResponseWrapper(localName = "getClientInfoResponse", targetNamespace = "http://connector/", className = "client.artefact.GetClientInfoResponse")
    public Client getClientInfo(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<client.artefact.Message>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getPost", targetNamespace = "http://connector/", className = "client.artefact.GetPost")
    @ResponseWrapper(localName = "getPostResponse", targetNamespace = "http://connector/", className = "client.artefact.GetPostResponse")
    public List<Message> getPost(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

    /**
     * 
     * @param arg5
     * @param arg4
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sendMessage", targetNamespace = "http://connector/", className = "client.artefact.SendMessage")
    @ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://connector/", className = "client.artefact.SendMessageResponse")
    public boolean sendMessage(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        String arg3,
        @WebParam(name = "arg4", targetNamespace = "")
        boolean arg4,
        @WebParam(name = "arg5", targetNamespace = "")
        String arg5);

}
