package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements regular polygons i.e equilateral shapes
 * @author Dasha
 *
 */
public class PolygoneRegulier extends Figure{
	
	public PolygoneRegulier(){
		super();
	}
	public PolygoneRegulier(ArrayList<Point2D>points){
		super(points);
	}
	public String getType(){
		return "Polygone regulier";
	}

}
