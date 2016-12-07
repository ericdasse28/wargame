package wargame;

import java.awt.Color;
import java.awt.Graphics;

public class Position implements IConfig, Dessinable {
	private int x, y;
	private Color couleur;
	
	Position(int x, int y) { 
		this.x = x; 
		this.y = y;
		
		
	}
	public int getX() { return x; }
	public int getY() { return y; }
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	
	/*Renvoie la couleur a la posiion courante
	 * @return Color
	 * */
	public Color getCoul(){
		return couleur;
	}
	
	/*Modifie la couleur a la position courante*/
	public void setCoul(Color color){
		couleur = color;
	}
	
	//return true si la position n'est pas valide
	public boolean estValide() {
		return (x<0 || x>=LARGEUR_CARTE || y<0 || y>=HAUTEUR_CARTE);
	}
	
	public String toString() { return "("+x+","+y+")"; }
	
	public boolean estVoisine(Position pos) {
		return ((Math.abs(x-pos.x)<=1) && (Math.abs(y-pos.y)<=1));
	}
	
	/*On dessine une case*/
	public void seDessiner(Graphics g){
		g.setColor(couleur);
		g.fillRect(x *NB_PIX_CASE, y * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
	}
	
	
	
}