import java.util.Scanner;

public class Methodes {
    public static String TEXT_MENU = Interface.TEXT_MENU;
    public static int carre = 4;
    public static int ESPACE = 8;
    public static int TIRET = 45;
    public static int AFFICHERNOMJEU = 20;
    public static int score = 0;

    //creation de table
    public static void createTable(int[][] tab){
        for(int i = 0; i < tab.length; i++){
            for(int j = 0; j < tab.length; j++){
                tab[i][j] = 0;
            }
        }
    }
    
    //afficher le tableau et ces valeurs
    public static void afficherTable(int[][] tab){
        for(int afficherEspace = 0; afficherEspace < AFFICHERNOMJEU; afficherEspace++)
            System.out.print(" ");
        System.out.println("--------");
        for(int afficherEspace = 0; afficherEspace < AFFICHERNOMJEU; afficherEspace++)
            System.out.print(" ");
        System.out.println("| 2048 | \t   score : " + score);

        for(int i = 0; i < tab.length; i++){
            afficherTirets();
            for(int j = 0; j < tab.length; j++){  
                System.out.print(" | ");           
                if(tab[i][j] == 0){
                    for(int nbespace = 0; nbespace < ESPACE; nbespace++)
                        System.out.print(" ");
                }
                else{
                    for(int nbespace = 0; nbespace < (ESPACE - String.valueOf(tab[i][j]).length()); nbespace++)
                        System.out.print(" ");
                    System.out.print(tab[i][j]);
                }
            }
            System.out.print(" | ");
            System.out.println();
        }
        afficherTirets();
    }

    //afficher les tirets
    public static void afficherTirets(){
        System.out.print(" ");
        for(int i = 0; i < TIRET; i++)
            System.out.print("-");
        System.out.println();
    }

    //retourne un chiffre aleatoire entre 2 et 4
    public static int retourne2Ou4(){
        int nbEntre2ou4 = (int)(Math.random()*2 + 1) * 2;
        return nbEntre2ou4;
    }

    //afficher aleatoirement les positions des 2 valeurs du debut 
    public static void positionAleatoire(int[][] tab){
        int ligneAleatoire;
        int colonneAleatoire;
        do{
            ligneAleatoire = (int)(Math.random()* 4);
            colonneAleatoire = (int)(Math.random()* 4);
        }while((tab[ligneAleatoire][colonneAleatoire] != 0));
        tab[ligneAleatoire][colonneAleatoire] = retourne2Ou4();
        //tab[3][3] = 2048;
    }

    //compter le nombre de case vide
    public static int voirSiCaseVide(int[][] tab){
        int full = 0;
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if(tab[i][j] != 0)
                    full++;
            }
        }
        return full;
    }
    
    //retourne true si le tableau n'est pas vide sinon false 
    public static boolean voirSiCaseVideBool(int[][] tab){
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if(tab[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    //demande de saisir la touche pour la direction
    public static String afficherReponse(){
        Scanner sc = new Scanner(System.in);
        String direction;
        do{
            System.out.println("Entrez 'z' pour aller vers le haut");
            System.out.println("Entrez 'q' pour aller vers la gauche");
            System.out.println("Entrez 's' pour aller vers le bas");
            System.out.println("Entrez 'd' pour aller vers la droite");
            System.out.println("Entrez 'quit' pour quitter la partie");
            direction = sc.nextLine();
            direction = direction.toLowerCase();
        }while(!(direction.equals("z") || direction.equals("haut") || direction.equals("q") || direction.equals("gauche") || direction.equals("s") || direction.equals("bas") || direction.equals("d")|| direction.equals("droite") || direction.equals("quit")));
        return direction;
    }
    
    //deplacer les valeurs selon la direction tappe
    public static void deplacement(String direction, int[][] tab){
        if (direction.equals("z") || direction.equals("haut"))
            deplacementHaut(tab);
        else if (direction.equals("q") || direction.equals("gauche"))
           deplacementGauche(tab);
        else if (direction.equals("s") || direction.equals("bas"))
           deplacementBas(tab);
        else if (direction.equals("d") || direction.equals("droite"))
            deplacementDroite(tab);
        else if (direction.equals("quit")) {
        	System.out.println("Vous avez quitte(e) la partie");
        	Interface.fin = true;
        }
    }

    //deplacer toutes les valeurs vers le haut et les fusionner
    public static void deplacementHaut(int[][] tab){
        int i2;
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if (tab[i][j] != 0){
                    i2 = i;
                    while(i2 > 0 && tab[i2-1][j] == 0){
                        tab[i2-1][j] = tab[i2][j];
                        tab[i2][j] = 0;
                        i2--;    
                    }
                }
            }  
        }
        for (int i = 0; i < tab.length -1; i++){
            for (int j = 0; j < tab.length; j++){
                if(tab[i+1][j] == tab[i][j]) {
                    tab[i][j] = tab[i][j] + tab[i][j];
                    tab[i+1][j] = 0;
                    score+= tab[i][j];
                }
            }
        }
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if (tab[i][j] != 0){
                    i2 = i;
                    while(i2 > 0 && tab[i2-1][j] == 0){
                        tab[i2-1][j] = tab[i2][j];
                        tab[i2][j] = 0;
                        i2--;    
                    }
                }
            }  
        }
    }
    
    //deplacer toutes les valeurs vers la gauche et les fusionner
    public static void deplacementGauche(int[][] tab){
        int j2;
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if (tab[i][j] != 0){
                    j2 = j;
                    while(j2 > 0 && tab[i][j2-1] == 0){
                        tab[i][j2-1] = tab[i][j2];
                        tab[i][j2] = 0;
                        j2--;
                    }
                }
            }
        }
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length -1; j++){
                if(tab[i][j] == tab[i][j+1]) {
                    tab[i][j] = tab[i][j] + tab[i][j];
                    tab[i][j+1] = 0;
                    score+= tab[i][j];
                }
            }
        }
        for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
                if (tab[i][j] != 0){
                    j2 = j;
                    while(j2 > 0 && tab[i][j2-1] == 0){
                        tab[i][j2-1] = tab[i][j2];
                        tab[i][j2] = 0;
                        j2--;
                    }
                }
            }
        }
    }
    
    //deplacer toutes les valeurs vers le bas et les fusionner    
    public static void deplacementBas(int[][] tab){
        int i2;
        for (int i = tab.length - 1; i >= 0; i--){
            for (int j = 0; j < tab.length; j++){
                if (tab[i][j] != 0){
                    i2 = i;
                    while(i2 < tab.length -1 && tab[i2+1][j] == 0) {
                        tab[i2+1][j] = tab[i2][j];
                        tab[i2][j] = 0;
                        i2++;
                    }
                }
            }  
        }
        for (int i = tab.length - 1; i >= 1; i--){
            for (int j = 0; j < tab.length; j++){
                if(tab[i-1][j] == tab[i][j]) {
                    tab[i][j] = tab[i][j] + tab[i][j];
                    tab[i-1][j] = 0;
                    score+= tab[i][j];
                }
            }
        }
        for (int i = tab.length - 1; i >= 0; i--){
            for (int j = 0; j < tab.length; j++){
                if (tab[i][j] != 0){
                    i2 = i;
                    while(i2 < tab.length -1 && tab[i2+1][j] == 0) {
                        tab[i2+1][j] = tab[i2][j];
                        tab[i2][j] = 0;
                        i2++;
                    }
                }
            }  
        }
    }

    //deplacer toutes les valeurs vers la droite et les fusionner
    public static void deplacementDroite(int[][] tab){
        int j2;
        for (int i = 0; i < tab.length; i++){
            for (int j = tab.length - 1; j >= 0; j--){
                if (tab[i][j] != 0){
                    j2 = j;
                    while(j2 < tab.length - 1 && tab[i][j2+1] == 0){
                        tab[i][j2+1] = tab[i][j2];
                        tab[i][j2] = 0;
                        j2++;
                    }
                }
            }
        }
        for (int i = 0; i < tab.length; i++){
            for (int j = tab.length - 1; j >= 1; j--){
                if(tab[i][j] == tab[i][j-1]) {
                    tab[i][j] = tab[i][j] + tab[i][j];
                    tab[i][j-1] = 0;
                    score+= tab[i][j];
                }
            }
        }
        for (int i = 0; i < tab.length; i++){
            for (int j = tab.length - 1; j >= 0; j--){
                if (tab[i][j] != 0){
                    j2 = j;
                    while(j2 < tab.length - 1 && tab[i][j2+1] == 0){
                        tab[i][j2+1] = tab[i][j2];
                        tab[i][j2] = 0;
                        j2++;
                    }
                }
            }
        }
    }

    public static boolean aGagner (int[][] tab) {
    	boolean aGagner = false;
    	for (int i = 0; i < tab.length; i++){
            for (int j = 0; j < tab.length; j++){
            	if (tab[i][j] == 2048)
            		aGagner = true;
            }
    	}
    	return aGagner;
    }

    //condition de fin de la partie
    public static boolean finDuJeu(int[][] tab) {
    	boolean diff = false;
    	for (int ligne = 0; ligne <= 3 ; ligne++){
    		for (int colone = 0; colone <= 2; colone++){
    			if (tab[ligne][colone+1] != tab[ligne][colone])
    				diff = true;
    		}
    	}
    	
    	for (int colone = 0; colone <= 2 ; colone++){
    		for (int ligne = 0; ligne <= 3; ligne++){
    			if (tab[ligne][colone+1] != tab[ligne][colone])
    				diff = true;
    		}
    	}
    	return diff;
    }

    //forcer la saisie d'un int pour le menu 
    public static int saisieIntForce () {
    	Scanner sc = new Scanner (System.in);
    	String s;
    	do {
    		do {
    			System.out.println(TEXT_MENU);
    			s = sc.nextLine();
    		}while(s.length() <= 0);
    	}while (!verificationChiffre(s));
    	return Integer.parseInt(s);
    }
    
    public static boolean verificationChiffre (String s) {
    	int i = 0;
    	while (i < s.length() && s.charAt(i) >= 48 && s.charAt(i) <= 57) {
    		i++;
    	}
    	return i == s.length();
    }

}