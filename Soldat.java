package wargame;

import java.awt.Graphics;

abstract public class Soldat extends Element implements ISoldat, Dessinable {
	  //protected String nom;
	  protected int numS;
	  protected static int nbS = 0;
	  private static int nbTour = 0;
	  protected final int POINTS_DE_VIE_MAX;
	  protected int pointsDeVie;
	  
	  Soldat(int pts){
		  POINTS_DE_VIE_MAX = pointsDeVie = pts;
	  }
	  
	  public int getTour(){ return nbTour;}
	  public void joueTour(int tour){}
	  public Boolean combat(Soldat soldat){
		  if(Math.abs(soldat.getPosition().getX()-this.getPosition().getX()) + Math.abs(soldat.getPosition().getY()-this.getPosition().getY()) <= this.getPortee()){
			  //combat
			  return true;
		  }
		  return false;
	  }
	  public void seDeplace(Position newPos){
		  this.setPosition(newPos);
	  }
	  public void seDessiner(Graphics g){
		  super.seDessiner(g);
		  //pos.seDessiner(g);
		  
		  g.setColor(COULEUR_TEXTE);
		  //g.drawString(nom, pos.getX(), pos.getY());
	  }
	  
	  public String toString(){
		  return "("+pointsDeVie+"PV / "+POINTS_DE_VIE_MAX+")";
	  }
}
