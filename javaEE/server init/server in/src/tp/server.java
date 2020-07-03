package tp;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class server extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest rec, HttpServletResponse res) throws ServletException, IOException
	{
		String nom = rec.getParameter("nom");
		String pre = rec.getParameter("prenom");
		String em = rec.getParameter("email");
		res.setContentType("text/html");
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		out.println("<html>");
		out.println("<head><title>server</title></head>");
		out.println("<body>");
		out.println("<H2> votre nom complet est : "+nom+" "+pre+"<br> votre e-mail : "+em+"</H2><br> données enregistrer !!");
		out.println("</body>");
		out.println("</html>");
		dbconn(nom,pre,em);
	}
	public void dbconn(String n,String p,String em)
	{
	    Connection m_connection;
	    Statement m_statement = null;
		ResultSet m_resultSet;
		int i =0;
	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			m_connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","");
			m_statement = m_connection.createStatement();
		}
		catch(Exception e)
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Exception thrown ," + e); 
		}	
		
		try
		{
		  m_resultSet = m_statement.executeQuery("select * from employes");
		  while(m_resultSet.next())
			{i++; }
          i++;
		 int nb = m_statement.executeUpdate("insert into employes values("+i+",'"+n+"','"+p+"','"+em+"')");
		}
		catch(Exception e)
		{
			javax.swing.JOptionPane.showMessageDialog(null,"Exception thrown ," + e);
		}
	}
}
