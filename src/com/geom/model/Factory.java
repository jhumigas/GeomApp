package com.geom.model;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Factory{
	public static Figure gfigure (ArrayList<Point2D> parametres){
		Figure poly;
		rejetpoint(parametres);
		switch(parametres.size()){
		case 0:
			poly = new FigureVide();
			break;
		case 1:
			poly = new Punct(parametres);
			break;
		case 2:
			poly = new Ligne(parametres);
			break;
		case 3:
			poly = FactoryTriangular.gfigure(parametres);
			break;
		case 4:
			poly = FactoryQuadrilateral.gfigure(parametres);
			break;
		default:
			poly=FactoryPolygonal.gfigure(parametres);
		}
		return poly;

	}
	public static boolean aAngledroit(ArrayList<Point2D>parametres){
		//verifier si au moins un des angles de la figure est droit
		double valeurs[]=coss(parametres);
		boolean droit = false;
		for(int i=0;i<valeurs.length;i++)
			if(valeurs[i]==0)
				droit =true;
		return droit;
		
	}
	public static boolean longueursEgales(ArrayList<Point2D> parametres){
		
		boolean equi = true;
		double distance = parametres.get(0).distance(parametres.get(parametres.size()-1));
		int i = 0;
		while(i<parametres.size()-1){
			equi = equi && distance == parametres.get(i).distance(parametres.get(i+1)) ;
			i++;
		}
		return equi;		
	}
	
	public static double sin(Point2D pt1, Point2D pt2, Point2D pt3){
		// renvoie le sinus l'angle entre p3p2 et p1p2
		double a [] = new double[2];
	    double b [] = new double[2];
		a [0] =pt3.getX()- pt2.getX();
		a [1]= pt3.getY()- pt2.getY();
		b [0] =pt1.getX()- pt2.getX();
		b [1]= pt1.getY()- pt2.getY();
		return (a[0]*b[1] -a[1]*b[0])/(pt3.distance(pt2) * pt1.distance(pt2));	
	}
	public static double cos(Point2D pt1, Point2D pt2, Point2D pt3){
		// renvoie le cosinus de l'angle entre p3p2 et p1p2
		double a [] = new double[2];
	    double b [] = new double[2];
		a [0] =pt3.getX()- pt2.getX();
		a [1]= pt3.getY()- pt2.getY();
		b [0] =pt1.getX()- pt2.getX();
		b [1]= pt1.getY()- pt2.getY();
		return (a[0]*b[0] +a[1]*b[1])/(pt3.distance(pt2) * pt1.distance(pt2));
		
	}
	public static double[] coss(ArrayList<Point2D>points){
		//renvoie une liste des cosinus des angles d'une figure
		double cos [] = new double[points.size()];
	    cos[0]= cos(points.get(1), points.get(0),points.get(points.size()-1));
		for(int i = 1; i< cos.length -1; i++){
			cos[i]= cos(points.get(i+1), points.get(i),points.get(i-1));
		}
		cos[cos.length -1]= cos(points.get(0), points.get(points.size()-1),points.get(points.size()-2));
		return cos;
	}
	
	public static double[] sins(ArrayList<Point2D>points){
		//renvoie une liste des sinus des angles d'une figure
		double sin [] = new double[points.size()];
	    sin[0]= sin(points.get(1), points.get(0),points.get(points.size()-1));
		for(int i = 1; i< sin.length -1; i++){
			sin[i]= sin(points.get(i+1), points.get(i),points.get(i-1));
		}
		sin[sin.length -1]= sin(points.get(0), points.get(points.size()-1),points.get(points.size()-2));
		return sin;
	}
	public static void rejetpoint(ArrayList<Point2D>points){
		//enlever le 3eme point aligner avec deux autres
		//cas figures impossibles ?
		if(points.size()>2){
			double []sins= sins(points);
			int k = 0;
			int i = 1;
			while(i<sins.length-1){
				if(sins[i]==0){
					points.remove(i-k);
					k++;
				}
				i++;
			}
		}
	}
	public static boolean anglesEgaux(ArrayList<Point2D>points){
		boolean equi = true;
		double cos[]= coss(points);
		double sin[]=sins(points);
		int i = 1;
		while(i<cos.length-1){
			equi = equi && (cos[0] == cos[i] && sin[0] == sin[i] );
			i++;
		}
		return equi;
	}


}