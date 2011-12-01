<%@page import="eai.*"%>
<%@page import="data.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<%ManageBeanRemote user=(ManageBeanRemote)session.getAttribute("user");
		if(user==null){%>
			<jsp:forward page="/index.jsp"></jsp:forward>
<%}
Client c=null;
List<Relation> allreadyFriends= null;
InitialContext ctx= new InitialContext();
ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");
RelationBeanRemote rb=(RelationBeanRemote)ctx.lookup("RelationBean/remote");
PhotoBeanRemote pb=(PhotoBeanRemote)ctx.lookup("PhotoBean/remote");
MessageBeanRemote mb = (MessageBeanRemote)ctx.lookup("MessageBean/remote");
int newRelId=0;
if(request.getParameter("id")!=null){
	c=cb.getClientInfo(Integer.parseInt(request.getParameter("id")));
	
	if(request.getParameter("rel")!=null)
	{
		newRelId=Integer.parseInt(request.getParameter("rel"));
	}
	else
		allreadyFriends = rb.checkMyFriends(user.getId(),user.getPassword());
}
int id;
if(c==null)
	id=user.getId();
else
	id=c.getId();

%>
<title><%=c==null?user.getName():c.getName() %></title>
</head>
<body>
	<div class="top">
		<a href="primary.jsp"><img alt="Phasebook Logo"   src="phasebook_logo.png" height="72px" style="position: absolute;left: 150px;top: 15px;"></a>
		<div class="login">
			<label class="label1"> <%=user.getName()%> </label><div class="logout" onclick="logout()" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"><label class="label1" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';">logout</label></div>
		</div> 
		<div class="search">
				<table>
					<tr>
						<td><input name="searchfor" placeholder="Search" id="searchfor" type="text" class="inputtextregister" onKeyPress="submitenter(this,event)"/></td>
						<td><input name="search" type="submit" value="search" onclick="getSearch()" style="width: 100px;height: 35px;"/></td>
					</tr>
				</table>
		</div>
	</div>
	<div class="leftCol">
	<label class="label5"><%=c==null?user.getName():c.getName() %></label>
		<% String photoPath="http://www.tutoresnarede.com.br/img/facebook-no-image.gif";%>
<!--		   if(request.getParameter("id")!=null && c!=null){-->
<!--			   if(c.getPhoto()!=null){-->
<!--			   		photoPath=c.getPhoto().getPath();-->
<!--			   }-->
<!--		   }-->
<!--		   else{-->
<!--			   photoPath=user.getPhotoPath();-->
<!--		   }-->
		
		<img src="<%=photoPath%>" width="180px" >
		<br>
		<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';" onclick="getProfile(<%=request.getParameter("id")%>)"><label class="label2">Info</label></div>
		<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';" onclick="getMessages(<%=request.getParameter("id")%>)"><label class="label2">Messages</label></div>
		<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="getFriends(<%=request.getParameter("id")%>)"><label class="label2">Friends (<%=rb.numberOfFriends(id) %>)</label></div>
		<% 
		if(c==null ||(c!=null && rb.checkIfIsApprovedFriend(user.getId(),user.getPassword(),allreadyFriends,c.getId()))){%>
			<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="getGallery(<%=request.getParameter("id")%>)"><label class="label2">Photo Gallery (<%=mb.privNumberOfPhotos(id) %>)</label></div>
		<%}
		  else{%>
			  <div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="getGallery(<%=request.getParameter("id")%>)"><label class="label2">Photo Gallery (<%=mb.pubNumberOfPhotos(id) %>)</label></div>
		  <%}
		if(c!=null && newRelId==0 && !rb.checkIfIsFriend(user.getId(),user.getPassword(),allreadyFriends,c.getId())){%>
			<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="addFriend(<%=request.getParameter("id")%>)"><label class="label3">Add as Friend</label></div> 
		<%}
		else if(c!=null && newRelId!=0){%>
			<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="acceptRequest(<%=newRelId%>,<%=c.getId() %>)"><label class="label3">Accept Request</label></div>
			<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="declineRequest(<%=newRelId%>,<%=c.getId()%>)"><label class="label3">Decline Request</label></div>
		<%} else if(c==null){%>
			<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="myBets()"><label class="label2">My Bets</label></div>	
		<%}else if(c!=null && rb.checkIfIsApprovedFriend(user.getId(),user.getPassword(),allreadyFriends,c.getId())){%>
			<div class="leftcolitem" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="removeFriend(<%=c.getId()%>)"><label class="label2">Remove friend</label></div>
		<%} %>
	</div>
	<div class="container" id="container">
		<br><iframe src="messages.jsp<%=request.getParameter("id")==null?"":"?id="+request.getParameter("id") %>" frameborder="0" width="100%" height="100%"></iframe>
	</div>
	
</body>

<script type="text/javascript">

	function submitenter(myfield,e){
		var keycode;
		if (window.event) keycode = window.event.keyCode;
		else if (e) keycode = e.which;
		else return true;
		
	
		if (keycode == 13){
			getSearch();
		   return false;
	   }
		else
	   	return true;
	}
	function getGallery(id){
		if(id==null)
			document.getElementById('container').innerHTML='<iframe src=\"gallery.jsp\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		else{
			document.getElementById('container').innerHTML='<iframe src=\"gallery.jsp?id='+id+'\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		}
	}
	function getProfile(id){
		if(id==null)
			document.getElementById('container').innerHTML='<iframe src=\"profile.jsp\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		else{
			document.getElementById('container').innerHTML='<iframe src=\"profile.jsp?id='+id+'\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		}
	}
	function getMessages(id){
		if(id==null){
			document.getElementById('container').innerHTML='<iframe src=\"messages.jsp\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		}else{
			document.getElementById('container').innerHTML='<iframe src=\"messages.jsp?id='+id+'\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		}	
	}	
	
	function getFriends(id){
		if(id==null){
			document.getElementById('container').innerHTML='<iframe src=\"friends.jsp\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		}else{
			document.getElementById('container').innerHTML='<iframe src=\"friends.jsp?id='+id+'\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		}
	}

	function getSearch(){
		document.getElementById('container').innerHTML='<iframe src=\"search.jsp?searchfor='+document.getElementById('searchfor').value+'\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
	}
	
	function addFriend(id)
	{
		window.top.document.location.href='addFriend.jsp?idTo='+id;		
	}
	function acceptRequest(idReq,idTo)
	{
		window.top.document.location.href='acceptFriend.jsp?idRel='+idReq+'&idTo='+idTo; 

	}
	function declineRequest(idReq,idTo)
	{
		window.top.document.location.href='declineFriend.jsp?idRel='+idReq+'&idTo='+idTo; 
	}
	function removeFriend(id)
	{
		window.top.document.location.href='removeFriend.jsp?idTo='+id; 
	}
	function myBets()
	{
		document.getElementById('container').innerHTML='<iframe src=\"bets.jsp" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';		
	}
	function logout()
	{
		window.top.document.location.href="logout.jsp";	
	}
	

</script>
</html>