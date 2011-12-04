<%@page import="eai.*"%>
<%@page import="data.*"%>
<%@page import="java.util.List"%>
<%@page import="javax.naming.InitialContext"%>
<link rel="stylesheet" href="style.css">

<%	
	ManageBeanRemote personal=(ManageBeanRemote)session.getAttribute("user");
	InitialContext ctx= new InitialContext();
	LottBeanRemote lb = (LottBeanRemote)ctx.lookup("LottBean/remote");
	BetBeanRemote bb = (BetBeanRemote)ctx.lookup("BetBean/remote");
%>
<%
if(session.getAttribute("error")!=null){%>
<label class="labelerror"><%=session.getAttribute("error") %></label>
<%
}
Lottery curLot = lb.getCurrentLottery();
if(curLot!=null)
{
%>
	<div class="infotitle">
		New Bet
	</div>
	<form method="post" action="MakeNewBetServlet">
		<input type="hidden" name="betId" value="<%=curLot.getId() %>">
		<label style="color: #333;">Select a number:</label>
		<select name="number" class="inputtextregister" style="width: 100px;">
			<%for(int i=1;i<=100;i++){ %>
				<option value="<%=i%>"><%=i%></option>
			<%} %>
		</select>
		<input type="submit" value="Bet">
	</form>
	<div style="font-size:large; ;color: #999; ">
		This lottery will end at <b><%= curLot.getDate().toString() %></b>
	</div>
<%	
List<Bet> currBets = bb.getMyCurrentBets(personal.getId(),personal.getPassword(), curLot);
int i=0;
if(currBets.size()>0)
{
	i=0;
			%>
			<div class="infotitle">
				Current bets in this lottery
			</div>
			<table width="40%">
				<tr>
					<th class="infotdtitle" style="width: 15%;">Bet Number</th>
					<th class="infotdtitle">Bet Status</th>
				</tr>
			<%for(Bet b: currBets)
			{
				%>
				<tr>
					<td class="infotd" style="width: 15%;"><b><%=b.getNumber()%></b></td>
					<td class="infotd">Waiting for result</td>
				</tr>
				<%i++;
			}%>
			</table>
<%}
}
List<Bet> oldestBets = bb.getMyOldBets(personal.getId(),personal.getPassword(),curLot);
if(oldestBets!=null)
{
	int i=0;%>
			<div class="infotitle">
				Old Bets
			</div>
			<table width="55%">
				<tr>
					<th class="infotdtitle" style="width: 10%;">Bet Number</th>
					<th class="infotdtitle" style="width: 10%;">Bet Result</th>
					<th class="infotdtitle">Lottery Date</th>
					<th class="infotdtitle" style="width: 10%;">Lottery Number</th>
				</tr>
			<%for(Bet b: oldestBets)
			{
				Lottery lot=lb.getLottery(b.getId_lottery());
				%>
				<tr>
					<td class="infotd" style="width: 10%;"><b><%=b.getNumber()%></b></td>
					<td class="infotd" style="width: 10%;"><%= lot.getResult()==b.getNumber()?"Won":"Not won" %></td>
					<td class="infotd"><%=lot.getDate().toString() %></td>
					<td class="infotd" style="width: 10%;"><%=lot.getResult() %></td>
				</tr>
				<%i++;
			}
		%></table>
<%
}%>
