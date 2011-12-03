<%@page import="eai.*"%>
<%@page import="client.artefact.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");		
		PhasebookMainWSService mainWS = new PhasebookMainWSService();
		PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
		Client client;
		
		client=ws.getClientInfo(personal.getId());
		personal.setEmail(client.getEmail());
		personal.setId(client.getId());
		personal.setPassword(client.getPassword());
		personal.setName(client.getName());
		personal.setIdPhoto(client.getIdPhoto());
		
		%>
	<div class="userprofile">
		<form method="post" action="EditProfileServlet">
			<div class="infotitle">
				Basic Information
			</div>
			<table width="100%">
				<tr>
					<td class="infotdtitle">Name</td>
					<td class="infotd"><input type="text" name="name" value="<%=client.getName()%>"></td>
				</tr>
				<tr>
					<td class="infotdtitle">Email</td>
					<td class="infotd"><input type="text" name="email" value="<%=client.getEmail()%>"></td>
				</tr>
				<tr>
					<td class="infotdtitle">Gender</td>
					<td class="infotd">
						&nbsp;<select name="gender" class="inputtextregister" style="width: 150px;">
							<%if(client.getGender()=='M'){%>
								<option value="M">Male</option>
								<option value="F">Female</option>
							<%}else{ %>
								<option value="F">Female</option>
								<option value="M">Male</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td class="infotdtitle">Password</td>
					<td class="infotd"><input type="password" name="pass" value="<%=client.getPassword()%>"></td>
				</tr>
			</table>
			<div class="infotitle">Lottery Information</div>
			<table width="100%">
				<tr>
					<td class="infotdtitle">Money</td>
					<td class="infotd"><input type="text" name="money" value="<%=client.getMoney()%>"> L&euro;</td>
				</tr>
			</table>
			<input type="submit" value="Edit">
		</form>
		
	</div>

</body>
</html>