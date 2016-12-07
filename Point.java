package wargame;


public class Point {
	private double x;
	private double y;
	static final int SAM = 1; //Sens des Aiguilles d'une Montre
	static final int SIAM = -1; //Sens Inverse des Aiguilles d'une Montre
	static final int ALIGNES = 0;

	
	public Point(double x, double y){
	    this.x = x;
	    this.y = y;
	}

	public Point(){
	    this(Math.random()*300, Math.random()*300);
	}

	
	public double getX(){
	    return this.x;
	}

	public double getY(){
	    return this.y;
	}

	public void setX(double x){
		this.x = x;
	}
	
	public void setY(double y){
		this.y = y;
	}
	
	public double distance(Point p){
	    double dif1 = p.getX() - this.getX();
	    double dif2 = p.getY() - this.getY();
	    
	    return Math.sqrt(Math.pow(dif1, 2) + Math.pow(dif2, 2));
	}

	public double distance(){
	    return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}

	
	public String toString(){
		return "("+getX()+","+getY()+")";
	}
}