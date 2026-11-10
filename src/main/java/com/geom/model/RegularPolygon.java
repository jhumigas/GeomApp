package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements regular polygons i.e equilateral shapes
 * @author Dasha
 *
 */
public class RegularPolygon extends Figure{
	
	public RegularPolygon(){
		super();
	}
	public RegularPolygon(ArrayList<Point2D>points){
		super(points);
	}
	public String getType(){
		return "Regular Polygon";
	}

}
