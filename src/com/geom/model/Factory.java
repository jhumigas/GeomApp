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
	 * Generates the geometric shapes given an ArrayList of points.
	 * The core idea is to first count the number of points then rely on specific properties
	 * about the sides to identify more specifically which Shape to build.
	 * Therefore, the Factory class is inherited by FactoryTriangular, FactoryQuadrilateral and FactoryPolygonal
	 * that are dedicated to a given family of shapes.
	 * @param parameters is an ArrayList of Points
	 * @return Figure i.e a new shape(triangle, square, Rhombus, etc)
	 */
	public static Figure gfigure (ArrayList<Point2D> parameters){
		Figure poly;
		// Remove points that are aligned among three consecutive points.
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
			poly = FactoryPolygonal.gfigure(parameters);
		}
		return poly;

	}
	/**
	 * Verifies if there are right angles in the shape.
	 * Relies on the fact that cos(Pi/2 + k*Pi) = 0, with k a given integer.
	 * @param parameters is an ArrayList of points.
	 * @return true if the shape contains right angles
	 */
	public static boolean hasRightAngle(ArrayList<Point2D>parameters){
		double values[] = coss(parameters);
		boolean isRight = false;
		for(int i=0; i<values.length; i++)
			if(values[i]==0)
				isRight = true;
		return isRight;
		
	}
	/**
	 * Verifies if the lengths of the sides are all equal.
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
	 * Computes the sine of the angle between three points.
	 * Use the properties of the cross product of two vectors in a 2D euclidean space.
	 * Here, we consider that counter-clockwise oriented angles are positive.
	 *
	 * @param pt1 Point defined by a location in a coordinate space
	 * @param pt2 Point defined by a location in a coordinate space
	 * @param pt3 Point defined by a location in a coordinate space
	 *
	 * @return the value of sin between the vector p2p3 and p2p1
	 */
	public static double sin(Point2D pt1, Point2D pt2, Point2D pt3){
		// Arrays to store the coordinates of the vectors
		double a[] = new double[2];
	    double b[] = new double[2];

		// Computing p3p2 and p1p2 vectors
		a[0] = pt3.getX() - pt2.getX();
		a[1] = pt3.getY() - pt2.getY();
		b[0] = pt1.getX() - pt2.getX();
		b[1] = pt1.getY() - pt2.getY();

		return (a[0]*b[1] - a[1]*b[0])/(pt3.distance(pt2) * pt1.distance(pt2));
	}

	/**
	 * Calculates the cosine of the angle between three points
	 * Use the properties of the scalar product of two vectors in a 2D euclidean space.
	 *
	 * @param pt1 Point defined by a location in a coordinate space
	 * @param pt2 Point defined by a location in a coordinate space
	 * @param pt3 Point defined by a location in a coordinate space
	 *
	 * @return the value of cosine between the vector p2p1 and p2p3
	 */
	public static double cos(Point2D pt1, Point2D pt2, Point2D pt3){
		// Arrays to store the coordinates of p3p2 and p1p2 vectors
		double a [] = new double[2];
	    double b [] = new double[2];

		// Computing p3p2 and p1p2 vectors
		a [0] =pt3.getX()- pt2.getX();
		a [1]= pt3.getY()- pt2.getY();
		b [0] =pt1.getX()- pt2.getX();
		b [1]= pt1.getY()- pt2.getY();

		return (a[0]*b[0] + a[1]*b[1])/(pt3.distance(pt2) * pt1.distance(pt2));
		
	}

	/**
	 * Calculates the cosine of the angles of a given polygon.
	 * The orientation of the shape will depend on the order of the points a priori.
	 *
	 * @param points ArrayList of the points of a shape
	 *
	 * @return an Array of the cosine of each angle
	 */
	public static double[] coss(ArrayList<Point2D>points){
		// Array of cosine values
		double cos [] = new double[points.size()];

	    cos[0]= cos(points.get(1), points.get(0), points.get(points.size()-1));

		for(int i = 1; i< cos.length -1; i++){
			cos[i]= cos(points.get(i+1), points.get(i),points.get(i-1));
		}

		cos[cos.length -1]= cos(points.get(0), points.get(points.size()-1),points.get(points.size()-2));
		return cos;
	}

	/**
	 * Calculates the sines of the angles of a given polygon.
	 * The orientation of the shape will depend on the order of the points a priori.
	 *
	 * @param points ArrayList of the points of a shape.
	 *
	 * @return an Array of the sine of each angle.
	 */
	public static double[] sins(ArrayList<Point2D>points){
		//Array in which the sines will be stored
		double sin [] = new double[points.size()];

	    sin[0]= sin(points.get(1), points.get(0), points.get(points.size()-1));
		for(int i = 1; i< sin.length -1; i++){
			sin[i]= sin(points.get(i+1), points.get(i),points.get(i-1));
		}
		sin[sin.length -1]= sin(points.get(0), points.get(points.size()-1),points.get(points.size()-2));
		return sin;
	}
	/**
	 * Should delete a point aligned to two other points.
	 * For three points p1, p2, p3, this method will compute the sine of (p2p3, p2p1)
	 * If the sine is null, it will remove p2.
	 * @param points ArrayList of Points of a given geometric shape
	 */
	public static void rejectALinedPoint(ArrayList<Point2D>points){

		//cas figures impossibles ?
		if(points.size()>2){
			// Array of Sines
			double []sins= sins(points);
			// Number of points removed
			int k = 0;
			// Current index of the angle considered

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
	 * Verifies if the picture is equiangular.
	 * Relies on the fact that two angles are equaled in measure once their sine and cosine are equal.
	 *
	 * @param points is an ArrayList of points.
	 * @return true if all the angles are equal.
	 */
	public static boolean isEquiangular(ArrayList<Point2D>points){
		boolean equi = true;
		// Arrays of cosines and sines
		double cos[]= coss(points);
		double sin[]=sins(points);
		int i = 1;
		while(i<cos.length-1){
			equi = equi && (cos[0] == cos[i] && sin[0] == sin[i]);
			i++;
		}
		return equi;
	}


}