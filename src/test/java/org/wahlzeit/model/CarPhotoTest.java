/*
 * CarPhotoTest
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
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CarPhotoTest {

	// TODO

	private CarManager carManager = null;

	private Car ford;

	private CarType fordCar = null;

	@Before
	public void setup() {

		carManager = CarManager.getInstance();

		fordCar = carManager.createType("Ford", null);

		ford = carManager.createCar(fordCar);

	}

	public void testConstructorWithoutParamsCreateCar() {
		CarPhoto photo = new CarPhoto();

		assertNotNull(photo.getCar());

	}

	@Test
	public void testConstructorSetsCorrectValuesOnlyCar() {

		// Arrange
		CarPhoto photo = new CarPhoto(ford);

		assertEquals(photo.getCar(), ford);

	}

	@Test
	public void testConstructorSetsCorrectValuesCarAndYear() {

		ford.setYear(2013);

		// Arrange
		CarPhoto photo = new CarPhoto(ford);

		assertEquals(photo.getCar(), ford);
		assertEquals(photo.getCar().getYear(), ford.getYear());

	}

}
