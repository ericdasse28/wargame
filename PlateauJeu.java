package wargame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
	JButton newpartie,fintour;
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
					if(select){
						select = false;
						if(carte.getElement(p) == null){
							carte.deplaceSoldat(p, (Soldat)carte.getElement(init));
							zone.repaint();
						}
					}else{
						Position p2 = carte.trouveHeros(p).getPosition();
						if(carte.trouveHeros(p2) instanceof Heros){
							init = p2;
							select = true;
						}
					}
				}
			}
			
		});
		
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

} 
