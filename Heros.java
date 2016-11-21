package wargame;

public class Heros extends Soldat {
	private final String nom;
	private final TypesH type;
	private final int numH;
	private static int nbH = 0;
	private Carte carte;
	
	public Heros(Carte carte, TypesH type, String nom, Position pos){
		this.type = type;
		this.nom = nom;
		this.setPosition(pos);;
		this.carte = carte;
		numH = ++nbH;
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
		return numH;
	}
	
	public int getPortee(){
		return type.getPortee();
	}
	
	public int getPoints(){
		return type.getPoints();
	}
}
