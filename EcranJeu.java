import javax.swing.*;
import java.awt.*;

/**
* La classe EcranJeu permet de créer un pannau contenant la grille et le menu du jeu.
*  
* @version 1.0
* @author Renault Steven / Philemon Christopher
*/

public class EcranJeu extends JPanel {
	
	/**
    * panneau de la grille du jeu
    */
	public Grille grille;

	/**
    * panneau contenant le menu du jeu.
    */
	public MenuJeu menuJeu;

	/**
    * fenetre dans laquelle on ajoutera le tout.
    */
	public JFrame fenetre;

	/**
    * Constructeur attribuant la fenetre.
    * @param f la fenetre
    */
	
	public EcranJeu(JFrame f){

		this.setLayout(new BorderLayout());
		
		this.fenetre = f;
		
	}

	/**
    * Ajout du menu du jeu.
    * @param m le menu de jeu
    */
	
	public void addMenu(MenuJeu m){
		
		this.menuJeu = m;
		
		this.add(this.menuJeu, BorderLayout.SOUTH);
	}

	/**
    * Ajout de la grille.
    * @param  g la grille
    */
	
	public void addGrille(Grille g){
		
		this.grille = g;
		
		this.add(this.grille);
		
		this.grille.afficherGrille();
	}

	/**
    * affichage de l'écran de jeu.
    */

	public void afficherEcranJeu(){

		this.fenetre.add(this);
	}

}
