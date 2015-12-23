package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Class implements the Square shape model
 * A square is a regular quadrilateral, which means that it has four equal sides and four equal angles (90-degree angles, or right angles)
 * @author Dasha
 *
 */
public class Carre extends Rectangle{
	public Carre(){
		super();
	}
	public Carre(ArrayList<Point2D>l){
		super(l);
	}
	public String getType(){
		return "Carre";
	}

}
