package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Handles the creation of quadrilateral shapes
 * @author Dasha
 *
 */
public class FactoryQuadrilateral {
	
	public static Figure gfigure(ArrayList<Point2D> parameters){
		Factory.rejectALinedPoint(parameters);
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
	 * Verifies if a quadrilateral shape is trapezois i.e there are two segment that are parallel 
	 * Uses the fact that the sum of two consecutive angles is equal to 180 degrees hence their sin are equal
	 * @param points is an ArrayList of points
	 * @return true if the shape is trapezois
	 */
	public static boolean isTrapezoid(ArrayList<Point2D> points){

		double sin []=Factory.sins(points);
		if ((sin[0]==sin[1])||sin[1]==sin[2]) //si deux angles sont supplémentaires
			return true;
		return false;
	}
	/**
	 * Verifies if a quadrilateral shape has two segments that have the same length
	 * @param points is an ArrayList of points
	 * @return
	 */
	public static boolean isParallelogram(ArrayList<Point2D>points){
		//verifier si les cotes deux à deux ont la meme Length
		if (points.get(0).distance(points.get(1)) == points.get(2).distance(points.get(3)))
				return true;
		return false;
	}
	/**
	 * Verifies if the shape is convex i.e if two segments cross each other
	 * @param points
	 * @return
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
