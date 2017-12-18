package triepatricia;

public class Noeud {

	private String valeur;
	private Boolean estFeuille;
	private Noeud[] fils;
	
	public Noeud() {
		fils = new Noeud[128];
		estFeuille = false;
	}

	public String getValeur() {
		return valeur;
	}
	
	public Noeud getFils(int i) {
		return fils[i];
	}
	
	public void ajoutFils(String valeur, Boolean motFini) {
		this.fils[valeur.charAt(0)] = new Noeud();
		this.fils[valeur.charAt(0)].valeur = valeur;
		this.estFeuille = motFini;
	}
}
