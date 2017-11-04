/*
 * Location
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
 * A Location represents a particular location, defined by a coordinate that
 * identifies the position of the location in a coordinate system.
 * 
 * @author xabuloes
 *
 */
public class Location {

	protected Coordinate coordinate;

	public Location(Coordinate coordinate) {
		this.setCoordinate(coordinate);
	}

	/**
	 * 
	 * @return Current coordinate instance.
	 */
	public Coordinate getCoordinate() {
		return coordinate;
	}

	/**
	 * Set new coordinate attribute for location.
	 * 
	 * @param coordinate
	 *            Coordinate instance to set.
	 * @throws IllegalArgumentException
	 *             If coordinate is null.
	 */
	public void setCoordinate(Coordinate coordinate) throws IllegalArgumentException {

		if (coordinate == null) {
			throw new IllegalArgumentException("Location has to hold a coordinate instance, but null was given.");
		}

		this.coordinate = coordinate;
	}

}
