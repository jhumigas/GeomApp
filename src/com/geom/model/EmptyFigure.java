package com.geom.model;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Implements an empty shape model.
 * An empty shape has no point whatsoever, and thus it's area is null.
 * @author Dasha
 *
 */
public class EmptyFigure extends Figure{
	
	public EmptyFigure(){
		super();
	}
	public EmptyFigure(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Inexistant";
	}
}
