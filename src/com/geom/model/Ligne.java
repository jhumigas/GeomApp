package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Ligne extends Figure{
	public Ligne(){
		super();
	}
	public Ligne(ArrayList<Point2D>points){
		super(points);
	}
	public String getType(){
		return "Segment";
	}
}
