package triepatricia;

public class TriePatricia {
	
	public static char charFin = (char) 1;
	public static int debutAlphabet = 0;
	public static int finAlphabet = 127;
	public static int tailleAlphabet = finAlphabet - debutAlphabet +1;
	
	private Noeud racine;
	
	public TriePatricia() {
		racine = new Noeud();
	}
	
	public Noeud getRacine() {
		return racine;
	}

	public void ajout(String mot) {
		StringBuilder sb = new StringBuilder(mot);
		Noeud noeudActuel = racine;

		while (true) {
			
			/*Cas ou le noeud actuel n'a pas de fils possédant un préfixe commun avec le mot a ajouter*/
			if (noeudActuel.getFils(mot.charAt(0)) == null) {
				
				Noeud n = new Noeud();
				n.setArcParent(sb.append(TriePatricia.charFin)); //on ajoute le charFin a la fin du mot a inserer
				noeudActuel.setFils(mot.charAt(0), n );
				break;
			}
			
		}
	}

}
