package test;

import java.util.List;

import triepatricia.*;

public class Brouillon {

	public static void main(String[] args) {
		
		TriePatricia p = new TriePatricia();
		
		/*TEST1 : Ajouts simples*/
		/*System.out.println("\najout test");
		p.ajout("test");
		System.out.println("\najout bonjour");
		p.ajout("bonjour");
		System.out.println("\najout bonjourjour");
		p.ajout("bonjourjour");
		System.out.println("ajout bon");
		p.ajout("bon");
		System.out.println("\najout bonsoir");
		p.ajout("bonsoir");
		System.out.println("\najout bonjourno");
		p.ajout("bonjourno");
		
		System.out.println("\n");
		Outils.afficher(p.racine, 0);*/
		
		String s = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, "
				+ "un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches "
				+ "du clavier de la machine a ecrire ?";
		
		String[] tab = s.split("[^a-zA-Z\\d]");
		for(String str : tab) p.ajout(str);
		System.out.println("\ncontenu du Trie Patricia :");
		Outils.afficher(p.racine, 0);
		
		/*TEST2 : Recherches*/
		System.out.println("\n\nRECHERCHE");
		//for(String str : tab) if (p.recherche(str) == false) System.out.println("mot ["+str+"] non trouve");
		System.out.println("recherche mot ["+"wa"+"] : "+p.recherche("wa"));
		System.out.println("recherche mot ["+"dactylo"+"] : "+p.recherche("dactylo"));
		System.out.println("recherche mot ["+"tou"+"] : "+p.recherche("tou"));
		System.out.println("recherche mot ["+"touriste"+"] : "+p.recherche("touriste"));
		System.out.println("fin recherche mots\n\n");
		
		/*TEST3 : Suppressions*/
		System.out.println("\n\nSUPPRESSION de tous les mots");
		for(String str : tab) {
			p.suppression(str);
			//if(p.suppression(str) == false) System.out.println("mot ["+str+"] non supprime");
			//System.out.println("recherche mot ["+str+"] : "+p.recherche(str));
		}
		
		System.out.println("\ncontenu du Trie Patricia :");
		Outils.afficher(p.racine, 0);
		
		/*TEST4 : Suppression specifique pour un debbugage*/
		/*System.out.println("recherche mot ["+"de"+"] : "+p.recherche("de"));
		System.out.println("recherche mot ["+"des"+"] : "+p.recherche("des"));
		
		if(p.suppression("de") == false) System.out.println("mot ["+"de"+"] non supprime");
		
		System.out.println("\nrecherche mot ["+"de"+"] : "+p.recherche("de"));
		System.out.println("recherche mot ["+"des"+"] : "+p.recherche("des"));
		
		//Outils.afficher(p.racine, 0);
		
		if(p.suppression("de") == false) System.out.println("mot ["+"de"+"] non supprime");
		
		System.out.println("\nrecherche mot ["+"de"+"] : "+p.recherche("de"));
		System.out.println("recherche mot ["+"des"+"] : "+p.recherche("des"));*/
		
		System.out.println("\n\nReajout des mots...\n");
		for(String str : tab) p.ajout(str);
		
		/*TEST5 : comptage Mot*/
		System.out.println("\ntableau STRING.SPLIT() : nbMots dans la phrase = "+tab.length+"\n");
		System.out.println("COMPTAGEMOTS : nbMots (uniques) dans le trie patricia = "+p.comptageMots());
		System.out.println("\n(pensez a soustraire les mots vides non inseres et les mots"
				+ " doublons inseres une seule fois de nbMots dans la phrase)\n ");
		
		/*TEST6 : liste Mots*/
		System.out.println("LISTEMOTS : ");
		List<String> l = p.ListeMots();
		int nbMotsListe = 0;
		for(String str : l) {
			nbMotsListe++;
			//System.out.println(str);
		}
		System.out.println(l+"\nnb mots liste : "+nbMotsListe);
	}

}
