package triepatricia;
public abstract class Outils {

	/*FONCTIONS D'AFFICHAGE*/
	
	/*Affiche l'arbre de racine n, avec un indentation de base de 4*d tirets*/
	public static void afficher(Noeud n, int d) {
		StringBuilder s = new StringBuilder("");
		for(int i = 0; i<d; i++) s.append("___");
		s.append(toString(n));
		System.out.println(s.toString());
		d+=1;
		for(int i = TriePatricia.debutAlphabet; i<= TriePatricia.finAlphabet; i++) {
			if(n.fils[i] != null) afficher(n.fils[i], d);
		}
	}
	
	/*Affiche l'attribut valeur du noeud, et le tableau de ses noeuds fils NON VIDES*/
	public static String toString(Noeud n) {
		StringBuilder s = new StringBuilder(n.arcParent + " {");
		for(int i = TriePatricia.debutAlphabet; i<= TriePatricia.finAlphabet; i++) {
			if(n.fils[i] != null) {
				s.append(n.fils[i].arcParent);
				s.append(" ");
			}
		}
		s.append("}");
		return s.toString();
	}
}
