package server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import server.constants.ConstantsServer;
import server.database.PhpMyAdminManager;



/**
 * @author Delta Tracking (Javier Martin, Àlex Albalà i Carles Orriols)
 * 
 * Servlet implementation class AutoterminalGenericQuoteServlet
 */
@WebServlet("/AutoterminalGenericQuoteServlet")
public class AutoterminalGenericQuoteServlet extends GenericServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see GenericServlet#GenericServlet()
	 */
	public AutoterminalGenericQuoteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 * 
	 */
	
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		
		PhpMyAdminManager manager = null;
		try {
			
			manager = new PhpMyAdminManager(ConstantsServer.URL, ConstantsServer.USER_BD, ConstantsServer.PASSWORD_BD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Map<String, String[]> parameters = request.getParameterMap();
		for (String p : parameters.keySet()) {
			System.out.println("Parametre desde app: "+p +" Dades desde app: "+ request.getParameter(p));
			String dades=manager.administra(Integer.parseInt(p), request.getParameter(p));
			response.getWriter().append(dades);

		}

		
	}

}
