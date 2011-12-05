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

<%ManageBeanRemote user=(ManageBeanRemote)session.getAttribute("user");
		if(user==null){%>
			<jsp:forward page="/index.jsp"></jsp:forward>
<%} %>
		
<title>Profile</title>
</head>
<body>
		<%
		
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
 		InitialContext ctx= new InitialContext();
 		PhotoBeanRemote pb = (PhotoBeanRemote)ctx.lookup("PhotoBean/remote");
		//ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");
		//ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");
		//int userid=personal.getId();
		
		String searchFor= request.getParameter("searchfor");
		
		List<client.artefact.Client> clients=ws.getSearch(searchFor); 
		//List<Relation> allreadyFriends = cb.checkMyFriends(user.getId(),user.getPassword());

		if(clients==null){%>
			<div class="searchresult">
				&nbsp;&nbsp;&nbsp;<label class="labelresult">No results for <%=request.getParameter("searchfor").equals("")?"\"\"":request.getParameter("searchfor")%></label><br><br>
			</div>
		<%}else{%>	
		<div class="searchresult">
				<%
				int i=0;
				if(clients!=null){
					%>
					&nbsp;&nbsp;&nbsp;<label class="labelresult">Search Results for <%=request.getParameter("searchfor").equals("")?"\"\"":request.getParameter("searchfor")%></label><br><br>
					<%
					for(client.artefact.Client client:clients) 
					{
					%>
						<div class="searchresult<%=i%2%>" onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='<%= i%2==0?"#F2F2F2":"white"%>';this.style.cursor='default';" onclick="doForward(<%=client.getId()%>)">
									<table width="50%">
										<tr>
										<td  class="searchphoto">
										<div class="avatarMessage" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';" > 
												<img alt="" src="<%=pb.getPhotoById(client.getIdPhoto())%>" width="50px" height="50px">
										</div>
										</td>
										<td  class="searchname">&nbsp;&nbsp;&nbsp;<%=client.getName() %></td>
										</tr>
									</table>
								</div>
							<%i++;
					}
				}
				else{%>
					&nbsp;&nbsp;&nbsp;<label class="labelresult">No users found for <%=request.getParameter("searchfor").equals("")?"\"\"":request.getParameter("searchfor")%></label><br><br>
				<%}
			}%>
					
		</div>
<script type="text/javascript">
	function doForward(id){
		window.top.document.location.href="primary.jsp?id="+id;
	}
</script>
</html>