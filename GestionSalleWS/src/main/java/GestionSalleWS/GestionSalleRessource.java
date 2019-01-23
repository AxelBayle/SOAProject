package GestionSalleWS;


import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Path("gestionsalle")
public class GestionSalleRessource {
	
	@POST
	@Path("/temperature/{idsalle}/{Tout}/{Tin}")
	@Produces(MediaType.APPLICATION_JSON)
	public String gestionTemperature(@PathParam("idsalle") int id, @PathParam("Tout") int TOut, @PathParam("Tin") int TIn) {
		String state ="0";
		Client client= ClientBuilder.newClient();
		Response reponse = client.target("http://localhost:8080/bdd/webapi/bdd/").request().get();
		String bat = reponse.readEntity(String.class);
		JsonObject jbatcheck = new Gson().fromJson(bat, JsonObject.class);
		String etatalarme = jbatcheck.get("Etat_alarme").toString();
		if (etatalarme.equals("1")) {
			state ="1";
		}
		else {
			 if (27>TOut&&TOut>20) {
				reponse=client.target("http://localhost:8080/FenetresWS/webapi/fenetres/fenetre/").path(Integer.toString(id)).path("/1").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/ChauffageWS/webapi/chauffages/chauffage/").path(Integer.toString(id)).path("/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/tout/").path(Integer.toString(TOut)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/salle/").path(Integer.toString(id)).path("/tin/").path(Integer.toString(TIn)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));

				state ="2";

			}
			else if(TOut<20 && TIn<18){
				reponse=client.target("http://localhost:8080/FenetresWS/webapi/fenetres/fenetre/").path(Integer.toString(id)).path("/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/ChauffageWS/webapi/chauffages/chauffage/").path(Integer.toString(id)).path("/1").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/tout/").path(Integer.toString(TOut)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/salle/").path(Integer.toString(id)).path("/tin/").path(Integer.toString(TIn)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));

				state ="3";
			}
			else {
				reponse=client.target("http://localhost:8080/FenetresWS/webapi/fenetres/fenetre/").path(Integer.toString(id)).path("/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/ChauffageWS/webapi/chauffages/chauffage/").path(Integer.toString(id)).path("/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/tout/").path(Integer.toString(TOut)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				reponse=client.target("http://localhost:8080/bdd/webapi/bdd/salle/").path(Integer.toString(id)).path("/tin/").path(Integer.toString(TIn)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
				state ="4";
			}
		}
		return state;
	}
	
	
	@POST
	@Path("/presence/{idsalle}/{presence}")
	@Produces(MediaType.APPLICATION_JSON)
	public String gestionPresence(@PathParam("idsalle") int id, @PathParam("presence") int presence) {
		String state ="0";
		Client client= ClientBuilder.newClient();
		Response reponse = client.target("http://localhost:8080/bdd/webapi/bdd/").request().get();
		String bat = reponse.readEntity(String.class);
		JsonObject jbatcheck = new Gson().fromJson(bat, JsonObject.class);
		String etatalarme = jbatcheck.get("Etat_alarme").toString();
		if (etatalarme.equals("1")&&presence==1) {
			state = "1";
		}
		else if (etatalarme.equals("1")&&presence==0){
			state = "2";
		}
		else if (etatalarme.equals("0")&&presence==0){
			reponse=client.target("http://localhost:8080/LumieresWS/webapi/lumieres/lumiere").path(Integer.toString(id)).path("/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
			state = "3";
		}
		else if (etatalarme.equals("0")&&presence==1){
			reponse=client.target("http://localhost:8080/LumieresWS/webapi/lumieres/lumiere").path(Integer.toString(id)).path("/1").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
			state = "4";
		}
		return state;
	}

}
