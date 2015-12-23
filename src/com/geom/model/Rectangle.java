package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Rectangle extends Parallelogramme {
	public Rectangle(){
		super();
	}
	public Rectangle(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Rectangle";
	}

}
