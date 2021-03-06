package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * This class implements the most basic shapes, without any condition regarding number of points, lengths.
 * @author Dasha
 *
 */
public class Figure{
	/**
	 * A geometric is here defined by a list of points.
	 */
	protected ArrayList<Point2D> points;
	/**
	 * Class that instantiates a new figure without any provided set of points.
	 */
	public Figure(){
		ArrayList<Point2D> point = new ArrayList<Point2D>();
		this.points= point;
	}
	/**
	 * Overloading the first constructor, to accept the instantiation of a figure with a list of points.
	 * @param points
	 */
	public Figure(ArrayList<Point2D> points){
		this.points=points;
	}
	/**
	 * 
	 * @return the ArrayList of points forming the geometric figure.
	 */
	public ArrayList<Point2D> getPoints(){
		return this.points;
	}
	/**
	 * Sets the ArrayList of points forming the geometric shape
	 * @param points
	 */
	public void setPoints(ArrayList<Point2D> points){
		this.points=points;
	}
	/**
	 * Adds a point to the shape.
	 * @param x abscissa of the new point to be added.
	 * @param y ordinate of the new point to be added.
	 */
	public void addPointXY(double x,double y){
		for(int i=0;i< this.numPoints(); i++){
			if(this.points.get(i).getX()==x && this.points.get(i).getY()==y)
				return;
		}
		this.points.add(new Point2D.Double(x, y));
	}
	/**
	 * 
	 * @return the number of points forming a shape.
	 */
	public int numPoints(){
		//renvoie le nombre de points de la figure
		return this.points.size();
	}
	/**
	 * 
	 * @return the type of geometric shapes i.e Quadrilateral, Square, etc.
	 */
	public String getType(){
		return "Figure";
	}
	
	/**
	 * 
	 * @return an array of lengths of the sides.
	 */
	public double[] lengths(){

		switch(this.numPoints()){
		case 0 & 1:
			double lengths1[]={0};
			return lengths1;
		case 2:
			double lengths2[]={this.points.get(0).distance(this.points.get(1))};
			return lengths2;
		default:
			double lengths[]=new double[this.points.size()];
			for (int i=0;i<this.points.size()-1;i++)
				lengths[i]=this.points.get(i).distance(this.points.get(i+1));
			lengths[this.points.size()-1]=this.points.get(this.points.size()-1).distance(this.points.get(0));
			return lengths;
			
		}
	}
	/**
	 * Calculates the perimeter of a geometric shape
	 * A perimeter is a path that surrounds a 2D shape.
	 * @return value of the perimeter
	 */
	public double perimeter(){
		double perimeter = 0;
		for(double Length:this.lengths())
			perimeter+=Length;
		return perimeter;
	}
	/**
	 * Calculates the area area of a geometric shape
	 * @return the area area of a geometric shape
	 */
	public double area(){

		if(this.numPoints()<=2){
			return 0;
		}
		else{
			double area = this.points.get(this.points.size()-1).getX()*this.points.get(0).getY()-this.points.get(0).getX()*this.points.get(this.points.size()-1).getY();
			for(int i = 0; i<this.points.size()-1;i++)
				area += this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY();
			return Math.abs(area)/2;
		}
	}
	/**
	 * Adds Point2D to a shape
	 * @param point is the Point to add
	 */
	public void addPoint(Point2D point) {
		this.addPointXY(point.getX(), point.getY());
	}
}