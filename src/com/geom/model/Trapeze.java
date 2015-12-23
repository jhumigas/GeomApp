package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Trapeze extends QuadrilatereConvexe{
	public Trapeze(){
		super();
	}
	public Trapeze(ArrayList<Point2D>points){
		super(points);
	}
	public String getType(){
		return "Trapeze";
	}
}
