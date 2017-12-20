package triepatricia;

public class Noeud {

	public StringBuilder arcParent;
	public Noeud[] fils;
	
	public Noeud() {
		fils = new Noeud[TriePatricia.tailleAlphabet];
	}
	
	public Noeud(StringBuilder str, Noeud[] tab) {
		arcParent = str;
		fils = tab;
	}
	
	public Boolean estFeuille() {
		return (arcParent.charAt(arcParent.length()-1) == TriePatricia.charFin ||
				fils[TriePatricia.charFin] != null);
	}

}
