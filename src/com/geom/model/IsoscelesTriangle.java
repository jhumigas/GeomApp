package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements Isosceles triangle
 * An isosceles triangle has two sides of equal length
 * @author Dasha
 *
 */
public class IsoscelesTriangle extends Triangle{
	
	public IsoscelesTriangle(){
		super();
	}
	public IsoscelesTriangle(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Triangle isocele";
	}

}
