package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TriangleEquilateral extends Triangle{
	public TriangleEquilateral(){
		super();
	}
	public TriangleEquilateral(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Triangle equilateral";
	}

}
