<%@page import="eai.*"%>
<%@page import="data.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<%ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");%>
<html>
<head>

</head>
<body>
<%if(personal==null)
{%>
	<jsp:forward page="/index.jsp"></jsp:forward>
<%
}
int idphoto=-1;
if(request.getParameter("id")!=null)
{	
	idphoto=Integer.parseInt(request.getParameter("id"));
	InitialContext ctx= new InitialContext();
	ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");
	cb.setProfilePhoto(personal.getId(),personal.getPassword(),idphoto);
	personal.setIdPhoto(idphoto);
	session.setAttribute("user", personal);
	response.sendRedirect("/PhasebookWebTier/primary.jsp");
}%>
</body>
</html>