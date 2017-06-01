import java.util.ArrayList;
import java.util.Scanner;

public class Personnage {
	//Le personnage
	private String nom;
	private int[] niveau; //tableau à 2 cases
	//Le combat du perso
	private Etat etat;
	private Arme armeActuelle;
	private int[] carac; //tableau à 8 cases
	private ArrayList<Arme> invArme;
	private ArrayList<Compétence> compétences;
	
	//Pour les tests
	public Personnage(String nom){
		int[] base = {30, 3, 3, 3, 3, 3, 0, 10};
		this.carac = base;
		this.nom = nom;
		this.etat = Etat.NORMAL;
		this.invArme = new ArrayList<Arme>();
		this.armeActuelle = null;
		this.compétences = new ArrayList<Compétence>();
		//Le niveau et l'exp plafond pour le level up
		this.niveau = new int[2];
		this.niveau[0] = 1;
		this.niveau[1] = 100;
	}
	
	public Personnage(){
		this.carac = new int[8];
		créationPerso();
		this.etat = Etat.NORMAL;
		this.invArme = new ArrayList<Arme>();
		this.armeActuelle = null;
		this.compétences = new ArrayList<Compétence>();
		//Le niveau et l'exp plafond pour le level up
		this.niveau = new int[2];
		this.niveau[0] = 1;
		this.niveau[1] = 100;
	}

	public void créationPerso(){
		int[] base = {30, 3, 3, 3, 3, 3, 0, 10};
		this.carac = base;
		int points = 5;
		//Demande de nom pour le personnage
		System.out.println("=======================================================================================================================================================================================================================================================");
		System.out.println("Choisissez un nom pour votre combattant:");
		Scanner input = new Scanner(System.in);
		String nom = input.nextLine();
		//Création du personnage
		this.nom = nom;
		//Explication des caractéristiques
		System.out.println("=======================================================================================================================================================================================================================================================");
		System.out.println("Vous allez maintenant répartir 5 points de caractéristiques bonus pour votre personnage. Chaque personnage possède 8 caractéristiques qui le définissent.");
		System.out.println("La CONSTITUTION est l'endurance de votre combattant, ses points de vie. La FORCE influe sur les dégats de vos coups. Les esquives sont dépendantes de l'AGILITE, qui permets d'esquiver certaines attaques.");
		System.out.println("La PRECISION permets d'augmenter les chances de toucher son adversaire. La VITESSE permets d'attaquer avant son adversaire, ou même plus souvent que lui. Les coups critiques sont basés sur la CHANCE, qui influe aussi sur d'autres effets aléatoires.");
		System.out.println("La RESISTANCE est la caractéristique qui définit l'armure du personnage, sa réduction de dégats aux attaques ennemies.Enfin, le MORAL affecte le comportement de votre combattant, et influe sur les autres caractéristiques.");
		System.out.println("Vous ne pouvez pas utiliser vos points de caractéristiques dans la RESISTANCE ou le MORAL.");
		System.out.println("=======================================================================================================================================================================================================================================================");
		System.out.println("Les caractéristiques de base de votre personnage sont:");
		//Demande des choix de répartition de caractéristiques bonus
		while(points > 0){
			this.afficheCarac();
			System.out.println("=======================================================================================================================================================================================================================================================");
			System.out.println("Entrez le numéro de la caractéristique dans laquelle vous souhaitez dépenser un point (de 0 à 5):");
			System.out.println("Points restants: " + points);
			Scanner in = new Scanner(System.in);
			int i = in.nextInt();
			this.carac[i] += 1;
			points -= 1;
		}
		System.out.println("Vos points ont été dépensés. Votre personnage a été créé avec succès.");
	}
	
	public void combat(Personnage p1, Personnage p2){
		System.out.println("=======================================================================================================================================================================================================================================================");
		System.out.println("C'est un duel à mort entre " + p1.nom + " et " + p2.nom + " ! Tous les coups sont permis ! FIGHT !!");
		while(p1.carac[0] > 0 && p2.carac[0] > 0){
			if(p1.carac[0] > 0){
				p1.joueTour(p2);
				this.afficheStatut(p1, p2);
			}
			if(p2.carac[0] > 0){
				p2.joueTour(p1);
				this.afficheStatut(p1, p2);
			}		
		}
	}
	
	public void joueTour(Personnage cible){
		this.attaque(cible);
	}
	
	public void attaque(Personnage cible){
		//initialisation des dégats
		int dégats = 0;
		//attaque
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		if(this.armeActuelle == null){
			//attaque sans arme
			dégats = this.carac[1] + ((int)(Math.random() * 4));
			System.out.println(this.nom + " attaque " + cible.nom + " à mains nues et lui inflige " + dégats + " points de dégats !");
		} else {
			//attaque avec arme
			dégats = armeActuelle.getDégats()[0] + ((int)(Math.random() * (armeActuelle.getDégats()[1]+1)));
			System.out.println(this.nom + " attaque " + cible.nom + " avec " + armeActuelle.getNom() + " et lui inflige " + dégats + " points de dégats !");
			this.armeActuelle.enlèveDura(1);
			//l'arme se casse si sa durabilité atteind 0
			if(armeActuelle.getDura() <= 0){
				System.out.println("L'arme tenue par " + nom + " se brise !");
				armeActuelle = null;
			}
		}
		cible.carac[0] -= dégats;
	}
	
	public void afficheCarac(){
		System.out.println("=======================================================================================================================================================================================================================================================");
		System.out.println("CONSTITUTION:  " + this.carac[0]);
		System.out.println("FORCE:         " + this.carac[1]);
		System.out.println("AGILITE:       " + this.carac[2]);
		System.out.println("PRECISION:     " + this.carac[3]);
		System.out.println("VITESSE:       " + this.carac[4]);
		System.out.println("CHANCE:        " + this.carac[5]);
		System.out.println("RESISTANCE:    " + this.carac[6]);
		System.out.println("MORAL:         " + this.carac[7]);
	}
	
	public void afficheStatut(Personnage perso1, Personnage perso2){
		System.out.println("=======================================================================================================================================================================================================================================================");
		System.out.println(perso1.nom + "                                                                         " + perso2.nom);
		System.out.println("POINTS DE VIE RESTANTS: " + perso1.carac[0] + "                                                    POINTS DE VIE RESTANTS: " + perso2.carac[0]);
		System.out.println("ETAT: " + perso1.etat + "                                                                  ETAT: " + perso2.etat);
		if(perso1.armeActuelle != null){
			System.out.print("ARME TENUE: " + perso1.armeActuelle.getNom());
		} else {
			System.out.print("ARME TENUE: aucune  ");
		}
		if(perso2.armeActuelle != null){
			System.out.println("                                                          ARME TENUE: " + perso2.armeActuelle.getNom());
		} else {
			System.out.println("                                                          ARME TENUE: aucune");
		}
	}
	
	public void équipeArme(Arme arme){
		this.armeActuelle = arme;
	}
}
