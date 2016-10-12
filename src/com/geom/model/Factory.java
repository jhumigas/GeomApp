package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Class that implements the creation of a geometric shape
 * Handles aligned points and points that have the same coordinates
 * @author Dasha
 *
 */
public class Factory{
	/**
	 * Generates the geometric shape given an ArrayList of points
	 * @param parameters is an ArrayList of Points
	 * @return a new specific shape(triangle, square, Rhombus, etc)
	 */
	public static Figure gfigure (ArrayList<Point2D> parameters){
		Figure poly;
		rejectALinedPoint(parameters);
		switch(parameters.size()){
		case 0:
			poly = new EmptyFigure();
			break;
		case 1:
			poly = new Punct(parameters);
			break;
		case 2:
			poly = new Line(parameters);
			break;
		case 3:
			poly = FactoryTriangular.gfigure(parameters);
			break;
		case 4:
			poly = FactoryQuadrilateral.gfigure(parameters);
			break;
		default:
			poly= FactoryPolygonal.gfigure(parameters);
		}
		return poly;

	}
	/**
	 * Verifies if there are right angles in the shape
	 * @param parameters is an ArrayList of point
	 * @return true if the shape contains right angles
	 */
	public static boolean hasRightAngle(ArrayList<Point2D>parameters){
		//verifier si au moins un des angles de la figure est droit
		double values[]=coss(parameters);
		boolean droit = false;
		for(int i=0;i<values.length;i++)
			if(values[i]==0)
				droit =true;
		return droit;
		
	}
	/**
	 * Verifies if the lengths of the lines forming the shape are all equal
	 * @param parameters is an arrayList of Points
	 * @return true if the shape is equilateral
	 */
	public static boolean hasEqualSides(ArrayList<Point2D> parameters){
		
		boolean equi = true;
		double distance = parameters.get(0).distance(parameters.get(parameters.size()-1));
		int i = 0;
		while(i<parameters.size()-1){
			equi = equi && distance == parameters.get(i).distance(parameters.get(i+1)) ;
			i++;
		}
		return equi;		
	}
	/**
	 * Calculates the sinus of the angle between three points
	 * @param pt1
	 * @param pt2
	 * @param pt3
	 * @return the value of sin between the vector p3p2 and p1p2
	 */
	public static double sin(Point2D pt1, Point2D pt2, Point2D pt3){
		// renvoie le sinus l'angle entre p3p2 et p1p2
		double a [] = new double[2];
	    double b [] = new double[2];
		a [0] =pt3.getX()- pt2.getX();
		a [1]= pt3.getY()- pt2.getY();
		b [0] =pt1.getX()- pt2.getX();
		b [1]= pt1.getY()- pt2.getY();
		return (a[0]*b[1] -a[1]*b[0])/(pt3.distance(pt2) * pt1.distance(pt2));	
	}
	/**
	 * Calculates the cosinus of the angle between three points
	 * @param pt1
	 * @param pt2
	 * @param pt3
	 * @return the value of sin between the vector p3p2 and p1p2
	 */
	public static double cos(Point2D pt1, Point2D pt2, Point2D pt3){
		// renvoie le cosinus de l'angle entre p3p2 et p1p2
		double a [] = new double[2];
	    double b [] = new double[2];
		a [0] =pt3.getX()- pt2.getX();
		a [1]= pt3.getY()- pt2.getY();
		b [0] =pt1.getX()- pt2.getX();
		b [1]= pt1.getY()- pt2.getY();
		return (a[0]*b[0] +a[1]*b[1])/(pt3.distance(pt2) * pt1.distance(pt2));
		
	}
	public static double[] coss(ArrayList<Point2D>points){
		//renvoie une liste des cosinus des angles d'une figure
		double cos [] = new double[points.size()];
	    cos[0]= cos(points.get(1), points.get(0),points.get(points.size()-1));
		for(int i = 1; i< cos.length -1; i++){
			cos[i]= cos(points.get(i+1), points.get(i),points.get(i-1));
		}
		cos[cos.length -1]= cos(points.get(0), points.get(points.size()-1),points.get(points.size()-2));
		return cos;
	}
	
	public static double[] sins(ArrayList<Point2D>points){
		//renvoie une liste des sinus des angles d'une figure
		double sin [] = new double[points.size()];
	    sin[0]= sin(points.get(1), points.get(0),points.get(points.size()-1));
		for(int i = 1; i< sin.length -1; i++){
			sin[i]= sin(points.get(i+1), points.get(i),points.get(i-1));
		}
		sin[sin.length -1]= sin(points.get(0), points.get(points.size()-1),points.get(points.size()-2));
		return sin;
	}
	/**
	 * Deletes a new point aligned to the previous ones
	 * @param points
	 */
	public static void rejectALinedPoint(ArrayList<Point2D>points){
		//enlever le 3eme point aLiner avec deux autres
		//cas figures impossibles ?
		if(points.size()>2){
			double []sins= sins(points);
			int k = 0;
			int i = 1;
			while(i<sins.length-1){
				if(sins[i]==0){
					points.remove(i-k);
					k++;
				}
				i++;
			}
		}
	}
	/**
	 * Verifies if the picture is equiangular
	 * @param points is an ArrayList of points
	 * @return true if all the angles are equal
	 */
	public static boolean isEquiangular(ArrayList<Point2D>points){
		boolean equi = true;
		double cos[]= coss(points);
		double sin[]=sins(points);
		int i = 1;
		while(i<cos.length-1){
			equi = equi && (cos[0] == cos[i] && sin[0] == sin[i] );
			i++;
		}
		return equi;
	}


}