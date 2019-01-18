package AlarmeWS;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("alarme")
public class alarmeRessource {

	
	@POST
	@Path("/{etat}")
	public void setAlarme(@PathParam("etat")int etat) {
		Client client= ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/bdd/webapi/bdd/alarme/").path(Integer.toString(etat)).request().post(Entity.entity("", MediaType.APPLICATION_JSON));
	}
}
