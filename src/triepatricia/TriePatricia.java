package triepatricia;

public class TriePatricia {
	
	private Noeud racine;
	
	public TriePatricia() {
		racine = new Noeud();
	}
	
	public Noeud getRacine() {
		return racine;
	}

	public void ajout(String mot) {
		
		int i = 0;
		Noeud noeudActuel = racine;

		while (true) {
			
			/*Cas ou le noeud actuel n'a pas de fils possédant un préfixe commun avec le mot a ajouter*/
			if (noeudActuel.getFils(mot.charAt(0)) == null) {
				noeudActuel.ajoutFils(mot, true);
				break;
			}
			
		}
	}

}
