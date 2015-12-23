package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Figure{
	protected ArrayList<Point2D> points;
	public Figure(){
		ArrayList<Point2D> point = new ArrayList<Point2D>();
		this.points= point;
	}
	public Figure(ArrayList<Point2D> points){
		this.points=points;
	}
	public ArrayList<Point2D> getPoints(){
		return this.points;
	}
	public void setPoints(ArrayList<Point2D> points){
		this.points=points;
	}
	public void addPointXY(double x,double y){
		for(int i=0;i< this.numpoints(); i++){
			if(this.points.get(i).getX()==x && this.points.get(i).getY()==y)
				return;
		}
		this.points.add(new Point2D.Double(x, y));
	}
	public int numpoints(){
		//renvoie le nombre de points de la figure
		return this.points.size();
	}
	public String getType(){
		return "Figure";
	}


	public double[] longueurs(){
		//renvoie un tableau contenant les longueurs des cotes
		//renvoie 0 par defaut pour le point

		switch(this.numpoints()){
		case 0 & 1:
			double longueurs1[]={0};
			return longueurs1;
		case 2:
			double longueurs2[]={this.points.get(0).distance(this.points.get(1))};
			return longueurs2;
		default:
			double longueurs[]=new double[this.points.size()];
			for (int i=0;i<this.points.size()-1;i++)
				longueurs[i]=this.points.get(i).distance(this.points.get(i+1));
			longueurs[this.points.size()-1]=this.points.get(this.points.size()-1).distance(this.points.get(0));
			return longueurs;
			
		}
	}
	public double perimetre(){
		double perimetre = 0;
		for(double longueur:this.longueurs())
			perimetre+=longueur;
		return perimetre;
	}
	public double surface(){
		//renvoie la surface de la figure
		// renvoie 0 si la figure a moins de 3 points
		if(this.numpoints()<=2){
			return 0;
		}
		else{
			double surface = this.points.get(this.points.size()-1).getX()*this.points.get(0).getY()-this.points.get(0).getX()*this.points.get(this.points.size()-1).getY();
			for(int i = 0; i<this.points.size()-1;i++)
				surface += this.points.get(i).getX()*this.points.get(i+1).getY()-this.points.get(i+1).getX()*this.points.get(i).getY();
			return Math.abs(surface)/2;
		}
	}
	public void addPoint(Point2D point) {
		this.addPointXY(point.getX(), point.getY());
	}
}