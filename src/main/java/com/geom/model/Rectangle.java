package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements rectangular shapes 
 * A rectangle is any quadrilateral with four right angles.
 * @author Dasha
 *
 */
public class Rectangle extends Parallelogram {
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
