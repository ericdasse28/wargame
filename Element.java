package wargame;

public abstract class Element {
	protected Position pos; //position de l'element sur la carte
	
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
		pos.setX(x);
		pos.setY(y);
	}
	
	public void setPosition(Position pos){
		pos.setX(pos.getX());
		pos.setX(pos.getY());
	}
}
