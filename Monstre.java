package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

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
	
	
	/*Methode qui definit naivement ce que les monstres vont faire...*/
	public void agir(){
		boolean act; //0 si le monstre n'agit pas
		
		/* Le monstre va t-il rien faire pendant le tour ou agir pour de vrai ? */
		act = getRandomBoolean();
		
		/* S'il agit... */
		if (act) {
			Heros h = carte.trouveHeros(pos); //potentiel adversaire
			
			/*S'il y a un heros dans les parages ...*/
			if (h != null){
				
				/*Le monstre le degomme! */
				this.combat(h, carte);
			}
			
			/* Sinon il voit s'il peut se deplacer...*/
			else {
			/* D'abord, choisir aleatoirement une position vide ou aller */
				Position p = carte.trouvePositionVide(pos);
				
				if (p != null) {
					if (!carte.deplaceSoldat(p, this)){
						this.repos(1); //Il se repose s'il se deplace pas
					}
				}
				else
					this.repos(1);
			}
		}
		
		/* S'il agit pas, il se repose aussi */
		else
			this.repos(1);
			
			
		
			
	}
	
	
	/*Methode qui va nous permettre de generer des booleens aleatoirement*/
	private boolean getRandomBoolean(){
		Random random = new Random();
		
		return random.nextBoolean();
	}
}

