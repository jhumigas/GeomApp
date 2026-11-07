package com.geom.database;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.geom.Point2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.geom.model.Factory;
import com.geom.model.Figure;

/** Runs the DAOs against a fresh in-memory SQLite database seeded like the real one. */
class FigureDAOTest {

	private Connection conn;
	private FigureDAO figures;

	@BeforeEach
	void openDatabase() throws SQLException {
		conn = DriverManager.getConnection("jdbc:sqlite::memory:");
		assertTrue(GeomAppConnection.testDatabase(conn));
		figures = new FigureDAO(conn);
	}

	@AfterEach
	void closeDatabase() throws SQLException {
		conn.close();
	}

	private static ArrayList<Point2D> points(double... xy) {
		ArrayList<Point2D> pts = new ArrayList<>();
		for (int i = 0; i < xy.length; i += 2) {
			pts.add(new Point2D.Double(xy[i], xy[i + 1]));
		}
		return pts;
	}

	@Test
	void schemaIsSeededWithOneSquare() {
		assertEquals(List.of(1), DAOFactory.getKeys(conn, "Figure"));
		assertEquals("Square", figures.find(1).getType());
	}

	@Test
	void savedFigureCanBeFoundAgain() {
		Figure triangle = Factory.gfigure(points(0, 0, 0, 3, 4, 0));

		int id = figures.returnLastKey(triangle);

		assertEquals(2, id);
		Figure found = figures.find(id);
		assertEquals("Right Triangle", found.getType());
		// Points come back in the order they were saved
		assertEquals(triangle.getPoints(), found.getPoints());
		assertEquals(List.of(1, 2), DAOFactory.getKeys(conn, "Figure"));
	}

	@Test
	void saveStoresTypePerimeterAndAreaAndLengths() {
		Figure rectangle = Factory.gfigure(points(0, 0, 0, 1, 3, 1, 3, 0));

		assertTrue(figures.save(rectangle));

		String[][] rows = figures.dump();
		assertEquals(2, rows.length);
		assertArrayEquals(new String[] { "2", "Rectangle", "8.0", "3.0" }, rows[1]);

		LengthDAO lengths = new LengthDAO(conn);
		// Seed has 4 lengths (ids 1-4), the rectangle's start at id 5
		assertEquals(1.0, lengths.find(5), 1e-9);
		assertEquals(3.0, lengths.find(6), 1e-9);
	}

	@Test
	void createStoresTheFigureType() {
		assertTrue(figures.create(Factory.gfigure(points(0, 0, 0, 3, 4, 0))));
		assertEquals("Right Triangle", figures.dump()[1][1]);
	}

	@Test
	void textIsStoredAsDataNotSql() {
		Figure tricky = new Figure() {
			@Override
			public String getType() {
				return "x'); DROP TABLE Figure; --";
			}
		};
		assertTrue(figures.create(tricky));
		assertEquals("x'); DROP TABLE Figure; --", figures.dump()[1][1]);
	}

	@Test
	void findsPointsAndLengthsById() {
		assertEquals(new Point2D.Double(10, 0), new Point2DDAO(conn).find(2));
		assertEquals(10.0, new LengthDAO(conn).find(1), 1e-9);
	}

	@Test
	void unknownIdsReturnEmptyValues() {
		assertEquals("Inexistant", figures.find(42).getType());
		assertEquals(new Point2D.Double(0, 0), new Point2DDAO(conn).find(42));
		assertEquals(0.0, new LengthDAO(conn).find(42));
	}

	@Test
	void rejectsUnknownTableNames() {
		assertThrows(IllegalArgumentException.class, () -> DAOFactory.getKeys(conn, "Figure; DROP TABLE Figure"));
	}
}
