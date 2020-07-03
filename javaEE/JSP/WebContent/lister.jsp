<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%@ page import="java.util.*, java.util.Date, aplimeth.*"  %>



<%
HttpSession sess = request.getSession();
String login = (String)sess.getAttribute("login");
if(login != null)
{
	if(login.equals("admin"))
	{%>
	
	${sessionScope.login} connected !!
	<form method="post" action="disconn.jsp">
		  <input type="submit" value="  deconnecter ?   " >
	</form>
		<% 
	}
}
else response.sendRedirect("index.jsp");




base b = new base();
b.ouvrir();
ArrayList<livre> tv= b.lister(livre.class, "select * from t_livre");
out.print("<table border = 1 width = 400 height = 100><tr><th>titre</th><th>auteur</th><th>annee</th></tr>");
for(int i=0;i<tv.size();i++)
{
	out.print("<tr>");
	out.print("<td>"+tv.get(i).gettitre()+"</td>");	
	out.print("<td>"+tv.get(i).getauteur()+"</td>");
	out.print("<td>"+ tv.get(i).getannee()+"</td>");
out.print("</tr>");

}
out.print("</table>");
%>


</body>
</html>