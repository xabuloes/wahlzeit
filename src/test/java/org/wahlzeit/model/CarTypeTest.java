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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CarTypeTest {

	private CarType carType = null;

	private CarType anotherCarType = null;

	private CarType carTypeWithBaseType = null;

	@Before
	public void setup() {

		this.carType = new CarType(null, "carType1");
		this.anotherCarType = new CarType(null, "carType2");
		this.carTypeWithBaseType = new CarType(anotherCarType, "carType3");

	}

	@Test
	public void testIsSubtypeWorksForNonSubtype() {
		assertFalse(carType.isSubtype());
	}

	@Test
	public void testIsSubtypeWorksForSubtype() {
		assertTrue(carTypeWithBaseType.isSubtype());
	}

}
