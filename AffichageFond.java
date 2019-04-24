import javax.swing.*;
import java.awt.*;
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


/**
 * La classe AffichageFond permet d'afficher des fonds en fonction du menu désiré.
 *  
 * @version 1.0
 * @author Renault Steven / Philemon Christopher
 */

public class AffichageFond extends JComponent {

	/**
    * fond a afficher.
    */

	private String fond;

	/**
    * Constructeur permettant d'attribuer le fond.
    */

	public AffichageFond(String f){

		this.fond = f;
	}

	/**
    * ajout du fond en fonction du fond voulu.
    */

	@Override

	public void paintComponent(Graphics g){

		if(this.fond.equals("menu principal")){

			Image imgMenuPrincipal;

			try {
                
                imgMenuPrincipal = ImageIO.read(new File("img/menuprincipal.jpg"));

                g.drawImage(imgMenuPrincipal, 0, 0, this);
                
            }catch(IOException e){
                System.out.println("Erreur attribution image");
            }
        }
        else if (this.fond.equals("regles")){

        	Image imgRegles;

		try {
                
                imgRegles = ImageIO.read(new File("img/fondregles.jpg"));

                g.drawImage(imgRegles, 0, 0, this);
                
            }catch(IOException e){
                System.out.println("Erreur attribution image");
            }
        }
        else if (this.fond.equals("choix grille")){

        	Image imgChoixGrille;

		try {
                
                imgChoixGrille= ImageIO.read(new File("img/choixgrille.jpg"));

                g.drawImage(imgChoixGrille, 0, 0, this);
                
            }catch(IOException e){
                System.out.println("Erreur attribution image");
            }
        }
        else if (this.fond.equals("fin partie")){

        	Image imgFinPartie;

		try {
                
                imgFinPartie = ImageIO.read(new File("img/finpartie.jpg"));

                g.drawImage(imgFinPartie, 0, 0, this);
                
            }catch(IOException e){
                System.out.println("Erreur attribution image");
            }
        }
	}

}