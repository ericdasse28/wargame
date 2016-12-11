package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

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
		
		/*Rajout des boutons et de la toolbar*/
		
		JButton newpartie = new JButton("Nouvelle Partie");
	    newpartie.setPreferredSize(new Dimension(180,40));
	    JButton fintour = new JButton  ("   Fin  Tour   ");
	    fintour.setPreferredSize(new Dimension(180,40));
	    JLabel hero = new JLabel(" nombre heros : " + IConfig.NB_HEROS);
	    hero.setPreferredSize(new Dimension(180,24));
	    JLabel monstre = new JLabel("  nombre Monstres : " + IConfig.NB_MONSTRES);
	    monstre.setPreferredSize(new Dimension(180,24));
		JToolBar tools = new JToolBar();tools.setRollover(true);
		tools.add(newpartie);tools.addSeparator();tools.addSeparator();
		tools.add(fintour);tools.addSeparator();tools.addSeparator();
		tools.add(hero);tools.addSeparator();tools.addSeparator();
		tools.add(monstre);tools.addSeparator();tools.addSeparator();
		tools.setBackground(Color.CYAN);
		panneau.add(tools,BorderLayout.NORTH);
		
		
		/*Gestion des clics sur les boutons*/
		fintour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				panneau.getCarte().jouerSoldats(panneau);
			}
		});
	}
	
}
