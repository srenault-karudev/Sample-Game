import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
* La classe ControleurMenu permet de gérer les actions a réalisé si un bouton contenu dans un menu est pressé.
*  
* @version 1.0
* @author Renault Steven / Philemon Christopher
*/

public class ControleurMenu implements ActionListener{

	/**
    * fenetre dans laquelle le panneau du menu se trouve.
    */
	private JFrame fenetre;

	/**
    * panneau contenant le menu.
    */
	private JPanel panel;

	/**
    * Constructeur dans le cas d'un menu classique.
    * @param p le panneau
    * @param f la fenetre
    */

	public ControleurMenu(JPanel p, JFrame f){

		this.fenetre = f;
		this.panel = p;
	}

	/**
    * gestion des evenements en fonction du bouton sur lequel on a cliqué.
    */

	public void actionPerformed(ActionEvent evenement){

		String nomBouton = new String(evenement.getActionCommand());

		if (nomBouton.equals("Regles du jeu")){

			/**
   			* Création du menu pour les règles du jeu.
    		*/
		 	
		 	Menu regles = new Menu(this.fenetre, "regles");

        	this.panel.setVisible(false);        	

		 }
		 else if (nomBouton.equals("Menu principal")){
		 	
		 	Menu menuPrincipal = new Menu(this.fenetre, "principal");

        	this.panel.setVisible(false);   	

		 }
		 else if(nomBouton.equals("Jouer")){

		 	Menu choixGrille = new Menu(this.fenetre, "choix grille");

        	this.panel.setVisible(false);

		 }
		 else {

		 	/**
   			* On a choisit le type de grille qu'on souhaitait donc on 
   			* créé l'écran du jeu, le menu du jeu et la grille en fonction
   			* de notre choix puis on rassemble le tout et on affiche
    		*/
          
      		EcranJeu ecranJeu = new EcranJeu(this.fenetre);

      		MenuJeu menu = new MenuJeu(this.fenetre, ecranJeu);

      		Grille grille;

      		if(nomBouton.equals("Grille aleatoire")){
        
       	 		grille = new Grille(false, this.fenetre, ecranJeu);
       	 	}
       	 	else{

       	 		grille = new Grille(true, this.fenetre, ecranJeu);
       	 	}
        
        	ecranJeu.addMenu(menu);
        
        	ecranJeu.addGrille(grille);

        	this.panel.setVisible(false);

        	ecranJeu.afficherEcranJeu();

		}

	}
}