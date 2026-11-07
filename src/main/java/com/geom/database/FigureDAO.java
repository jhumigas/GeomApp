package com.geom.database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	private static final Logger log = LoggerFactory.getLogger(FigureDAO.class);

	private static final String INSERT_FIGURE = "INSERT INTO Figure(type,perimeter,area) VALUES (?,?,?)";
	private static final String SELECT_POINTS = "SELECT x, y FROM Point WHERE id_figure = ? ORDER BY num_point";

	/**
	 * Sets the connection used to access to the tables
	 * @param conn
	 */
	public FigureDAO(Connection conn) {
		super(conn);
	}

	/**
	 * Inserts a row in the 'Figure' table only (no points, no lengths).
	 * @return the generated key of the figure
	 */
	private int insertFigure(Figure figure) throws SQLException {
		try (PreparedStatement statement = this.connect.prepareStatement(INSERT_FIGURE, Statement.RETURN_GENERATED_KEYS)) {
			statement.setString(1, figure.getType());
			statement.setDouble(2, figure.perimeter());
			statement.setDouble(3, figure.area());
			statement.executeUpdate();
			try (ResultSet keys = statement.getGeneratedKeys()) {
				if(!keys.next())
					throw new SQLException("No key generated for figure " + figure.getType());
				return keys.getInt(1);
			}
		}
	}

	/**
	 * Creates a figure in the database ('Figure' table only)
	 * @return true if the figure was inserted
	 */
	@Override
	public boolean create(Figure figure) {
		try {
			insertFigure(figure);
			return true;
		} catch (SQLException e) {
			log.error("Could not create figure {}", figure.getType(), e);
			return false;
		}
	}

	/**
	 * Saves a figure in the database and its components (lengths, points)
	 * @return true if the figure was saved
	 */
	@Override
	public boolean save(Figure figure){
		return returnLastKey(figure) > 0;
	}

	@Override
	public boolean save(Figure obj, int id_figure, int num) {
		return false;
	}

	/**
	 * Saves a figure with its lengths and points, in a single transaction,
	 * and returns its key.
	 * @param figure the figure to save
	 * @return key of the saved figure, or 0 if nothing was saved
	 */
	public int returnLastKey(Figure figure){
		try {
			boolean autoCommit = this.connect.getAutoCommit();
			this.connect.setAutoCommit(false);
			try {
				int id_figure = insertFigure(figure);
				new LengthDAO(this.connect).insert(figure.lengths(), id_figure);
				new Point2DDAO(this.connect).insert(figure.getPoints(), id_figure);
				this.connect.commit();
				return id_figure;
			} catch (SQLException e) {
				this.connect.rollback();
				throw e;
			} finally {
				this.connect.setAutoCommit(autoCommit);
			}
		} catch (SQLException e) {
			log.error("Could not save figure {}", figure.getType(), e);
			return 0;
		}
	}
	@Override
	public boolean delete(Figure figure) {
		return false;
	}
	@Override
	public boolean update(Figure figure) {
		return false;
	}

	/**
	 * Gets a figure if the id is provided
	 * @return the figure rebuilt from its points (an EmptyFigure if the id is unknown)
	 */
	@Override
	public Figure find(int id) {
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		try (PreparedStatement statement = this.connect.prepareStatement(SELECT_POINTS)) {
			statement.setInt(1, id);
			try (ResultSet result = statement.executeQuery()) {
				while(result.next()){
					points.add(new Point2D.Double(result.getDouble("x"), result.getDouble("y")));
				}
			}
		} catch (SQLException e) {
			log.error("Could not find figure {}", id, e);
		}
		return Factory.gfigure(points);
	}

	/**
	 * Print the 'Figure' table in the console
	 */
	@Override
	public void show() {
		printTable("Figure");
	}

	/**
	 * @return every row of the 'Figure' table as strings (id, type, perimeter, area)
	 */
	public String[][] dump() {
		List<String[]> rows = new ArrayList<String[]>();
		try (PreparedStatement statement = this.connect.prepareStatement("SELECT * FROM Figure ORDER BY id");
		     ResultSet resultSet = statement.executeQuery()) {
			ResultSetMetaData metaData = resultSet.getMetaData();
			while(resultSet.next()){
				String[] row = new String[metaData.getColumnCount()];
				for(int i = 1; i <= metaData.getColumnCount(); i++)
					row[i-1] = String.valueOf(resultSet.getObject(i));
				rows.add(row);
			}
		} catch (SQLException e) {
			log.error("Could not dump the Figure table", e);
		}
		return rows.toArray(new String[0][]);
	}
}
