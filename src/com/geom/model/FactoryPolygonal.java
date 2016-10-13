package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Handles the creation of polygonal shapes.
 * @author Dasha
 *
 */
public class FactoryPolygonal {
	public static Figure gfigure(ArrayList<Point2D>parameters){
		if(Factory.hasEqualSides(parameters))
			return new RegularPolygon(parameters);
		else
			return new Polygon(parameters);	
	}

}
