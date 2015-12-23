package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements convex quadrilateral shapes :  any line drawn through the polygon (and not tangent to an edge or corner) meets its boundary exactly twice.
 * @author Dasha
 *
 */
public class QuadrilatereConvexe extends Figure{
	public QuadrilatereConvexe(){
		super();
	}
	public QuadrilatereConvexe(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Quadrilatere convexe";
	}

}
