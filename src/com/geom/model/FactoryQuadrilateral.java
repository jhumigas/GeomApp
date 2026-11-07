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
	 * Verifies if a quadrilateral shape is a trapezoid i.e there are two opposite sides that are parallel.
	 * Two sides are parallel when the cross product of their direction vectors is null.
	 *
	 * @param points is an ArrayList of points
	 * @return true if the shape is trapezoid
	 */
	public static boolean isTrapezoid(ArrayList<Point2D> points){
		return areParallel(points.get(0), points.get(1), points.get(2), points.get(3))
				|| areParallel(points.get(1), points.get(2), points.get(3), points.get(0));
	}

	/**
	 * @return true if segment [a,b] is parallel to segment [c,d]
	 */
	private static boolean areParallel(Point2D a, Point2D b, Point2D c, Point2D d){
		double ux = b.getX() - a.getX(), uy = b.getY() - a.getY();
		double vx = d.getX() - c.getX(), vy = d.getY() - c.getY();
		double cross = ux * vy - uy * vx;
		return Math.abs(cross) <= Factory.EPSILON * a.distance(b) * c.distance(d);
	}

	/**
	 * Verifies if a convex quadrilateral is a parallelogram,
	 * i.e both pairs of opposite sides have the same length.
	 *
	 * @param points is an ArrayList of points.
	 * @return true if both pairs of opposite sides have the same length
	 */
	public static boolean isParallelogram(ArrayList<Point2D>points){
		return Factory.approxEqual(points.get(0).distance(points.get(1)), points.get(2).distance(points.get(3)))
				&& Factory.approxEqual(points.get(1).distance(points.get(2)), points.get(3).distance(points.get(0)));
	}

	/**
	 * Verifies if the shape is convex, i.e all its angles turn in the same direction.
	 * The sign of each sine gives the turning direction, which depends on the order of the points,
	 * so the quadrilateral is convex when all sines are strictly positive or all strictly negative.
	 * @param points ArrayList of the quadrilateral points.
	 * @return true if the quadrilateral is convex
	 */
	public static boolean isConvexQuad(ArrayList<Point2D> points){
		boolean allPositive = true, allNegative = true;
		for(double sin : Factory.sins(points)){
			if(sin <= Factory.EPSILON) allPositive = false;
			if(sin >= -Factory.EPSILON) allNegative = false;
		}
		return allPositive || allNegative;
	}

}
