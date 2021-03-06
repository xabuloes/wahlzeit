/*
 * CarTest
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

import org.junit.Before;
import org.junit.Test;

public class CarTest {

	private CarManager carManager;

	private CarType someCarType;

	@Before
	public void setup() {

		carManager = CarManager.getInstance();

		someCarType = carManager.createType("Ford", "Mustang");

	}

	@Test
	public void testConstructorSetsCorrectValues() {

		// Arrange
		Car car = new Car(someCarType, carManager);

		// Act & Assert
		assertEquals(car.getType(), someCarType);

	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorOnNullCarType() {

		// Act
		Car car = new Car(null, carManager);

	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorOnNullCarManager() {

		// Act
		Car car = new Car(someCarType, null);

	}

}
