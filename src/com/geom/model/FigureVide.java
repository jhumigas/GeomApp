package com.geom.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Implements an empty shape
 * @author Dasha
 *
 */
public class FigureVide extends Figure{
	
	public FigureVide(){
		super();
	}
	public FigureVide(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Inexistant";
	}
}
