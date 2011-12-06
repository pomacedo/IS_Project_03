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

if(request.getParameter("idMsg")!=null)
{	
	int idMsg=Integer.parseInt(request.getParameter("idMsg"));
	InitialContext ctx= new InitialContext();
	MessageBeanRemote mb=(MessageBeanRemote)ctx.lookup("MessageBean/remote");
	
	mb.deleteMessage(personal.getId(),personal.getPassword(),idMsg);

}
response.sendRedirect("/PhasebookWebTier/primary.jsp");
%>

</body>
</html>