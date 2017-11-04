/*
 * CoordinateTest
 * 
 * Copyright (c) 2017 by xabuloes, http://github.com/xabuloes
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author xabuloes
 *
 */
public class CoordinateTest {
	
	/**
	 * Delta value used for comparison of double values.
	 */
	private static double COMPARISON_DELTA = 0.00001;
	
	
	// 4 different coordinates that are used multiple times in the test methods.
	private Coordinate coordinateA;
	private Coordinate coordinateB;
	private Coordinate coordinateC;
	private Coordinate coordinateD;
	
	// Coordinate with null to test edge cases
	private Coordinate nullCoordinate;
	
	@Before
	public void setup() {
		
		this.coordinateA = new Coordinate(1.23, 2.34, 3.45);
		this.coordinateB = new Coordinate(3.45, 2.34, 1.23);
		this.coordinateC = new Coordinate(-1.23, -2.34, -3.45);
		this.coordinateD = new Coordinate(-3.45, -2.34, -1.23);
		
		this.nullCoordinate = null;
		
	}

	@Test
	public void testConstructorSetsCorrectValues() {

		// Act & Assert
		
		assertEquals(this.coordinateA.getX(), 1.23, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateA.getY(), 2.34, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateA.getZ(), 3.45, CoordinateTest.COMPARISON_DELTA);
		
		assertEquals(this.coordinateB.getX(), 3.45, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateB.getY(), 2.34, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateB.getZ(), 1.23, CoordinateTest.COMPARISON_DELTA);

		assertEquals(this.coordinateC.getX(), -1.23, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateC.getY(), -2.34, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateC.getZ(), -3.45, CoordinateTest.COMPARISON_DELTA);
		
		assertEquals(this.coordinateD.getX(), -3.45, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateD.getY(), -2.34, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateD.getZ(), -1.23, CoordinateTest.COMPARISON_DELTA);
		
	}

	@Test
	public void settersSetCorrectValues() {

		// Act
		this.coordinateA.setX(3.45);
		this.coordinateA.setY(2.34);
		this.coordinateA.setZ(1.23);

		// Assert
		assertEquals(this.coordinateA.getX(), 3.45, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateA.getY(), 2.34, CoordinateTest.COMPARISON_DELTA);
		assertEquals(this.coordinateA.getZ(), 1.23, CoordinateTest.COMPARISON_DELTA);
	}

	@Test
	public void isEqualMatchesOnEqualCoordinates() {

		// Arrange
		Coordinate coordinateEqualToCoordinateA = new Coordinate(this.coordinateA.getX(), this.coordinateA.getY(), this.coordinateA.getZ());
		
		// Act & Assert
		assertEquals(this.coordinateA.equals(coordinateEqualToCoordinateA), true);
	}

	@Test
	public void isEqualDoesNotMatchOnNullCoordinates() {

		// Act & Assert
		assertEquals(this.coordinateA.isEqual(this.nullCoordinate), false);
	}

	@Test
	public void isEqualDoesNotMatchOnInequalCoordinates() {

		// Act & Assert
		assertEquals(this.coordinateA.isEqual(this.coordinateB), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDistanceFromNullCoordinateRaisesException() {

		// Act & Assert
		this.coordinateA.getDistance(this.nullCoordinate);
	}

	@Test
	public void getDistanceCalculatesDistanceBetweenCoordinates() {

		// Arrange
		double deltaX = Math.abs(this.coordinateA.getX() - this.coordinateB.getX());
		double deltaY = Math.abs(this.coordinateA.getY() - this.coordinateB.getY());
		double deltaZ = Math.abs(this.coordinateA.getZ() - this.coordinateB.getZ());

		double precalculatedResult = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);
		
		// Act & Assert
		assertEquals(this.coordinateA.getDistance(this.coordinateB), precalculatedResult, CoordinateTest.COMPARISON_DELTA);

	}

	@Test
	public void getDistanceReturnsPositiveValuesWithNegativeCoordinates() {

		// Act & Assert
		assertEquals(this.coordinateC.getDistance(this.coordinateD) > 0, true);

	}

	@Test
	public void getDistanceReturnsPositiveValuesWithSingleNegativeCoordinate() {

		// Act & Assert
		assertEquals(this.coordinateA.getDistance(this.coordinateC) > 0, true);

	}

	@Test
	public void equalsDoesNotMatchOnNotMatchingClass() {

		// Arrange
		Object notACoordinate = new Object();

		// Act & Assert
		assertEquals(this.coordinateA.equals(notACoordinate), false);

	}

}
