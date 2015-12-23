package com.geom.database;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class implements the specific layer to access to the table 'Longueur'
 * That specific table contains the lengths of the different segments of a geometric shape
 * A 'Longueur' belongs to a Figure (geometric shapes). It is defined by :
 * <ul>
 * <li>id : the specific key of the length </li>
 * <li>valeur: the value of a length in cm</li>
 * <li> id_figure : the ID of the figure to which the Longueur belongs too</li>
 * <li> num_longueur: a number to order the lengths (1st length, 2nd length,...) because a given figure respect a certain direction
 * </ul> 
 * @author Dasha
 *
 */
public class LongueurDAO extends DAO<Double>{

	/**
	 * Sets the database connection
	 * @param conn is the database connection
	 * @see com.geom.database.GeomAppConnection
	 */
	public LongueurDAO(Connection conn) {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Double obj) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 
	 * @param l the value of a given length
	 * @param id_figure the id of the 'Figure' to which the length belongs to
	 * @param numero the number of the length
	 * @return true
	 */
	public boolean save(double l,int id_figure,int numero){
		String query = "INSERT INTO Longueur(valeur,id_figure,num_longueur) VALUES ('"+l+"','"+id_figure+"','"+numero+"')";
		try{
			this.connect.createStatement().executeUpdate(query);
			//ResultSetMetaData resultMeta = result.getMetaData();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return true;
	}
	public boolean save(Double obj) {
		return true;
	}
	/**
	 * Saves a list of length to the database
	 * @param longueurs an array of lengths to be saved
	 * @param id_figure the ID of the figure to which belongs the length
	 * @return
	 */
	public boolean save(double [] longueurs,int id_figure){
		for(int i =0; i<longueurs.length;i++)
			save(longueurs[i],id_figure,i);
		return true;
	}

	@Override
	public boolean delete(Double obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Double obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	/**
	 * retrieves a length provided its key
	 */
	public Double find(int id) {
		// TODO Auto-generated method stub
		Double longueur = (double) 0;
		try{
			ResultSet result = this.connect.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Longueur WHERE id="+id);
			//ResultSetMetaData resultMeta = result.getMetaData();
			result.first();
			if(result.next()){
				longueur= (Double) result.getObject("x");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return longueur;
	}
	/**
	 *  Prints the TABLE 'Longueur' in the console
	 */
	public void afficher() {
		try {
           ResultSet resultSet = this.connect.createStatement().executeQuery("SELECT * FROM Longueur");
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
			e.printStackTrace();
		}
		
	}

	@Override
	public String[][] sortir() {
		// TODO Auto-generated method stub
		return null;
	}
}
