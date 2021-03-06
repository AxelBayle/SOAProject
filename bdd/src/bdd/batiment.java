package bdd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.Serializable;

public class batiment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int Etat_alarme;
	private int Temperature_out;
	private Map<Integer, Salle> map_salle;
	private int lastID;

	private static final batiment instance = new batiment();

	private batiment() {
		Map<Integer, Salle> new_map = new HashMap<Integer, Salle>();
		map_salle = new_map;
		lastID = 0;
		Etat_alarme = 0;
	}

	public static final batiment getInstance() {
		return instance;
	}

	public int getEtat_alarme() {
		return Etat_alarme;
	}

	public void setEtat_alarme(int etat_alarme) {
		Etat_alarme = etat_alarme;
	}

	public int getTemperature_out() {
		return Temperature_out;
	}

	public void setTemperature_out(int temperature_out) {
		Temperature_out = temperature_out;
	}
	
	public void setLastID(int LastId) {
		lastID = LastId;
	}
	
	public int getLastID() {
		return lastID;
	}
	
	public Salle getSalle(int x) {
		Salle salle = map_salle.get(x);
		return salle;
	}

	public Map<Integer, Salle> getSalles() {
		return map_salle;
	}

	public void addSalle(Salle salle) {
		lastID += 1;
		map_salle.put(lastID, salle);
	}
	
	/*
	public void supSalle(int ID) {
		lastID -= 1;
		map_salle.remove(ID);
	}*/

	public int getNbSalles() {
		return map_salle.size();
	}

	public List<Integer> getIDSalles() {

		List<Integer> mainList = new ArrayList<Integer>();
		Set<Integer> set = map_salle.keySet();
		mainList.addAll(set);
		return mainList;

	}

}
