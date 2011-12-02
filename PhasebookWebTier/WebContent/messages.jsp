<%@page import="eai.*"%>
<%@page import="client.artefact.*"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<link rel="stylesheet" href="style.css">

<%
	InitialContext ctx= new InitialContext();
	ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");
	//MessageBeanRemote mb=(MessageBeanRemote)ctx.lookup("MessageBean/remote");
	
	PhasebookMainWSService mainWS = new PhasebookMainWSService();
	PhasebookMainWS ws = mainWS.getPhasebookMainWSPort();
	
	ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");
	
if(session.getAttribute("error")!=null){ %>
	<label class="labelerror"><%=session.getAttribute("error") %></label>
	<%session.removeAttribute("error");
}%>
<div class="infotitle">New message</div>

	<form method="post" action="PostServlet" enctype="multipart/form-data">
		<input name="message" class="newmessage" id="newmessage" onchange="javascript:youtube()" maxlength="140" type="text" placeholder="Write your message here..." />
		<input type="hidden" name="to"  value="1"/>
		<select name="private" class="inputtextregister" style="width: 100px;">
			<option value="T">Private</option>
			<option value="F">Public</option>
		</select>
		<input type="submit" value="Send">
		<br><input type="file" name="file">
		
	</form>
	<div class="preview" id="preview"></div>
<div class="infotitle">Messages</div>
<%	
	List<client.artefact.Message> messages=null;
	int idTo=-1;
	if(request.getParameter("id")!=null){
		try{
			idTo=Integer.parseInt(request.getParameter("id"));
			messages=ws.getPost(personal.getId(), personal.getPassword(),idTo);
		}catch(NumberFormatException e){}
	}
	else{
		messages=ws.getPost(personal.getId(), personal.getPassword(),-1);
	}
	if(messages!=null)
	{
		for(Message m: messages)
		{%>
			<div class="message">
				<div class="avatarMessage" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';" onclick="doForward(<%=m.getIdClientFrom()%>)"> 
					ir buscar a imagem 					img alt="" src="" width="50px"
				</div>
				&nbsp;&nbsp;&nbsp;<div style="float: left;" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';" onclick="doForward(<%=m.getIdClientFrom()%>)">&nbsp;&nbsp;<label class="label3" onmouseover="this.style.cursor='pointer';" onmouseout="this.style.cursor='default';" onclick="doForward(<%=m.getIdClientFrom()%>)"><%="nome a ir buscar"/*m.getclient_from().getName()*/ %></label></div><label class="label3" style="font-weight: normal;">&nbsp;says:</label>
				<%if(idTo==-1 || idTo==personal.getId()){ %>
					<div onmouseover="this.style.backgroundColor='#D8DFEA';this.style.cursor='pointer';" onmouseout="this.style.backgroundColor='White';this.style.cursor='default';"onclick="deleteMessage(<%=m.getId()%>)" style="float: right;"><label class="label2" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default'" >Delete</label></div>
				<%}%>
				<div class="textMessage">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label class="messagetext"><%=m.getText() %></label>
					<%if(m.getIdPhoto()!=-1){ %>
						<div style="position:relative;top:20px;left: 70px;width: 300px;"><img alt="" src="<%=m.getIdPhoto()%>" width="300px"></div>
					<%} %>
				</div>
				<div class="messagedate">
					at <%=m.getMsgDate().toString() %>&nbsp;&nbsp;<img alt="" src="<%=m.isIsPrivate()?"private.png":"public.png"%>">
				</div>
			</div>	
			
		<%}
	}	
%>

<script type="text/javascript">
	function youtube()
	{
	    str=document.getElementById('newmessage').value;
		if(str.search('www.youtube.com')>-1)
			document.getElementById('preview').innerHTML='<object width="425" height="355"> <param name="movie" value="'+str+'?version=3&autohide=1&showinfo=0"></param>  <param name="allowScriptAccess" value="always"></param>  <embed src="'+str+'?version=3&autohide=1&showinfo=0" type="application/x-shockwave-flash" allowscriptaccess="always" width="425" height="355"></embed></object>';
	}
	
	function doForward(id){
		
		window.top.document.location.href="primary.jsp?id="+id;
	}
	function deleteMessage(id){
		window.top.document.location.href="deleteMessage.jsp?idMsg="+id;
		}
</script>