package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;



public class PlateauJeu extends JPanel implements ActionListener, IConfig{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
	Carte carte;
	Dimension dim;
	JToolBar tools ;
	JButton newpartie,fintour, sauvegarder, charger;
	JLabel label,hero,monstre;		
	Position init = new Position(-1,-1);
	Boolean select = false;
	/******************************************zone carte *****************************************************************/
public class Zonecarte extends JPanel{
		
	private static final long serialVersionUID = 1L;
	public Zonecarte(){
	 	dim = new Dimension(IConfig.NB_PIX_CASE*IConfig.LARGEUR_CARTE,IConfig.NB_PIX_CASE*IConfig.HAUTEUR_CARTE);
	 	carte = new Carte();
	}
		
	public void paintComponent(Graphics g){
			super.paintComponent(g);
			/*g.setColor(Color.WHITE);wws
			g.fillRect(0, 0, dim.width, dim.height);
			g.setColor(Color.BLACK);
			for(int i = 0; i < IConfig.LARGEUR_CARTE; i++){
				for(int j = 0; j < IConfig.HAUTEUR_CARTE; j++){
					g.setColor(Color.BLACK);
					g.drawRect(i * IConfig.NB_PIX_CASE, j * IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
					//g.setColor(c);	Couleur de la case
					//g.fillRect(i * IConfig.NB_PIX_CASE, j * IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
				}
			}*/
			
			//
			
			carte.toutDessiner(g);
	}
			
}
/*************************************fin zone carte**********************************************************************/
    Zonecarte zone=new Zonecarte();
	PlateauJeu(){
	
		this.setLayout(new BorderLayout());
		
		newpartie =new JButton("Nouvelle Partie");
	    newpartie.setPreferredSize(new Dimension(180,40));
	    fintour =new JButton  ("   Fin  Tour   ");
	    fintour.setPreferredSize(new Dimension(180,40));
	    fintour.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//faire jouer les monstres

				//actualisation des couleurs des heros pour pouvoir les redeplacer ou gain de 1 pdv
				for(int i = 0; i < IConfig.LARGEUR_CARTE; i++)
					for(int j = 0; j < IConfig.HAUTEUR_CARTE; j++){
						Position pt = new Position(i, j);
						if(carte.getElement(pt) instanceof Heros)
							if(carte.getElement(pt).couleur == IConfig.COULEUR_HEROS_DEJA_JOUE)
								((Heros)carte.getElement(pt)).actualiseCouleur();
							else
								if(((Heros)carte.getElement(pt)).pointsDeVie < ((Heros)carte.getElement(pt)).POINTS_DE_VIE_MAX)
									((Heros)carte.getElement(pt)).pointsDeVie++;
					}
				zone.repaint();
			}
		});
	    sauvegarder = new JButton("Sauvegarder");
	    sauvegarder.setPreferredSize(new Dimension(180,40));
	    charger = new JButton("Charger");
	    charger.setPreferredSize(new Dimension(180,40));
	    
	    sauvegarder.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileOutputStream fos = null;
			    ObjectOutputStream out = null;
			    try {
			        fos = new FileOutputStream("wargame.ser",false);
			        out = new ObjectOutputStream(fos);
			        out.writeObject(carte);
			        out.close();
			        System.out.println("Sauvegarde effectuée");
			    } catch (IOException ex) {
			        ex.printStackTrace();
			    }
			}
		});
	    
	    charger.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FileInputStream fos = null;
				Object c = null;
			    try {
			    	fos = new FileInputStream("wargame.ser");
		            ObjectInputStream oos = new ObjectInputStream(fos);
		            c = oos.readObject();
		            fos.close();
		            if(c instanceof Carte){
		            	carte = new Carte((Carte)c);
		            	System.out.println("Restauration effectuée");
		            	zone.repaint();
		            }else
		            	System.out.println("Restauration impossible");
			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}
		});
	    
	    tools = new JToolBar();tools.setRollover(true);
	    label=new JLabel("     ");
	    label.setPreferredSize(new Dimension(180,24));
	    hero=new JLabel(" nombre heros : " + carte.NB_HEROS);
	    hero.setPreferredSize(new Dimension(180,24));
	    monstre=new JLabel("  nombre Monstres : " + carte.NB_MONSTRES);
	    monstre.setPreferredSize(new Dimension(180,24));
		tools.add(newpartie);tools.addSeparator();tools.addSeparator();
		tools.add(fintour);tools.addSeparator();tools.addSeparator();
		tools.add(hero);tools.addSeparator();tools.addSeparator();
		tools.add(monstre);tools.addSeparator();tools.addSeparator();
		tools.add(sauvegarder);tools.addSeparator();tools.addSeparator();
		tools.add(charger);tools.addSeparator();tools.addSeparator();
		tools.setBackground(Color.CYAN);

		
		this.add(tools,BorderLayout.NORTH);
		this.add(zone,BorderLayout.CENTER);
		this.add(label,BorderLayout.SOUTH);
		zone.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				int i = e.getX()/IConfig.NB_PIX_CASE;
				int j = e.getY()/IConfig.NB_PIX_CASE;
				Position p = new Position(i,j);
				
				if(i < IConfig.LARGEUR_CARTE && j < IConfig.HAUTEUR_CARTE){
					if(carte.getElement(p) != null && carte.getVision()[j][i] == 1)
						label.setText(p.toString()+" "+carte.getElement(p).toString());
					else
						label.setText("");
					label.repaint();
				}
			}
			
		});
		
		zone.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int i = e.getX()/IConfig.NB_PIX_CASE;
				int j = e.getY()/IConfig.NB_PIX_CASE;
				Position p = new Position(i,j);
				if(i < IConfig.LARGEUR_CARTE && j < IConfig.HAUTEUR_CARTE){
					Position p2;
					try{
						if(!select)
							p2 = carte.trouveHeros(p).getPosition();
						else
							p2 = p;
						
						if(carte.getElement(p2) instanceof Heros && carte.getElement(p2).couleur == COULEUR_HEROS){
							init = p2;
							select = true;
						}else{
							if(select){
								select = false;
								if(carte.getElement(p2) instanceof Monstre){
									if(((Soldat)carte.getElement(init)).combat((Soldat)carte.getElement(p2))){
										((Heros)carte.getElement(init)).actualiseCouleur();
										zone.repaint();
									}
								}
								if(carte.getElement(p2) == null){
									if(carte.deplaceSoldat(p2, (Soldat)carte.getElement(init))){
										((Heros)carte.getElement(p2)).actualiseCouleur();
										zone.repaint();
									}
								}
							}
						}
					}
					catch(Exception except){}; //l'exception permet de ne pas avoir d'erreur quand on ne trouve pas de heros
						/*if(carte.getElement(p) == null){
							carte.deplaceSoldat(p, (Soldat)carte.getElement(init));
							zone.repaint();
						}*/
					/*}else{
						Position p2 = carte.trouveHeros(p).getPosition();
						if(carte.trouveHeros(p2) instanceof Heros && carte.getElement(p2).couleur == COULEUR_HEROS){
							init = p2;
							select = true;
						}
					}*/
				}
			}
			
		});
		
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

} 
