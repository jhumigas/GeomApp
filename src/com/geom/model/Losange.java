package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements a particular quadrilateral shape : A rhombus
 * In Euclidean geometry, a rhombus(◊), plural rhombi or rhombuses, is a simple (non-self-intersecting) quadrilateral whose four sides all have the same length. 
 * Another name is equilateral quadrilateral, since equilateral means that all of its sides are equal in length.
 * @author Dasha
 *
 */
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
