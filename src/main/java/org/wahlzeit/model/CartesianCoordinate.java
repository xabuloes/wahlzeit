/*
 * CartesianCoordinate
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

public class CartesianCoordinate implements Coordinate {

	private double x;
	private double y;
	private double z;

	/**
	 * TODO
	 * 
	 * @param x
	 * @param y
	 * @param z
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * TODO
	 * 
	 * @return X value of coordinate.
	 */
	public double getX() {
		return x;
	}

	/**
	 * TODO
	 * 
	 * @param x
	 *            New X value of coordinate.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * TODO
	 * 
	 * @return Y value of coordinate.
	 */
	public double getY() {
		return y;
	}

	/**
	 * TODO
	 * 
	 * @param y
	 *            New Y value of coordinate.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * TODO
	 * 
	 * @return Z value of coordinate.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * TODO
	 * 
	 * @param z
	 *            New Z value of coordinate.
	 */
	public void setZ(double z) {
		this.z = z;
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

		CartesianCoordinate asCartesianCoordinate = toCompare.asCartesianCoordinate();

		return (this.getX() == asCartesianCoordinate.getX()) && (this.getY() == asCartesianCoordinate.getY())
				&& (this.getZ() == asCartesianCoordinate.getZ());
	}

	/**
	 * This method is auto-generated by Eclipse. hashCode() has to be implemented
	 * when equals is overridden
	 * 
	 * @return hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/**
	 * Only compares Cartesian to Cartesian coordinates
	 * 
	 * @return true if equal, false if not
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj.getClass() != CartesianCoordinate.class) {
			return false;
		}

		return this.isEqual((Coordinate) obj);
	}

	/**
	 * Returns spheric distance to given coordinate. If coordinate is not spherical,
	 * it is converted.
	 * 
	 * @param coordinateB
	 *            Coordinate to which distance should be calculated
	 * @return calculated distance between the two coordinates in the coordinate
	 *         system
	 *         
	 * @throws IllegalArgumentException
	 *             When input coordinate is null
	 */
	public double getDistance(Coordinate coordinateB) throws IllegalArgumentException {

		if (coordinateB == null) {
			throw new IllegalArgumentException("null value was given to calculate distance between to coordinates");
		}

		return this.getCartesianDistance(coordinateB);
	}

	/**
	 * Return Cartesian coordinate value (trivial in this case; method exists to
	 * have interchangeable coordinate representations)
	 * 
	 * @return The coordinate object's instance
	 */
	public CartesianCoordinate asCartesianCoordinate() {
		return this; // TODO: Potentially return cloned instance?
	}

	/**
	 * Return a new coordinate object, representing the Cartesian coordinate in a
	 * spheric representation
	 * 
	 * @return Converted coordinate values as a new object
	 */
	public SphericCoordinate asSphericCoordinate() {

		double latitude = Math.atan(Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY()) / this.getZ());
		double longitude = Math.atan(this.getY() / this.getX());
		double radius = Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY() + this.getZ() * this.getZ());

		return new SphericCoordinate(latitude, longitude, radius);
	}

	/**
	 * Calculates the distance between the associated coordinate object and the
	 * given coordinate object.
	 * 
	 * @param coordinateB
	 *            Coordinate to which distance should be calculated; If not
	 *            Cartesian, it is converted to a Cartesian coordinate
	 * @return Distance calculated based on the Cartesian system
	 */
	public double getCartesianDistance(Coordinate coordinateB) {

		CartesianCoordinate asCartesianCoordinate = coordinateB.asCartesianCoordinate();

		final double dx = Math.abs(this.getX() - asCartesianCoordinate.getX());
		final double dy = Math.abs(this.getY() - asCartesianCoordinate.getY());
		final double dz = Math.abs(this.getZ() - asCartesianCoordinate.getZ());

		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}

	/**
	 * Delegates calculation of spheric distance to SphericCoordinate, since this is
	 * a Cartesian coordinate.
	 * 
	 * @param coordinateB
	 *            Coordinate to which distance should be calculated
	 * @return Distance calculated based on the Spheric system
	 */
	public double getSphericDistance(Coordinate coordinateB) {

		return this.asSphericCoordinate().getSphericDistance(coordinateB);
	}

}