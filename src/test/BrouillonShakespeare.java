package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
			System.out.println("nombre de mots dans ["+file.getName()+"] : "+p.comptageMots());      
			
			
			
			
			
			
			
			
			
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
