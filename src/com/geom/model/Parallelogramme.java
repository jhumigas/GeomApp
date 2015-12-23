package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Parallelogramme extends Trapeze{
	public Parallelogramme(){
		super();
	}
	public Parallelogramme(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Parallelogramme";
	}
}
