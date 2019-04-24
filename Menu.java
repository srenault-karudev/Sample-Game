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
 * La classe Menu permet de créer différents menus.
 *  
 * @version 1.0
 * @author Renault Steven / Philemon Christopher
 */

public class Menu extends JPanel {

    /**
    * fenetre dans laquelle le menu va être ajouté.
    */
    private JFrame fenetre;

    /**
    * Constructeur de création de menu classique
    *
    * @param fenetre : fenetre dans laquelle ajouter le menu
    * @param type : type de menu désiré 
    */

    public Menu(JFrame f, String type){

        this.fenetre = f;
        this.setLayout(new BorderLayout());

        /**
        * affichage du menu en fonction de son type
        */

        afficher(type);

        this.fenetre.add(this);

    }

    /**
    * Constructeur de création du menu de fin de partie
    *
    * @param fenetre : fenetre dans laquelle ajouter le menu
    * @param type : type de menu désiré 
    * @param score : score obtenu durant la partie
    */

    public Menu(JFrame f, String type, int score){

        this.fenetre = f;
        this.setLayout(new BorderLayout());

        /**
        * affichage du menu de fin de partie
        */

        afficher(type, score);

        this.fenetre.add(this);

    }

    /**
    * affichage du menu en fonction de son type
    * @param type le type du menu
    */
    private void afficher(String type){

        /**
        * création des boutons du menu
        */

        JButton boutton1 = new JButton();
        JButton boutton2 = new JButton();

        if(type.equals("principal")){

            /**
            * ajout d'un fond au menu
            */

            this.add(new AffichageFond("menu principal"));

            /**
            * configuration des boutons du menu
            */

            boutton1.setText("Jouer");
            boutton2.setText("Regles du jeu");

        }
        else if(type.equals("regles")){

            this.add(new AffichageFond("regles"));

            boutton1.setText("Jouer");
            boutton2.setText("Menu principal");

        }
        else if(type.equals("choix grille")){

            this.add(new AffichageFond("choix grille"));

            boutton1.setText("Grille aleatoire");
            boutton2.setText("Grille dans un fichier");
        }

        JPanel panboutton = new JPanel();

        panboutton.add(boutton1);
        panboutton.add(boutton2);

        /**
        * Créaton du controleur des boutons
        */

        ControleurMenu controleur = new ControleurMenu(this, this.fenetre);

        boutton1.addActionListener(controleur);
        boutton2.addActionListener(controleur);


        this.add(panboutton,BorderLayout.SOUTH);

    }

    /**
    * affichage du menu de fin de partie
    * @param type le type du menu
    * @param score le score
    */

    private void afficher(String type, int score){  

        this.add(new AffichageFond("fin partie"));

        JButton boutton1 = new JButton("Menu principal");
        JLabel scoreObtenu = new JLabel("Votre score : " + score);

        ControleurMenu controleur = new ControleurMenu(this, this.fenetre);

        boutton1.addActionListener(controleur);

        JPanel pan = new JPanel();

        pan.add(scoreObtenu);
        pan.add(boutton1);

        this.add(pan,BorderLayout.SOUTH);

    }

  }