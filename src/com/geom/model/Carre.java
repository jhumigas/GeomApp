package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Carre extends Rectangle{
	public Carre(){
		super();
	}
	public Carre(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Carre";
	}

}
