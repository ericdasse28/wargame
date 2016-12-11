package wargame;

import java.awt.Graphics;

public class Heros extends Soldat implements IConfig {
	private final char nom;
	private final TypesH type;
	private final int numH;
	private static int nbH = 0;
	private static int nbTrH = 0; //nombre de heros ayant joue
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
	
	
	//Retourne le nombre de heros ayant deja joue lors d'un tour
	public static int getNBH(){
		return nbTrH;
	}
	
	
	//Incremente le nombre de heros ayant joue pendant le tour
	public static void incNBH() {	
		if (getNBH() < NB_HEROS)
			nbTrH++;
		else nbTrH = 0; //remet a 0 si tous les heros ont joue
	}
	
	
	//Mutateur de la donnee statique 'nbTrH'
	public static void setNBH(int val) {
		nbTrH = val;
	}
	
	
	//Change la couleur du heros selon qu'il ait deja joue ou pas
	public void actualiseCouleur(){
		if(couleur == COULEUR_HEROS)
			couleur = COULEUR_HEROS_DEJA_JOUE;
		else
			couleur = COULEUR_HEROS;
	}
	
	
	//Retourne le nom du heros sous forme de caractere
	public char getNomC(){
		return nom;
	}
	
	
	//Retourne le nom du heros sous forme de String
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
		g.setColor(COULEUR_TEXTE);
		g.drawString(Character.toString(nom), pos.getX()*NB_PIX_CASE + NB_PIX_CASE/2, pos.getY() *NB_PIX_CASE + NB_PIX_CASE/2);
	}


}