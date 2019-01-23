package ProjectAppWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class LoginVerify
 */
public class LoginVerify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginVerify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("UserName");
	    String pass = request.getParameter("pass");
	    Client client =ClientBuilder.newClient();
	    String firstlunch = "0";
		if(name.equals("admin")&& pass.equals("WaTree")) {
			
			
			Response reponse = client.target("http://localhost:8080/bdd/webapi/bdd/").request().get();
			String bat = reponse.readEntity(String.class);
			JsonObject jbat = new Gson().fromJson(bat, JsonObject.class);
			System.out.println(jbat.get("lastID"));
			System.out.println(firstlunch);
			if(firstlunch.equals(jbat.get("lastID").toString())) {
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/chargerbat").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				System.out.println("chargement du batiment");
			}
			response.sendRedirect("index.jsp");
		}
		else {
			request.setAttribute("connection","Identifiant et/ou mot de passe incorect(s)");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
