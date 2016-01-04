package com.geom.MVCviews;

import javax.swing.JPanel;

import com.geom.model.Factory;
import com.geom.model.Figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.ArrayList;
/**
 * Implements the 'Board' where the geometric shapes is drawn 
 * The user can select, set or delete a point
 * The Shape is updated every time a point is added, reset or deleted
 * Built with WindowBuilder
 * @author Dasha
 *
 */
public class Board extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Figure figure;
	private double x,y;
	private static final int size = 8;
	public Board(){
		super();
		addMouseListener(this);
		this.figure=new Figure();
	}
	/**
	 * Setting coordinates for the Board's repere
	 * @param x
	 * @param y
	 */
	public void centerFigure(double x, double y){
		for(Point2D point: figure.getPoints()){
			point.setLocation(point.getX()+x,-point.getY()+y);
		}
	}
	public Board(Figure figure){
		super();
		this.figure=Factory.gfigure(figure.getPoints());
	}
	public void setFigure(Figure figure){
		this.figure=Factory.gfigure(figure.getPoints());
	}
	public Figure getFigure(){
		return this.figure;
	}
	/**
	 * Draws the geometric shape in the board
	 */
	public void paintComponent(Graphics g){
		//draw figure
		
		if(figure.numpoints()!=0 && figure!=null){
			Graphics2D g2 = (Graphics2D)g;
			GeneralPath poly = new GeneralPath(GeneralPath.WIND_EVEN_ODD,this.figure.getPoints().size());
			poly.moveTo(this.figure.getPoints().get(0).getX(), this.figure.getPoints().get(0).getY());
			for (int i = 1; i < this.figure.getPoints().size(); i++)
				poly.lineTo(this.figure.getPoints().get(i).getX(), this.figure.getPoints().get(i).getY());
			poly.closePath();
			for(Point2D pt : figure.getPoints()){
				if( pt.getX()==x && pt.getY()==y){
				    g2.setColor(Color.RED);
				    g2.fillOval((int)pt.getX()-size/2, (int)pt.getY()-size/2, size, size);
				}
				else{
					g2.setColor(Color.blue);
				    g2.drawOval((int)pt.getX()-size/2, (int)pt.getY()-size/2, size, size);
				}
			}
			g2.draw(poly);
			g2.setPaint(Color.lightGray);
			g2.fill(poly);
			g2.setPaint(Color.black);
			g2.draw(poly);
		}
	}
	public void removeLastPoint(){
		ArrayList<Point2D>pts = this.getFigure().getPoints();
		if(figure.numpoints()>0){
			pts.remove(this.figure.numpoints()-1);
			this.figure= new Figure(pts);
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(Point2D pt: this.figure.getPoints())
			if( Math.abs(e.getX()-(int)pt.getX()) <= size/2 && Math.abs(e.getY()-(int)pt.getY()) <= size/2){
		         x=pt.getX();
		         y=pt.getY();
		         this.repaint();
			}	
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {	
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public double[] getPressedCoord(){
		double coord []= {(this.x-this.getWidth()/2)/10,(this.y-this.getHeight()/2)/(-10)};
		return coord;
	}

}