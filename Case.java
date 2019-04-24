import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/* La classe Case represente une cellule d'une Grille
*  
* @version 1.0
* @author Renault Steven / Philemon Christopher
*/

public class Case extends JComponent implements MouseListener{
	
	/**
    * l'image de la case.
    */	
	private Image img;
	/**
    * le numero d'image de la case.
    */	
	private int numeroImage;
	/**
    * la grille contenant la case.
    */	
	private Grille grille;
	/**
    * la ligne de la case.
    */	
	private int x;
	/**
    * la colonne de la case.
    */	
	private int y;
	/**
    * la valeur de la case.
    */	
	private int valeur;
	/**
    * si la case a été modifé ou non.
    */	
	private boolean modification;
	/**
    * le statut de la case ( 1 = marqué, 0 = non marqué ).
    */	
	private int statut;

	/**
    * Constructeur initialisant les valeurs.
    * @param i l'image
    * @param n le numero de l'image
    * @param g la grille
    * @param x la ligne
    * @param y la colonne
    * @param v la valeur
    */	
	public Case(Image i, int n, Grille g, int x, int y, int v){
		
		this.img = i;
		this.numeroImage = n;
		this.grille = g;
		this.x = x;
		this.y = y;
		this.valeur = v;
		this.statut = 0;
		this.addMouseListener(this);
		this.modification = true;
	}

	@Override
  	public void paintComponent(Graphics pinceau) {

  		pinceau.drawImage(this.img, 0, 0, this);
  	}
	
	/**
    * Renvoie le statut.
	* @return le statut ( 1 ou 0 )
    */	

	public int getStatut(){
		
		return this.statut;
	}

	/**
    * modifie le statut.
    * @param s le nouveau statut
    */	
	
	public void setStatut(int s){
		
		this.statut = s;
	}

	/**
    * renvoie si modifié ou non.
    * @return modification ( true ou false )
    */	
	
	public boolean getModification(){
		return this.modification;
	}

	/**
    * modifie.
    * @param b nouveau modification
    */	
	
	public void setModification(boolean b){
		this.modification = b;
	}
	
	/**
    * renvoie la valeur.
    * @return valeur ( 0, 1, 2 ou -1 )
    */	
	public int getValeur(){
		
		return this.valeur;
	}

	/**
    * modifie la valeur.
    * @param v nouvelle valeur
    */	
	
	public void setValeur(int v){
		
		this.valeur = v;
		
	}

	/**
    * renvoie l'image.
    * @return img ( l'image de la case )
    */	
	
	public Image getImg(){
		
		return this.img;
	}

	/**
    * renvoie le numero d'image.
    * @return numeroImage ( 0, 1, 2 ou 3 )
    */	
	
	public int getNumeroImage(){
		
		return this.numeroImage;
	}

	/**
    * modifie le numero d'image.
	* @param nIm le nouveau numéro d'image
    */	
	
	public void modifierImg(int nIm){
		
		switch(nIm){
			case 0:
				this.img = this.grille.imgTriforce;
				this.numeroImage = 0;
				break;
			case 1:
				this.img = this.grille.imgMasterSword;
				this.numeroImage = 1;
				break;
			case 2:
				this.img = this.grille.imgBouclier;
				this.numeroImage = 2;
				break;
			default:
				this.img = this.grille.imgVide;
				this.numeroImage = 3;
				break;
		}
		
	}
	
	/**
    * modifie l'image ( hover ) .
    * @param numeroImage le nouveau numero d'image
    */	

	public void setHover(int numeroImage){
			
		switch(this.numeroImage){
			case 0:
				this.img = this.grille.imgTriforceHover;
				break;
			case 1:
				this.img = this.grille.imgMasterSwordHover;
				break;
			case 2:
				this.img = this.grille.imgBouclierHover;
				break;
			default:
				break;
		}
		

	}

	/**
    * supprime le hover
    * @param numeroImage le nouveau numero d'image
    */	
	
	public void deleteHover(int numeroImage){
		
		switch(this.numeroImage){
			case 0:
				this.img = this.grille.imgTriforce;
				break;
			case 1:
				this.img = this.grille.imgMasterSword;
				break;
			case 2:
				this.img = this.grille.imgBouclier;
				break;
			default:
				break;
		}
	}

	// Si on a cliqué
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	/**
    * met a jour les cases ( si la case n'était pas vide ) .
    */	

	@Override
	public void mousePressed(MouseEvent e) {
		
		if( ( this.numeroImage != -1) && ( this.numeroImage != 3) ){

			if(this.grille.verifierCasesAdjacentes(this.x, this.y) != false){
			
				this.grille.miseAjourCases(this.x, this.y);

			}
		}
		
	}

	// si on relache la souris

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	/**
    * declenche le hover ( si la case n'était pas vide ) .
    */	

	@Override
	public void mouseEntered(MouseEvent e) {
		
		if( ( this.numeroImage != -1) && ( this.numeroImage != 3) ){

			if(this.grille.verifierCasesAdjacentes(this.x, this.y) != false){
			
				this.grille.afficherHover(this.x, this.y, this.numeroImage);
			}
		}
				
	}

	/**
    * supprime le hover ( si la case n'était pas vide ) .
    */	

	@Override
	public void mouseExited(MouseEvent e) {
		
		if( ( this.numeroImage != -1) && ( this.numeroImage != 3)){

			if(this.grille.verifierCasesAdjacentes(this.x, this.y) != false){
			
				this.grille.supprimerHover(this.x, this.y, this.numeroImage);

			}
		}
		
	}
	
}
