import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;


/**
* La classe MenuJeu permet de créer panneau de menu spécifique a celui du jeu
* ( et totalement différents des menus classique ).
*  
* @version 1.0
* @author Renault Steven / Philemon Christopher
*/

public class MenuJeu extends JPanel{

	/**
    * Label du score.
    */	
	private JLabel score;

	/**
    * valeur du score.
    */
	private int scoreActuel;

	/**
    * ecran de jeu dans lequel le menu est contenu.
    */
	public EcranJeu ecranJeu;

	/**
    * Constructeur attribuant initialisant les valeurs et affichant le score et le bouton pour abandonner.
    * @param f la fenetre
    * @param e l'ecran du jeu
    */
	
	public MenuJeu(JFrame f, EcranJeu e){

		this.setLayout(new BorderLayout());
		
        this.scoreActuel = 0;

        this.ecranJeu = e;

        this.score = new JLabel("Score : 0");

        this.add(this.score, BorderLayout.EAST);

        JButton boutton1 = new JButton("Abandonner");

        ControleurMenuJeu controleur = new ControleurMenuJeu(this, f, this.ecranJeu);

        boutton1.addActionListener(controleur);

        this.add(boutton1, BorderLayout.WEST);       
	}

	/**
    * permet d'obtenir le score.
    * @return scoreActuel ( un int contenant le score actuel )
    */
	
	public int getScore(){
		
		return this.scoreActuel;
	}
	
	/**
    * permet de modifier le score.
    * @param s ce qu'on va ajouter au score actuel
    */
	public void setScore(int s){
		
		this.scoreActuel = this.scoreActuel + s;
		
	}

	/**
    * permet de mettre a jour le label du score.
    */

	public void updateScore(){

		this.score.setText(" Score : " + this.scoreActuel);
	}
	

}
