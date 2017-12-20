package test;

import triepatricia.*;

public class Brouillon {

	public static void main(String[] args) {
		
		TriePatricia p = new TriePatricia();
		
		/*TEST1*/
		/*p.ajout("test");
		p.ajout("bonjour");

		
		System.out.println("\najout bonjourjour");
		p.ajout("bonjourjour");
		System.out.println("ajout bon");
		p.ajout("bon");
		System.out.println("\najout bonsoir");
		p.ajout("bonsoir");
		System.out.println("\najout bonjourno");
		p.ajout("bonjourno");*/
		
		/*TEST2*/
		String s = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, "
				+ "un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches "
				+ "du clavier de la machine a ecrire ?";
		
		String[] tab = s.split("[,? ]");
		for(String str : tab) p.ajout(str);
		
		System.out.println("\n");
		Outils.afficher(p.racine, 0);
	}

}
