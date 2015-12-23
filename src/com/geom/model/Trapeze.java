package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements trapezois
 * In Euclidean geometry, a convex quadrilateral with at least one pair of parallel sides is referred to as a trapezoid
 * in American and Canadian English but as a trapezium in English outside North America
 * @author Dasha
 *
 */
public class Trapeze extends QuadrilatereConvexe{
	public Trapeze(){
		super();
	}
	public Trapeze(ArrayList<Point2D>points){
		super(points);
	}
	public String getType(){
		return "Trapeze";
	}
}
