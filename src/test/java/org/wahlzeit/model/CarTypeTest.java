/*
 * CarTypeTest
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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CarTypeTest {

	private CarManager carManager = null;

	private CarType carType = null;

	private CarType anotherCarType = null;

	private CarType carTypeWithBaseType = null;

	@Before
	public void setup() {

		carManager = CarManager.getInstance();

		this.carType = new CarType(null, "carType1", carManager, "Ford", null);
		this.anotherCarType = new CarType(null, "carType2", carManager, "Mercedes-Benz", null);
		this.carTypeWithBaseType = new CarType(anotherCarType, "carType3", carManager, "Fiat", null);

	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorWhenTypeNameIsNull() {
		CarType shouldNotBeCreated = new CarType(null, null, carManager, "Ford", "Focus");
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorWhenTypeNameIsEmpty() {
		CarType shouldNotBeCreated = new CarType(null, "", carManager, "Ford", "Fiesta");
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorWhenCarManagerIsNull() {
		CarType shouldNotBeCreated = new CarType(null, "someTypeName", null, "Ford", "Fiesta");
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorWhenCarMakeIsNull() {
		CarType shouldNotBeCreated = new CarType(null, "someTypeName", carManager, null, "Fiesta");
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsAssertionErrorWhenCarMakeIsEmpty() {
		CarType shouldNotBeCreated = new CarType(null, "someTypeName", carManager, "", "Fiesta");
	}

	@Test
	public void testIsSubtypeWorksForNonSubtype() {

		// Assert
		assertFalse(carType.isSubtype());
	}

	@Test
	public void testIsSubtypeWorksForSubtype() {

		// Assert
		assertTrue(carTypeWithBaseType.isSubtype());
	}

	@Test
	public void testConstructorSetsCorrectValues() {

		// Assert
		assertEquals(this.carType.getMake(), "Ford");
		assertEquals(this.carType.getModel(), null);
	}

}
