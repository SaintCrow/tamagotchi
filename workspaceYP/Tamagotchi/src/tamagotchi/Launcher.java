package tamagotchi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Launcher {
	
	
	
	//Afficher graphiquement le menu
	//User a le choix entre creer une partie et charger une partie
	
	
	public Partie creer_partie() {
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Numero (entre 1 et 9) de la nouvelle partie (la partie deja presente sera ecrasee) : ");
		int numero = reader.nextInt();
		
		System.out.println("Creez un surnom pour votre tamagotchi : ");
		String surnom = reader.next();
		reader.close();
				
		return new Partie(numero,surnom);
		
	}
	
	public Partie charger_partie() {

		//Si le fichier partie_sauvegardee est vide, affiche graphiquement qu'il n'y a pas de partie
		//return 0
		//Revenir ensuite au menu de départ
		
		//Si la partie existe, return 1
		//Charger la partie
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Numero de la partie que vous voulez charger : ");
		int num = reader.nextInt();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("parties_sauvegardees/"+num+".txt"));
			
		} catch (FileNotFoundException e) {
			System.out.println("Il ne peut pas y avoir plus de 9 sauvegardes !");
			return null;
		}
		
		reader.close();
		try {
			if (br.readLine() == null) {
			    System.out.println("Cette partie n'existe pas encore !");
				//Afficher graphiquement qu'il n'y a pas de partie
				return null;
			}
			else {
				return Partie.charger(num);
			}
		} catch (IOException e) {
			return null;
		}	
		
	}
	
	public static void main(String[] args) {
		
		Launcher l = new Launcher();
		// Partie p = l.creer_partie();
		// p.enregistrer();// on sauvegarde la nouvelle partie directement
		// System.out.println("fin!!");
		
		Partie test = l.charger_partie();
		System.out.println(test.tama.sante);
	}
	
}


