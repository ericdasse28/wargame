package wargame;

import java.awt.Color;
import java.awt.Graphics;

import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte, IConfig{
	Element[][] grille; //Les indices correspondent directement aux positions
	Heros[] listeH;
	Monstre[] listeM;
	Obstacle[] listeO;
	private int vision[][];
	private int[] action;
	
	public int[][] getVision(){
		return vision;
	}
	
	public void resetAction(){
		for(int i =0; i < action.length; i++)
			action[i] = 0;
	}
	
	
	
	public Carte(){
		grille = new Element[LARGEUR_CARTE][HAUTEUR_CARTE];
		listeH = new Heros[NB_HEROS];
		action = new int[NB_HEROS];
		listeM = new Monstre[NB_MONSTRES];
		listeO = new Obstacle[NB_OBSTACLES];
		vision = new int[HAUTEUR_CARTE][LARGEUR_CARTE];
		
		//Positionnement des obstacles sur la carte
		for (int i = 0; i < NB_OBSTACLES; i++){
			Position p;
		
				p = trouvePositionVide();
				
			
			/*Maintenant on peut inserer*/
			grille[p.getX()][p.getY()] = listeO[i] = new Obstacle(TypeObstacle.getObstacleAlea(), p);
		}
		
		//Positionnement des heros sur la carte
		for (int i = 'A'; i < 'A' + NB_HEROS; i++){
			Position p;
			
			do {
				p = trouvePositionVide();
			} while (p.getX() >= (LARGEUR_CARTE/2));	
			// System.out.println(i);
			
			grille[p.getX()][p.getY()] = listeH[i - 'A'] = new Heros(this, ISoldat.TypesH.getTypeHAlea(), (char) i, p);
		}
		
		//Positionnement des monstres sur la carte
		for (int i = 0; i < NB_MONSTRES; i++){
			Position p;
			
			do {
				p = trouvePositionVide();
			} while (p.getX() < (LARGEUR_CARTE/2));
			
			grille[p.getX()][p.getY()] = listeM[i] = new Monstre(this, ISoldat.TypesM.getTypeMAlea(), 1+i, p);
		}
	}
	
	@Override
	public Element getElement(Position pos) {
		return grille[pos.getX()][pos.getY()];
	}

	@Override
	public Position trouvePositionVide() {
		/*
		int x = (int) (Math.random() * (IConfig.LARGEUR_CARTE - 1));
		int y = (int) (Math.random() * (IConfig.HAUTEUR_CARTE - 1));
		
		while(grille[x][y] != null && !((new Position(x, y)).estValide())){
			x = (int) (Math.random() * (IConfig.LARGEUR_CARTE - 1));
			y = (int) (Math.random() * (IConfig.HAUTEUR_CARTE - 1));
		}
		return new Position(x,y);
		*/
		
		Position p;
		
		do {
			p = new Position((int) (Math.random()*(LARGEUR_CARTE+1)),
					(int) (Math.random()*(HAUTEUR_CARTE+1)));
		} while (p.estValide() || grille[p.getX()][p.getY()] != null);
		
		return p;
	}

	@Override
	public Position trouvePositionVide(Position pos) {
		//si la case est vide on la renvoie
		if(grille[pos.getX()][pos.getY()] == null)
			return pos;
		
		//sinon on calcule les positions possibles
		int x = pos.getX();
		int y = pos.getY();
		Position [] p = new Position[9];
		int l = 0;
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				p[l] = new Position(x+i, y+j);
				l++;
			}
		}
		//on prepare le tableau random
		Position[] r = new Position[9];
		int rand;
		//pour chaque position calculee on la stock a la position rand du tableau r
		for(int i = 0; i < 9; i++){
			rand = (int) (Math.random() * 9);
			while(r[rand] != null)
				rand = (int) (Math.random() * 9);
			r[rand] = p[i];
		}
		//parcourt des positions dans l'ordre random jusqu'a trouver une position valide vide
		for(int i = 0; i < 9; i++){
			if(r[i].getX() >= 0 && r[i].getX() < IConfig.LARGEUR_CARTE && r[i].getY() >= 0 && r[i].getY() < IConfig.HAUTEUR_CARTE && grille[r[i].getX()][r[i].getY()] == null)
				return r[i];
		}
		//renvoyer null si aucune position valide vide trouvee
		return null;
	}

	@Override
	public Heros trouveHeros() {
		return listeH[(int) (Math.random() * listeH.length)];
	}

	@Override
	public Heros trouveHeros(Position pos) {
		//si la case est occupee par un heros on la renvoie
		if(grille[pos.getX()][pos.getY()] instanceof Heros)
			return (Heros)grille[pos.getX()][pos.getY()];
		
		//sinon on calcule les positions possibles
		int x = pos.getX();
		int y = pos.getY();
		Position [] p = new Position[9];
		int l = 0;
		for(int i = -1; i <= 1; i++){
			for(int j = -1; j <= 1; j++){
				p[l] = new Position(x+i, y+j);
				l++;
			}
		}
		//on prepare le tableau random
		Position[] r = new Position[9];
		int rand;
		//pour chaque position calculee on la stock a la position rand du tableau r
		for(int i = 0; i < 9; i++){
			rand = (int) (Math.random() * 9);
			while(r[rand] != null)
				rand = (int) (Math.random() * 9);
			r[rand] = p[i];
		}
		//parcourt des positions dans l'ordre random jusqu'a trouver un heros
		for(int i = 0; i < 9; i++){
			if(r[i].getX() >= 0 && r[i].getX() < IConfig.LARGEUR_CARTE && r[i].getY() >= 0 && r[i].getY() < IConfig.HAUTEUR_CARTE && grille[r[i].getX()][r[i].getY()] instanceof Heros)
				return (Heros) grille[r[i].getX()][r[i].getY()];
		}
		//renvoyer null si aucun heros ne se trouve dans les environs
		return null;
	}

	@Override
	public boolean deplaceSoldat(Position pos, Soldat soldat) {
		// TODO Auto-generated method stub
		if(pos.getX() >= 0 && pos.getY() >= 0 && pos.getX() < IConfig.LARGEUR_CARTE && pos.getY() < IConfig.HAUTEUR_CARTE && soldat.getPosition().estVoisine(pos)){
			grille[soldat.getPosition().getX()][soldat.getPosition().getY()]=null;//mise à null de la position ancienne
			soldat.seDeplace(pos);	//actualisation de la position du soldat
			((Heros) soldat).actualiseCouleur();
			grille[soldat.getPosition().getX()][soldat.getPosition().getY()]=soldat; //actualisation de la carte
			return true;
		}
		return false;
	}

	@Override
	public void mort(Soldat perso) {
		grille[perso.getPosition().getX()][perso.getPosition().getY()] = null;
		if(perso instanceof Heros){
			Heros[] liste = new Heros[listeH.length-1];
			for(int i = 0; i < listeH.length; i++)
				if(perso != listeH[i])
					liste[i] = listeH[i];
			listeH = liste;
		}else{
			Monstre[] liste = new Monstre[listeM.length-1];
			for(int i = 0; i < listeM.length; i++)
				if(perso != listeM[i])
					liste[i] = listeM[i];
			listeM = liste;
		}
	}

	@Override
	public boolean actionHeros(Position pos, Position pos2) {
		//verification que pos donne acces a un heros
		if(pos.getX() >= 0 && pos.getY() >=0 && pos.getX() < IConfig.LARGEUR_CARTE && pos.getY() < IConfig.HAUTEUR_CARTE && grille[pos.getX()][pos.getY()] instanceof Heros)
			//verification que pos2 est valide et non obstacle ni occupee par un heros
			if(pos2.getX() >= 0 && pos2.getY() >=0 && pos2.getX() < IConfig.LARGEUR_CARTE && pos2.getY() < IConfig.HAUTEUR_CARTE && !(grille[pos2.getX()][pos2.getY()] instanceof Obstacle || grille[pos2.getX()][pos2.getY()] instanceof Heros)){
				/*
				 * ICI Portee = portee d'attaque mais aussi de deplacement
				 */
				//si pos2 donne monstre alors combat si possible
				if(grille[pos2.getX()][pos2.getY()] instanceof Monstre && ((Heros)grille[pos.getX()][pos.getY()]).getPortee() >= (Math.abs(pos.getX() - pos2.getX()) + Math.abs(pos.getY() - pos2.getY()) - 1)){
					((Heros)grille[pos.getX()][pos.getY()]).combat((Monstre)grille[pos2.getX()][pos2.getY()], this);
					return true;
				}
				//si pos2 donne vide alors deplacement si possible (verification rapide = teleportation)
				if(grille[pos2.getX()][pos2.getY()] == null && ((Heros)grille[pos.getX()][pos.getY()]).getPortee() >= (Math.abs(pos.getX() - pos2.getX()) + Math.abs(pos.getY() - pos2.getY()))){
					//((Heros)grille[pos.getX()][pos.getY()]).seDeplace(grille[pos2.getX()][pos2.getY()]);
					((Heros)grille[pos.getX()][pos.getY()]).seDeplace(pos2);
					return true;
				}
			}
		return false;
	}

	@Override
	public void jouerSoldats(PanneauJeu pj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toutDessiner(Graphics g) {
		/*for(int i = 0; i < IConfig.LARGEUR_CARTE; i++){
			for(int j = 0; j < IConfig.HAUTEUR_CARTE; j++){
				if(grille[i][j] instanceof Heros)
					g.setColor(IConfig.COULEUR_HEROS);	//Couleur heros
				else
					if(grille[i][j] instanceof Monstre)	//Couleur monstres
						g.setColor(IConfig.COULEUR_MONSTRES);
					else
						if(grille[i][j] instanceof Obstacle)	//Couleur obstacle (ROCHER SEULEMENT POUR TESTER)
							g.setColor( IConfig.COULEUR_ROCHER );
						else
							g.setColor(Color.GREEN);	//Couleur du plateau
				g.fillRect(i * IConfig.NB_PIX_CASE + 1, j * IConfig.NB_PIX_CASE - 1,IConfig.NB_PIX_CASE + 1,IConfig.NB_PIX_CASE - 1); //dessinage
			}*/ //A abandonner, pas tres pratique 
		
		
			//int x1 = 0;
			//int y1 = 0;
			//int x2 = 0;
			//int y2 = LARGEUR_CARTE;
			
		
			//Actualisation de la vision
			for(int l = 0; l < HAUTEUR_CARTE; l++)
				for(int c = 0; c < LARGEUR_CARTE; c++)
					vision[l][c] = 0;
		
			//Affichage des heros
			for (int i = 0; i<NB_HEROS; i++){
				listeH[i].seDessiner(g);
				for(int l = listeH[i].getPosition().getY()-listeH[i].getPortee(); l <= listeH[i].getPosition().getY()+listeH[i].getPortee(); l++)
					for(int c = listeH[i].getPosition().getX()-listeH[i].getPortee(); c <= listeH[i].getPosition().getX()+listeH[i].getPortee(); c++)
						if(l >= 0 && c >= 0 && l < HAUTEUR_CARTE && c < LARGEUR_CARTE)
							vision[l][c] = 1;
			}
			
			//Affichage des monstres
			for (int i = 0; i<NB_MONSTRES; i++){
				listeM[i].seDessiner(g);
			}
			
			//Affichage des obstacles
			for (int i = 0; i<NB_OBSTACLES; i++){
				listeO[i].seDessiner(g);
			}
			
			//Affichage du FOG
			g.setColor(COULEUR_INCONNU);
			for(int l = 0; l < HAUTEUR_CARTE; l++)
				for(int c = 0; c < LARGEUR_CARTE; c++)
					if(vision[l][c] == 0)
						g.fillRect(c *NB_PIX_CASE, l * NB_PIX_CASE, NB_PIX_CASE, NB_PIX_CASE);
			
			//Affichage des lignes de la carte
			for (int x=0; x<LARGEUR_CARTE;x++){
				g.setColor(Color.BLACK);
				g.drawLine(0,  x*NB_PIX_CASE, LARGEUR_CARTE*NB_PIX_CASE, x*NB_PIX_CASE);
					g.drawLine(x*NB_PIX_CASE, 0, x*NB_PIX_CASE, HAUTEUR_CARTE*NB_PIX_CASE);
			}
	}

}
