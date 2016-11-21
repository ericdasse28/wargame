package wargame;

abstract public class Soldat extends Element implements ISoldat {
	  protected String nom;
	  protected int numS;
	  protected static int nbS = 0;
	  private static int nbTour = 0;
	  
	  public int getTour(){ return nbTour;}
	  public void joueTour(int tour){}
	  public void combat(Soldat soldat){}
	  public void seDeplace(Position newPos){}
}
