package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import triepatricia.TriePatricia;

public class BrouillonShakespeare {

	public static void main(String[] args) {
		try {
			File file = new File("Shakespeare/hamlet.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			
			
			
			
			
			/*Debut du test*/
			
			TriePatricia p = new TriePatricia();
			
			for(String line; (line = br.readLine()) != null; ) {
			    p.ajout(line);
			}
			System.out.println("fichier ["+file.getName()+"] charge dans le trie patricia.\n");
			
			
			/*TESTS FONCTIONS AVANCEES*/
			
			/*comptageMots : a verifier avec : sort -n Shakespeare/hamlet.txt | uniq | wc -w
			 * a la racine du projet ( 4554 pour hamlet.txt)
			 */
			System.out.println("COMPTAGEMOTS : nombre de mots dans ["+file.getName()+"] : "+p.comptageMots());  
			
			
			/*listeMots : a verifier avec : sort -n Shakespeare/hamlet.txt | uniq 
			 * a la racine du projet, en comparant des series de mots,
			 * ou a verifier avec : sort -n Shakespeare/hamlet.txt | uniq | wc -w
			 * a la racine du projet, en comparant le nombre de mots dans la liste retournee.
			 */
			List<String> l = p.ListeMots();
			int nbMotsListe = 0;
			for(String str : l) {
				nbMotsListe++;
				//System.out.println(str);
			}
			System.out.println("LISTEMOTS : nombre de mots dans la liste : "+nbMotsListe);
			System.out.println("\nVERIFICATION : nombre de mots dans Hamlet.txt: 4554 (check avec bash)\n"
					+ "pour verifier avec un autre fichier : "
					+ "-modifier le nomdu fichier dans BrouillonShakespeare.java\n"
					+ "lancer : \"sort -n Shakespeare/AUTREFICHIER.txt | uniq | wc -w\" a la racine du projet\n");
			
			
			
			
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
