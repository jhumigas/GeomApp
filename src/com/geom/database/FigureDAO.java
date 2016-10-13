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

/**
 * This class implements the specific layer to access to interact with 'Figure' table in the database
 * A 'Figure' is here french for geometric shapes. It is formed by points, and lengths (or segments).
 * To define a 'Figure' in the database you basically needs :
 * <ul>
 * <li> ID : The specific id of a figure</li>
 * <li> Type : The type of a Figure e.g triangle, rectangular, square,...</li>
 * <li> perimeter : perimeter</li>
 * <li> area : The area area of the geometric figure</li>
 * </ul>
 * @author Dasha
 *
 */
public class FigureDAO extends DAO<Figure> implements DAOInterface<Figure>{

	/**
	 * Sets the connection used to access to the tables
	 * @param conn
	 */
	public FigureDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Creates a figure in the database
	 */
	@Override
	public boolean create(Figure figure) {
		String query = "INSERT INTO Figure(type,perimeter,area) VALUES ('"+figure.perimeter()+"','"+figure.perimeter()+"', '"+figure.area()+"');";
		try{
			this.connect.createStatement().executeUpdate(query);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	/**
	 * Saves a figure in the database and its components (lengths, points)
	 */
	@Override
	public boolean save(Figure figure){
		String query = "INSERT INTO Figure(type,perimeter,area) VALUES ('"+figure.getType()+"','"+figure.perimeter()+"', '"+figure.area()+"');";
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
		new LengthDAO(this.connect).save(figure.lengths(), id_figure);
		new Point2DDAO(this.connect).save(figure.getPoints(), id_figure);
		return true;
	}

	@Override
	public boolean save(Figure obj, int id_figure, int num) {
		return false;
	}

	/**
	 * Gets the key of the last figure saved in the database
	 * Same method as save but returns the keys
	 * @param figure
	 * @return key if the saved figure
	 */
	public int returnLastKey(Figure figure){
		String query = "INSERT INTO Figure(type,perimeter,area) VALUES ('"+figure.getType()+"','"+figure.perimeter()+"', '"+figure.area()+"');";
		int id_figure = 0;
		try{
			Statement state = this.connect.createStatement();
			state.executeUpdate(query);
			//state.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			ResultSet res = state.getGeneratedKeys();
			res.next();
			id_figure = res.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
		}
		new LengthDAO(this.connect).save(figure.lengths(), id_figure);
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

	/**
	 * Gets a figure if the id is provided
	 */
	@Override
	public Figure find(int id) {
		double x = 0;
		double y = 0;
		ArrayList<Point2D>points = new ArrayList<Point2D>();
		try{
			ResultSet result = this.connect.createStatement(
					//ResultSet.TYPE_SCROLL_INSENSITIVE,
					//ResultSet.CONCUR_READ_ONLY
			).executeQuery("SELECT * FROM Point WHERE id_figure="+id);
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

	/**
	 * Print the 'Figure' table in the console
	 */
	@Override
	public void show() {
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

	public String[][] dump() {
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
