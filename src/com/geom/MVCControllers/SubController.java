package com.geom.MVCControllers;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import com.geom.MVCviews.Board;
import com.geom.database.DAOFactory;
import com.geom.database.FigureDAO;
import com.geom.model.Figure;
/**
 * 
 * @author Dasha
 *
 */
public class SubController {
	
	public static void addCoord(String coord,Board board){
		ArrayList<Point2D>points = board.getFigure().getPoints();
		points.add(OutputUser.traite(coord,board.getWidth()/2,board.getHeight()/2));
		board.setFigure(new Figure(points));
	}
	public static void addCoord(Figure figure,String coord){
		figure.addPoint(OutputUser.traduction(coord));
	}
	public static int save(Board board){
		Figure figure =board.getFigure();
		FigureDAO figuredao = (FigureDAO)DAOFactory.getFigureDAO();
		int i= figuredao.returnLastKey(figure);
		return i;
	}
}