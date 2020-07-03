package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class servletTest
 */
@WebServlet("/servletTest")
public class servletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		HttpSession sess = request.getSession();
		if(sess.getValue("cpt") == null) sess.setAttribute("cpt", new Integer(0));
		else {
			
			Integer s = (Integer)sess.getAttribute("cpt");
			s++; 
			sess.setAttribute("cpt", new Integer(s));
		}
		
	/*	if(request.getParameter("mess") != null )
		{
			if(!request.getParameter("mess").equals("")){
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>server</title></head>");
			out.println("<body>");
			out.println("<br> "+request.getParameter("mess"));
			out.println("</body>");
			out.println("</html>");
			}
		}
		else{*/
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><title>server</title></head>");
		out.println("<body>");
		out.println("<br> bonjour "+ (Integer)sess.getAttribute("cpt"));
		out.println("</body>");
		out.println("</html>");
	//	}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
