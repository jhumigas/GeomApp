package com.geom.model;
import java.util.ArrayList;
import java.awt.geom.*;
/**
 * Implements triangular shapes
 * @author Dasha
 *
 */
public class Triangle extends Polygone{
	public Triangle(){
		super();
	}
	
	public Triangle(ArrayList<Point2D> points){
		super(points);	
	}
	
	public Triangle(Point2D pt1,Point2D pt2,Point2D pt3){
		ArrayList<Point2D>points=new ArrayList<Point2D>(3);
		points.add(pt1);
		points.add(pt2);
		points.add(pt3);
		this.points=points;
	}
	public String getType(){
		return "Triangle";
	}
}