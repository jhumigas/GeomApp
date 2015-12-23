package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Losange extends Parallelogramme{
	public Losange(){
		super();
	}
	public Losange(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Losange";
	}

}
