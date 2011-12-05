<%@page import="eai.*"%>
<%@page import="client.artefact.*"%>
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
		ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");		
		
		client.artefact.Client client;
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
		
		if(request.getParameter("id")!=null){
			try{
				client=ws.getClientInfo(Integer.parseInt(request.getParameter("id")));
				
			}catch(NumberFormatException e){
				
				client=ws.getClientInfo(personal.getId());
				personal.setEmail(client.getEmail());
				personal.setId(client.getId());
				personal.setPassword(client.getPassword());
				personal.setName(client.getName());
				personal.setIdPhoto(client.getIdPhoto());		
			}
		}		
		else{
			client=ws.getClientInfo(personal.getId());
			personal.setEmail(client.getEmail());
			personal.setId(client.getId());
			personal.setPassword(client.getPassword());
			personal.setName(client.getName());
			personal.setIdPhoto(client.getIdPhoto());
		}
		
		%>
	<div class="userprofile">

		<%if(request.getParameter("error")!=null)
		{ %>
			<label class="labelerror"><%= request.getParameter("error")%></label>
		<%session.removeAttribute("error");
		}%>
		<div class="infotitle">
			Basic Information
			<% if(request.getParameter("id")==null)
			{%> 
			<div style="float: right;" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';" onclick="editProfile()">EDIT</div>
			<% }%>
		</div>
		<table width="100%">
			<tr>
				<td class="infotdtitle">Name</td>
				<td class="infotd"><%=client.getName()%></td>
			</tr>
			<tr>
				<td class="infotdtitle">Email</td>
				<td class="infotd"><%=client.getEmail()%></td>
			</tr>
			<tr>
				<td class="infotdtitle">Gender</td>
				<td class="infotd"><%
				char c=(char)client.getGender();
				if(c=='M'){
					%><%="Male"%>
				<%}else{%>
					<%="Female"%> 
				<%} %>
					</td>
			</tr>
		</table>
		<div class="infotitle">Lottery Information</div>
		<table width="100%">
			<tr>
				<td class="infotdtitle">Money</td>
				<td class="infotd"><%=client.getMoney()%> L&euro;</td>
			</tr>
		</table>		
	</div>

	

<script type="text/javascript">
	function editProfile(){
		//document.getElementById('container').innerHTML='<iframe src=\"editProfile.jsp\" frameborder=\"0\" width=\"100%\" height=\"100%\"></iframe>';
		//window.top.document.location.href="editProfile.jsp";
		document.location.href="editProfile.jsp";
	}
</script>
</html>