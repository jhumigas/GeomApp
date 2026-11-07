package com.geom.MVCModel;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

import com.geom.MVCControllers.OutputUser;
import com.geom.database.DAOFactory;
import com.geom.model.Factory;
import com.geom.model.Figure;
/**
 * Implements the Model of the UI i.e
 * <ul>
 * <li> Model of the Board
 * <li> Model of the Search Window
 * </ul>
 * @author Dasha
 *
 */
public class Model  extends Observable{
	/**
	 * figure is the geometric shape to be viewed in the board
	 * idFigure is the key of of a given Figure in the database
	 * keys is an ArrayList of keys of shapes stored in the database
	 */
	private Figure figure;
	private String idFigure;
	private ArrayList<Integer>keys;
	
	public Model(){	
		this.figure=new Figure();
		this.idFigure="";
		this.setListKeys();
	}
	/**
	 * Sets the list of keys of existing shapes in the database
	 * Change the hasChanged state to true
	 * Notifies observers i.e the views
	 */
	public void setListKeys(){
		this.keys=DAOFactory.getKeys("Figure");
		setChanged();
		notifyObservers();
	}
	/**
	 * Getter of the attribute keys
	 * @return
	 */
	public ArrayList<Integer> getListKeys(){
		return this.keys;	
	}
	public Model(Figure figure, String id_figure){
		this.figure=figure;
		this.idFigure=id_figure;
		this.setListKeys();
	}
	/**
	 * Sets the attribute figure, to be drew
	 * @param figure
	 */
	public void setFigure(Figure figure){	
		this.figure=figure;
		setChanged();
		notifyObservers();		
	}
	public Figure getFigure(){
		return Factory.gfigure(this.figure.getPoints());
	}
	/**
	 * Gets the figure drew and changes its coordinate so that the figure is in the absolute system of axis
	 * @param x abscissa of the point 
	 * @param y ordinate of the point
	 * @return a new Figure in the absolute system of coordinates
	 */
	public Figure getFigureDrew(int x,int y){
		return OutputUser.toRepereUser(this.figure, x, y);
	}
	public void setidFigure(int id){
		
		this.idFigure=Integer.toString(id);
		setChanged();
		notifyObservers();
	}
	public String getiDFigure(){
		return this.idFigure;
	}
	/**
	 * Add one point to the shape
	 * @param point the new point to be added
	 */
	public void addPoint(Point2D point){
		this.figure.addPoint(point);
		setChanged();
		notifyObservers();
		
	}
	public Double[] getLengths(){
		Double[] ds=null;
		try{
			ds = new Double [this.figure.numPoints()];
			for (int i = 0; i<this.figure.numPoints();i++)
				ds[i]= new Double ( this.figure.lengths()[i]);
			return ds;
		}catch(Exception e){
			//System.out.println("C'est vide");
		}
		return ds;
	}
	public String giveTextArea(){
		return Double.toString(this.figure.area());
		
	}
	public String giveTextP(){
		return Double.toString(this.figure.perimeter());
	}
	/**
	 * Removes last point
	 */
	public void removeLastPt(){
		ArrayList<Point2D> points = this.figure.getPoints();
		points.remove(points.size()-1);
		this.figure.setPoints(points);
		setChanged();
		notifyObservers();
		
	}
	/**
	 * Finds the position of a point in the ArrayList of point of a given 'Figure'
	 * @param coord is the pair of coordinates of the searched point
	 * @return the position of the point in the ArrayList
	 */
	public int findInt(double[] coord){
		int j= 0;
		for(int i =0; i<figure.numPoints();i++)
			if(figure.getPoints().get(i).getX()==coord[0] && figure.getPoints().get(i).getY()==coord[1])
				j=i;
		return j;
	}
	/**
	 * Removes one point
	 * @param t is the position of the point in the ArrayList of points
	 */
	public void removeOne(int t){
		ArrayList<Point2D> pts = this.figure.getPoints();
		pts.remove(t);
		this.setFigure(Factory.gfigure(pts));
	}
	/**
	 * Changes the coordinates of one point
	 * @param i the position of the point in the ArrayList
	 * @param pt the point to substitute to the old point
	 */
	public void setOnePoint(int i, Point2D pt){
		ArrayList<Point2D> pts = this.figure.getPoints();
		pts.remove(i);
		pts.add(i, pt);
		this.setFigure(Factory.gfigure(pts));	
	}
	
}
