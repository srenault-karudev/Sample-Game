import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * La classe Fenetre permet de créer une nouvelle fenetre.
 *  
 * @version 1.0
 * @author Renault Steven / Philemon Christopher
 */

public class Fenetre extends JFrame {

	/**
  	* Constructeur de création de la fenetre
   	*
   	* @param largeur : largeur de la fenetre 
   	* @param hauteur : hauteur de la fenetre 
   	*/
	
	public Fenetre(int largeur, int hauteur){
	
		this.setTitle("Sample Game");
		this.setSize(largeur, hauteur);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
