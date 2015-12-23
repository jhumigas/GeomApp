package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements quadrilateral shapes
 * @author Dasha
 *
 */
public class Quadrilatere extends Polygone{
	public Quadrilatere(){
		super();
	}
	public Quadrilatere(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Quadrilatere";
	}
}
