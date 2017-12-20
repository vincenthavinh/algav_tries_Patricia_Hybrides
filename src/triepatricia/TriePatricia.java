package triepatricia;

public class TriePatricia {
	
	public static final char charFin = (char) 1;
	public static final int debutAlphabet = 0;
	public static final int finAlphabet = 127;
	public static final int tailleAlphabet = finAlphabet - debutAlphabet +1;
	
	public Noeud racine;
	
	public TriePatricia() {
		racine = new Noeud();
	}

	public void ajout(String mot) {
		StringBuilder sb = new StringBuilder(mot);
		sb.append(charFin); //on ajoute le charFin a la fin du mot a inserer
		
		racine.fils[sb.charAt(0)] = ajoutRecursif(sb, racine.fils[sb.charAt(0)]);
	}
	
	private Noeud ajoutRecursif(StringBuilder sb, Noeud noeudActuel) {
		
		
		/*CAS TERMINAL : le noeud dans lequel inserer la clef n'existe pas*/
		if (noeudActuel == null) {

			Noeud n = new Noeud(sb, new Noeud[tailleAlphabet]);
			return n;
		}
		
		else {
		
			/*On compare les lettres du mot a inserer a l'etiquette du noeud actuel*/
			int i = 0;
			while(i<sb.length() && i<noeudActuel.arcParent.length() && noeudActuel.arcParent.charAt(i)==sb.charAt(i)) {
				i++;
			}
			
			
			/*CAS TERMINAL : le mot est deja contenu dans le trie*/
			if(i == sb.length()) {
				/*NE RIEN FAIRE*/
				System.out.println("mot "+sb+" deja dedans");
				return noeudActuel;
			}
	
	
			/*CAS RECURSIF : le mot et le noeud actuel ont un prefixe en commun
			 * on cree deux nouveaux noeuds contenant l'un le suffix du mot a inserer,
			 * l'autre le suffixe du noeud actuel et le tableau des fils du noeudActuel.
			 * Puis on transforme le noeud actuel en noeud prefixe, en lui donnant le prefixe et un tableau de fils vide.
			 * Enfin on ajoute au tableau des fils du noeud prefixe les deux nouveaux noeuds suffixes crees.*/
			if(i < sb.length() && i < noeudActuel.arcParent.length()) {
	
				/*creation du noeud suffixe de noeudActuel*/
				StringBuilder suffixeNoeudAc = new StringBuilder();
				suffixeNoeudAc.append(noeudActuel.arcParent.subSequence(i, noeudActuel.arcParent.length()));
				
				Noeud noeudSuffActuel = ajoutRecursif(suffixeNoeudAc, null);
				noeudSuffActuel.fils = noeudActuel.fils; //Passage du tableau de fils du noeudActuel
				
				/*creation du noeud suffixe du mot a inserer*/
				StringBuilder suffixeSB = new StringBuilder();
				suffixeSB.append(sb.subSequence(i, sb.length()));
				
				Noeud noeudSuffSB = ajoutRecursif(suffixeSB, null);
				
				/*transformation du noeudActuel en noeudPrefixe*/
				noeudActuel.arcParent.delete(i, noeudActuel.arcParent.length()); //suppression du suffixe du noeudActuel
				noeudActuel.fils = new Noeud[tailleAlphabet]; //suppression de l'ancien tableau de fils
				
				noeudActuel.fils[suffixeSB.charAt(0)] = noeudSuffSB;
				noeudActuel.fils[suffixeNoeudAc.charAt(0)] = noeudSuffActuel;
				
				return noeudActuel;
			}
			
			/*CAS RECURSIF : le noeud Actuel est un prefixe du mot a inserer
			 * On ajoute recursivement le suffixe du mot dans la case correpondante du tableau des fils du noeudActuel*/
			if(i < sb.length() && i == noeudActuel.arcParent.length()) {
				
				sb.delete(0, i); //on supprime ce prefixe du mot a inserer
				noeudActuel.fils[sb.charAt(0)] = ajoutRecursif(sb, noeudActuel.fils[sb.charAt(0)]);
				return noeudActuel;
			}
			
			
			
			return new Noeud(new StringBuilder("ERREUR"), new Noeud[tailleAlphabet]);
		}
	}
	
	public boolean recherche(String mot) {
		StringBuilder sb = new StringBuilder(mot);

		return rechercheRecursive(sb, racine);
	}
	
	private boolean rechercheRecursive(StringBuilder sb, Noeud noeudActuel) {
		
		/*toutes les lettres du mot on ete trouvees ET le noeudActuel est bien une feuille*/
		if(noeudActuel.estFeuille() && sb.length() == 0) {
			return true;
		}
		
		/*toutes les lettres du mot on ete trouvees mais le noeudActuel n'est pas une feuille*/
		if(!noeudActuel.estFeuille() && sb.length() == 0) {
			return false;
		}
	
		return false;
	}
}
