package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class TriangleRectangle extends Triangle {
	public TriangleRectangle(){
		super();
	}
	public TriangleRectangle(ArrayList<Point2D> points){
		super(points);
	}
	public String getType(){
		return "Triangle rectangle";
	}

}
