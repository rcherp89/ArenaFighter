
public class Compétence {
	private String nom;
	private String description;
	private int[] dégats; //tableau de 2 valeurs qui encadrent les dégats min et max
	private int[] soin;
	private Etat etat;
	
	public Compétence(String nom, String description, int dégats, int bonus, int soin, int bonusSoin, Etat etat){
		this.nom = nom;
		this.description = description;
		this.dégats = new int[2];
		this.dégats[0] = dégats;
		this.dégats[1] = bonus;
		this.soin = new int[2];
		this.soin[0] = soin;
		this.soin[1] = bonusSoin;
		this.etat = etat;
	}
}
