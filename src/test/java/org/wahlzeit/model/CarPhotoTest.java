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

import org.junit.Test;

public class CarPhotoTest {

	// TODO

	@Test
	public void testConstructorSetsModelAndMakeCorrectly() {

		// Arrange & Act
		CarPhoto carPhoto = new CarPhoto("Ford", "Mustang", 2013);

		// Assert
		assertEquals(carPhoto.getMake(), "Ford");
		assertEquals(carPhoto.getModel(), "Mustang");
		assertEquals(carPhoto.getYear(), new Integer(2013));
		assertEquals(carPhoto.getYearAsString(), "2013");

	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnNullMake() {
		CarPhoto carPhoto = new CarPhoto(null, "Mustang", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnNullMakeWithPhotoId() {
		CarPhoto carPhoto = new CarPhoto(new PhotoId(0x0), null, "Mustang", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnEmptyMakeWithPhotoId() {
		CarPhoto carPhoto = new CarPhoto(new PhotoId(0x0), "", "Mustang", null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnNullMakeWithNullPhotoId() {
		CarPhoto carPhoto = new CarPhoto(null, null, "Mustang", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnEmptyMakeWithNullPhotoId() {
		CarPhoto carPhoto = new CarPhoto(null, "", "Mustang", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnNullModel() {
		CarPhoto carPhoto = new CarPhoto("Ford", null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorThrowsExceptionOnEmptyModel() {
		CarPhoto carPhoto = new CarPhoto("Ford", "", null);
	}

	// TODO

}
