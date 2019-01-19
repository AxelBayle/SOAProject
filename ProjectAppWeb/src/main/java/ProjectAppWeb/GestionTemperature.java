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
 * Servlet implementation class GestionTemperature
 */
public class GestionTemperature extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GestionTemperature() {
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
		String id = request.getParameter("idsalleChauffage");
		String TIn = request.getParameter("TempeIn");
		String TOut = request.getParameter("TempeOut");
		System.out.println(id);
		if (id.equals("")) {
			request.setAttribute("temperatureBat","/!\\ Entrer le numero de la salle /!\\");
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
				request.setAttribute("temperatureBat","/!\\ La salle demandé n'existe pas, il n'y a que "+ number + " salle(s) /!\\");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else {
				if(TIn.equals("")||TOut.equals("")) {
					request.setAttribute("temperatureBat","/!\\ Entrer les temperatures /!\\");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				else {
					reponse=client.target("http://localhost:8080/GestionSalleWS/webapi/gestionsalle/temperature/").path(id).path(TOut).path(TIn).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
					String state = reponse.readEntity(String.class);
					
					if (state.equals("1")) { //batiment verouille
						request.setAttribute("temperatureBat","/!\\ Batiment verouiller, pas d'ouverture ni de chauffage /!\\");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
					else {
						 if (state.equals("2")) {
							request.setAttribute("temperatureBat","/!\\ T°-Out > T°-In, Chauffage eteint et fenetre ouverte dans la salle "+ id +" /!\\");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
						else if( state.equals("3")){
							request.setAttribute("temperatureBat","/!\\ T°-Out < T°-In, Chauffage allume et fenetre fermee dans la salle "+ id +" /!\\");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
						else if(state.equals("4")){
							request.setAttribute("temperatureBat","/!\\ T°-Out < T°-In, Chauffage eteint et fenetre fermee dans la salle "+ id +" /!\\");
							request.getRequestDispatcher("index.jsp").forward(request, response);
						}
					}
				}
			}
		}
	}
}
