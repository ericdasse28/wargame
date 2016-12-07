package wargame;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Element implements IConfig, Dessinable {
	protected Position pos; //position de l'element sur la carte
	protected Color couleur;
	
	/*public Element (int x, int y){
		pos.setX(x);
		pos.setY(y);
	}
	
	public Element (Position pos){
		pos.setX(pos.getX());
		pos.setY(pos.getY());
	}*/
	
	public Position getPosition(){
		return pos;
	}
	
	public void setPosition(int x, int y){
		pos = new Position(x,y);
	}
	
	public void setPosition(Position p){
		pos = p;
	}
	
	/*Change la couleur de la position*/
	public void seDessiner(Graphics g){
		pos.setCoul(couleur);
		pos.seDessiner(g);
	}
	

}
