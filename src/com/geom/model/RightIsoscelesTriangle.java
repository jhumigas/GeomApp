package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements right isosceles triangles
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
