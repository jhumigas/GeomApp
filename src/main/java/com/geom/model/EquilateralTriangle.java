package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements equilateral triangles
 * All the sides that have the same lengths and all the angles are equal in measure i.e 60°.
 * These properties are used by the Factory Class to identify Equilateral Triangles.
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
