package PorteWS;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;

@Path ("portes")
public class portesRessource {

	
	// gestion une seule porte 
	@POST
	@Path("/porte/{id}/{etat}")
	public void setPorte(@PathParam("etat")int etat, @PathParam("id")int id) {
		Client client= ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/bdd/webapi/bdd/salle/").path(Integer.toString(id)).path("/porte/").path(Integer.toString(etat)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));

	}
	
	//gestion toutes les portes
	@POST
	@Path("/{etat}")
	public void setPortes(@PathParam("etat")int etat) {
		Client client= ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/bdd/webapi/bdd/salles/id").request().get();
		String stliste = response.readEntity(String.class);
		Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
		List<Integer> ints2 = new Gson().fromJson(stliste,listType);
		 for (int i:ints2) {
			 setPorte(etat, i);
		 }
		
	}
	
}
