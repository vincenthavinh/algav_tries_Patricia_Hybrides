package triepatricia;
public abstract class TrieOutils {

	/*FONCTIONS D'AJOUT / TEST DE MARQUEUR DE FIN DE MOT*/
	public static String finDeMot() {
		return Character.toString((char) 3);
	}
	
	public static Boolean estFini(Noeud n) {
		String valeur = n.getValeur();
		return (valeur.charAt(valeur.length()-1) == 0);
	}
	
	/*FONCTIONS D'AFFICHAGESS*/
	
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
		StringBuilder s = new StringBuilder(n.getValeur() + " {");
		for(int i=0; i<128; i++) {
			if(n.getFils(i) != null) s.append(n.getFils(i).getValeur());
		}
		s.append("}");
		return s.toString();
	}
}
