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

public class SphericCoordinate implements Coordinate {

	/**
	 * Latitude (polar angle in radian measure)
	 */
	private double latitude;

	/**
	 * Longitude (azimuth angle in radian measure)
	 */
	private double longitude;

	/**
	 * Radius
	 */
	private double radius;

	/**
	 *
	 * @param latitude	in radian measure
	 * @param longitude	in radian measure
	 * @param radius	
	 *            Radius in radian measure
	 */
	public SphericCoordinate(double latitude, double longitude, double radius) {
		super();

		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;

	}

	/**
	 * 
	 * @return
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * 
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * 
	 * @return
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * 
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * Get radius as radian measure value
	 * 
	 * @return
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * Set radius to given radian measure value
	 * 
	 * @param radius
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	/**
	 * Return a new coordinate object, representing the spheric coordinate in an
	 * Cartesian representation
	 */
	public CartesianCoordinate asCartesianCoordinate() {

		double x = this.getRadius() * Math.sin(this.getLatitude()) * Math.cos(this.getLongitude());
		double y = this.getRadius() * Math.sin(this.getLatitude()) * Math.sin(this.getLongitude());
		double z = this.getRadius() * Math.cos(this.getLatitude());

		return new CartesianCoordinate(x, y, z);
	}

	/**
	 * Return spheric coordinate value (trivial in this case; method exists to have
	 * interchangeable coordinate representations)
	 * 
	 * @return	Coordinate in spheric values
	 */
	public SphericCoordinate asSphericCoordinate() {
		return this; // TODO: Return cloned object?
	}

	/**
	 * TODO
	 * 
	 * @param
	 * @return
	 * 
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public double getCartesianDistance(Coordinate coordinateB) {

		if (coordinateB == null) {
			throw new IllegalArgumentException("null value was given to calculate distance between to coordinates");
		}

		return this.asCartesianCoordinate().getCartesianDistance(coordinateB);
	}

	/**
	 * Delegate distance calculation to Cartesian distance calculation
	 * 
	 * @param	Coordinate to calculate spheric distance to
	 * @return	(Spheric) distance between this coordinate and coordinateB
	 * 
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public double getSphericDistance(Coordinate coordinateB) {

		if (coordinateB == null) {
			throw new IllegalArgumentException("null value was given to calculate distance between to coordinates");
		}

		return this.getCartesianDistance(coordinateB);
	}

	/**
	 * Returns spheric distance to given coordinate. If coordinate is not spherical,
	 * it is converted.
	 * 
	 * @param	Coordinate to calculate distance to (in this case spheric distance) 
	 * @return	(Spheric) distance between this coordinate an coordinate 
	 * 
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public double getDistance(Coordinate coordinateB) throws IllegalArgumentException {

		if (coordinateB == null) {
			throw new IllegalArgumentException("null value was given to calculate distance between to coordinates");
		}

		return this.getSphericDistance(coordinateB);
	}

	/**
	 * Compares the x, y and z values of the associated coordinate object with the
	 * given coordinate object.
	 * 
	 * @param toCompare
	 *            Coordinate object that should be compared to associated Coordinate
	 *            object
	 * @return true if x,y and z are equal in both coordinates, false if at least
	 *         one is not equal.
	 */
	public boolean isEqual(Coordinate toCompare) {
		if (toCompare == null) {
			return false;
		}

		SphericCoordinate asSphericCoordinate = toCompare.asSphericCoordinate();

		return (this.getRadius() == asSphericCoordinate.getRadius())
				&& (this.getLongitude() == asSphericCoordinate.getLongitude())
				&& (this.getLatitude() == asSphericCoordinate.getLatitude());
	}

	/**
	 * TODO
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
	 * TODO
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != SphericCoordinate.class) {
			return false;
		}

		return this.isEqual((Coordinate) obj);
	}

}
