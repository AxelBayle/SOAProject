package ProjectAppWeb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class InfoSalle
 */
public class InfoSalle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoSalle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Client client =ClientBuilder.newClient();
		Gson gson = new Gson();
		String id = request.getParameter("idsalle");
		System.out.println(id);
		if (id.equals("")) {
			request.setAttribute("ajoutNumero","/!\\ Entrer le numero de la salle /!\\");
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
				request.setAttribute("ajoutNumero","/!\\ La salle demandé n'existe pas, il n'y a que "+ number + " salle(s) /!\\");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
			else {
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/salle").path(id).request().get();
				//PrintWriter out = response.getWriter();
				
				String salle =reponse.readEntity(String.class);
				//out.println(salle);
				
				System.out.println(salle);
				JsonObject jbat = new Gson().fromJson(salle, JsonObject.class);
				request.setAttribute("porte",jbat.get("etat_porte"));
				request.setAttribute("fenetre",jbat.get("etat_fenetre"));
				request.setAttribute("chauffage",jbat.get("etat_chauffage"));
				request.setAttribute("lumiere",jbat.get("etat_lumiere"));
				request.setAttribute("temp_in",jbat.get("temperature_in"));
				request.getRequestDispatcher("index.jsp").forward(request, response);
				//request.getRequestDispatcher("index.jsp?id=Getbat").forward(request, response);
				//response.getWriter().append("Served at: ").append(request.getContextPath());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
