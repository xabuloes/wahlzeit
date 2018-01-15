/*
 * CarManagerTest
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

package org.wahlzeit.managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.Car;
import org.wahlzeit.model.CarManager;
import org.wahlzeit.model.CarType;
import org.wahlzeit.model.CustomAssertionError;

public class CarManagerTest {

	private CarManager carManager = null;

	@Before
	public void setup() {

		carManager = CarManager.getInstance();

		assertNotNull(carManager);

	}

	@Test
	public void testCreateUnknownCarCreatesUnknownCar() {

		// Arrange & Act
		Car unknownCar = carManager.createUnknownCar();

		// Assert
		assertEquals(unknownCar.getType().getTypeName(), CarManager.UNKNOWN_CAR_TYPE_NAME);
	}

	@Test(expected = CustomAssertionError.class)
	public void testCreateCarTypeWithNullMakeThrowsAssertionError() {

		// Act
		CarType shouldNotBeCreated = carManager.createType(null, null);
	}

	@Test(expected = CustomAssertionError.class)
	public void testCreateCarTypeWithEmptyMakeThrowsAssertionError() {

		// Act
		CarType shouldNotBeCreated = carManager.createType("", null);
	}

	@Test(expected = CustomAssertionError.class)
	public void testCreateCarTypeWithNullTypeNameThrowsAssertionError() {

		// Act
		CarType shouldNotBeCreated = carManager.createType(null, "Ford", null);
	}

	@Test(expected = CustomAssertionError.class)
	public void testCreateCarTypeWithEmptyTypeNameThrowsAssertionError() {

		// Act
		CarType shouldNotBeCreated = carManager.createType("", null, null);
	}

	@Test(expected = CustomAssertionError.class)
	public void testCreateCarSubTypeWithNullBaseClassThrowsAssertionError() {

		// Act
		CarType shouldNotBeCreated = carManager.createSubType(null, "Ford", null);
	}

	@Test
	public void testCreateCarTypeSetsValuesCorrectly() {

		// Arrange & Act
		CarType newCarType = carManager.createType("Ford", null);

		// Assert
		assertEquals(newCarType.getMake(), "Ford");
		assertEquals(newCarType.getModel(), null);
		assertEquals(newCarType.getTypeName(), "Ford");
	}

	@Test
	public void testCreateCarTypeSetsValuesCorrectly2() {

		// Arrange & Act
		CarType newCarType = carManager.createType("ford mustang", "Ford", "Mustang");

		// Assert
		assertEquals(newCarType.getMake(), "Ford");
		assertEquals(newCarType.getModel(), "Mustang");
		assertEquals(newCarType.getTypeName(), "ford mustang");
	}

	@Test
	public void testCreateCarSubTypeSetsValuesCorrectly2() {

		// Arrange & Act
		CarType baseType = carManager.createType("Ford", null);
		CarType subType = carManager.createSubType(baseType, "Ford", "Mustang");

		// Assert
		assertFalse(baseType.isSubtype());
		assertTrue(subType.isSubtype());
		assertEquals(subType.getMake(), "Ford");
		assertEquals(subType.getModel(), "Mustang");
		assertEquals(subType.getTypeName(), "FordMustang");
	}

	@Test
	public void testCreateCarCreatesCarCorrectly() {

		// Arrange & Act
		CarType someCarType = carManager.createType("Mercedes-Benz", "C-Klasse");

		Car newCar = carManager.createCar(someCarType);

		// Assert
		assertNotNull(newCar);
		assertEquals(newCar.getType(), someCarType);
		assertEquals(newCar.getMake(), "Mercedes-Benz");
		assertEquals(newCar.getModel(), "C-Klasse");
		assertEquals(newCar.getYearAsString(), "");
	}

	@Test
	public void testCreateCarWithYearCreatesCarCorrectly() {

		// Arrange & Act
		CarType someCarType = carManager.createType("Mercedes-Benz", "CLS-Klasse");

		Car newCar = carManager.createCar(someCarType, 2014);

		// Assert
		assertNotNull(newCar);
		assertEquals(newCar.getType(), someCarType);
		assertEquals(newCar.getMake(), "Mercedes-Benz");
		assertEquals(newCar.getModel(), "CLS-Klasse");
		assertEquals(newCar.getYearAsString(), "2014");
	}

}
