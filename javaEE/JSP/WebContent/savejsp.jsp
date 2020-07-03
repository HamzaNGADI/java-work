<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>save jsp</title>
</head>
<body>

<h4>enregistrer livre :</h4>
<%
HttpSession sess = request.getSession();
String login = (String)sess.getAttribute("login");
if(login != null)
{
	if(login.equals("admin"))
	{
		%>
		
		${sessionScope.login} connected !!
	<form method="post" action="disconn.jsp">
		  <input type="submit" value="  deconnecter ?   " >
	</form>
		<% 
	}
}
else response.sendRedirect("index.jsp");



     sess = request.getSession();
	  String til = (String)sess.getAttribute("titless");
	  String auil = (String)sess.getAttribute("auteurss");
	  String annil = (String)sess.getAttribute("annss");
	  if(til==null) til="";
	  if(auil==null) auil="";
	  if(annil==null) annil="";
%>
 <form method="post" action="enregistrer.jsp">
 
 <label>titre : </label>  <input type="text" name="titre" value="<%out.print(til); %>"/>
  <label>auteur : </label>  <input type="text" name="auteur" value="<%out.print(auil); %>"/>
  <label>annee : </label>  <input type="text" name="annee" value="<%out.print(annil); %>" />
  <input type="submit" value="   save   " >
</form>
<%
sess.setAttribute("auteurss", new String(""));
sess.setAttribute("titless", new String(""));
sess.setAttribute("annss", new String(""));

%>


</body>
</html>