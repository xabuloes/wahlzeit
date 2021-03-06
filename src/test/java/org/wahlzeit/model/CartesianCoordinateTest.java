/*
 * CartesianCoordinateTest
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

public class CartesianCoordinateTest {

	/**
	 * Delta value used for comparison of double values.
	 */
	private static double DOUBLE_COMPARISON_DELTA = 0.00001;

	// 4 different coordinates that are used multiple times in the test methods.
	private CartesianCoordinate cartesianCoordinateA;
	private CartesianCoordinate cartesianCoordinateB;
	private CartesianCoordinate cartesianCoordinateC;
	private CartesianCoordinate cartesianCoordinateD;

	// Coordinate with null to test edge cases
	private CartesianCoordinate nullCoordinate;

	@Before
	public void setup() {

		this.cartesianCoordinateA = CartesianCoordinate.get(1.23, 2.34, 3.45);
		this.cartesianCoordinateB = CartesianCoordinate.get(3.45, 2.34, 1.23);
		this.cartesianCoordinateC = CartesianCoordinate.get(-1.23, -2.34, -3.45);
		this.cartesianCoordinateD = CartesianCoordinate.get(-3.45, -2.34, -1.23);

		this.nullCoordinate = null;

	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorDoesNotAcceptNanValue() {
		CartesianCoordinate.get(Double.NaN, 0.0, 0.0);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetXDoesNotAcceptNanValues() {
		this.cartesianCoordinateA.setX(Double.NaN);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetYDoesNotAcceptNanValues() {
		this.cartesianCoordinateA.setY(Double.NaN);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetZDoesNotAcceptNanValues() {
		this.cartesianCoordinateA.setZ(Double.NaN);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetXDoesNotAcceptInfiniteValues() {
		this.cartesianCoordinateA.setX(Double.POSITIVE_INFINITY);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetYDoesNotAcceptInfiniteValues() {
		this.cartesianCoordinateA.setY(Double.POSITIVE_INFINITY);
	}

	@Test(expected = CustomAssertionError.class)
	public void testSetZDoesNotAcceptInfiniteValues() {
		this.cartesianCoordinateA.setZ(Double.POSITIVE_INFINITY);
	}

	@Test
	public void testGetDistanceIsCommutative() {

		final double distanceFromAToB = cartesianCoordinateA.getDistance(cartesianCoordinateB);
		final double distanceFromBToA = cartesianCoordinateB.getDistance(cartesianCoordinateA);

		assertEquals(distanceFromAToB, distanceFromBToA, DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void sphericAndCartesianDistanceReturnSameValues() {

		final double sphericDistance = this.cartesianCoordinateA.getSphericDistance(this.cartesianCoordinateB);
		final double cartesianDistance = this.cartesianCoordinateA.getCartesianDistance(this.cartesianCoordinateB);

		assertEquals(sphericDistance, cartesianDistance, DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void testConstructorSetsCorrectValues() {

		// Act & Assert

		assertEquals(this.cartesianCoordinateA.getX(), 1.23, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateA.getY(), 2.34, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateA.getZ(), 3.45, DOUBLE_COMPARISON_DELTA);

		assertEquals(this.cartesianCoordinateB.getX(), 3.45, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateB.getY(), 2.34, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateB.getZ(), 1.23, DOUBLE_COMPARISON_DELTA);

		assertEquals(this.cartesianCoordinateC.getX(), -1.23, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateC.getY(), -2.34, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateC.getZ(), -3.45, DOUBLE_COMPARISON_DELTA);

		assertEquals(this.cartesianCoordinateD.getX(), -3.45, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateD.getY(), -2.34, DOUBLE_COMPARISON_DELTA);
		assertEquals(this.cartesianCoordinateD.getZ(), -1.23, DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void settersSetCorrectValues() {

		// Act
		CartesianCoordinate coordinateWithNewValues = this.cartesianCoordinateA.setX(3.45).setY(2.34).setZ(1.23);

		// Assert
		assertEquals(coordinateWithNewValues.getX(), 3.45, DOUBLE_COMPARISON_DELTA);
		assertEquals(coordinateWithNewValues.getY(), 2.34, DOUBLE_COMPARISON_DELTA);
		assertEquals(coordinateWithNewValues.getZ(), 1.23, DOUBLE_COMPARISON_DELTA);
	}

	@Test
	public void settersCreateSeparateInstances() {

		// Arrange & Act
		CartesianCoordinate firstCoordinateValue = this.cartesianCoordinateA.setX(3.45);

		CartesianCoordinate secondCoordinateValue = firstCoordinateValue.setY(4.56);

		CartesianCoordinate thirdCoordinateValue = secondCoordinateValue.setZ(1.23);

		// Assert
		assertNotEquals(firstCoordinateValue, secondCoordinateValue);
		assertNotEquals(secondCoordinateValue, thirdCoordinateValue);
		assertNotEquals(thirdCoordinateValue, firstCoordinateValue);
	}

	@Test
	public void coordinateObjectsAreImmutable() {

		// Arrange
		double xValueWeAssumeToBeImmutable = this.cartesianCoordinateA.getX();

		// Act
		this.cartesianCoordinateA.setX(xValueWeAssumeToBeImmutable * 2);

		// Assert
		assertEquals(this.cartesianCoordinateA.getX(), xValueWeAssumeToBeImmutable, DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void coordinateObjectsAreReallyShared() {

		CartesianCoordinate cartesianCoordinate = CartesianCoordinate.get(this.cartesianCoordinateA.getX(),
				this.cartesianCoordinateA.getY(), this.cartesianCoordinateA.getZ());

		// References should be both pointing to the shared object
		assertEquals(cartesianCoordinate, this.cartesianCoordinateA);
	}

	@Test
	public void equalsMatchesOnEqualCoordinates() {

		// Arrange
		CartesianCoordinate coordinateEqualToCoordinateA = CartesianCoordinate.get(this.cartesianCoordinateA.getX(),
				this.cartesianCoordinateA.getY(), this.cartesianCoordinateA.getZ());

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.equals(coordinateEqualToCoordinateA), true);
	}

	@Test
	public void isEqualDoesNotMatchOnNullCoordinates() {

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.isEqual(this.nullCoordinate), false);
	}

	@Test
	public void isEqualDoesNotMatchOnInequalCoordinates() {

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.isEqual(this.cartesianCoordinateB), false);
	}

	@Test(expected = CustomAssertionError.class)
	public void getDistanceFromNullCoordinateRaisesException() {

		// Act & Assert
		this.cartesianCoordinateA.getDistance(this.nullCoordinate);
	}

	@Test
	public void getDistanceReturnsTheSameValueAsGetCartesianDistance() {

		// Act & Assert
		assertEquals(cartesianCoordinateA.getDistance(cartesianCoordinateB),
				cartesianCoordinateA.getCartesianDistance(cartesianCoordinateB), DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void getDistanceReturnsTheSameValueAsGetCartesianDistanceWithSphericCoordinate() {

		SphericCoordinate sphericCoordinate = SphericCoordinate.get(1.0, 1.0, 1.0);

		// Act & Assert
		assertEquals(cartesianCoordinateA.getDistance(sphericCoordinate),
				cartesianCoordinateA.getCartesianDistance(sphericCoordinate), DOUBLE_COMPARISON_DELTA);

	}

	@Test
	public void getCartesianDistanceCalculatesDistanceBetweenCoordinates() {

		// Arrange
		double deltaX = Math.abs(this.cartesianCoordinateA.getX() - this.cartesianCoordinateB.getX());
		double deltaY = Math.abs(this.cartesianCoordinateA.getY() - this.cartesianCoordinateB.getY());
		double deltaZ = Math.abs(this.cartesianCoordinateA.getZ() - this.cartesianCoordinateB.getZ());

		double precalculatedResult = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.getCartesianDistance(this.cartesianCoordinateB), precalculatedResult,
				DOUBLE_COMPARISON_DELTA);
	}

	@Test
	public void getCartesianDistanceReturnsPositiveValuesWithNegativeCoordinates() {

		// Act & Assert
		assertEquals(this.cartesianCoordinateC.getCartesianDistance(this.cartesianCoordinateD) > 0, true);
	}

	@Test
	public void getCartesianDistanceReturnsPositiveValuesWithSingleNegativeCoordinate() {

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.getCartesianDistance(this.cartesianCoordinateC) > 0, true);
	}

	@Test
	public void equalsDoesNotMatchOnNotMatchingClass() {

		// Arrange
		Object notACoordinate = new Object();

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.equals(notACoordinate), false);
	}

	@Test
	public void isEqualDoesNotMatchOnNotMatchingClass() {

		// Arrange
		SphericCoordinate notACartesianCoordinate = SphericCoordinate.get(1.0, 1.0, 1.0);

		// Act & Assert
		assertEquals(this.cartesianCoordinateA.isEqual(notACartesianCoordinate), false);
	}

	@Test
	public void asSphericCoordinateReturnsValidValues() {

		// Arrange
		double expectedRadius = Math.sqrt(Math.pow(cartesianCoordinateA.getX(), 2)
				+ Math.pow(cartesianCoordinateA.getY(), 2) + Math.pow(cartesianCoordinateA.getZ(), 2));

		double expectedLongitude = Math.atan(cartesianCoordinateA.getY() / cartesianCoordinateA.getX());
		double expectedLatitude = Math
				.atan(Math.sqrt(Math.pow(cartesianCoordinateA.getX(), 2) + Math.pow(cartesianCoordinateA.getY(), 2))
						/ (cartesianCoordinateA.getZ()));

		// Act
		SphericCoordinate asSphericCoordinate = this.cartesianCoordinateA.asSphericCoordinate();

		// Assert
		assertTrue(asSphericCoordinate instanceof SphericCoordinate);

		assertEquals(asSphericCoordinate.getRadius(), expectedRadius, DOUBLE_COMPARISON_DELTA);
		assertEquals(asSphericCoordinate.getLongitude(), expectedLongitude, DOUBLE_COMPARISON_DELTA);
		assertEquals(asSphericCoordinate.getLatitude(), expectedLatitude, DOUBLE_COMPARISON_DELTA);

	}

}
