package wargame;

public class Monstre extends Soldat{
	private final String nom;
	private final TypesH type;
	private final int numM;
	private static int nbM = 0;
	private Carte carte;
	
	public Monstre(Carte carte, TypesH type, String nom, Position pos){
		super(type.getPoints());
		
		this.type = type;
		this.nom = nom;
		this.pos = pos;
		this.carte = carte;
		numM = ++nbM;
		numS = ++nbS;
	}
	
	public String getNom(){
		return nom;
	}
	
	public String getType(){
		if (type == Soldat.TypesH.HUMAIN)
			return "Humain";
		else if (type == Soldat.TypesH.NAIN)
			return "Nain";
		else if (type == Soldat.TypesH.ELF)
			return "Elf";
		else if (type == Soldat.TypesH.HOBBIT)
			return "Hobbit";
		return "Inconnu";
	}
	
	public int getNum(){
		return numM;
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
}
