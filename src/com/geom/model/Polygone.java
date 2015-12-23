package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements a polygon shape
 * In elementary geometry, a polygon is a plane figure that is bounded by a finite chain 
 * of straight line segments closing in a loop to form a closed chain or circuit.
 * @author Dasha
 *
 */
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
