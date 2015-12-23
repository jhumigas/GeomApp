package com.geom.MVCModel;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Observable;

import com.geom.MVCControllers.OutputUser;
import com.geom.database.DAOFactory;
import com.geom.model.Factory;
import com.geom.model.Figure;

public class Model  extends Observable{
	private Figure figure;
	private String idFigure;
	private ArrayList<Integer>keys;
	
	public Model(){	
		this.figure=new Figure();
		this.idFigure="";
		this.setListKeys();
	}
	public void setListKeys(){
		this.keys=DAOFactory.getKeys("Figure");
		setChanged();
		notifyObservers();
	}
	public ArrayList<Integer> getListKeys(){
		return this.keys;	
	}
	public Model(Figure figure, String id_figure){
		this.figure=figure;
		this.idFigure=id_figure;
		this.setListKeys();
	}
	public void setFigure(Figure figure){
		
		this.figure=figure;
		setChanged();
		notifyObservers();		
	}
	public Figure getFigure(){
		return Factory.gfigure(this.figure.getPoints());
	}
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
	public void addPoint(Point2D point){
		this.figure.addPoint(point);
		setChanged();
		notifyObservers();
		
	}
	public Double[] getLengths(){
		Double[] ds=null;
		try{
			ds = new Double [this.figure.numpoints()];
			for (int i = 0; i<this.figure.numpoints();i++)
				ds[i]= new Double ( this.figure.longueurs()[i]);
			return ds;
		}catch(Exception e){
			//System.out.println("C'est vide");
		}
		return ds;
	}
	public String giveTextArea(){
		return Double.toString(this.figure.surface());
		
	}
	public String giveTextP(){
		return Double.toString(this.figure.perimetre());
	}
	public void removeLastPt(){
		ArrayList<Point2D> points = this.figure.getPoints();
		points.remove(points.size()-1);
		this.figure.setPoints(points);
		setChanged();
		notifyObservers();
		
	}
	public int findInt(double[] coord){
		int j= 0;
		for(int i =0; i<figure.numpoints();i++)
			if(figure.getPoints().get(i).getX()==coord[0] && figure.getPoints().get(i).getY()==coord[1])
				j=i;
		return j;
	}
	public void removeOne(int t){
		ArrayList<Point2D> pts = this.figure.getPoints();
		pts.remove(t);
		this.setFigure(Factory.gfigure(pts));
	}
	public void setOnePoint(int i, Point2D pt){
		ArrayList<Point2D> pts = this.figure.getPoints();
		pts.remove(i);
		pts.add(i, pt);
		this.setFigure(Factory.gfigure(pts));
		
	}
	
}
