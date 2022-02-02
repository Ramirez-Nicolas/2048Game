import java.util.Scanner;
public class Interface {
	
	public static String TEXT_MENU = "saisir votre choix :\n1- Jouer\n2- Quitter";
	public static boolean fin = false;

	public static void menu() {		
		int choixMenu;
		do {
			do {
				choixMenu = Methodes.saisieIntForce();
			}while(choixMenu < 1 || choixMenu > 2);
			switch (choixMenu) {
			case 1:
				Methodes.score = 0;
				Interface2048();
				break;
			}
		}while(choixMenu != 2);
	}

	public static void Interface2048(){
		int carre = 4;
		int[][] tab = new int[carre][carre];
		fin = false;
	    Methodes.createTable(tab);
	    Methodes.positionAleatoire(tab);
	    while(fin == false){
	    	Methodes.positionAleatoire(tab);
	        Methodes.afficherTable(tab);
	        Methodes.deplacement(Methodes.afficherReponse(), tab);
	        if (Methodes.voirSiCaseVideBool(tab)) {
	        	if (Methodes.finDuJeu(tab)) {
	        		fin = true;
	        	}
	        }
	    }
		System.out.println("Fin de la partie");
	    if (!Methodes.aGagner(tab))
	    	System.out.println("Vous avez perdu");
	    else
	    	System.out.println("Vous avez gagné");
	}
}