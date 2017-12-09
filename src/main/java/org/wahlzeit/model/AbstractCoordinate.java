/*
 * AbstractCoordinate
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

/**
 * Represents a generic coordinate and associated calculations.
 * Uses a "design-by-primitives" pattern to delegate specific logic to implementing subclasses.
 */
public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * We assume that subclasses need to compare double (floating point) values, so
	 * we provide the delta/precision in a central place.
	 */
	protected final static double DEFAULT_DOUBLE_COMPARISON_DELTA = 0.00001;
	
	/**
	 * Returns distance to given coordinate. Measure of distance depends on the
	 * implementation in the subclass.
	 * 
	 * @param coordinateB
	 *            Coordinate to which distance should be calculated
	 * @return calculated distance between the two coordinates in the coordinate
	 *         system
	 * 
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public final double getDistance(Coordinate coordinateB) {

		this.assertValueIsNotNull(coordinateB);

		final double distance = this.doGetDistance(coordinateB);
		
		this.assertDoubleValueIsGreaterOrEqualThanZero(distance);
		this.assertClassInvariants();
		
		return distance;
	}

	/**
	 * Calculate Cartesian distance to coordinateB.
	 * 
	 * @param coordinateB
	 * 
	 * @return
	 * 
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public final double getCartesianDistance(Coordinate coordinateB) {

		this.assertValueIsNotNull(coordinateB);

		final double distance = this.doGetCartesianDistance(coordinateB);
		
		this.assertDoubleValueIsGreaterOrEqualThanZero(distance);
		this.assertClassInvariants();
		
		return distance;
	}

	/**
	 * Calculate spheric distance to coordinateB.
	 * 
	 * @param coordinateB
	 * 
	 * @return
	 * 
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public final double getSphericDistance(Coordinate coordinateB) {

		this.assertValueIsNotNull(coordinateB);

		final double distance = this.doGetSphericDistance(coordinateB);
		
		this.assertDoubleValueIsGreaterOrEqualThanZero(distance);
		this.assertClassInvariants();
		
		return distance;
	}

	/**
	 * Check if given coordinate is (value-)equal to associated coordinate.
	 * 
	 * @param coordinateToCompare
	 * 
	 * @return
	 */
	public final boolean isEqual(Coordinate coordinateToCompare) {
	
		// notNull assertion is skipped, since we allow null coordinates here
		
		if (coordinateToCompare == null) {
			return false;
		} else if (!this.getClass().isInstance(coordinateToCompare)) {
			return false;
		}

		final boolean isEqual = this.areDoublesEqual(this.getDistance(coordinateToCompare), 0.0);
		
		this.assertClassInvariants();
		
		return isEqual;
	}

	/**
	 * Override of equals() method to compare coordinate values.
	 * 
	 * @return true if equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		
		this.assertValueIsNotNull(obj);
		
		if (!this.getClass().isInstance(obj)) {
			return false;
		}

		// Delegate execution (+ further assertions) to isEqual()
		final boolean isEqual = this.isEqual((Coordinate) obj);
		
		this.assertClassInvariants();
		
		return isEqual;
	}

	/**
	 * Checks whether the two given double values are equal (with a certain
	 * precision).
	 * 
	 * This method is not final, since subclasses wish to increase precision by
	 * using another value for DEFAULT_DOUBLE_COMPARISON_DELTA.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected boolean areDoublesEqual(double a, double b) {
		return (Math.abs(a - b) < DEFAULT_DOUBLE_COMPARISON_DELTA);
	}
	
	/**
	 * Throw a {@link CoordinateAssertionError} when the given @param object is null.
	 * 
	 * @param object
	 */
	protected final void assertValueIsNotNull(Object object) {
		if (object == null) {
			throw new CoordinateAssertionError("null value was given to calculate distance between to coordinates");
		}
	}
	
	/**
	 * Assert that shouldBeGreaterOrEqualZero has a value greater or equal to zero.
	 * 
	 * @param shouldBeGreaterOrEqualZero
	 */
	protected final void assertDoubleValueIsGreaterOrEqualThanZero(double shouldBeGreaterOrEqualZero) {
		if(shouldBeGreaterOrEqualZero < 0) {
			throw new CoordinateAssertionError("Double value " + shouldBeGreaterOrEqualZero + " is smaller than zero");
		}
	}
	
	/**
	 * Assert that shouldBeAFiniteNumber is finite and not NaN.
	 * 
	 * @param shouldBeAFiniteNumber
	 * 
	 */
	protected final void assertDoubleIsFiniteNumber(double shouldBeAFiniteNumber) {
		if(Double.isNaN(shouldBeAFiniteNumber) || Double.isInfinite(shouldBeAFiniteNumber)) {
			throw new CoordinateAssertionError("Double value " + shouldBeAFiniteNumber + " is either NaN or infinite");
		}
	}
	
	/**
	 * Method is not final, since sub classes may extend class invariants and therefore overwrite it.
	 * If this method is overwritten, be sure to delegate execution to this method afterwards (via super.assertClassInvariants())
	 */
	protected void assertClassInvariants() {
		// List all assertions that relate to the class invariant
	}
	

	/**
	 * Actual calculation of distance to coordinateB. 
	 * Implemented by subclass.
	 * 
	 * @param coordinateB
	 * @return
	 */
	protected abstract double doGetDistance(Coordinate coordinateB);

	/**
	 * Actual calculation of Cartesian distance to coordinateB. 
	 * Implemented by subclass.
	 * 
	 * @param coordinateB
	 * @return
	 */
	protected abstract double doGetCartesianDistance(Coordinate coordinateB);

	/**
	 * Actual calculation of spheric distance to coordinateB. 
	 * Implemented by subclass.
	 * 
	 * @param coordinateB
	 * @return
	 */
	protected abstract double doGetSphericDistance(Coordinate coordinateB);


}
