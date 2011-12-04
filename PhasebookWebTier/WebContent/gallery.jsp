<%@page import="eai.*"%>
<%@page import="data.*"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css">
<title>Gallery</title>
</head>
<body TOPMARGIN=0>
		<%
 		InitialContext ctx= new InitialContext();
		/*ClientSessionBeanRemote cb=(ClientSessionBeanRemote)ctx.lookup("ClientSessionBean/remote");*/
		RelationBeanRemote rb = (RelationBeanRemote)ctx.lookup("RelationBean/remote");
		MessageBeanRemote mb = (MessageBeanRemote)ctx.lookup("MessageBean/remote");
		PhotoBeanRemote pb = (PhotoBeanRemote)ctx.lookup("PhotoBean/remote");
		ManageBeanRemote user=(ManageBeanRemote)session.getAttribute("user");
		
		List<Photo> photos=null;
		List<Relation> allreadyFriends= rb.checkMyFriends(user.getId(),user.getPassword());
		if(request.getParameter("id")!=null){
			try{ 
				photos=pb.getPhotosById(mb.getAllIdPhotos(user.getId(),user.getPassword(),Integer.parseInt(request.getParameter("id")),rb.checkIfIsApprovedFriend(user.getId(),user.getPassword(),allreadyFriends,Integer.parseInt(request.getParameter("id")))));
			}catch(Exception e){
				out.println("Please try again Later");
				photos=null;
			}
		}else{
			try{ 
				photos=pb.getPhotos(user.getId(),true);
				photos=pb.getPhotosById(mb.getAllIdPhotos(user.getId(),user.getPassword(),user.getId(),true));
			}catch(Exception e){
				out.println("Please try again Later");
				photos=null;
			}
		}
		if(photos!=null && photos.size()>0){%>
			<div class="photoControl">
				<div class="photoBtn" onclick="back()" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"><label class="label1" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"> Back |</label></div> 
				<div class="photoBtn" onclick="next()" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"><label class="label1" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"> Next |</label></div>
				<%if(request.getParameter("id")==null){%>
					<div class="photoBtn" onclick="setProfilePhoto()" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"><label class="label1" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';"> Set as Profile </label></div>
				<%}%>
			</div>
							
			<div class="photodiv">
				<img class="photo" id="photoFrame"  onclick="javascript:next()"   src="<%=photos.get(0).getPath()%>" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';">
				&emsp;
				<div class="allPhotos">
					<%int i=0;
					for(Photo p:photos){%>
						<img id="photoFrame" width="19%" height="90px" onclick="javascript:set(<%=i %>)"  src="<%=p.getPath()%>" onmouseover="this.style.cursor='pointer';" onmouseout=";this.style.cursor='default';">
						<%i++;
					}%>
				</div>
			</div>

			
			<script type="text/javascript">
				photosId=[<%for(Photo p:photos){out.print(p.getId()+",");};out.print("'-1'");%>];
				photos=[<%for(Photo p:photos){out.print("'"+p.getPath()+"',");};out.print("'END'");%>];
				currentPhoto=0;
				function back(){ 
					if(currentPhoto-1<0)
						currentPhoto=(photos.length-2);
					else
						currentPhoto=(currentPhoto-1);
					document.getElementById('photoFrame').src=photos[currentPhoto];
				}
				function next(){
					currentPhoto=(currentPhoto+1)%(photos.length-1);
					document.getElementById('photoFrame').src=photos[currentPhoto];
				}
				function set(id){
					currentPhoto=id;
					document.getElementById('photoFrame').src=photos[id];
					window.scroll(0,0);
				}
				<%if(request.getParameter("id")==null){%>
				function setProfilePhoto(){ 
					window.top.document.location.href="setProfilePhoto.jsp?id="+photosId[currentPhoto];
				}
				<%}%>
			</script>
		<%}
		else { 
		%>
			No photos found for this user
		<%}%>

</html>