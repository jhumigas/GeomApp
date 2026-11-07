package com.geom.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class FactoryTest {

	private static final double S3 = Math.sqrt(3);

	/** Exact type check: the figure classes inherit from each other (e.g. Square extends Rhombus). */
	static void assertType(Class<? extends Figure> expected, Figure actual) {
		assertEquals(expected.getSimpleName(), actual.getClass().getSimpleName());
	}

	static ArrayList<Point2D> points(double... xy) {
		ArrayList<Point2D> pts = new ArrayList<>();
		for (int i = 0; i < xy.length; i += 2) {
			pts.add(new Point2D.Double(xy[i], xy[i + 1]));
		}
		return pts;
	}

	/** Rotates every point by the given angle (radians) around the origin. */
	static ArrayList<Point2D> rotate(ArrayList<Point2D> pts, double angle) {
		ArrayList<Point2D> out = new ArrayList<>();
		double c = Math.cos(angle), s = Math.sin(angle);
		for (Point2D p : pts) {
			out.add(new Point2D.Double(p.getX() * c - p.getY() * s, p.getX() * s + p.getY() * c));
		}
		return out;
	}

	static Stream<Arguments> shapes() {
		return Stream.of(
				// Degenerate cases
				Arguments.of("empty", points(), EmptyFigure.class),
				Arguments.of("point", points(1, 1), Punct.class),
				Arguments.of("line", points(0, 0, 3, 4), Line.class),
				// Triangles
				Arguments.of("equilateral", points(0, 0, 2, 0, 1, S3), EquilateralTriangle.class),
				Arguments.of("right isosceles", points(0, 0, 0, 1, 1, 0), RightIsoscelesTriangle.class),
				Arguments.of("right", points(0, 0, 0, 3, 4, 0), RightTriangle.class),
				Arguments.of("isosceles", points(0, 0, 2, 0, 1, 3), IsoscelesTriangle.class),
				Arguments.of("scalene", points(0, 0, 4, 0, 1, 3), Triangle.class),
				// Quadrilaterals
				Arguments.of("square", points(0, 0, 0, 2, 2, 2, 2, 0), Square.class),
				Arguments.of("rhombus", points(0, 0, 2, 1, 4, 0, 2, -1), Rhombus.class),
				Arguments.of("rectangle", points(0, 0, 0, 1, 3, 1, 3, 0), Rectangle.class),
				Arguments.of("parallelogram", points(0, 0, 1, 2, 4, 2, 3, 0), Parallelogram.class),
				Arguments.of("isosceles trapezoid", points(0, 0, 1, 2, 3, 2, 4, 0), Trapezoid.class),
				Arguments.of("right trapezoid", points(0, 0, 0, 2, 2, 2, 4, 0), Trapezoid.class),
				Arguments.of("convex quadrilateral", points(0, 0, 1, 3, 4, 4, 5, 0), ConvexQuadrilateral.class),
				Arguments.of("concave quadrilateral", points(0, 0, 2, 1, 4, 0, 2, 3), Quadrilateral.class),
				// Polygons
				Arguments.of("regular hexagon", points(2, 0, 1, S3, -1, S3, -2, 0, -1, -S3, 1, -S3), RegularPolygon.class),
				Arguments.of("irregular pentagon", points(0, 0, 0, 2, 2, 3, 4, 2, 3, 0), Polygon.class));
	}

	@ParameterizedTest(name = "{0}")
	@MethodSource("shapes")
	void classifiesShape(String name, ArrayList<Point2D> pts, Class<? extends Figure> expected) {
		assertType(expected, Factory.gfigure(pts));
	}

	@ParameterizedTest(name = "{0} (reversed order)")
	@MethodSource("shapes")
	void classificationDoesNotDependOnOrientation(String name, ArrayList<Point2D> pts, Class<? extends Figure> expected) {
		Collections.reverse(pts);
		assertType(expected, Factory.gfigure(pts));
	}

	@ParameterizedTest(name = "{0} (rotated)")
	@MethodSource("shapes")
	void classificationSurvivesRotation(String name, ArrayList<Point2D> pts, Class<? extends Figure> expected) {
		// Rotating introduces floating-point noise in every coordinate
		assertType(expected, Factory.gfigure(rotate(pts, Math.PI / 7)));
	}

	@Test
	void dropsAlignedPoints() {
		// The middle point (1,1) lies on the segment and is removed
		assertType(Line.class, Factory.gfigure(points(0, 0, 1, 1, 2, 2)));
		// (2,0) lies on the bottom side of the square
		assertType(Square.class, Factory.gfigure(points(0, 0, 0, 4, 4, 4, 4, 0, 2, 0)));
	}

	@Test
	void computesPerimeterAndArea() {
		Figure rightTriangle = Factory.gfigure(points(0, 0, 0, 3, 4, 0));
		assertEquals(12.0, rightTriangle.perimeter(), 1e-9);
		assertEquals(6.0, rightTriangle.area(), 1e-9);

		Figure rectangle = Factory.gfigure(points(0, 0, 0, 1, 3, 1, 3, 0));
		assertEquals(8.0, rectangle.perimeter(), 1e-9);
		assertEquals(3.0, rectangle.area(), 1e-9);

		Figure concave = Factory.gfigure(points(0, 0, 2, 1, 4, 0, 2, 3));
		assertEquals(4.0, concave.area(), 1e-9);
	}

	@Test
	void degenerateFiguresHaveNoArea() {
		assertEquals(0.0, Factory.gfigure(points(1, 1)).area());
		assertEquals(5.0, Factory.gfigure(points(0, 0, 3, 4)).perimeter(), 1e-9);
		assertEquals(0.0, Factory.gfigure(points(0, 0, 3, 4)).area());
	}
}
