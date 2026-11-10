package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements a 'Figure' forms by one point
 * @author Dasha
 *
 */
public class Punct extends Figure{
	public Punct(){
		super();
	}
	public Punct(ArrayList<Point2D> points){
		super(points);
	}
	public Punct(double x, double y){
		ArrayList<Point2D> point = new ArrayList<Point2D>();
		Point2D pt = new Point2D.Double(x, y);
		point.add(pt);
		this.points = point;
	}
	public String getType(){
		return "Point";
	}

}
