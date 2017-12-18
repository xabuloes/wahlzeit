/*
 * SphericCoordinate
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

import java.util.HashMap;

import org.wahlzeit.utils.CustomAssertionUtils;

/**
 * A coordinate representing a position defined by spheric values (radius,
 * azimuth and polar angle).
 */
public class SphericCoordinate extends AbstractCoordinate {

	/**
	 * 
	 */
	private static final HashMap<String, SphericCoordinate> sharedSphericCoordinates = new HashMap<String, SphericCoordinate>();

	/**
	 * Requests a shared CartesianCoordinate instance.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	public static final SphericCoordinate get(double latitude, double longitude, double radius) {

		final String coordinateAsString = asSphericCoordinateString(latitude, longitude, radius);

		if (sharedSphericCoordinates.get(coordinateAsString) == null) {
			synchronized (SphericCoordinate.class) {
				if (sharedSphericCoordinates.get(coordinateAsString) == null) {

					sharedSphericCoordinates.put(coordinateAsString,
							new SphericCoordinate(latitude, longitude, radius));
				}

			}
		}

		return sharedSphericCoordinates.get(coordinateAsString);

	}

	/**
	 * Creates a string of Cartesian coordinate values for distinct identification
	 * of a coordinate value.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param radius
	 * @return
	 */
	private static final String asSphericCoordinateString(double latitude, double longitude, double radius) {
		return latitude + ";" + longitude + ";" + radius;
	}

	/**
	 * Assert that valueShouldBeInRadianRange is within valid radian range [0;2*PI[.
	 * Method is protected to allow usage in further sub classes.
	 * 
	 * @param valueShouldBeInRadianRange
	 * @return
	 */
	protected static final void assertValueIsInRadianRange(double valueShouldBeInRadianRange) {
		if (valueShouldBeInRadianRange < 0 || valueShouldBeInRadianRange >= Math.PI * 2) {
			// Throw coordinate-specifc assertion error
			throw new CustomAssertionError(
					"Value " + valueShouldBeInRadianRange + " is not a valid radian value ( range: [0;PI*2[ ).");
		}
	}

	/**
	 * Latitude (polar angle in radian measure)
	 */
	private final double latitude;

	/**
	 * Longitude (azimuth angle in radian measure)
	 */
	private final double longitude;

	/**
	 * Radius
	 */
	private final double radius;

	/**
	 *
	 * @param latitude
	 *            in radian measure
	 * @param longitude
	 *            in radian measure
	 * @param radius
	 *            Radius
	 */
	private SphericCoordinate(double latitude, double longitude, double radius) {
		super();

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;

	}

	/**
	 * Get latitude value.
	 * 
	 * @return
	 */
	public double getLatitude() {
		// No assertions, since this is only a getter
		return latitude;
	}

	/**
	 * Return the value object representing the coordinate with the changed latitude
	 * value.
	 * 
	 * @param latitude
	 *            Radian value in the range [0;PI*2[
	 */
	public SphericCoordinate setLatitude(double latitude) {

		CustomAssertionUtils.assertDoubleIsFiniteNumber(latitude);
		assertValueIsInRadianRange(latitude);

		SphericCoordinate newCoordinate = SphericCoordinate.get(latitude, this.getLongitude(), this.getRadius());

		// No class invariant assertion, since we are in an immutable shared value
		// object.

		return newCoordinate;
	}

	/**
	 * Get longitude value.
	 * 
	 * @return
	 */
	public double getLongitude() {
		// No assertions, since this is only a getter
		return longitude;
	}

	/**
	 * Return the value object representing the coordinate with the changed
	 * longitude value.
	 * 
	 * @param longitude
	 *            Radian value in the range [0;PI*2[
	 */
	public SphericCoordinate setLongitude(double longitude) {

		CustomAssertionUtils.assertDoubleIsFiniteNumber(longitude);
		assertValueIsInRadianRange(longitude);

		SphericCoordinate newCoordinate = SphericCoordinate.get(this.getLatitude(), longitude, this.getRadius());

		// No class invariant assertion, since we are in an immutable shared value
		// object.

		return newCoordinate;
	}

	/**
	 * Get radius value.
	 * 
	 * @return
	 */
	public double getRadius() {
		// No assertions, since this is only a getter
		return radius;
	}

	/**
	 * Return the value object representing the coordinate with the changed radius
	 * value.
	 * 
	 * @param radius
	 */
	public SphericCoordinate setRadius(double radius) {

		CustomAssertionUtils.assertDoubleIsFiniteNumber(radius);
		CustomAssertionUtils.assertDoubleValueIsGreaterOrEqualThanZero(radius);

		SphericCoordinate newCoordinate = SphericCoordinate.get(this.getLatitude(), this.getLongitude(), radius);

		this.assertClassInvariants();

		return newCoordinate;
	}

	/**
	 * {@inheritDoc}
	 */
	public CartesianCoordinate asCartesianCoordinate() {

		final double x = this.getRadius() * Math.sin(this.getLatitude()) * Math.cos(this.getLongitude());
		final double y = this.getRadius() * Math.sin(this.getLatitude()) * Math.sin(this.getLongitude());
		final double z = this.getRadius() * Math.cos(this.getLatitude());

		this.assertClassInvariants();

		return CartesianCoordinate.get(x, y, z);
	}

	/**
	 * {@inheritDoc}
	 */
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	/**
	 * This method is auto-generated by Eclipse. hashCode() has to be implemented
	 * when equals is overridden.
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected double doGetDistance(Coordinate coordinateB) throws IllegalArgumentException {
		return this.doGetSphericDistance(coordinateB);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected double doGetCartesianDistance(Coordinate coordinateB) {
		return this.asCartesianCoordinate().getCartesianDistance(coordinateB);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected double doGetSphericDistance(Coordinate coordinateB) {
		// TODO: This calculation is wrong, fix it!
		return this.asCartesianCoordinate().getCartesianDistance(coordinateB);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void assertClassInvariants() {

		CustomAssertionUtils.assertDoubleIsFiniteNumber(this.longitude);
		assertValueIsInRadianRange(this.longitude);

		CustomAssertionUtils.assertDoubleIsFiniteNumber(this.latitude);
		assertValueIsInRadianRange(this.latitude);

		CustomAssertionUtils.assertDoubleIsFiniteNumber(this.radius);
		CustomAssertionUtils.assertDoubleValueIsGreaterOrEqualThanZero(this.radius);

		super.assertClassInvariants();
	}

}
