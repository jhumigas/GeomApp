package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TriangleIsocele extends Triangle{
	
	public TriangleIsocele(){
		super();
	}
	public TriangleIsocele(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Triangle isocele";
	}

}
