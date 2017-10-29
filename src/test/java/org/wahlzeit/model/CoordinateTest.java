package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CoordinateTest {
	
	@Test
	public void settersSetCorrectValues() {
		// Arrange
		Coordinate coordinate = new Coordinate(0.0, 0.0, 0.0);
		
		// Act
		coordinate.setX(1.23);
		coordinate.setY(2.34);
		coordinate.setZ(3.45);
		
		// Assert
		assertTrue(coordinate.getX() == 1.23);
		assertTrue(coordinate.getY() == 2.34);
		assertTrue(coordinate.getZ() == 3.45);
	}

	@Test
	public void isEqualMatchesOnEqualCoordinates() {

		// Arrange
		Coordinate coordinateA = new Coordinate(1.23, 2.34, 3.45);
		Coordinate coordinateB = new Coordinate(1.23, 2.34, 3.45);

		// Act & Assert
		assertEquals(coordinateA.equals(coordinateB), true);
	}

	@Test
	public void isEqualDoesNotMatchOnNullCoordinates() {

		// Arrange
		Coordinate coordinateA = new Coordinate(1.23, 2.34, 3.45);
		Coordinate coordinateB = null;

		// Act & Assert
		assertEquals(coordinateA.isEqual(coordinateB), false);
	}

	@Test
	public void isEqualDoesNotMatchOnInequalCoordinates() {

		// Arrange
		Coordinate coordinateA = new Coordinate(1.23, 2.34, 3.45);
		Coordinate coordinateB = new Coordinate(3.45, 2.34, 1.23);

		// Act & Assert
		assertEquals(coordinateA.isEqual(coordinateB), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDistanceFromNullCoordinateRaisesException() {

		// Arrange
		Coordinate coordinateA = new Coordinate(1.23, 2.34, 3.45);
		Coordinate coordinateB = null;

		// Act & Assert
		coordinateA.getDistance(coordinateB);
	}

	@Test
	public void getDistanceCalculatesDistanceBetweenCoordinates() {

		// Arrange
		Coordinate coordinateA = new Coordinate(1.23, 2.34, 3.45);
		Coordinate coordinateB = new Coordinate(3.45, 1.23, 2.34);

		System.out.println(coordinateA.getDistance(coordinateB));

		// Act & Assert
		double dx = Math.abs(coordinateA.getX() - coordinateB.getX());
		double dy = Math.abs(coordinateA.getY() - coordinateB.getY());
		double dz = Math.abs(coordinateA.getZ() - coordinateB.getZ());

		// Act & Assert
		assertTrue(coordinateA.getDistance(coordinateB) == Math.sqrt(dx * dx + dy * dy + dz * dz));

	}

	@Test
	public void getDistanceReturnsPositiveValuesWithNegativeCoordinates() {

		// Arrange
		Coordinate coordinateA = new Coordinate(-1.23, -2.34, -3.45);
		Coordinate coordinateB = new Coordinate(-3.45, -1.23, -2.34);

		// Act & Assert
		assertTrue(coordinateA.getDistance(coordinateB) > 0);

	}

	@Test
	public void getDistanceReturnsPositiveValuesWithSingleNegativeCoordinate() {

		// Arrange
		Coordinate coordinateA = new Coordinate(-1.23, -2.34, -3.45);
		Coordinate coordinateB = new Coordinate(3.45, 1.23, 2.34);

		// Act & Assert
		assertTrue(coordinateA.getDistance(coordinateB) > 0);

	}

}
