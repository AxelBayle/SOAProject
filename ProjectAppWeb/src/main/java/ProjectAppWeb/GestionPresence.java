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
 * Servlet implementation class GestionPresence
 */
public class GestionPresence extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionPresence() {
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
		Client client =ClientBuilder.newClient();
		Gson gson = new Gson();
		String id = request.getParameter("idsallePresence");
		String presence = request.getParameter("Presence");
		System.out.println(id);
		if (id.equals("")) {
			request.setAttribute("presenceBat","/!\\ Entrer le numero de la salle /!\\");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else {
			Response reponse = client.target("http://localhost:8080/bdd/webapi/bdd/").request().get();
			String bat = reponse.readEntity(String.class);
			JsonObject jbatcheck = new Gson().fromJson(bat, JsonObject.class);
			String number = jbatcheck.get("lastID").toString();
			System.out.println(number);
			int testid = Integer.parseInt(id);
			if (testid>Integer.parseInt(number)) {
				request.setAttribute("presenceBat","/!\\ La salle demandé n'existe pas, il n'y a que "+ number + " salle(s) /!\\");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else {
				reponse=client.target("http://localhost:8080/GestionSalleWS/webapi/gestionsalle/presence/").path(id).path(presence).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				String state = reponse.readEntity(String.class);
				System.out.println(state);
				if (state.equals("1")) {
					request.setAttribute("presenceBat","/!\\ Effraction, alarme declenche /!\\");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else if (state.equals("2")){
					request.setAttribute("presenceBat","/!\\ Pas d'effraction, aucune action /!\\");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else if (state.equals("3")){
					request.setAttribute("presenceBat","/!\\ Il y a personne, lumiere eteinte dans la salle "+id+" /!\\");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else if (state.equals("4")){
					request.setAttribute("presenceBat","/!\\ Presence, lumiere allume dans la salle " + id +" /!\\");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
			}
		}
	}

}
