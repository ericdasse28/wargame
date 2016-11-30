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
	  
	  public int getTour(){ 
		  return nbTour;
	  }
	  public void joueTour(int tour){};
	 public void combat(Soldat soldat, Carte carte) {
    	int frappe1;// of this
    	int frappe2;//of soldat
    	if (((this instanceof Heros) && (soldat instanceof Monstre)) || ((this instanceof Monstre) && (soldat instanceof Heros))){
    		if (this.pos.estVoisine(soldat.pos)){// combat corps à corps
    			frappe1 =(int) (Math.random()*(this.Puissance));
    			frappe2 = (int) (Math.random()*(soldat.Puissance));
    			soldat.Points_de_Vie=(soldat.getPoints()-frappe1);
    			if(soldat.getPoints()>0){
    				this.Points_de_Vie=Points_de_Vie-frappe2;
    				if(this.getPoints()<=0){
    					this.Points_de_Vie=0;
    					carte.mort(this);
    				}
    			}
    			else {
    				soldat.Points_de_Vie=0;
    				carte.mort(soldat);
    			}
    		}
    		else {//combat à distance
    			if (this.getPortee() <= pos.dist(soldat.pos)){
    				frappe1 = (int)  (Math.random()*(Tir));
    				frappe2 = (int) (Math.random()*(soldat.Tir));
    				soldat.Points_de_Vie=soldat.getPoints()-frappe1;

    				if(soldat.getPoints()>0){
    					this.Points_de_Vie=this.getPoints()-frappe2;

    					if(this.getPoints()<=0){

    						this.Points_de_Vie=0;
    						carte.mort(this);

    					}
    				}
    				else {
    					soldat.Points_de_Vie=0;
    					carte.mort(soldat);
    				}
    			}
    		}
    	}
   }

	public void seDeplace(Position Newpos){
		this.setPosition(Newpos);
	
		}
			
}
