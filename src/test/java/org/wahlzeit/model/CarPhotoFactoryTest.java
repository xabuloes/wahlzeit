/*
 * CarPhotoFactoryTest
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
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class CarPhotoFactoryTest {

	private static final int TEST_PHOTO_ID = 0xdeadbeef;

	private CarManager carManager = null;

	private Car ford = null;

	@Before
	public void setup() {

		carManager = CarManager.getInstance();

		ford = carManager.createCar(carManager.createType("Ford", null));

	}

	@Test
	public void testInitializeMultipleTimesDoesNotLeadToMultipleInstances() {

		// Arrange & Act
		CarPhotoFactory.initialize();

		CarPhotoFactory carPhotoFactory = CarPhotoFactory.getInstance();

		CarPhotoFactory.initialize();

		// Assert
		assertEquals(carPhotoFactory.hashCode(), CarPhotoFactory.getInstance().hashCode());

	}

	@Test(expected = IllegalStateException.class)
	public void testSetInstanceMutltipleTimesThrowsException() {

		// Ensure that singleton instance is in place
		CarPhotoFactory.initialize();

		// Try to overwrite singleton instance
		CarPhotoFactory.setInstance(new CarPhotoFactory());

	}

	@Test
	public void testGetInstanceReturnsACarPhotoInstance() {

		// Arrange & Act
		CarPhotoFactory factory = CarPhotoFactory.getInstance();

		// Assert
		assertTrue(factory instanceof CarPhotoFactory);

	}

	@Test
	public void testFactoryMethodCreatesACarPhoto() throws CarPhotoCreationException {

		CarPhotoFactory carPhotoFactory = CarPhotoFactory.getInstance();

		CarPhoto newPhoto = carPhotoFactory.createPhoto(ford);

		assertTrue(newPhoto instanceof CarPhoto);
		assertEquals(newPhoto.getCar().getMake(), ford.getMake());
		assertEquals(newPhoto.getCar().getModel(), ford.getModel());
		assertEquals(newPhoto.getCar().getYearAsString(), ford.getYearAsString());
		assertTrue(newPhoto.getId() != null);

	}

	@Test
	public void testFactoryMethodCreatesACarPhotoWithGivenId() throws CarPhotoCreationException {

		CarPhotoFactory carPhotoFactory = CarPhotoFactory.getInstance();

		CarPhoto newPhoto = carPhotoFactory.createPhoto(new PhotoId(TEST_PHOTO_ID), ford);

		assertTrue(newPhoto instanceof CarPhoto);
		assertEquals(newPhoto.getCar().getMake(), ford.getMake());
		assertEquals(newPhoto.getCar().getModel(), ford.getModel());
		assertEquals(newPhoto.getCar().getYearAsString(), ford.getYearAsString());
		assertEquals(newPhoto.getId().asInt(), TEST_PHOTO_ID);

	}

	@Test(expected = CarPhotoCreationException.class)
	public void testFactoryMethodThrowsExceptionOnNullCar() throws CarPhotoCreationException {

		CarPhotoFactory.getInstance().createPhoto(new PhotoId(TEST_PHOTO_ID), null);

	}

}
