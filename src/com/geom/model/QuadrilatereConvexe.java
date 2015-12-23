package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class QuadrilatereConvexe extends Figure{
	public QuadrilatereConvexe(){
		super();
	}
	public QuadrilatereConvexe(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Quadrilatere convexe";
	}

}
