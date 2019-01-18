package bdd;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Path("bdd")
public class batimentRessource {

	batiment bat = batiment.getInstance();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSalle(String STRSalle) {
		Gson gson=new Gson();
		Type myType= new TypeToken<Salle>(){}.getType();
		Salle newSalle = gson.fromJson(STRSalle, myType); 
		bat.addSalle(newSalle);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getBat() {
		Gson gson = new Gson();
		return gson.toJson(bat);
	}

	// methodes pour récupérer de l'information sur le batiment

	@GET
	@Path("/salles")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer, Salle> getSalles() {
		Map<Integer, Salle> salles = bat.getSalles();
		return salles;
	}

	@GET
	@Path("/salles/id")
	@Produces(MediaType.APPLICATION_JSON)
	public String getsallesID() {
		// JsonObject sallesjson ;
		// sallesjson.put("IDS", bat.getIDSalles());
		Gson gson = new Gson();
		return gson.toJson(bat.getIDSalles());

	}

	@GET
	@Path("/salle/{idSalle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Salle getSalle(@PathParam("idSalle") int id) {
		return bat.getSalle(id);
	}

	// methodes pour set des etat d'une salle

	@POST
	@Path("/salle/{idsalle}/porte/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setPorteSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_porte(etat);
	}

	@POST
	@Path("/salle/{idsalle}/fenetre/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setFenetreSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_fenetre(etat);
	}

	@POST
	@Path("/salle/{idsalle}/lumiere/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setLumiereSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_lumiere(etat);
	}

	@POST
	@Path("/salle/{idsalle}/chauffage/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setChauffageSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_chauffage(etat);
	}

	@POST
	@Path("/alarme/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setAlarme(@PathParam("etat") int etat) {
		bat.setEtat_alarme(etat);
	}

	@GET
	@Path("/alarme")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAlarme() {
		return bat.getEtat_alarme();
	}
}
