package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.HashMap;
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
	@Path("/chargerbat")
	@Consumes(MediaType.APPLICATION_JSON)
	public void chargeBat() {
		try {
			File file = new File("Batiment.txt");
			Gson gson = new Gson();

			if (!file.exists()) {
				saveBat();
			} else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				String Sbat = ois.readObject().toString();
				batiment bat2 = gson.fromJson(Sbat, batiment.class);
				System.out.println("chargement du batiment");
				System.out.println(Sbat);
				bat.setEtat_alarme(bat2.getEtat_alarme());
				bat.setTemperature_out(bat2.getTemperature_out());
				int nbSalle =bat2.getLastID();
				for (int i=0; i<nbSalle;i++) {
					if (nbSalle!=0) {
						Salle newsalle = new Salle();
						System.out.println(i);
						newsalle.setEtat_chauffage(bat2.getSalle(i+1).getEtat_chauffage());
						newsalle.setEtat_fenetre(bat2.getSalle(i+1).getEtat_fenetre());
						newsalle.setEtat_lumiere(bat2.getSalle(i+1).getEtat_lumiere());
						newsalle.setEtat_porte(bat2.getSalle(i+1).getEtat_porte());
						newsalle.setTemperature_in(bat2.getSalle(i+1).getTemperature_in());
						bat.addSalle(newsalle);
						System.out.println(newsalle);
					}
				}
				ois.close();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void saveBat() {
		/*try {
			String Sbat = new Gson().toJson(bat, JsonObject.class);
			File file = new File("Batiment.txt");
	         FileOutputStream fos;
	         fos = new FileOutputStream(file);
	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(Sbat);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }	*/
		System.out.println("enregistrement");
		try {
			Gson gson = new Gson();
			String Sbat = gson.toJson(bat);
			System.out.println(Sbat);
			File file = new File("Batiment.txt");
	         FileOutputStream fos;
	         fos = new FileOutputStream(file);
	         ObjectOutputStream oos = new ObjectOutputStream(fos);		
	         oos.writeObject(Sbat);
	         oos.close();
	      } catch (FileNotFoundException e) {
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }	
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSalle(String STRSalle) {
		Gson gson=new Gson();
		Type myType= new TypeToken<Salle>(){}.getType();
		Salle newSalle = gson.fromJson(STRSalle, myType); 
		bat.addSalle(newSalle);
		saveBat();
	}
	
	/*
	@POST
	@Path("/supprimer/{idsalle}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSalle(@PathParam("idSalle") int id) {
		Gson gson=new Gson();
		bat.supSalle(id);
		saveBat();
	}*/

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
		saveBat();
	}

	@POST
	@Path("/salle/{idsalle}/fenetre/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setFenetreSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_fenetre(etat);
		saveBat();
	}

	@POST
	@Path("/salle/{idsalle}/lumiere/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setLumiereSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_lumiere(etat);
		saveBat();
	}

	@POST
	@Path("/salle/{idsalle}/chauffage/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setChauffageSalle(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setEtat_chauffage(etat);
		saveBat();
	}
	
	@POST
	@Path("/salle/{idsalle}/tin/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setTemperatureIn(@PathParam("idsalle") int id, @PathParam("etat") int etat) {
		bat.getSalle(id).setTemperature_in(etat);
		saveBat();
	}
	
	@POST
	@Path("/tout/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setTemperatureOut(@PathParam("etat") int etat) {
		bat.setTemperature_out(etat);
		saveBat();
	}

	@POST
	@Path("/alarme/{etat}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void setAlarme(@PathParam("etat") int etat) {
		bat.setEtat_alarme(etat);
		saveBat();
	}

	@GET
	@Path("/alarme")
	@Produces(MediaType.APPLICATION_JSON)
	public int getAlarme() {
		return bat.getEtat_alarme();
	}
}
