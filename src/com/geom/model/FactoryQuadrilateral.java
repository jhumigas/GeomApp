package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class FactoryQuadrilateral {
	
	public static Figure gfigure(ArrayList<Point2D> parametres){
		Factory.rejetpoint(parametres);
		if(isConvexQuad(parametres)){
			if (Factory.longueursEgales(parametres) && Factory.aAngledroit(parametres)){
				return new Carre(parametres);
			}
			else if(Factory.longueursEgales(parametres)){
				return new Losange(parametres);
			}
			else if(Factory.aAngledroit(parametres) && isParallelogramme(parametres))
				return new Rectangle(parametres);
			else if(isParallelogramme(parametres))
				return new Parallelogramme(parametres);
			else if(isTrapeze(parametres))
				return new Trapeze(parametres);
			else 
				return new QuadrilatereConvexe(parametres);
			}
		else
			return new Quadrilatere(parametres);
		
	}
	
	public static boolean isTrapeze(ArrayList<Point2D> points){

		double sin []=Factory.sins(points);
		if ((sin[0]==sin[1])||sin[1]==sin[2]) //si deux angles sont supplémentaires
			return true;
		return false;
	}
	public static boolean isParallelogramme(ArrayList<Point2D>points){
		//verifier si les cotes deux à deux ont la meme longueur
		if (points.get(0).distance(points.get(1)) == points.get(2).distance(points.get(3)))
				return true;
		return false;
	}
	public static boolean isConvexQuad(ArrayList<Point2D> points){
		double sin[]=Factory.sins(points);
		boolean convexity = true;
		for(int i = 0;i<sin.length;i++)
			if(sin[i]<0 && Math.abs(sin[i])!=1)
				convexity = false;
		return convexity;
	}


}
