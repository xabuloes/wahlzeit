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
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.search.query.ExpressionParser.expression_return;
import com.google.appengine.repackaged.com.google.type.LatLngProtoInternalDescriptors;

public class SphericCoordinateTest {

	/**
	 * Delta value used for comparison of double values.
	 */
	private static double DOUBLE_COMPARISON_DELTA = 0.00001;

	private SphericCoordinate sphericCoordinateA;
	private SphericCoordinate sphericCoordinateB;

	private SphericCoordinate nullCoordinate;

	@Before
	public void setup() {

		sphericCoordinateA = SphericCoordinate.get(1.00, 2.00, 3.00);
		sphericCoordinateB = SphericCoordinate.get(1.50, 1.60, 1.70);

		nullCoordinate = null;

	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorDoesNotAcceptNanValue() {
		CartesianCoordinate.get(Double.NaN, 0.0, 0.0);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetLatitudeDoesNotAcceptNanValues() {
		this.sphericCoordinateA.setLatitude(Double.NaN);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetLongitudeDoesNotAcceptNanValues() {
		this.sphericCoordinateA.setLongitude(Double.NaN);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetRadiusDoesNotAcceptNanValues() {
		this.sphericCoordinateA.setRadius(Double.NaN);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetLatitudeDoesNotAcceptInfiniteValues() {
		this.sphericCoordinateA.setLatitude(Double.POSITIVE_INFINITY);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetLongitudeDoesNotAcceptInfiniteValues() {
		this.sphericCoordinateA.setLongitude(Double.POSITIVE_INFINITY);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetRadiusDoesNotAcceptInfiniteValues() {
		this.sphericCoordinateA.setRadius(Double.POSITIVE_INFINITY);
	}

	@Test
	public void testConstructorSetsCorrectValues() {

		// Act & Assert

		assertEquals(sphericCoordinateA.getLatitude(), 1.00, DOUBLE_COMPARISON_DELTA);
		assertEquals(sphericCoordinateA.getLongitude(), 2.00, DOUBLE_COMPARISON_DELTA);
		assertEquals(sphericCoordinateA.getRadius(), 3.00, DOUBLE_COMPARISON_DELTA);

		assertEquals(sphericCoordinateB.getLatitude(), 1.50, DOUBLE_COMPARISON_DELTA);
		assertEquals(sphericCoordinateB.getLongitude(), 1.60, DOUBLE_COMPARISON_DELTA);
		assertEquals(sphericCoordinateB.getRadius(), 1.70, DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void testGetDistanceIsCommutative() {

		final double distanceFromAToB = sphericCoordinateA.getDistance(sphericCoordinateB);
		final double distanceFromBToA = sphericCoordinateB.getDistance(sphericCoordinateA);

		assertEquals(distanceFromAToB, distanceFromBToA, DOUBLE_COMPARISON_DELTA);

	}

	@Test(expected = CustomAssertionError.class)
	public void testSetLatitudeDoNotAcceptInvalidValues() {

		// Act
		sphericCoordinateA.setLatitude(100);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetLongitudeDoNotAcceptInvalidValues() {

		// Act
		sphericCoordinateA.setLongitude(100);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetRadiusDoesNotAcceptNegativeValues() {

		// Act
		sphericCoordinateA.setRadius(-10);

	}

	@Test
	public void settersSetCorrectValues() {

		// Act
		SphericCoordinate newCoordinateValue = sphericCoordinateA.setLatitude(3.45).setLongitude(2.34).setRadius(1.23);

		// Assert
		assertEquals(newCoordinateValue.getLatitude(), 3.45, DOUBLE_COMPARISON_DELTA);
		assertEquals(newCoordinateValue.getLongitude(), 2.34, DOUBLE_COMPARISON_DELTA);
		assertEquals(newCoordinateValue.getRadius(), 1.23, DOUBLE_COMPARISON_DELTA);
	}

	@Test
	public void settersCreateSeparateInstances() {

		// Arrange & Act
		SphericCoordinate firstCoordinateValue = this.sphericCoordinateA.setLatitude(3.45);

		SphericCoordinate secondCoordinateValue = firstCoordinateValue.setLongitude(2.34);

		SphericCoordinate thirdCoordinateValue = secondCoordinateValue.setRadius(1.23);

		// Assert
		assertNotEquals(firstCoordinateValue, secondCoordinateValue);
		assertNotEquals(secondCoordinateValue, thirdCoordinateValue);
		assertNotEquals(thirdCoordinateValue, firstCoordinateValue);
	}

	@Test
	public void coordinateObjectsAreImmutable() {

		// Arrange
		double latitudeValueWeAssumeToBeImmutable = this.sphericCoordinateA.getLatitude();
		double longitudeValueWeAssumeToBeImmutable = this.sphericCoordinateA.getLongitude();
		double radiusValueWeAssumeToBeImmutable = this.sphericCoordinateA.getRadius();

		// Act
		this.sphericCoordinateA.setLatitude(latitudeValueWeAssumeToBeImmutable / 2);
		this.sphericCoordinateA.setLongitude(longitudeValueWeAssumeToBeImmutable / 2);
		this.sphericCoordinateA.setLatitude(radiusValueWeAssumeToBeImmutable / 2);

		// Assert
		assertEquals(this.sphericCoordinateA.getLatitude(), latitudeValueWeAssumeToBeImmutable,
				DOUBLE_COMPARISON_DELTA);
		assertEquals(this.sphericCoordinateA.getLongitude(), longitudeValueWeAssumeToBeImmutable,
				DOUBLE_COMPARISON_DELTA);
		assertEquals(this.sphericCoordinateA.getRadius(), radiusValueWeAssumeToBeImmutable, DOUBLE_COMPARISON_DELTA);
	}

	@Test
	public void coordinateObjectsAreReallyShared() {

		SphericCoordinate sphericCoordinate = SphericCoordinate.get(this.sphericCoordinateA.getLatitude(),
				this.sphericCoordinateA.getLongitude(), this.sphericCoordinateA.getRadius());

		// References should be both pointing to the shared object
		assertEquals(sphericCoordinate, this.sphericCoordinateA);
		assertTrue(sphericCoordinate.equals(this.sphericCoordinateA));
	}

	@Test
	public void isEqualMatchesOnEqualCoordinates() {

		// Arrange
		SphericCoordinate coordinateEqualToCoordinateA = SphericCoordinate.get(sphericCoordinateA.getLatitude(),
				sphericCoordinateA.getLongitude(), sphericCoordinateA.getRadius());

		// Act & Assert
		assertEquals(sphericCoordinateA.equals(coordinateEqualToCoordinateA), true);
	}

	@Test
	public void equalsDoesNotMatchOnNotMatchingClass() {

		// Arrange
		Object notACoordinate = new Object();

		// Act & Assert
		assertEquals(this.sphericCoordinateA.equals(notACoordinate), false);
	}

	@Test
	public void isEqualDoesNotMatchOnNotMatchingClass() {

		// Arrange
		CartesianCoordinate notASphericCoordinate = CartesianCoordinate.get(1.0, 1.0, 1.0);

		// Act & Assert
		assertEquals(this.sphericCoordinateA.isEqual(notASphericCoordinate), false);
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

	@Test(expected = CustomAssertionError.class)
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

		assertEquals(cartesianCoordinate.getX(), expectedX, DOUBLE_COMPARISON_DELTA);
		assertEquals(cartesianCoordinate.getY(), expectedY, DOUBLE_COMPARISON_DELTA);
		assertEquals(cartesianCoordinate.getZ(), expectedZ, DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void getDistanceReturnsTheSameValueAsGetSphericDistance() {

		// Act & Assert
		assertEquals(sphericCoordinateA.getDistance(sphericCoordinateB),
				sphericCoordinateA.getSphericDistance(sphericCoordinateB), DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void getDistanceReturnsTheSameValueAsGetSphericDistanceWithCartesianCoordinate() {

		CartesianCoordinate cartesianCoordinate = CartesianCoordinate.get(1.23, 2.34, 3.45);

		// Act & Assert
		assertEquals(sphericCoordinateA.getDistance(cartesianCoordinate),
				sphericCoordinateA.getSphericDistance(cartesianCoordinate), DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void sphericDistanceIsCalculatedCorrectly() {

		// TODO: Adjust to correct spheric distance calculation

		// Arrange
		double expectedDistance = 1.9011823808457222;

		// Act & Assert
		assertEquals(sphericCoordinateA.getSphericDistance(sphericCoordinateB), expectedDistance,
				DOUBLE_COMPARISON_DELTA);

	}

}
