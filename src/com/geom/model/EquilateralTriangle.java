package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements equilateral triangles
 * @author Dasha
 *
 */
public class EquilateralTriangle extends Triangle{
	public EquilateralTriangle(){
		super();
	}
	public EquilateralTriangle(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Triangle equilateral";
	}

}
