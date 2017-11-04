/*
 * LocationTest
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

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author xabuloes
 *
 */
public class LocationTest {
	
	/**
	 * Coordinate instance is used as the default input parameter for Location object construction in most of the test cases
	 */
	private Coordinate coordinateInstance;
	
	@Before
	public void setup() {
		
		this.coordinateInstance = new Coordinate(1.23, 2.34, 3.45);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void locationConstructionFailsOnNullCoordinate() {
		
		// Arrange & Act
		new Location(null);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setCoordinateFailsOnNullCoordinate() {
		
		// Arrange
		Location location = new Location(this.coordinateInstance);
		
		// Act
		location.setCoordinate(null);
	}
	
	@Test
	public void locationConstructorSetsCoordinateInstance() {
		// Arrange & Act
		Location location = new Location(this.coordinateInstance);
		
		// Assert
		assertTrue(location.getCoordinate() == this.coordinateInstance);
	}
	
	@Test
	public void setCoordinateSetsCoordinateInstance() {
		Location location = new Location(this.coordinateInstance);
		
		// Arrange
		Coordinate newCoordinateInstance = new Coordinate(3.45, 1.23, 2.34);
		
		// Act
		location.setCoordinate(newCoordinateInstance);
		
		// Assert
		assertTrue(location.getCoordinate() == newCoordinateInstance);
		
	}
	
	
	

}
