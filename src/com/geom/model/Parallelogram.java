package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * In Euclidean geometry, a parallelogram is a (non self-intersecting) quadrilateral with two pairs of parallel sides. 
 * The opposite or facing sides of a parallelogram are of equal length and the opposite angles of a parallelogram are of equal measure.
 * @author Dasha
 *
 */
public class Parallelogram extends Trapezoid{
	public Parallelogram(){
		super();
	}
	public Parallelogram(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Parallelogram";
	}
}
