package triepatricia;

public class Noeud {

	private StringBuilder arcParent;
	private Noeud[] fils;
	
	public Noeud() {
		fils = new Noeud[TriePatricia.tailleAlphabet];
	}
	
	public Boolean estFeuille() {
		return (arcParent.charAt(arcParent.length()-1) == TriePatricia.charFin ||
				fils[TriePatricia.charFin] != null);
	}
	
	public Noeud getFils(int i) {
		return fils[i];
	}
	
	public void setFils(int i, Noeud enfant) {
		fils[i] = enfant;
	}
	
	public StringBuilder getArcParent() {
		return arcParent;
	}
	
	public void setArcParent(StringBuilder arcParent) {
		this.arcParent = arcParent;
	}
}
