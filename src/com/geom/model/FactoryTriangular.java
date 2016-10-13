package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * This class handles the creation of triangular shapes
 * @author Dasha
 *
 */
public class FactoryTriangular {
	public static Figure gfigure(ArrayList<Point2D>parameters){
		//Factory.rejectALinedPoint(parameters);
		if (Factory.hasEqualSides(parameters))
			return new EquilateralTriangle(parameters);
		else if(Factory.hasRightAngle(parameters) && isIsosceles(parameters))
			return new RightIsoscelesTriangle(parameters);
		else if(Factory.hasRightAngle(parameters))
			return new RightTriangle(parameters);
		else if(isIsosceles(parameters))
			return new IsoscelesTriangle(parameters);
		else
			return new Triangle(parameters);
		
	}
	/**
	 * Verifies if 3 points form an Isosceles triangle
	 * @param parameters is an ArrayList of points
	 * @return true if the 3 points form an Isosceles triangle
	 */
	public static boolean isIsosceles(ArrayList<Point2D>parameters){
		Figure figure = new Figure(parameters);
		double lengths[]=figure.lengths();
		if( lengths[0]==lengths [1] || lengths[1]==lengths[2] || lengths[0]==lengths[2] )
			return true;
		return false;
	}

}
