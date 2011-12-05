<%@page import="eai.*"%>
<%@page import="data.*"%>
<%@page import="client.artefact.*"%>
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
int idTo=-1;
if(request.getParameter("idTo")!=null)
{	
	
	PhasebookMainWSService mainWS = new PhasebookMainWSService();
	PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
	idTo=Integer.parseInt(request.getParameter("idTo"));
	//InitialContext ctx= new InitialContext();
	//RelationBeanRemote rb=(RelationBeanRemote)ctx.lookup("RelationBean/remote");
	ws.removeFriend(personal.getId(),personal.getPassword(),idTo);
}

String str="/PhasebookWebTier/primary.jsp";
if(idTo!=-1)
	str=str+"?id="+idTo;
response.sendRedirect(str);
%>	

</body>
</html>