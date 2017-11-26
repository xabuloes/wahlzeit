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
 * TODO
 */
public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * We assume that subclasses need to compare double (floating point) values, so
	 * we provide the delta/precision in a central place.
	 */
	protected final static double DEFAULT_DOUBLE_COMPARISON_DELTA = 0.00001;

	public AbstractCoordinate() {
		// TODO
	}

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

		return this.doGetDistance(coordinateB);
	}

	/**
	 * TODO
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

		return this.doGetCartesianDistance(coordinateB);
	}

	/**
	 * TODO
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

		return this.doGetSphericDistance(coordinateB);
	}

	/**
	 * TODO
	 * 
	 * @param coordinateToCompare
	 * 
	 * @return
	 */
	public final boolean isEqual(Coordinate coordinateToCompare) {

		if (coordinateToCompare == null) {
			return false;
		} else if (!this.getClass().isInstance(coordinateToCompare)) {
			return false;
		}

		return this.doIsEqual(coordinateToCompare);
	}

	/**
	 * 
	 * 
	 * @return true if equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {

		if (!this.getClass().isInstance(obj)) {
			return false;
		}

		// Delegate execution (+ further assertions) to isEqual()
		return this.isEqual((Coordinate) obj);
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
	 * TODO
	 * 
	 * @param coordinateB
	 * @return
	 */
	protected abstract double doGetDistance(Coordinate coordinateB);

	/**
	 * TODO
	 * 
	 * @param coordinateB
	 * @return
	 */
	protected abstract double doGetCartesianDistance(Coordinate coordinateB);

	/**
	 * TODO
	 * 
	 * @param coordinateB
	 * @return
	 */
	protected abstract double doGetSphericDistance(Coordinate coordinateB);

	/**
	 * TODO
	 * 
	 * @param
	 * @return
	 */
	protected abstract boolean doIsEqual(Coordinate coordinateToCompare);

	/**
	 * Throw an IllegalArgumentException when the given @param object is null.
	 * 
	 * @param object
	 */
	protected final void assertValueIsNotNull(Object object) {
		// TODO: Make assert statement out of that
		if (object == null) {
			throw new IllegalArgumentException("null value was given to calculate distance between to coordinates");
		}
	}

}
