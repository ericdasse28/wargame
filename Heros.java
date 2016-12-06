package wargame;

import java.awt.Graphics;

public class Heros extends Soldat implements IConfig {
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
		this.setPosition(pos);;
		this.carte = carte;
		numH = ++nbH;
		numS = ++nbS;
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
		if (type == TypesH.HUMAIN)
			return "HUMAIN";
		else if (type == TypesH.NAIN)
			return "NAIN";
		else if (type == TypesH.ELF)
			return "ELF";
		else if (type == TypesH.HOBBIT)
			return "HOBBIT";
		
		
		return "INCONNU";
	}
	
	public void seDessiner(Graphics g){
		super.seDessiner(g);
		g.drawString(Character.toString(nom), pos.getX()*NB_PIX_CASE + NB_PIX_CASE/2, pos.getY() *NB_PIX_CASE + NB_PIX_CASE/2);
	}
}
