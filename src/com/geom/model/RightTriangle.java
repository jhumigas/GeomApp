package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements right triangle
 * @author Dasha
 *
 */
public class RightTriangle extends Triangle {
	public RightTriangle(){
		super();
	}
	public RightTriangle(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Right Triangle";
	}

}
