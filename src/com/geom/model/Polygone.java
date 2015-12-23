package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Polygone extends Figure{
	public Polygone(){
		super();
	}
	public Polygone(ArrayList<Point2D>points){
		super(points);
	}
	public String getType(){
		return "Polygone";
	}

}
