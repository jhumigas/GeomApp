package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements convex quadrilateral shapes models:  any line drawn through the polygon (and not tangent to an edge or corner) meets its boundary exactly twice.
 * @author Dasha
 *
 */
public class ConvexQuadrilateral extends Figure{
	public ConvexQuadrilateral(){
		super();
	}
	public ConvexQuadrilateral(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Convex Quadrilateral";
	}

}
