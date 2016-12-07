package wargame;


import java.awt.Graphics;

abstract public class Soldat extends Element implements ISoldat, Dessinable {
	  //protected String nom;
	  protected int numS;
	  protected static int nbS = 0;
	  private static int nbTour = 0;
	  protected final int pointsDeVie_MAX;
	  protected int pointsDeVie;
	  protected boolean mort = false; //false si mort...
	  
	  Soldat(int pts){
		  pointsDeVie_MAX = pointsDeVie = pts;
	  }
	  
	  public boolean getEtat(){
		  return mort;
	  }
	  
	  public void setEtat(boolean b){
		  mort = b;
	  }
	  
	  public int getTour(){ return nbTour;}
	  public void joueTour(int tour){}
	  public void seDessiner(Graphics g){
		  if (!mort)
			  super.seDessiner(g);
	  }
	  
	  public String toString(){
		  return " "+pointsDeVie+"PV/"+pointsDeVie_MAX+", TIR : "+getTir()+", PUISSANCE : "+ getPuissance()+", PORTEE : " + getPortee();
	  }

	  public int getPortee(){
		  if (this instanceof Heros)
			  return ((Heros)this).getType().getPortee();
		
			  return ((Monstre)this).getType().getPortee();
	  }
	  
	  public int getPuissance(){
		  if (this instanceof Heros)
			  return ((Heros)this).getType().getPuissance();
	
			  return ((Monstre)this).getType().getPuissance();
	  }
	  
	  public int getTir(){
		  if (this instanceof Heros)
			  return ((Heros)this).getType().getTir();
		 
			  return ((Monstre)this).getType().getTir();
	  }
	  public int getPoints(){
		  if (this instanceof Heros)
			  return ((Heros)this).getType().getPoints();
		  
			  return ((Monstre)this).getType().getPoints();
	  }
	
	 public void combat(Soldat soldat, Carte carte) {
    	int frappe1;// of this
    	int frappe2;//of soldat
    	
    	if (((this instanceof Heros) && (soldat instanceof Monstre)) || ((this instanceof Monstre) && (soldat instanceof Heros))){
    		if (this.pos.estVoisine(soldat.pos)){// combat corps à corps
    			frappe1 =(int) (Math.random()*(this.getPuissance()));
    			frappe2 = (int) (Math.random()*soldat.getPuissance());
    			
    			soldat.pointsDeVie=(soldat.getPoints()-frappe1);
    			
    			if(soldat.getPoints()>0){
    				this.pointsDeVie = pointsDeVie-frappe2;
    				
    				if(this.getPoints()<=0){
    					this.pointsDeVie=0;
    					carte.mort(this);
    				}
    			}
    			else {
    				soldat.pointsDeVie=0;
    				mort = true;
    				carte.mort(soldat);
    			}
    		}
    		else {//combat à distance
    			if (this.getPortee() >= pos.dist(soldat.pos)){
    				frappe1 = (int)  (Math.random()*(getTir()));
    				frappe2 = (int) (Math.random()*(soldat.getTir()));
    				soldat.pointsDeVie=soldat.getPoints()-frappe1;

    				if(soldat.getPoints()>0){
    					this.pointsDeVie=this.getPoints()-frappe2;

    					if(this.getPoints()<=0){

    						this.pointsDeVie=0;
    						carte.mort(this);

    					}
    				}
    				else {
    					soldat.pointsDeVie=0;
    					mort = true;
    					carte.mort(soldat);
    				}
    			}
    		}
    	}
	 }
  

	public void seDeplace(Position newPos){
		this.setPosition(newPos);
	
	}
}
