package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements right isosceles triangles
 * @author Dasha
 *
 */
public class TriangleIsoceleRectangle extends TriangleIsocele {
	public TriangleIsoceleRectangle(){
		super();
	}
	public TriangleIsoceleRectangle(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Triangle isocele rectangle";
	}
}
