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
* La classe ControleurMenuJeu permet de gérer les actions a réaliser si un bouton du menu de jeu est pressé.
*  
* @version 1.0
* @author Renault Steven / Philemon Christopher
*/

public class ControleurMenuJeu implements ActionListener{

  /**
    * fenetre dans laquelle le panneau du menu se trouve.
    */
  private JFrame fenetre;

  /**
    * panneau contenant le menu.
    */
  private JPanel panel;

  /**
    * panneau contenant les differents panneaux du jeu.
    */
  private EcranJeu ecranJeu;

  /**
  * Constructeur attribuant les valeurs.
  * @param p le panneau
  * @param f la fenetre
  * @param e l'écran de jeu
  */

  public ControleurMenuJeu(JPanel p, JFrame f, EcranJeu e){

    this.fenetre = f;
    this.panel = p;
    this.ecranJeu = e;
  }

  public void actionPerformed(ActionEvent evenement){

    String nomBouton = new String(evenement.getActionCommand());

    if(nomBouton.equals("Abandonner")){

		  	/**
   			* On rend le menu du jeu et l'écran du jeu invisible.
    		*/

        	this.panel.setVisible(false);
        	this.ecranJeu.setVisible(false);

        	/**
   			* Création du menu principal.
    		*/

        	Menu menuPrincipal = new Menu(this.fenetre, "principal");

		 }

    }
    
  }