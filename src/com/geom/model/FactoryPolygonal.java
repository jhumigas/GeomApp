package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Handles the creation of polygonal shapes
 * @author Dasha
 *
 */
public class FactoryPolygonal {
	public static Figure gfigure(ArrayList<Point2D>parametres){
		if(Factory.longueursEgales(parametres))
			return new PolygoneRegulier(parametres);
		else
			return new Polygone(parametres);	
	}

}
