package test;

import triepatricia.*;

public class Brouillon {

	public static void main(String[] args) {
		TriePatricia p = new TriePatricia();
		p.ajout("babar");
		
		TrieOutils.afficher(p.getRacine(), 0);
	}

}
