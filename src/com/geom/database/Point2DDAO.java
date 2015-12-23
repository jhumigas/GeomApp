package com.geom.database;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Point2DDAO extends DAO<Point2D> {

	public Point2DDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Point2D point) {
		return false;
		// TODO Auto-generated method stub
	}
	public boolean save(Point2D point2d,int id_figure,int numero){
		String query = "INSERT INTO Point(x,y,id_figure,num_point) VALUES ('"+point2d.getX()+"', '"+point2d.getY()+"', '"+id_figure+"','"+numero+"')";
		try{
			this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	public boolean save(ArrayList<Point2D> arrayList,int id_figure){
		for(int i =0; i<arrayList.size();i++)
			save(arrayList.get(i),id_figure,i);
		return true;
	}

	@Override
	public boolean delete(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Point2D point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Point2D.Double find(int id) {
	    double x = 0;
	    double y = 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Point WHERE id="+id);
			//ResultSetMetaData resultMeta = result.getMetaData();
			result.first();
			if(result.next()){
				x = (double) result.getObject("x");
				y = (double) result.getObject("y");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return new Point2D.Double(x, y);
	}
	public void afficher() {
		try {
           ResultSet resultSet = this.connect.createStatement().executeQuery("SELECT * FROM Point");
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
	public boolean save(Point2D obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[][] sortir() {
		// TODO Auto-generated method stub
		return null;
	}
}
