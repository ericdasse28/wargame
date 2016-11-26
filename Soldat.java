package wargame;

abstract public class Soldat extends Element implements ISoldat {
	  protected String nom;
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
	  public void combat(Soldat soldat){}
	  public void seDeplace(Position newPos){}
}
