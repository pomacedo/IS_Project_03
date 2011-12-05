
package client.artefact;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAXWS SI.
 * JAX-WS RI 2.0_02-b08-fcs
 * Generated source version: 2.0
 * 
 */
@WebService(name = "PhasebookMainWS", targetNamespace = "http://connector/")
public interface PhasebookMainWS {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "acceptFriend", targetNamespace = "http://connector/", className = "client.artefact.AcceptFriend")
    @ResponseWrapper(localName = "acceptFriendResponse", targetNamespace = "http://connector/", className = "client.artefact.AcceptFriendResponse")
    public void acceptFriend(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

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
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "addFriend", targetNamespace = "http://connector/", className = "client.artefact.AddFriend")
    @ResponseWrapper(localName = "addFriendResponse", targetNamespace = "http://connector/", className = "client.artefact.AddFriendResponse")
    public boolean addFriend(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        int arg2);

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
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "declineFriend", targetNamespace = "http://connector/", className = "client.artefact.DeclineFriend")
    @ResponseWrapper(localName = "declineFriendResponse", targetNamespace = "http://connector/", className = "client.artefact.DeclineFriendResponse")
    public void declineFriend(
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
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Integer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getFriends", targetNamespace = "http://connector/", className = "client.artefact.GetFriends")
    @ResponseWrapper(localName = "getFriendsResponse", targetNamespace = "http://connector/", className = "client.artefact.GetFriendsResponse")
    public List<Integer> getFriends(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.util.List<client.artefact.Relation>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getNewRequests", targetNamespace = "http://connector/", className = "client.artefact.GetNewRequests")
    @ResponseWrapper(localName = "getNewRequestsResponse", targetNamespace = "http://connector/", className = "client.artefact.GetNewRequestsResponse")
    public List<Relation> getNewRequests(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

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
     * @param arg0
     * @return
     *     returns java.util.List<client.artefact.Client>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSearch", targetNamespace = "http://connector/", className = "client.artefact.GetSearch")
    @ResponseWrapper(localName = "getSearchResponse", targetNamespace = "http://connector/", className = "client.artefact.GetSearchResponse")
    public List<Client> getSearch(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "removeFriend", targetNamespace = "http://connector/", className = "client.artefact.RemoveFriend")
    @ResponseWrapper(localName = "removeFriendResponse", targetNamespace = "http://connector/", className = "client.artefact.RemoveFriendResponse")
    public void removeFriend(
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
