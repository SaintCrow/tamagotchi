package tamagotchi;

import java.util.List;

public class Tamagotchi {
	
	public int bonheur = 50;
	public int education = 50;
	public int energie = 100;
	public int sante = 100;
	public int satiete = 100;
	
	public String surnom;
	
	public List<String> mots_appris;
	public boolean sommeil;
	
	public Tamagotchi(String surnom, int bonheur, int education, int energie, int sante, int satiete){
		this.surnom = surnom;
		this.bonheur = bonheur;
		this.education = education;
		this.energie = energie;
		this.sante = sante;
		this.satiete = satiete;
		// initialiser la liste des mots connus?
	}
	
	
}
