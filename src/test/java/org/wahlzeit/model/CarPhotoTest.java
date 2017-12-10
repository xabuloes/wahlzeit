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

import java.util.Calendar;

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
		assertEquals(carPhoto.getYear(), 2013);
		assertEquals(carPhoto.getYearAsString(), "2013");

	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnNullMake() {
		CarPhoto carPhoto = new CarPhoto(null, "Mustang", 0);
	}
	
	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnNullMakeWithPhotoId() {
		CarPhoto carPhoto = new CarPhoto(new PhotoId(0x0), null, "Mustang", 0);
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnEmptyMakeWithPhotoId() {
		CarPhoto carPhoto = new CarPhoto(new PhotoId(0x0), "", "Mustang", 0);
	}
	
	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnNullMakeWithNullPhotoId() {
		CarPhoto carPhoto = new CarPhoto(null, null, "Mustang", 0);
	}
	
	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnEmptyMakeWithNullPhotoId() {
		CarPhoto carPhoto = new CarPhoto(null, "", "Mustang", 0);
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnNullModel() {
		CarPhoto carPhoto = new CarPhoto("Ford", null, 0);
	}

	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnEmptyModel() {
		CarPhoto carPhoto = new CarPhoto("Ford", "", 0);
	}
	
	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnFutureYear() {
		CarPhoto carPhoto = new CarPhoto("Ford", "Mustang", Calendar.getInstance().get(Calendar.YEAR) + 1);
	}
	
	@Test
	public void testConstructorDoesNotThrowExceptionOnPresentYear() {
		CarPhoto carPhoto = new CarPhoto("Ford", "Mustang", Calendar.getInstance().get(Calendar.YEAR));
		
		assertEquals(carPhoto.getYear(), Calendar.getInstance().get(Calendar.YEAR));
	}
	
	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnNegativeYear() {
		CarPhoto carPhoto = new CarPhoto("Ford", "Mustang", -1);
	}
	
	@Test(expected = CustomAssertionError.class)
	public void testConstructorThrowsExceptionOnYearBeforeInventionOfTheAutomobile() {
		CarPhoto carPhoto = new CarPhoto("Ford", "Mustang", CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED - 1);
	}
	
	@Test
	public void testConstructorDoesNotThrowExceptionOnYearInventionOfTheAutomobile() {
		CarPhoto carPhoto = new CarPhoto("Ford", "Mustang", CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED);
		
		assertEquals(carPhoto.getYear(), CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED);
	}

	// TODO

}
