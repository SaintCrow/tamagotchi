package tamagotchi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Partie {

	
	public int nombre_de_tours;
	public Tamagotchi tama;
	public int nom_partie; // int(1-9)
	
	
	protected void apprendre() {
		//AJOUTER LE SCANNER ET HASH
	}
	
	protected void interpreter_message(String message) {
		if (tama.sommeil == false) {
			switch (message) {
				case "viande" : 
					tama.satiete += 10;
					break;
				case "legume" :
					tama.satiete +=10;
					tama.sante +=5;
					break;
				case "bonbon" :
					tama.satiete +=5;
					tama.sante -=5;
					break;
				case "aspirine" :
					tama.sante += 20;
					break;
				case "dodo" :
					tama.energie += 20;
					tama.sommeil = true;
					break;
				case "apprendre" :
					apprendre();
					break;
				case "jouer" :
					jouer();
					break;
				case "save" :
					//COMMENT SAUVEGARDER
					this.enregistrer();
				case "quit" :
					this.enregistrer();// save and quit?
					System.exit(0); // exit, everything's ok
				default :
					System.out.println("Message non valide");
					break;
			}
		}
		else {
			System.out.println(tama.surnom + " dort !");
			if (message == "eveil") {
				tama.sommeil = false;
			}
		}
	}
	
	public void jouer() {
		//SCANNER QUEL JEU ENTRE BLACKJACK ET MORPION
	}
	
	public void entree_temps(){
		float m = 0.5f; // nb de minutes à attendre
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long startTime = System.currentTimeMillis();
		try {
			while ((System.currentTimeMillis() - startTime) < m *60*1000 // on attend m minutes
			        && !in.ready()) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (in.ready()) {
			    try {
			    	String message = in.readLine();
			    	// si un message est entre
					System.out.println("You entered: " + message);
					interpreter_message(message);
					fin_de_tour();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				// si rien n'est entre
				fin_de_tour();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	protected void fin_de_tour(){
		System.out.println("fin du tour"); 
		tama.sante -= 5;
		tama.satiete -= 5;
		tama.bonheur -= 5;
		tama.energie -= 5;
		tama.education -= 1;
		
		if (tama.sante <= 0 || tama.satiete <= 0 || tama.bonheur <= 0 || tama.energie <= 0 || tama.education <= 0) {
			System.out.println(tama.surnom + " est mort :c");
			//INTERFACE GRAPHIQUE TAMA MORT
		}
		
		if (tama.sante >= 100 || tama.satiete >= 100 || tama.bonheur >= 100 || tama.energie >= 100) {
			System.out.println(tama.surnom + " va exploser psk tu lui as donné trop de truc :c");
		}
			
		
		}
	
	public static Partie charger(int nom_partie){
		Path path = Paths.get("parties_sauvegardees/"+nom_partie+".txt");
		try(BufferedReader reader = Files.newBufferedReader(path)){
			String line = reader.readLine();
			if (line != null){ // si le fichier
				String[] data = line.split("/");
				Partie p = new Partie(nom_partie, data[0]);
				p.nombre_de_tours = Integer.parseInt(data[6]); // nb de tours
				p.tama = new Tamagotchi(data[0],Integer.parseInt(data[1]), // lecture des attributs du tamagotchi
						Integer.parseInt(data[2]),Integer.parseInt(data[3]),
						Integer.parseInt(data[4]),Integer.parseInt(data[5]));
				return p;
			}else{
				System.out.println("fichier vide!!");
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		return null; // erreur si on arrive ici
	}
	
	
	public void enregistrer() {
		// ouverture ou creation du fichier
		Path logFile = Paths.get("parties_sauvegardees/"+this.nom_partie+".txt");
		try { 
			Files.createFile(logFile);// si le fichier n'existe pas on le cree
		}catch(IOException e ) {
			System.out.println("Ecrasement du fichier...");
			//e.printStackTrace();
		}
		try(BufferedWriter writer = Files.newBufferedWriter(logFile,
				StandardCharsets.UTF_8 , StandardOpenOption.WRITE)){
			writer.write(this.tama.surnom + "/" + this.tama.bonheur + "/"+
					this.tama.education + "/" + this.tama.energie  + "/" +
					this.tama.sante + "/"+ this.tama.satiete+"/"+this.nombre_de_tours);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("Sauvegarde reussie!");
	}
	
	public Partie(int numero, String surnom){
		this.tama = new Tamagotchi(surnom,50,50,100,100,100); // valeurs par defaut
		this.nombre_de_tours = 0;
		this.nom_partie = numero;
		
	}
	
	
	public static void main(String[] args) {

	}
	

}
