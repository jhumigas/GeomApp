package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Handles the creation of quadrilateral shapes.
 * This class mainly checks the relations between the sides to identify a given type
 * of Quadrilateral. For instance, a Trapezoid has two sides that are parallel
 * whereas a Rhombus has its sides equal two-by-two in measure.
 * @author Dasha
 *
 */
public class FactoryQuadrilateral {
	
	public static Figure gfigure(ArrayList<Point2D> parameters){
		// Check if the Quadrilateral has no sides that cross each other
		if(isConvexQuad(parameters)){
			if (Factory.hasEqualSides(parameters) && Factory.hasRightAngle(parameters)){
				return new Square(parameters);
			}
			else if(Factory.hasEqualSides(parameters)){
				return new Rhombus(parameters);
			}
			else if(Factory.hasRightAngle(parameters) && isParallelogram(parameters))
				return new Rectangle(parameters);
			else if(isParallelogram(parameters))
				return new Parallelogram(parameters);
			else if(isTrapezoid(parameters))
				return new Trapezoid(parameters);
			else 
				return new ConvexQuadrilateral(parameters);
			}
		else
			return new Quadrilateral(parameters);
		
	}
	/**
	 * Verifies if a quadrilateral shape is a trapezoid i.e there are two sides that are parallel.
	 * Uses the fact that if the sum of two consecutive angles are equal to 180 degrees, their sine are equal.
	 *
	 * @param points is an ArrayList of points
	 * @return true if the shape is trapezoid
	 */
	public static boolean isTrapezoid(ArrayList<Point2D> points){

		double sin []=Factory.sins(points);
		if ((sin[0]==sin[1])||sin[1]==sin[2]) // two angles are supplementary
			return true;
		return false;
	}
	/**
	 * Verifies if a quadrilateral shape has two sides that are equal.
	 *
	 * @param points is an ArrayList of points.
	 * @return true if two opposing sides have the same length
	 */
	public static boolean isParallelogram(ArrayList<Point2D>points){
		// Check if two side have the same length
		if (points.get(0).distance(points.get(1)) == points.get(2).distance(points.get(3)))
				return true;
		return false;
	}
	/**
	 * Verifies if the shape is convex i.e if two sides cross each other.
	 * @param points ArrayList of the quadrilateral points.
	 * @return true if the quadrilateral is convex
	 */
	public static boolean isConvexQuad(ArrayList<Point2D> points){
		double sin[]=Factory.sins(points);
		boolean convexity = true;
		for(int i = 0;i<sin.length;i++)
			if(sin[i]<0 && Math.abs(sin[i])!=1)
				convexity = false;
		return convexity;
	}


}
