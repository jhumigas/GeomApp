package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Class implements the Square shape model.
 * A square is a regular quadrilateral, which means that it has four equal sides and four equal angles (90-degree angles, or right angles)
 * It can also be defined as a rectangle in which two adjacent sides have equal length. 
 * @author Dasha
 *
 */
public class Square extends Rectangle{
	public Square(){
		super();
	}
	public Square(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Square";
	}

}
