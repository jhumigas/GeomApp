package com.geom.MVCControllers;

import java.awt.geom.Point2D;
import com.geom.database.*;
import java.util.ArrayList;

import com.geom.database.DAOFactory;
import com.geom.model.Figure;
import com.geom.MVCviews.Board;
/**
 * Implements the interactions between the views and the DAO classes.
 * @author Dasha
 *
 */
public class DAOController {
	/**
	 * Finds a geometric shape in the database
	 * Instantiates the connection to the database then gets a figure given its id
	 * @param id is the primary key to find a 'Figure' in the database
	 * @return a Figure i.e geometric shape given its id in the database
	 */
	public static Figure searchFigure(String id){
		return DAOFactory.getFigureDAO().find(OutputUser.idSaisi(id));
	}
	/**
	 * 
	 * @param id
	 * @return
	 */
	public static Figure searchFigure(int id){
		String ide= Integer.toString(id);
		return DAOFactory.getFigureDAO().find(OutputUser.idSaisi(ide));
	}
	public static Board drawSearchedFigure(String id){
		Figure figure =searchFigure(id);
		return new Board(figure);	
	}
	public static void saveFigureDrawed(Figure figure){
		DAOFactory.getFigureDAO().save(figure);
	}
	public static int returnLastKey(Figure figure){
		return ((FigureDAO)DAOFactory.getFigureDAO()).returnLastKey(figure);
	}
	
	public static String getTypeFigure(String id){
		Figure figure = searchFigure(id);
		return figure.getType();
		
	}
	public static String[] getLengths(String id){
		Figure figure = searchFigure(id);
		double[]l=figure.lengths();
		String[]ls=new String[figure.numPoints()];
		try{
		for(int i=0;i<l.length;i++){
				ls[i]= Double.toString(l[i]);
				if(ls[i].length()>5)
					ls[i]=ls[i].substring(0, 4);
		}
		}catch(Exception e){
			System.out.println("Hors des points");
		}
		return ls;
	}
	public static String[] getXs(String id){
		Figure figure = searchFigure(id);
		ArrayList<Point2D>pts= figure.getPoints();
		String[] xs = new String[pts.size()];
		for( int i=0; i<xs.length;i++){
			xs[i]=Double.toString(pts.get(i).getX());
			if(xs[i].length()>5)
				xs[i]=xs[i].substring(0, 4);
			
		}
		return xs;	
	}
	public static String[] getYs(String id){
		Figure figure = searchFigure(id);
		ArrayList<Point2D>pts= figure.getPoints();
		String[] ys = new String[pts.size()];
		for( int i=0; i<ys.length;i++){
			ys[i]=Double.toString(pts.get(i).getY());
			if(ys[i].length()>5)
				ys[i]=ys[i].substring(0, 4);
		}
		return ys;
	}
	public static String getPerimeter(String id){
		Figure figure = searchFigure(id);
		String p =Double.toString(figure.perimeter());
		if(p.length()>5)
			p=p.substring(0,4);
		return p;
	}
	public static String getarea(String id){
		Figure figure = searchFigure(id);
		return Double.toString(figure.area());
	}
	public static String getnumPoints(String id){
		Figure figure = searchFigure(id);
		return Integer.toString(figure.numPoints());
	}

}