package wargame;

import java.awt.Dimension;
import javax.swing.JFrame;

public class FenetreJeu extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public static void main(String[] agrs){
		JFrame fenetre = new JFrame();
		fenetre.setTitle("WARGAME!");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlateauJeu panneau = new PlateauJeu();
		fenetre.setPreferredSize(new Dimension(IConfig.NB_PIX_CASE * IConfig.LARGEUR_CARTE+11, IConfig.NB_PIX_CASE * IConfig.HAUTEUR_CARTE + 100));
		fenetre.add(panneau);
		fenetre.setLocation(IConfig.POSITION_X, IConfig.POSITION_Y);
		fenetre.pack();
		fenetre.setVisible(true);
		fenetre.setResizable(false);
	}
	
}
