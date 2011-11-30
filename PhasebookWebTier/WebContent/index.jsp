<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Phasebook</title>
</head>
<body>
	<div class="top">
		<a href="index.jsp"><img alt="Phasebook Logo" src="phasebook_logo.png" height="72px" style="position: absolute;left: 150px;top: 15px;"></a>
		<form method="post" action="LogInServlet">
			<div class="login">
				<table>
					<tr>
						<td><label class="label1">Email</label></td> 
						<td><label class="label1">Password</label></td>
					</tr>
					<tr>
						<td><input name="email" type="text" class="inputtext"/></td>
						<td><input name="pass" type="password" class="inputtext"/></td>
						<td><input name="login" type="submit" value="Log In"/></td>
					</tr>
					<%if(session.getAttribute("error")!=null){ %>
					<tr>
						<td><label class="labelerror"><%=session.getAttribute("error") %></label></td>
					</tr>
					<%} %>
				</table>
			</div>
		</form>
	</div>
	<img alt="Facebook Intro" src="facebook_intro.png" style="left: 200px;top: 200px;position: absolute;">
	<div class="register">
		<form method="post" action="RegisterServlet">
			<table>
				<%if(session.getAttribute("errorreg")!=null){ %>
				<tr>
					<td></td>
					<td><label class="label2"><%=session.getAttribute("errorreg") %></label></td>
				</tr>
				<%} %>
				<tr>
					<td><label class="label2">E-mail</label></td>
					<td align="left"> <input name="email" type="text" class="inputtextregister"/></td>
				</tr>
				<tr>
					<td><label class="label2">Password:</label></td>
					<td align="left"><input name="pass" type="password" class="inputtextregister"/></td>
				</tr>
				<tr>
					<td><label class="label2">Name:</label></td>
					<td align="left"><input name="name" type="text" class="inputtextregister"/></td>
				</tr>
				<tr>
					<td><label class="label2">Gender:</label></td>
					<td align="left">
						<select name="gender" class="inputtextregister" style="width: 150px;">
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
					</td>
				</tr>
				<tr>
					<td></td>
					<td align="left"><input name="register" type="submit" value="Register" style="width: 100px;height: 35px;"/></td>
				</tr>				
			</table>
		</form>
	</div>
</body>
</html>