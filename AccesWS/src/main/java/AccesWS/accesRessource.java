package AccesWS;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("acces")
public class accesRessource {

	@POST
	@Path("/unlock")
	public void unlock() {
		Client client= ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/PortesWS/webapi/portes/1").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		response=client.target("http://localhost:8080/AlarmeWS/webapi/alarme/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		System.out.println("Deverouillage du batiment");
	}
	
	
	@POST
	@Path("/lock")
	public void lock() {
		Client client= ClientBuilder.newClient();
		Response response=client.target("http://localhost:8080/PortesWS/webapi/portes/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		response=client.target("http://localhost:8080/FenetresWS/webapi/fenetres/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		response=client.target("http://localhost:8080/LumieresWS/webapi/lumieres/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		response=client.target("http://localhost:8080/AlarmeWS/webapi/alarme/1").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		response=client.target("http://localhost:8080/ChauffageWS/webapi/chauffages/0").request().post(Entity.entity("", MediaType.APPLICATION_JSON));
		System.out.println("/!\\ Verouillage du batiment /!\\");
	}
	
	

	
}
