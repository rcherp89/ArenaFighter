
public class ArenaFighter {
	
	public static void main(String[] args){
		Arme lol = new Arme("Durandil", 12, 5, 1, 1, 1);
		Personnage test = new Personnage("Roger");
		Personnage test2 = new Personnage("Jean Michel");
		test.combat(test, test2);
	}
}
