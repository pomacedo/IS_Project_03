<%@page import="eai.*"%>
<%@page import="data.*"%>
<%@page import="client.artefact.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Profile</title>
</head>
<body>
		<%
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
 		InitialContext ctx= new InitialContext();
		//ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");
		//RelationBeanRemote rb = (RelationBeanRemote)ctx.lookup("RelationBean/remote");
		PhotoBeanRemote pb = (PhotoBeanRemote)ctx.lookup("PhotoBean/remote");
		ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");
		String name;
		List<client.artefact.Client> clients=null;
		List<client.artefact.Relation> newRequests=null;
		if(request.getParameter("id")!=null){
			try{
				clients=ws.getFriends(Integer.parseInt(request.getParameter("id")));
			}catch(Exception e){
				out.println("Please try again Later");
				clients=null;
			}
			try{
				client.artefact.Client client=ws.getClientInfo(Integer.parseInt(request.getParameter("id")));
				name=client.getName()+"'s ";
			}catch(NumberFormatException e){
				name="";
			}
		}
		else{
			name=" Your ";
			//clients=ws.getFriends(personal.getId());
			newRequests=ws.getNewRequests(personal.getId(),personal.getPassword());
			
		}
		int i=0;	
		if(newRequests!=null && newRequests.size()!=0){ %>
			<div class="searchresult">
			<div class="labelresult">Pending Friend Requests</div>
				<%i=0;
				for(client.artefact.Relation r: newRequests)
				{
					client.artefact.Client Requestclient =ws.getClientInfo(r.getIdClientFrom());
					%>
					<div class="searchresult<%=i%2%>" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='#F2F2F2';this.style.cursor='default';" onclick="seeRequester(<%=Requestclient.getId()%>,<%=r.getId()%>)">

						<table width="50%">
							<tr>
							<td  class="searchphoto"><img alt="Phasebook Logo"   src="<%=pb.getPhotoById(Requestclient.getId())%>" width="50px" height="50px"></td>   

							<td  class="searchname">&nbsp;&nbsp;&nbsp;<%=Requestclient.getName() %></td>
							
							</tr>
						</table>
					</div>
					<%i++;
				}
				%></div><%
			}%>
		<div class="searchresult">				
			<%			
			if(clients!=null)
			{%>
				&nbsp;&nbsp;&nbsp;<label class="labelresult"> <%=name%>  Friends:</label><br><br>
				<%
				for(client.artefact.Client client:clients)
				{%>
					<div class="searchresult<%=i%2%>" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='<%= i%2==0?"#F2F2F2":"white"%>';this.style.cursor='default';" onclick="doForward(<%=client.getId()%>)">

						<table width="50%">
							<tr>
							<td  class="searchphoto">
										<div class="avatarMessage" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';" > 
												<img alt="" src="<%=pb.getPhotoById(client.getId())%>" width="50px" height="50px">
										</div>
										</td>
							<td  class="searchname">&nbsp;&nbsp;&nbsp;<%=client.getName() %></td>
							</tr>
						</table>
					</div>
					<%i++;
				}
			}
			else{
				%>
				&nbsp;&nbsp;&nbsp;<label class="labelresult">No friends found </label><br><br>
				
				<%} %>
					
		</div>			
					
		<script type="text/javascript">
			function doForward(id){
				window.top.document.location.href="primary.jsp?id="+id;
			}
			function seeRequester(idReq, idRel){
				window.top.document.location.href="primary.jsp?id="+idReq+"&rel="+idRel;
			}
		</script>
</html>