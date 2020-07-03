<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
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
		<form method="post" action="savejsp.jsp">
       <input type="submit" name="commit" value="enregistrer">
      </form>
      <form method="post" action="lister.jsp">
       <input type="submit" name="commitu" value="lister">
      </form>
     </body>
		<% 
	}
}
else response.sendRedirect("index.jsp");
%>

</html>