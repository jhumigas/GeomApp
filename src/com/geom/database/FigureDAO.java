package com.geom.database;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.geom.model.Factory;
import com.geom.model.Figure;

public class FigureDAO extends DAO<Figure> {

	public FigureDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean create(Figure figure) {
		String query = "INSERT INTO Figure(type,perimetre,surface) VALUES ('"+figure.perimetre()+"','"+figure.perimetre()+"', '"+figure.surface()+"');";
		try{
			this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	public boolean save(Figure figure){
		String query = "INSERT INTO Figure(type,perimetre,surface) VALUES ('"+figure.getType()+"','"+figure.perimetre()+"', '"+figure.surface()+"');";
		int id_figure = 0;
		try{
			Statement state = this.connect.createStatement();
			state.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet res = state.getGeneratedKeys();
			res.next();
			id_figure = res.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		new LongueurDAO(this.connect).save(figure.longueurs(), id_figure);
		new Point2DDAO(this.connect).save(figure.getPoints(), id_figure);
		return true;
	}
	public int returnLastKey(Figure figure){
		String query = "INSERT INTO Figure(type,perimetre,surface) VALUES ('"+figure.getType()+"','"+figure.perimetre()+"', '"+figure.surface()+"');";
		int id_figure = 0;
		try{
			Statement state = this.connect.createStatement();
			state.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet res = state.getGeneratedKeys();
			res.next();
			id_figure = res.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		new LongueurDAO(this.connect).save(figure.longueurs(), id_figure);
		new Point2DDAO(this.connect).save(figure.getPoints(), id_figure);
		return id_figure;	
	}
	@Override
	public boolean delete(Figure figure) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean update(Figure figure) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Figure find(int id) {
		double x = 0;
		double y = 0;
		ArrayList<Point2D>points = new ArrayList<Point2D>();
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Point WHERE id_figure="+id);
			while(result.next()){
				x = (double) result.getObject("x");
				y = (double) result.getObject("y");
				points.add(new Point2D.Double(x,y));	
			}
		}catch(SQLException e){
			//e.printStackTrace();
			System.out.println("Introuvable");
		}
		return Factory.gfigure(points);
	}
	@Override
	public void afficher() {
		try {
           ResultSet resultSet = this.connect.createStatement().executeQuery("SELECT * FROM Figure");
           ResultSetMetaData metaData = resultSet.getMetaData();
           for(int i = 1;i<metaData.getColumnCount();i++)
        	   System.out.print("\t" + metaData.getColumnName(i).toUpperCase() + "\t *");
           
           System.out.println("\n**************************************************************************************");
           
           while(resultSet.next()){         
               for(int i = 1; i <= metaData.getColumnCount(); i++)
                 System.out.print("\t" + resultSet.getObject(i).toString() + "\t |");
                   
               System.out.println("\n----------------------------------------------------------------------------------");
           }
             resultSet.close();    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	public String[][] sortir() {
		String table [][]=new String [150][4];
		try {
           ResultSet resultSet = this.connect.createStatement().executeQuery("SELECT * FROM Figure");
           ResultSetMetaData metaData = resultSet.getMetaData();
           //for(int i = 1;i<metaData.getColumnCount();i++)
        	   //table[0][i]=metaData.getColumnName(i).toUpperCase();
           int j = 0;
           while(resultSet.next()){         
               for(int i = 1; i <= metaData.getColumnCount(); i++)
            	   table[j][i-1]= resultSet.getObject(i).toString();
               j++;
           }
             resultSet.close();    
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
		// TODO Auto-generated method stub
	}
}
