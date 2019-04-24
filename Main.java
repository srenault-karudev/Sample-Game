import java.awt.*;
import javax.swing.*;

/**
 * La classe Main est la classe principale.
 * Elle crée une fenetre et y ajouter le menu principal.
 *  
 * @version 1.0
 * @author Renault Steven / Philemon Christopher
 */


public class Main {


	/**
   	* Constante representant la hauteur de la fenetre.
   	*/
	public static final int HAUTEUR = 750;

	/**
   	* Constante representant la largeur de la fenetre.
   	*/
	public static final int LARGEUR = 1050;	
	
	
	/**
   	* Methode principale du programme.
   	* @param args les arguments de la ligne de commande
   	*/
	public static void main(String[] args) {


		/**
   		* Création d'une fenetre.
   		*/

		Fenetre fenetre = new Fenetre(LARGEUR, HAUTEUR);

		/**
   		* Création du menu principal.
   		*/

		Menu menuPrincipal = new Menu(fenetre, "principal");
        
		fenetre.setVisible(true);
		
	}

}
