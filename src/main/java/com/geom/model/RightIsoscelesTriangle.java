package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements right isosceles triangles
 * It is a right triangle with the two legs (and their corresponding angles) equal.
 * @author Dasha
 *
 */
public class RightIsoscelesTriangle extends IsoscelesTriangle {
	public RightIsoscelesTriangle(){
		super();
	}
	public RightIsoscelesTriangle(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Right Isolesces Triangle";
	}
}
