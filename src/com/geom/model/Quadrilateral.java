package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements quadrilateral shapes
 * @author Dasha
 *
 */
public class Quadrilateral extends Polygon{
	public Quadrilateral(){
		super();
	}
	public Quadrilateral(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Quadrilateral";
	}
}
