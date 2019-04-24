import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * La classe ChoixFichier permet de recuperer les valeurs d'une grille dans un fichier.
 *  
 * @version 1.0
 * @author Renault Steven / Philemon Christopher
 */

public class ChoixFichier{

	/**
    * fenetre dans laquelle le menu de selection va être affiché.
    */
	private JFrame fenetre;

	/**
    * tableau qui contiendra les valeurs du fichier.
    */
	private int[][] tab;

	/**
    * fichier dans lequel on recuperera la grille.
    */
	private File fichier;

	/**
    * variable contenant vrai si le fichier a bien ete charge ou faux sinon.
    */
	private boolean fichierCharge;

	/**
    * Constructeur attribuant la fenetre et declenchant la methode de chargement du fichier.
    * @param f la fenetre
    */

	public ChoixFichier(JFrame f){
		this.fenetre=f;
		this.chargerFichier();
	}

	/**
    * ouverture du menu de chargement du fichier a partir de la position dans laquell se trouve les sources.
    * modifie fichierCharge en cas de réussite ( true ) ou d'erreur ( false )
    */

	private void chargerFichier(){
		JFileChooser filechooser = new JFileChooser(".");
		
		int choix = filechooser.showOpenDialog(this.fenetre);
		
		if(choix==JFileChooser.APPROVE_OPTION){
			this.fichier=filechooser.getSelectedFile();
			this.fichierCharge=true;
		}

		else if(choix==JFileChooser.CANCEL_OPTION){
			this.fichierCharge=false;
		}
	}

	/**
   	* Renvoie si le fichier a ete charge ou non.
   	*
   	* @return fichierCharge ( true si oui, false si non )
   	*/

	public boolean verifierFichierCharge(){

		return this.fichierCharge;
	}

	/**
   	* Renvoie le tableau contenant les valeurs extraites du fichier, après les avoir récupérées.
   	*
   	* @return tab le tableau contenant les valeurs du fichier
   	*/


	public int[][] lectureFichier(){
		this.tab = new int[10][15];
		int charActuel = 0;
		char c = 0;

		if(this.fichierCharge){
			try{
				try{
					BufferedReader buffer = new BufferedReader(new FileReader(this.fichier));
					try{
						for(int i=0;i<10;i++){
							for(int j=0;j<15;j++){
								charActuel=buffer.read();
								c=(char)charActuel;
								if(c=='V')
									this.tab[i][j]=0;
								if(c=='B')
									this.tab[i][j]=1;
								if(c=='R')
									this.tab[i][j]=2;
							}
						}
					}catch(IOException e){
						System.err.println("Erreur lecture");}
					try{
						buffer.close();
					}catch(IOException e){
						System.err.println("Erreur fermeture");}

					}catch(IOException e){
						System.err.println("Erreur creation buffer");}

				}catch(NullPointerException e){
					System.err.println("Fichier non sélectionné");
				}
			}		
		return this.tab;
	}

}
