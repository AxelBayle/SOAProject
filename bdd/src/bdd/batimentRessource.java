package bdd;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("bdd")
public class batimentRessource {

	batiment bat = batiment.getInstance();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSalle(Salle newSalle) {
		bat.addSalle(newSalle);
		System.out.println("toto");
	}
	
	
	// methodes pour r�cup�rer de l'information sur le batiment
	
	@GET
	@Path("/salles")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<Integer,Salle> getSalles(){
		Map<Integer,Salle> salles = bat.getSalles();
		return salles;
	}
	
	@GET
	@Path("/salles/id")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Integer> getIDsalles(){
		return bat.getIDSalles();
	}
	
	@GET
	@Path("/salle/{idSalle}")
	@Produces(MediaType.APPLICATION_JSON)
	public Salle getSalle(@PathParam ("idSalle")int  id) {
		return bat.getSalle(id);
	}
	
	// methodes pour set des etat d'une salle
	
	@POST
	@Path("/salle/{idsalle}/porte/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setPorteSalle(@PathParam("idsalle")int id, @PathParam("etat")int etat) {
		bat.getSalle(id).setEtat_porte(etat);
	}
	
	@POST
	@Path("/salle/{idsalle}/fenetre/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setFenetreSalle(@PathParam("idsalle")int id, @PathParam("etat")int etat) {
		bat.getSalle(id).setEtat_porte(etat);
	}
	
	@POST
	@Path("/salle/{idsalle}/lumiere/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setLumiereSalle(@PathParam("idsalle")int id, @PathParam("etat")int etat) {
		bat.getSalle(id).setEtat_porte(etat);
	}
	
	@POST
	@Path("/salle/{idsalle}/chauffage/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setChauffageSalle(@PathParam("idsalle")int id, @PathParam("etat")int etat) {
		bat.getSalle(id).setEtat_porte(etat);
	}
	
	@POST
	@Path("/alarme/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setAlarme(@PathParam("etat")int etat) {
		bat.setEtat_alarme(etat);
	}
}
