import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/* La classe Grille permet de gerer la grille ( tableau de Case ) du jeu
*  
* @version 1.0
* @author Renault Steven / Philemon Christopher
*/

public class Grille extends JPanel {
	
	/**
    * nombre de lignes.
    */	
	private final static int LIGNES = 10;

	/**
    * nombre de colonnes.
    */	
	private final static int COLONNES = 15;

	/**
    * fenetre contenant la grille.
    */	
	private JFrame fenetre;

	/**
    * grille en elle même ( tableau de Case ).
    */	
	private Case grilleGraphique[][] = new Case[LIGNES][COLONNES];

	/**
    * ecran de jeu contenant la grille.
    */	
	private EcranJeu ecranJeu;
	
	/**
    * images utilisées.
    */	
	public Image imgBouclier;
	public Image imgMasterSword;
	public Image imgTriforce;
	public Image imgBouclierHover;
	public Image imgMasterSwordHover;
	public Image imgTriforceHover;
	public Image imgVide;

	/**
    * Constructeur initialisant les valeurs et appelant l'initialisation de la grille.
    * @param  fichier le boolean qui vaut true si le fichier a ete charge, false sinon
    * @param f la fenetere
    * @param ec l'ecran de jeu
    */	
	
	public Grille(boolean fichier, JFrame f, EcranJeu ec){
			
			this.setBackground(new Color(255, 251, 242));
	        
	        GridLayout gestionnaireGrille = new GridLayout(LIGNES,COLONNES);
	        
	        this.setLayout(gestionnaireGrille);
	        
	        this.fenetre = f;

	        this.ecranJeu = ec;
	        
	        try {
	        	
				this.imgTriforce = ImageIO.read(new File("img/triforce.jpg"));
				this.imgMasterSword = ImageIO.read(new File("img/mastersword.jpg"));
				this.imgBouclier = ImageIO.read(new File("img/bouclier.jpg"));
				this.imgTriforceHover = ImageIO.read(new File("img/triforcehover.jpg"));
				this.imgMasterSwordHover = ImageIO.read(new File("img/masterswordhover.jpg"));
				this.imgBouclierHover = ImageIO.read(new File("img/bouclierhover.jpg"));
				this.imgVide = ImageIO.read(new File("img/vide.jpg"));
				
	        }catch(IOException e){
	        	System.out.println("Erreur attribution images");
	        }
	        
			initialiserGrilleGraphique(fichier);
	}

	/**
    * initialisation de la grille.
    * @param fichier le boolean qui vaut true si le fichier a ete charge, false sinon
    */	
	
	private void initialiserGrilleGraphique(boolean fichier){
		
		int grilleNumerique[][] = new int[LIGNES][COLONNES];

		/**
    	* si on veut charger un fichier.
    	*/

		if(fichier == true){

			ChoixFichier choix;

			do{

				choix = new ChoixFichier(this.fenetre);

			}while(choix.verifierFichierCharge() != true);

			grilleNumerique = choix.lectureFichier();

		}

		/**
   		* grille aléatoire.
	    */
		else{
		
			Random rand = new Random();
		
			for(int i = 0; i<LIGNES; i++){
			
				for(int j = 0; j<COLONNES; j++){
				
					grilleNumerique[i][j] = (rand.nextInt(100 - 5 + 1) + 5)%3;
				}
			}	

	    }

	    Image img;

	    /**
    	* attribution des valeurs / images au tableau de Case.
    	*/		
			
	    for(int i = 0; i<LIGNES; i++){
	
	        for(int j = 0;j<COLONNES; j++){
			
				switch(grilleNumerique[i][j]){
					
					case 0:
						img = this.imgTriforce;
						grilleGraphique[i][j] = new Case(img, 0, this, i, j, 0);
						break;
					case 1:
						img = this.imgMasterSword;
						grilleGraphique[i][j] = new Case(img, 1, this, i, j, 1);
						break;
					case 2:
						img = imgBouclier;
						grilleGraphique[i][j] = new Case(img, 2, this, i, j, 2);
						break;
					default:
						img = imgVide;
						grilleGraphique[i][j] = new Case(img, 3, this, i, j, 3);
						break;
				}
	        }
	    }

	    afficherGrille();
	}

	/**
    * affichage de la grille.
    */
	
	public void afficherGrille(){

       for(int i = 0; i<LIGNES; i++){
	
	        for(int j = 0;j<COLONNES; j++){

	        	/**
   				* on affiche uniquement les case qui ont recemment été modifiées.
    			*/
	        	
	        	if(this.grilleGraphique[i][j].getModification() == true){

   					this.grilleGraphique[i][j].setVisible(false);
					this.grilleGraphique[i][j].repaint();
					this.grilleGraphique[i][j].setVisible(true);
					this.add(this.grilleGraphique[i][j]);

	        	}
	        	
	        }

      	}

       /**
   		* on verifie la fin de la partie a chaque affichage de la grille
    	*/

       if(verifierFinPartie() == true){

       	/**
   		* on rend l'écran de jeu insivible et on affiche l'écran de fin de partie.
    	*/

       		this.ecranJeu.setVisible(false);

       		Menu rejouer = new Menu(this.fenetre, "principal", this.ecranJeu.menuJeu.getScore());
       }

	}

	/**
    * verification des cases adjacentes.
    * @param i la ligne
    * @param j la colonne
    */
	
	public void verifierCases(int i, int j){
		
		// case de gauche
		if( (j-1) >= 0){

			// si la case de gauche est la même
									
			if( this.grilleGraphique[i][j].getValeur() == this.grilleGraphique[i][j-1].getValeur() ) {
						
				// si cette case n'a pas deja ete marque ( pour eviter la recursion infinie )
				if(this.grilleGraphique[i][j-1].getStatut() != 1){
							
					// on marque la case
					this.grilleGraphique[i][j-1].setStatut(1);
						
					// on verifie les cases adjacentes de la case qui vient d'etre marquee
					verifierCases(i, j-1);
						
				}
			}
		}
				
		// case de droite
		if( (j+1) < COLONNES){
					
			if( this.grilleGraphique[i][j].getValeur()  == this.grilleGraphique[i][j+1].getValeur() ){
						
				if(this.grilleGraphique[i][j+1].getStatut() != 1){
						
					this.grilleGraphique[i][j+1].setStatut(1);
						
					verifierCases(i, j+1);
							
				}
			}
		}
				
		// case du haut
		if( (i-1) >= 0){
					
			if( this.grilleGraphique[i][j].getValeur()  == this.grilleGraphique[i-1][j].getValeur() ){
						
				if(this.grilleGraphique[i-1][j].getStatut() != 1){
						
					this.grilleGraphique[i-1][j].setStatut(1);;
						
					verifierCases(i-1, j);
						
				}
			}
		}
				
		// case du bas
		if( (i+1) < LIGNES){
					
			if( this.grilleGraphique[i][j].getValeur()  == this.grilleGraphique[i+1][j].getValeur() ){
						
				if(this.grilleGraphique[i+1][j].getStatut() != 1){

					this.grilleGraphique[i+1][j].setStatut(1);
						
					verifierCases(i+1, j);
						
				}
			}
		}
		
	}

	/**
    * affichage des cases en surbrillance si elles font partie d'un groupe de cases.
    * @param x la ligne
    * @param y la colonne
    * @param numeroImage le numero de l'image
    */
	
	public void afficherHover(int x, int y, int numeroImage){

		// On vérifie et on marque ( verifierCases vérifie ET marque ) les cases adjacentes à la case (x, y)
		
		verifierCases(x, y);
		
		for(int i = 0; i<LIGNES; i++){
			for(int j = 0; j<COLONNES; j++){

				// si on a marqué l'image, qu'elle a le bon numéro et qu'elle fait ben partie du groupe
				
				if( ( this.grilleGraphique[i][j].getStatut() == 1 ) && ( this.grilleGraphique[i][j].getNumeroImage() == numeroImage ) && ( verifierCasesAdjacentes(i, j) == true ) ){

					// on met le hoover sur les cases marquées et dit qu'elles on été modifié
				
					this.grilleGraphique[i][j].setHover(numeroImage);
					this.grilleGraphique[i][j].setModification(true);
				
				}				
				
			}
		
		}
		
		afficherGrille();
	}

	/**
    * afficherHover inversé.
    * @param x la ligne
    * @param y la colonne
    * @param numeroImage le numero de l'image
    */
	
	public void supprimerHover(int x, int y, int numeroImage){

		verifierCases(x, y);
		
		for(int i = 0; i<LIGNES; i++){
			for(int j = 0; j<COLONNES; j++){
				
				if(this.grilleGraphique[i][j].getStatut() == 1){
				
					this.grilleGraphique[i][j].deleteHover(numeroImage);
					this.grilleGraphique[i][j].setModification(true);
					this.grilleGraphique[i][j].setStatut(0);

				}

			}
		
		}
		
		afficherGrille();
	
	}

	/**
    * suppression des cases.
    */
	
	private void supprimerCases(){

		// on va compter le nombre de cases ( pour le score )
		int nombreCases = 0;
		
		for(int i=0; i<LIGNES; i++){
			for(int j=0; j<COLONNES; j++){

				// si la case a été maquée et n'est pas déjà vide
				
				if(( this.grilleGraphique[i][j].getStatut() == 1 ) && ( this.grilleGraphique[i][j].getValeur() != -1 )){

					/**
   					* on supprime ( = on met la valeur " -1 " correspondant a une case supprimée 
   					* et on met l'imag 3 correspondant à du vide )
   					* et on précise que la case a été modifié
    				*/
					
					this.grilleGraphique[i][j].setValeur(-1);
					this.grilleGraphique[i][j].modifierImg(3);
					this.grilleGraphique[i][j].setModification(true);
					

					// on incrémente le nombre de cases supprimé
					nombreCases += 1;
					
				}
			}
		}

		// on actualise le score
		gererScore(nombreCases);
		
	}

	/**
    * en fonction du nombre de cases du bloc, on modifie le score.
    * @param nombreCasesBloc le nombre de case que contient le bloc 
    */

	private void gererScore(int nombreCasesBloc){

		this.ecranJeu.menuJeu.setScore( (nombreCasesBloc - 2) * (nombreCasesBloc - 2) );
		this.ecranJeu.menuJeu.updateScore();

	}

	/**
    * decalage des cases vers le bas.
    */
	
	private void faireTomberCases(){
		
		for(int i=0; i<LIGNES; i++){
			for(int j=0; j<COLONNES; j++){
				
				if( (i-1) >= 0){

					// si la case est vide
					
					if(this.grilleGraphique[i][j].getValeur()  == -1){

						// si la case au dessus n'est pas vide
						
						if(this.grilleGraphique[i-1][j].getValeur()  != -1){

							//On inverse les deux cases
						
							this.grilleGraphique[i][j].setValeur(this.grilleGraphique[i-1][j].getValeur() );
							this.grilleGraphique[i][j].modifierImg(this.grilleGraphique[i-1][j].getNumeroImage());
							this.grilleGraphique[i][j].setModification(true);
							this.grilleGraphique[i-1][j].setValeur(-1);
							this.grilleGraphique[i-1][j].modifierImg(3);
							this.grilleGraphique[i-1][j].setModification(true);
							
							// on rappelle la fonction pour verifier que tout est tombé
							faireTomberCases();
						
						}
					}
				}
			}
		}
		
	}

	/**
    * on decale les cases vers la gauche.
    */
	
	private void decalerCases(){
		
		// variable pour savoir si on doit encore decaler
		int verification;
		
		do{	
			verification = 0;
			
			for(int j = 0; j<COLONNES-1; j++){

				/**
   				* si la colonne est vide
   				* ( la colonne est vide si la case de la dernière ligne vaut -1 
   				* car on faire tomber les cases avant de les decaler )
    			*/
				
				if(this.grilleGraphique[LIGNES-1][j].getValeur() == -1){
					
					for(int z = LIGNES-1; z>=0; z--){

						// on decale les colonnes
						
						this.grilleGraphique[z][j].setValeur(this.grilleGraphique[z][j+1].getValeur() );
						this.grilleGraphique[z][j].modifierImg(this.grilleGraphique[z][j+1].getNumeroImage());
						this.grilleGraphique[z][j].setModification(true);
						this.grilleGraphique[z][j].setStatut(0);
						this.grilleGraphique[z][j+1].setValeur(-1);
						this.grilleGraphique[z][j+1].modifierImg(3);
						this.grilleGraphique[z][j+1].setModification(true);
						this.grilleGraphique[z][j+1].setStatut(0);
					}
					
					// On verifie que toutes les colonnes suivantes soient vide
					for(int z = j; z<COLONNES; z++){
						
						if(this.grilleGraphique[LIGNES-1][z].getValeur() != -1){
							
							verification = 1;
						}
					}
					
				}
				
			}

			// on continue tant que toutes les cases de droite ne non pas vide
		
		}while(verification == 1);
	}

	/**
    * met a jour la grille
    * @param i la ligne
    * @param j la colonne
    */

	
	public void miseAjourCases(int i, int j){

		verifierCases(i, j);
		
		supprimerCases();

		faireTomberCases();

		decalerCases();

		// permet d'éviter les bugs d'affichage

		this.grilleGraphique[0][0].mouseEntered(null);
		this.grilleGraphique[0][0].mouseExited(null);
		this.grilleGraphique[LIGNES-1][0].mouseEntered(null);
		this.grilleGraphique[LIGNES-1][0].mouseExited(null);
		afficherGrille();
		this.grilleGraphique[i][j].mouseEntered(null);
		this.grilleGraphique[i][j].mouseExited(null);
		afficherGrille();


		// on declenche le hover sur la case où on se trouve une fois la MAJ effectuée
		this.grilleGraphique[i][j].mouseEntered(null);
		
	}

	/**
    * verification des cases adjacentes.
    * même principe que verifierCases sauf qu'on verifie
    * ici uniquement si une case est isolé
	* @param i la ligne
	* @param j la colonne
	* @return true si la case contient des cases adjacentes, false sinon
    */

	public boolean verifierCasesAdjacentes(int i, int j){

		// case de gauche
		if( (j-1) >= 0){

			if( (this.grilleGraphique[i][j].getValeur() != -1) && (this.grilleGraphique[i][j].getValeur() == this.grilleGraphique[i][j-1].getValeur()) ) {

				return true;
			}

		}

		// case de droite
		if( (j+1) < COLONNES){
					
			if( (this.grilleGraphique[i][j].getValeur() != -1) && (this.grilleGraphique[i][j].getValeur()  == this.grilleGraphique[i][j+1].getValeur()) ){

				return true;

			}

		}

		// case du haut
		if( (i-1) >= 0){
					
			if( (this.grilleGraphique[i][j].getValeur() != -1) && (this.grilleGraphique[i][j].getValeur()  == this.grilleGraphique[i-1][j].getValeur()) ){

				return true;

			}

		}

		// case du bas
		if( (i+1) < LIGNES){
					
			if( (this.grilleGraphique[i][j].getValeur() != -1) && (this.grilleGraphique[i][j].getValeur()  == this.grilleGraphique[i+1][j].getValeur()) ){

				return true;

			}

		}

		return false;

	}

	/**
    * verification de la fin de partie.
    * @return true si la partie est terminé, false sinon
    */

	public boolean verifierFinPartie(){

		// si la premiere colonne est vide, la grille est vide

		if(grilleGraphique[LIGNES-1][0].getValeur() == -1){

			return true;
		}
		else {

			boolean presenceBloc = false;

			for(int i = 0; i<LIGNES; i++){

				for(int j = 0; j<COLONNES; j++){

					// si la case n'est pas vide

					if(grilleGraphique[i][j].getValeur() != -1){

						// on verifie uniquement tant qu'on n'a pas trouvé de case non isolé

						if(presenceBloc == false){

							presenceBloc = verifierCasesAdjacentes(i, j);

						}
					}
				}
			}

			// si il n'y a pas de bloc ( donc que des cases isolées )

			if(presenceBloc == false){

				return true;
			}
			else{

				return false;
			}

		}

	}

}
