package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * This class handles the creation of triangular shapes
 * @author Dasha
 *
 */
public class FactoryTriangular {
	public static Figure gfigure(ArrayList<Point2D>parametres){
		Factory.rejetpoint(parametres);
		if (Factory.longueursEgales(parametres))
			return new TriangleEquilateral(parametres);
		else if(Factory.aAngledroit(parametres) && isIsocele(parametres))
			return new TriangleIsoceleRectangle(parametres);
		else if(Factory.aAngledroit(parametres))
			return new TriangleRectangle(parametres);
		else if(isIsocele(parametres))
			return new TriangleIsocele(parametres);
		else
			return new Triangle(parametres);
		
	}
	/**
	 * Verifies if 3 points form an Isosceles triangle
	 * @param parametres is an ArrayList of points
	 * @return true if the 3 points form an Isosceles triangle
	 */
	public static boolean isIsocele(ArrayList<Point2D>parametres){
		Figure figure = new Figure(parametres);
		double longueurs[]=figure.longueurs();
		if( longueurs[0]==longueurs [1] || longueurs[1]==longueurs[2] || longueurs[0]==longueurs[2] )
			return true;
		return false;
	}

}
