package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements a geometric shape formed by two points
 * This is the so-called segment ;)
 * @author Dasha
 *
 */
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
