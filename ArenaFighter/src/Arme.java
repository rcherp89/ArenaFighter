
public class Arme {
	private String nom;
	private int[] dégats; //tableau de 2 valeurs, dégats[0] sont les dégats de base, dégats[1] est la valeur aléatoire de bonus
	private int précision;
	private int durabilité;
	private int portée;
	
	public Arme(String nom, int dégatsBase, int bonus, int précision, int durabilité, int portéé){
		this.nom = nom;
		this.dégats = new int[2];
		this.dégats[0] = dégatsBase;
		this.dégats[1] = bonus;
		this.précision = précision;
		this.durabilité = durabilité;
		this.portée = portée;
	}
	
	public int[] getDégats(){
		return this.dégats;
	}
	
	public void enlèveDura(int i){
		this.durabilité -= 1;
	}
	
	public int getDura(){
		return this.durabilité;
	}
	
	public String getNom(){
		return this.nom;
	}
}
