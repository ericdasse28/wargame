package wargame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PlateauJeu extends JPanel{
	Dimension dim;
	
	PlateauJeu(){
		dim = new Dimension(IConfig.NB_PIX_CASE*IConfig.LARGEUR_CARTE,IConfig.NB_PIX_CASE*IConfig.HAUTEUR_CARTE);
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.BLACK);
		for(int i = 0; i < IConfig.LARGEUR_CARTE; i++){
			for(int j = 0; j < IConfig.HAUTEUR_CARTE; j++){
				g.setColor(Color.BLACK);
				g.drawRect(i * IConfig.NB_PIX_CASE, j * IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
				//g.setColor(c);	Couleur de la case
				//g.fillRect(i * IConfig.NB_PIX_CASE, j * IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE,IConfig.NB_PIX_CASE);
			}
		}
	}

}
