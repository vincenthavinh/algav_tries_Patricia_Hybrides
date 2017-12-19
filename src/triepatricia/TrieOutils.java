package triepatricia;
public abstract class TrieOutils {

	/*FONCTIONS D'AFFICHAGE*/
	
	/*Affiche l'arbre de racine n, avec un indentation de base de 2d tirets*/
	public static void afficher(Noeud n, int d) {
		StringBuilder s = new StringBuilder("");
		for(int i = 0; i<d; i++) s.append("--");
		s.append(toString(n));
		System.out.println(s.toString());
		d+=1;
		for(int i = 0; i<128; i++) {
			if(n.getFils(i) != null) afficher(n.getFils(i), d);
		}
	}
	
	/*Affiche l'attribut valeur du noeud, et le tableau de ses noeuds fils NON VIDES*/
	public static String toString(Noeud n) {
		StringBuilder s = new StringBuilder(n.getArcParent() + " {");
		for(int i=0; i<128; i++) {
			if(n.getFils(i) != null) s.append(n.getFils(i).getArcParent());
		}
		s.append("}");
		return s.toString();
	}
}
