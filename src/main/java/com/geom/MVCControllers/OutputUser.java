package com.geom.MVCControllers;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.geom.model.Factory;
import com.geom.model.Figure;
/**
 * This class deals with the user inputs e.g when providing coordinates directly in the terminal
 * @author Dasha
 *
 */
public class OutputUser {
	public static Point2D traduction(String coordonnee){
		// We expect the user to give the coordinate in the (x,y) format
		// Retrieve the positions of the parenthesis and comma
		int indexvirgule = coordonnee.indexOf(',');
		int par1 = coordonnee.indexOf('(');
		int par2 = coordonnee.indexOf(')');
		double x = 0,y = 0;

		// Parsing string into Doubles
		try{
		x = Double.parseDouble(coordonnee.substring(par1+1, indexvirgule));
		y = Double.parseDouble(coordonnee.substring(indexvirgule+1,par2));
		}catch(Exception e){	
		}
		return new Point2D.Double(x, y);	
	}

	/**
	 * Changes the view's frame so that the model is expressed in the user frame
	 * The board has its frame that is not whose Y axis pointing down
	 * Hence if the user enter (0, 10), the view should actually get (0, -10)
	 * Also performs a dilation so that the point are displayed significantly to the user
	 * @param pt Point to project into the new frame
	 * @param x translation abscissa parameter
	 * @param y translation ordinate parameter
	 * @return
	 */
	public static Point2D repereUser(Point2D pt,double x, double y){

		return new Point2D.Double(10*pt.getX()+x,-10*pt.getY()+y);	
	}

	/**
	 * Changes the coordinate of a point so that it is expressed in the original frame
	 * @param pt Point to project into the screen frame
	 * @param x translation abscissa parameter
	 * @param y translation ordinate parameter
	 * @return
	 */
	public static Point2D repereScreen(Point2D pt,double x, double y){

		return new Point2D.Double((pt.getX()-x)/10, (pt.getY()-y)/(-10));	
	}

	/**
	 * Changes the figure point to set them in the user frame
	 * @param figure expressed in an absolute frame
	 * @param x translation abscissa parameter
	 * @param y translation ordinate parameter
	 * @return a new figure in the user frame
	 */
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

	/**
	 * Parses a String into an Integer
	 * @param id String to parse
	 * @return parsed integer
	 */
	public static int idSaisi(String id){
		int id_int =0;
		try{
			id_int = Integer.parseInt(id);
		}catch(Exception e){
			System.out.println("Mauvaise saisi de l'id");	
		}
		return id_int;
	}

	/**
	 * Get the input directly from terminal
	 * @return a figure
	 */
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