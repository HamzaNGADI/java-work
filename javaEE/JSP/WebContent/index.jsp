<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>jsp title</title>
</head>
<body>
<%@ page import="java.util.*, java.util.Date, aplimeth.*"  %>
<%

HttpSession sess = request.getSession();
String login = (String)sess.getAttribute("login");
int s=0;
if(login!=null) s=1;

if(s==0)
{
	%>
	<form method="post" action="connect.jsp">
        <p><input type="text" name="login" value="" placeholder="Username"></p>
        <p><input type="password" name="password" value="" placeholder="Password"></p>
       <input type="submit" name="commit" value="connecter">
      </form>
	<% 
	
}
else
{
%>
	
	${sessionScope.login} connected !!
	<form method="post" action="disconn.jsp">
		  <input type="submit" value="  deconnecter ?   " >
	</form><% 

}

%>
</body>
</html>