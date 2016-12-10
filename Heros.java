package wargame;

import java.awt.Graphics;
import java.io.Serializable;

public class Heros extends Soldat implements IConfig, Serializable {
	private final char nom;
	private final TypesH type;
	private final int numH;
	private static int nbH = 0;
	private Carte carte;
	
	public Heros(Carte carte, TypesH type, char nom, Position pos){
		super(type.getPoints());
		this.pos = new Position(pos.getX(), pos.getY());
		this.type = type;
		this.nom = nom;
		this.setPosition(pos);
		this.carte = carte;
		numH = ++nbH;
		numS = ++nbS;
		couleur = COULEUR_HEROS;
	}
	
	public void actualiseCouleur(){
		if(couleur == COULEUR_HEROS)
			couleur = COULEUR_HEROS_DEJA_JOUE;
		else
			couleur = COULEUR_HEROS;
	}
	
	public String getNom(){
		return Character.toString(nom);
	}
	
	public TypesH getType(){
		return type;
	}
	
	public int getNum(){
		return numH;
	}
	
	public int getPortee(){
		return type.getPortee();
	}
	
	public int getPoints(){
		return type.getPoints();
	}
	
	public Carte getCarte(){
		return carte;
	}
	
	public String toString(){
		String ch = "";
		
		if (type == TypesH.HUMAIN)
			 ch += "HUMAIN";
		else if (type == TypesH.NAIN)
			 ch += "NAIN";
		else if (type == TypesH.ELF)
			 ch += "ELF";
		else if (type == TypesH.HOBBIT)
			 ch += "HOBBIT";
		
		
		else ch += "INCONNU";
		 
		return ch+" "+nom+" "+super.toString();
	}
	
	public void seDessiner(Graphics g){
		super.seDessiner(g);
		g.drawString(Character.toString(nom), pos.getX()*NB_PIX_CASE + NB_PIX_CASE/2, pos.getY() *NB_PIX_CASE + NB_PIX_CASE/2);
	}
}
