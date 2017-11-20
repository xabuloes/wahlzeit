/*
 * Coordinate
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
 * A Coordinate represents a coordinate in a 3D Cartesian coordinate system.
 * 
 * @author xabuloes
 *
 */
public interface Coordinate {

	/**
	 * Return instance of associated coordinate in Cartesian representation
	 * 
	 * @return
	 */
	CartesianCoordinate asCartesianCoordinate();

	/**
	 * Return instance of associated coordinate in spheric representation
	 * 
	 * @return
	 */
	SphericCoordinate asSphericCoordinate();

	/**
	 * Get Cartesian distance between two coordinates 
	 * 
	 * @param coordinateB
	 * @return
	 */
	double getCartesianDistance(Coordinate coordinateB);

	/**
	 * Get spherical distance between two coordinates
	 * 
	 * @param coordinateB
	 * @return
	 */
	double getSphericDistance(Coordinate coordinateB);

	/**
	 * Get distance (default representation, depending on implementation) between two coordinates
	 * 
	 * @param coordinateB
	 * @return
	 */
	double getDistance(Coordinate coordinateB);

	/**
	 * Compare two coordinate values
	 * 
	 * @param coordinate
	 * @return
	 */
	boolean isEqual(Coordinate coordinate);
	
}
