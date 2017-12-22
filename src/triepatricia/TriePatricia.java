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
		
		if(sb.length() == 0) {
			System.out.println("mot a inserer vide !");
			return; //cas mot vide
		}
		
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
				return noeudActuel;
			}
	
	
			/*CAS RECURSIF : le mot et le noeud actuel ont un prefixe en commun
			 * on cree deux nouveaux noeuds contenant l'un le suffixe du mot a inserer,
			 * l'autre le suffixe du noeud actuel et le tableau des fils du noeudActuel.
			 * Puis on transforme le noeud actuel en noeud prefixe, en lui donnant le prefixe et un tableau de fils vide.
			 * Enfin on ajoute au tableau des fils du noeud prefixe les deux nouveaux noeuds suffixes crees.
			 */
			else if(i < noeudActuel.arcParent.length()) {
	
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
			 * On ajoute recursivement le suffixe du mot dans la case correpondante du tableau des fils du noeudActuel
			 */
			else if(i == noeudActuel.arcParent.length()) {
				
				sb.delete(0, i); //on supprime ce prefixe du mot a inserer
				noeudActuel.fils[sb.charAt(0)] = ajoutRecursif(sb, noeudActuel.fils[sb.charAt(0)]);
				return noeudActuel;
			}
			
			/*CAS OUBLIE ? erreur*/
			else {
				return new Noeud(new StringBuilder("ERREUR"), new Noeud[tailleAlphabet]);
			}
		}
	}
	
	
	public boolean recherche(String mot) {
		StringBuilder sb = new StringBuilder(mot);
		
		if(sb.length() == 0) {
			System.out.println("mot a rechercher vide !");
			return false; //cas mot vide
		}

		return rechercheRecursive(sb, racine.fils[sb.charAt(0)]);
	}
	
	private boolean rechercheRecursive(StringBuilder sb, Noeud noeudActuel) {
		/*CAS TERMINAL : Le noeud actuel n'existe pas. aucune lettre du mot n'a ete trouvee*/
		if(noeudActuel == null) {
			return false;
		}
		
		/*On compare les lettres du mot recherche a l'etiquette du noeud actuel*/
		int i = 0;
		while(i<sb.length() && i<noeudActuel.arcParent.length() && noeudActuel.arcParent.charAt(i)==sb.charAt(i)) {
			i++;
		}
		
		/*toutes les lettres du mot ont ete trouvees*/
		if(i == sb.length()) {
			
			/*CAS TERMINAl : le mot appartient au trie :
			 * - le noeudActuel a la meme taille que le mot recherche, et a un noeud fils "fin de mot"
			 * OU
			 * - le noeudActuel a la taille du mot recherche +1 char, et son dernier char est le char de fin.
			 */
			if((noeudActuel.arcParent.length() == sb.length() && noeudActuel.fils[charFin] != null) ||
					(noeudActuel.arcParent.length() == sb.length()+1 && noeudActuel.arcParent.charAt(sb.length()) == charFin)) {
				return true;
			}
			
			/*CAS TERMINAL : le mot n'appartient pas au trie :
			 * - le noeudActuel n'est pas une feuille
			 * OU
			 * - le noeudActuel a pour prefixe le mot recherche mais n'est pas ce mot
			 */
			else {
				return false;
			}
		}
		
		/*CAS RECURSIF : toutes les lettres n'ont pas ete trouvees.
		 * On recherche recursivement le reste du mot dans la case correspondante du tableau des fils du noeudActuel
		 */
		else{
			sb.delete(0, i); //on retire les lettres communes entre le mot recherche et le noeud actuel
			return rechercheRecursive(sb, noeudActuel.fils[sb.charAt(0)]);
		}
		
	}
	
	
	public boolean suppression(String mot) {
		StringBuilder sb = new StringBuilder(mot);
		
		if(sb.length() == 0) {
			System.out.println("mot a supprimer vide !");
			return false; //cas mot vide
		}
		
		return suppressionRecursive(sb,racine.fils[sb.charAt(0)] , racine);
	}
	
	private boolean suppressionRecursive(StringBuilder sb, Noeud noeudActuel, Noeud noeudParent) {
		/*CAS TERMINAL : ERREUR ! le mot n'est pas dans le trie*/
		if(noeudActuel == null) {
			return false;
		}
		
		/*On compare les lettres du mot recherche a l'etiquette du noeud actuel*/
		int i = 0;
		while(i<sb.length() && i<noeudActuel.arcParent.length() && noeudActuel.arcParent.charAt(i)==sb.charAt(i)) {
			i++;
		}
		
		/*toutes les lettres du mot ont ete trouvees*/
		if(i == sb.length()) {
			
			/*CAS TERMINAL : le noeudActuel a la meme taille que le mot recherche, et a un noeud fils "fin de mot": 
			 * on supprime son noeud fils "fin de mot".
			 * Puis, s'il ne reste qu'un seul fils au noeudActuel, on les fusionne.
			 */
			if(noeudActuel.arcParent.length() == sb.length() && noeudActuel.fils[charFin] != null) {
				noeudActuel.fils[TriePatricia.charFin] = null;
				noeudActuel = fusionParentFilsUnique(noeudActuel);
				return true;
			}
			
			/*CAS TERMINAL : le noeudActuel a la taille du mot recherche +1 char, et son dernier char est le char de fin:
			 * on supprime le noeudActuel.
			 * Puis, s'il ne reste qu'un seul fils au noeudparent, on les fusionne.
			 */
			else if(noeudActuel.arcParent.length() == sb.length()+1 && noeudActuel.arcParent.charAt(sb.length()) == charFin) {
				noeudParent.fils[noeudActuel.arcParent.charAt(0)] = null;
				noeudParent = fusionParentFilsUnique(noeudParent);
				return true;
			}
			
			/*CAS TERMINAL : le mot n'appartient pas au trie : on ne peut pas le supprimer.*/
			else {
				return false;
			}
		}
		
		/*CAS RECURSIF : toutes les lettres n'ont pas ete trouvees.
		 * On recherche recursivement le reste du mot a supprimer dans la case correspondante du tableau des fils du noeudActuel
		 */
		else {
			sb.delete(0, i); //on retire les lettres communes entre le mot recherche et le noeud actuel
			return suppressionRecursive(sb, noeudActuel.fils[sb.charAt(0)], noeudActuel);
		}
	}
	
	/*verifie le nombre de fils du noeudParent. S'il n'a qu'un seul fils, les fusionne de la sorte :
	 * Concatene le suffixe du noeudFils au mot du parent.
	 * remplace le tableau des fils du noeudParent par celui du noeudFils.
	 */
	private Noeud fusionParentFilsUnique(Noeud noeudParent) {
		/*exception : le noeudParent est la racine : Pas de fusion avec un unique fils !
		 *la racine n'aura jamais de préfixe commun pour laisser accessibles les autres lettres de l'alphabet*/
		if(noeudParent.arcParent == null) {
			return noeudParent;
		}
		
		/*on compte le nombre de fils du noeud parent*/
		int enfantsRestants = 0;
		for(Noeud node : noeudParent.fils ) {
			if (node != null) {
				enfantsRestants ++;
			}
		}
		
		/*s'il ne reste qu'un fils, on fusionne parent et fils*/
		if(enfantsRestants == 1) {
			Noeud enfantUnique = null;;
			for(Noeud node : noeudParent.fils ) {
				if (node != null) {
					enfantUnique = node;
				}
			}
			noeudParent.arcParent.append(enfantUnique.arcParent);
			noeudParent.fils = enfantUnique.fils;
		}
		
		return noeudParent;
	}
	
	
	public int comptageMots() {
		int nbMots = 0;
		
		/*on boucle sur tout le tableau de noeuds fils de la racine*/
		for (Noeud n : racine.fils) {
			nbMots += comptageMotsRecursif(n, "");
		}
		return nbMots;
	}
	
	public int comptageMotsRecursif(Noeud noeudActuel, String sb) {
		
		/*CAS TERMINAL : noeud non existant*/
		if(noeudActuel == null) {
			return 0;
		}
		
		int nbMotsNoeudActuel = 0;
		sb += noeudActuel.arcParent;
		
		/*CAS TERMINAL : le noeud actuel marque une fin de mot.
		 * Ce cas comprend les noeuds dont la fin de mot est a la fin d'un suffixe unique,
		 * et les noeuds "marque de fin de mot" qui sont fils de mots prefixes.*/
		if(noeudActuel.arcParent.charAt(noeudActuel.arcParent.length()-1) == charFin) {
			nbMotsNoeudActuel ++;
		}
		
		/*CAS RECURSIF : on boucle sur le tableau de fils du noeud actuel*/
		for (Noeud n : noeudActuel.fils) {
			nbMotsNoeudActuel += comptageMotsRecursif(n, sb);
		}
		
		return nbMotsNoeudActuel;
	}
}
