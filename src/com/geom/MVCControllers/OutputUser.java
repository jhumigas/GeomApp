package com.geom.MVCControllers;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.geom.model.Factory;
import com.geom.model.Figure;
/**
 * This class deals with the user inputs e.g when providing coordinates
 * @author Dasha
 *
 */
public class OutputUser {
	public static Point2D traduction(String coordonnee){
		//traduction des coordonnees saisies en points
		
		int indexvirgule = coordonnee.indexOf(',');
		int par1 = coordonnee.indexOf('(');
		int par2 = coordonnee.indexOf(')');
		double x = 0,y = 0;
		try{
		x = Double.parseDouble(coordonnee.substring(par1+1, indexvirgule));
		y = Double.parseDouble(coordonnee.substring(indexvirgule+1,par2));
		}catch(Exception e){	
		}
		return new Point2D.Double(x, y);	
	}
	public static Point2D repereUser(Point2D pt,double x, double y){
		//changement de repere pour que l'affichage corresponde aux coordonnees saisies
		return new Point2D.Double(10*pt.getX()+x,-10*pt.getY()+y);	
	}
	public static Point2D repereScreen(Point2D pt,double x, double y){
		//passage dans le repere absolu
		return new Point2D.Double((pt.getX()-x)/10, (pt.getY()-y)/(-10));	
	}
	public static Figure toRepereUser(Figure figure, double x, double y){
		ArrayList<Point2D>points = new ArrayList<Point2D>();
		if(figure.numPoints()!=0){
			for(Point2D pt: figure.getPoints())
				points.add(repereUser(pt,x,y));	
		}
		return Factory.gfigure(points);		
	}
	public static Figure toRepereScreen(Figure figure, double x, double y){
		ArrayList<Point2D>points = new ArrayList<Point2D>();
		if(figure.numPoints()!=0){
			for(Point2D pt: figure.getPoints())
				points.add(repereScreen(pt,x,y));	
		}
		return new Figure(points);
		
	}
	public static Point2D traite(String coord,int x, int y){
		return repereUser(traduction(coord),x,y);
	}
	public static void pointsaisi(Point2D point, ArrayList<Point2D>points){
		boolean present = false;
		for(int i = 0; i<points.size();i++)
			if(points.get(i).equals(point))
				present = true;
		if(present)
			System.out.println("Le point a deja ete saisi,veuillez recommencer");
		else
			points.add(point);
	}
	public static int idSaisi(String id){
		int id_int =0;
		try{
			id_int = Integer.parseInt(id);
		}catch(Exception e){
			System.out.println("Mauvaise saisi de l'id");	
		}
		return id_int;
	}
	public static Figure ecrire(){
		//ecrire les coordonnees directement dans la console
		System.out.println("Veuillez saisir les coordonnees (x,y) d'un point");
		System.out.println("Appuyer sur entrer pour valider");
		System.out.println("Saisissez 'N' pour arreter");
		ArrayList<Point2D> points = new ArrayList <Point2D>();
		java.util.Scanner entree = new java.util.Scanner(System.in);
		String clavier = "";
		int i = 0;
		do{
			clavier = entree.next();
			if(clavier.charAt(0)!='N'){
				try{
				pointsaisi(traduction(clavier),points);
				i++;
				}catch(Exception e){
					System.out.println("Vous avez mal saisi votre entree...");
				}
				//System.out.println("Saisir des coordonnees:");
			}
			entree.nextLine();
		}
		while(clavier.charAt(0) != 'N');
		System.out.println("Nombre de coordonnees saisies: "+ i);
		System.out.println("Nombre de coordonnees retenues: "+ points.size());
		entree.close();
		return Factory.gfigure(points);	
	}
}