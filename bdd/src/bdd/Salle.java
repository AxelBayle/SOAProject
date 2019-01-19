package bdd;

import java.io.Serializable;

public class Salle implements Serializable{
	private static final long serialVersionUID = 1L;
	private int Etat_porte;
	private int Etat_fenetre;
	private int Etat_chauffage;
	private int Etat_lumiere;
	private int Temperature_in;

	
	public int getEtat_porte() {
		return Etat_porte;
	}
	public void setEtat_porte(int etat_porte) {
		Etat_porte = etat_porte;
	}
	public int getEtat_fenetre() {
		return Etat_fenetre;
	}
	public void setEtat_fenetre(int etat_fenetre) {
		Etat_fenetre = etat_fenetre;
	}
	public int getEtat_chauffage() {
		return Etat_chauffage;
	}
	public void setEtat_chauffage(int etat_chauffage) {
		Etat_chauffage = etat_chauffage;
	}
	public int getEtat_lumiere() {
		return Etat_lumiere;
	}
	public void setEtat_lumiere(int etat_lumiere) {
		Etat_lumiere = etat_lumiere;
	}
	public int getTemperature_in() {
		return Temperature_in;
	}
	public void setTemperature_in(int temperature_in) {
		Temperature_in = temperature_in;
	}	
	
}
