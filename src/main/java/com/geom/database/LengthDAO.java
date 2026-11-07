package com.geom.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class implements the specific layer to access to the table 'Length'
 * That specific table contains the lengths of the different segments of a geometric shape
 * A 'Length' belongs to a Figure (geometric shapes). It is defined by :
 * <ul>
 * <li> id : the specific key of the length </li>
 * <li> value: the value of a length in cm</li>
 * <li> id_figure : the ID of the figure to which the Length belongs too</li>
 * <li> num_length: a number to order the lengths (1st length, 2nd length,...) because a given figure respect a certain direction
 * </ul> 
 * @author Dasha
 *
 */
public class LengthDAO extends DAO<Double>{

	private static final String INSERT_LENGTH = "INSERT INTO Length(value,id_figure,num_length) VALUES (?,?,?)";

	/**
	 * Sets the database connection
	 * @param conn is the database connection
	 * @see com.geom.database.GeomAppConnection
	 */
	public LengthDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Double obj) {
		return false;
	}
	/**
	 * 
	 * @param l the value of a given length
	 * @param id_figure the id of the 'Figure' to which the length belongs to
	 * @param num the number of the length
	 * @return true if the length was saved
	 */
	@Override
	public boolean save(Double l, int id_figure, int num){
		try {
			insert(new double[]{l}, id_figure, num);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean save(Double obj) {
		return false;
	}

	/**
	 * Saves a list of length to the database
	 * @param lengths an array of lengths to be saved
	 * @param id_figure the ID of the figure to which belongs the length
	 * @return true if all the lengths were saved
	 */
	public boolean save(double[] lengths,int id_figure){
		try {
			insert(lengths, id_figure);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/** Inserts the lengths in one batch, numbered from 0. */
	void insert(double[] lengths, int id_figure) throws SQLException {
		insert(lengths, id_figure, 0);
	}

	private void insert(double[] lengths, int id_figure, int firstNum) throws SQLException {
		try (PreparedStatement statement = this.connect.prepareStatement(INSERT_LENGTH)) {
			for(int i = 0; i < lengths.length; i++){
				statement.setDouble(1, lengths[i]);
				statement.setInt(2, id_figure);
				statement.setInt(3, firstNum + i);
				statement.addBatch();
			}
			statement.executeBatch();
		}
	}

	@Override
	public boolean delete(Double obj) {
		return false;
	}

	@Override
	public boolean update(Double obj) {
		return false;
	}

	/**
	 * retrieves a length provided its key
	 * @return the value of the length, or 0 if the key is unknown
	 */
	@Override
	public Double find(int id) {
		try (PreparedStatement statement = this.connect.prepareStatement("SELECT value FROM Length WHERE id = ?")) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				if(result.next())
					return result.getDouble("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0.0;
	}
	/**
	 *  Prints the TABLE 'Length' in the console
	 */
	@Override
	public void show() {
		printTable("Length");
	}
}
