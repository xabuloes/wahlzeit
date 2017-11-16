/*
 * SphericCoordinateTest
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
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.search.query.ExpressionParser.expression_return;
import com.google.appengine.repackaged.com.google.type.LatLngProtoInternalDescriptors;

public class SphericCoordinateTest {

	/**
	 * Delta value used for comparison of double values.
	 */
	private static double COMPARISON_DELTA = 0.00001;

	private SphericCoordinate sphericCoordinateA;
	private SphericCoordinate sphericCoordinateB;

	private SphericCoordinate nullCoordinate;

	@Before
	public void setup() {

		sphericCoordinateA = new SphericCoordinate(1.00, 2.00, 3.00);
		sphericCoordinateB = new SphericCoordinate(10.00, 100.00, 3.00);

		nullCoordinate = null;

	}

	@Test
	public void testConstructorSetsCorrectValues() {

		// Act & Assert

		assertEquals(sphericCoordinateA.getLatitude(), 1.00, COMPARISON_DELTA);
		assertEquals(sphericCoordinateA.getLongitude(), 2.00, COMPARISON_DELTA);
		assertEquals(sphericCoordinateA.getRadius(), 3.00, COMPARISON_DELTA);

		assertEquals(sphericCoordinateB.getLatitude(), 10.00, COMPARISON_DELTA);
		assertEquals(sphericCoordinateB.getLongitude(), 100.00, COMPARISON_DELTA);
		assertEquals(sphericCoordinateB.getRadius(), 3.00, COMPARISON_DELTA);

	}

	@Test
	public void settersSetCorrectValues() {

		// Act
		sphericCoordinateA.setLatitude(3.45);
		sphericCoordinateA.setLongitude(2.34);
		sphericCoordinateA.setRadius(1.23);

		// Assert
		assertEquals(sphericCoordinateA.getLatitude(), 3.45, COMPARISON_DELTA);
		assertEquals(sphericCoordinateA.getLongitude(), 2.34, COMPARISON_DELTA);
		assertEquals(sphericCoordinateA.getRadius(), 1.23, COMPARISON_DELTA);
	}

	@Test
	public void isEqualMatchesOnEqualCoordinates() {

		// Arrange
		SphericCoordinate coordinateEqualToCoordinateA = new SphericCoordinate(sphericCoordinateA.getLatitude(),
				sphericCoordinateA.getLongitude(), sphericCoordinateA.getRadius());

		// Act & Assert
		assertEquals(sphericCoordinateA.equals(coordinateEqualToCoordinateA), true);
	}

	@Test
	public void isEqualDoesNotMatchOnNullCoordinates() {

		// Act & Assert
		assertEquals(sphericCoordinateA.isEqual(nullCoordinate), false);
	}

	@Test
	public void isEqualDoesNotMatchOnInequalCoordinates() {

		// Act & Assert
		assertEquals(sphericCoordinateA.isEqual(sphericCoordinateB), false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void getDistanceFromNullCoordinateRaisesException() {

		// Act & Assert
		sphericCoordinateA.getDistance(nullCoordinate);
	}

	@Test
	public void asCartesianCoordinateReturnsValidValues() {

		// Arrange
		double expectedX = sphericCoordinateA.getRadius() * Math.sin(sphericCoordinateA.getLatitude())
				* Math.cos(sphericCoordinateA.getLongitude());
		double expectedY = sphericCoordinateA.getRadius() * Math.sin(sphericCoordinateA.getLatitude())
				* Math.sin(sphericCoordinateA.getLongitude());
		double expectedZ = sphericCoordinateA.getRadius() * Math.cos(sphericCoordinateA.getLatitude());

		// Act
		CartesianCoordinate cartesianCoordinate = this.sphericCoordinateA.asCartesianCoordinate();

		// Assert
		assertTrue(cartesianCoordinate instanceof CartesianCoordinate);

		assertEquals(cartesianCoordinate.getX(), expectedX, COMPARISON_DELTA);
		assertEquals(cartesianCoordinate.getY(), expectedY, COMPARISON_DELTA);
		assertEquals(cartesianCoordinate.getZ(), expectedZ, COMPARISON_DELTA);

	}

	@Test
	public void getDistanceReturnsTheSameValueAsGetSphericDistance() {

		// Act & Assert
		assertEquals(sphericCoordinateA.getDistance(sphericCoordinateB),
				sphericCoordinateA.getSphericDistance(sphericCoordinateB), COMPARISON_DELTA);

	}

	@Test
	public void getDistanceReturnsTheSameValueAsGetSphericDistanceWithCartesianCoordinate() {

		CartesianCoordinate cartesianCoordinate = new CartesianCoordinate(1.23, 2.34, 3.45);

		// Act & Assert
		assertEquals(sphericCoordinateA.getDistance(cartesianCoordinate),
				sphericCoordinateA.getSphericDistance(cartesianCoordinate), COMPARISON_DELTA);

	}

	@Test
	public void sphericDistanceIsCalculatedCorrectly() {

		// Arrange
		double expectedDistance = 4.4056103950073995;

		// Act & Assert
		assertEquals(sphericCoordinateA.getSphericDistance(sphericCoordinateB), expectedDistance, COMPARISON_DELTA);

	}

}
