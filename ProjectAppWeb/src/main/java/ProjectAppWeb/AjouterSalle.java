package ProjectAppWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Form;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class AjouterSalle
 */

public class AjouterSalle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterSalle() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Client client = ClientBuilder.newClient();
		Response reponse = client.target("http://localhost:8080/bdd/webapi/bdd/").request().get();
		// PrintWriter out = response.getWriter();

		String bat = reponse.readEntity(String.class);
		// out.println(bat);

		System.out.println(bat);
		JsonObject jbat = new Gson().fromJson(bat, JsonObject.class);
		request.setAttribute("alarme", jbat.get("Etat_alarme"));
		request.setAttribute("salle", jbat.get("lastID"));
		request.getRequestDispatcher("index.jsp").forward(request, response);
		// request.getRequestDispatcher("index.jsp?id=Getbat").forward(request,
		// response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		// response.sendRedirect("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO Auto-generated method stub
		Gson gson = new Gson();
		Salle salle = new Salle();

		System.out.println("test");
		String name = request.getParameter("UserName");
		String pass = request.getParameter("pass");
		String Etat_porte = request.getParameter("Porte");
		String Etat_fenetre = request.getParameter("Fenetre");
		String Etat_chauffage = request.getParameter("Chauffage");
		String Etat_lumiere = request.getParameter("Lumiere");
		System.out.println("mdp ok");
		salle.setEtat_porte(Integer.parseInt(Etat_porte));
		salle.setEtat_fenetre(Integer.parseInt(Etat_fenetre));
		salle.setEtat_chauffage(Integer.parseInt(Etat_chauffage));
		salle.setEtat_lumiere(Integer.parseInt(Etat_lumiere));

		String Sgson = gson.toJson(salle);
		System.out.println(Sgson);

		Client client = ClientBuilder.newClient();

		Response reponse = client.target("http://localhost:8080/bdd/webapi/bdd").request()
				.post(Entity.entity(Sgson, MediaType.APPLICATION_JSON), Response.class);
		
		reponse = client.target("http://localhost:8080/bdd/webapi/bdd/").request().get();
		String bat = reponse.readEntity(String.class);
		JsonObject jbatcheck = new Gson().fromJson(bat, JsonObject.class);
		String number = jbatcheck.get("lastID").toString();
		request.setAttribute("ajoutSalle","/!\\ Ajout de la salle " + number +" /!\\");
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
}
