package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Monstre extends Soldat{
	private final int nom; //ici nom est plutôt un int
	private final TypesM type;
	private final int numM;
	private static int nbM = 0;
	private Carte carte;
	
	public Monstre(Carte carte, TypesM type, int nom, Position pos){
		super(type.getPoints());
		
		this.type = type;
		this.nom = nom;
		this.pos = new Position(pos.getX(), pos.getY());
		this.carte = carte;
		numM = ++nbM;
		numS = ++nbS;
		couleur = COULEUR_MONSTRES;
	}
	
	
	//Retourne le nom sous forme de int
	public int getNomI(){
		return nom;
	}
	
	
	//Retourne le nom sous forme de chaines de caracteres
	public String getNom(){
		return Integer.toString(nom);
	}
	
	public TypesM getType(){
		return type;
	}
	
	public int getNum(){
		return numM;
	}
	
	
	public Carte getCarte(){
		return carte;
	}
	
	public String toString(){
		String ch = "";
		
		if (type == Soldat.TypesM.TROLL)
			ch += "TROLL";
		else if (type == Soldat.TypesM.ORC)
			ch += "ORC";
		else if (type == Soldat.TypesM.GOBELIN)
			ch += "GOBELIN";
		
		
		else ch += "INCONNU";
		
		return ch+" "+nom+" "+super.toString();
	}
	
	public void seDessiner(Graphics g){
		super.seDessiner(g);
		g.setColor(Color.WHITE);
		g.drawString(Integer.toString(nom), pos.getX()*NB_PIX_CASE + NB_PIX_CASE/2, pos.getY()*NB_PIX_CASE + NB_PIX_CASE/2);
	}
	
}
