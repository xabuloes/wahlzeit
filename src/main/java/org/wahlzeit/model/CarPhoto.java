/*
 * CarPhoto
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

import java.util.Calendar;

import org.wahlzeit.utils.CustomAssertionUtils;

import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CarPhoto extends Photo {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Minimum value for the car's year (no car before the invention of the first
	 * automobile)
	 */
	protected static final int YEAR_THE_AUTOMOBILE_WAS_INVENTED = 1886;

	/**
	 * Make of the displayed car.
	 */
	private String make = null;

	/**
	 * Model of the displayed car.
	 */
	private String model = null;

	/**
	 * Make year of the displayed car (optional)
	 */
	private Integer year = null;

	/**
	 * Car object of the displayed car.
	 */
	private Car car = null;

	/**
	 * Default constructor.
	 */
	public CarPhoto() {
		super();
	}

	/**
	 * Constructor with specified ID.
	 * 
	 * @param photoId
	 */
	public CarPhoto(PhotoId photoId) {
		super(photoId);

		this.make = "UNKNOWN_MAKE";
		this.model = "UNKNOWN_MODEL";
		this.year = Calendar.getInstance().get(Calendar.YEAR);

	}

	/**
	 * Constructor with specified data.
	 * 
	 * @param make
	 *            Make of the displayed car (e.g. "Ford")
	 * @param model
	 *            Model of the displayed car (e.g. "Mustang")
	 * @param year
	 *            Make year of the displayed car (e.g. 2013)
	 */
	public CarPhoto(String make, String model, Integer year) {
		super();

		this.make = make;
		this.model = model;
		this.year = year;

		this.assertClassInvariants();
	}

	/**
	 * Constructor with specified data and ID.
	 * 
	 * @param photoId
	 *            Id for the new photo
	 * @param make
	 *            Make of the displayed car (e.g. "Ford")
	 * @param model
	 *            Model of the displayed car (e.g. "Mustang")
	 * @param year
	 *            Make year of the displayed car (e.g. 2013)
	 */
	public CarPhoto(PhotoId photoId, String make, String model, Integer year) {
		super(photoId);

		this.make = make;
		this.model = model;
		this.year = year;

		this.assertClassInvariants();
	}

	/**
	 * Set the displayed car's make.
	 * 
	 * @param make
	 */
	public void setMake(String make) {
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);

		this.make = make;

		this.assertClassInvariants();
	}

	/**
	 * Set the displayed car's model.
	 * 
	 * @param model
	 */
	public void setModel(String model) {
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(model);

		this.model = model;

		this.assertClassInvariants();
	}

	/**
	 * Set the displayed car's year.
	 * 
	 * @param year
	 */
	public void setYear(int year) {
		CustomAssertionUtils.assertValueIsBetween(year, CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED,
				Calendar.getInstance().get(Calendar.YEAR) + 1);

		this.year = year;

		this.assertClassInvariants();
	}

	/**
	 * Get displayed car's make.
	 * 
	 * @return
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Get displayed car's model.
	 * 
	 * @return
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Get displayed car's year.
	 * 
	 * @return
	 */
	public int getYear() {
		return year.intValue();
	}

	/**
	 * Get displayed car's year as a string.
	 * 
	 * @return
	 */
	public String getYearAsString() {
		return year.toString();
	}

	/**
	 * Assert all class invariants.
	 */
	private void assertClassInvariants() {

		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(model);
		CustomAssertionUtils.assertValueIsNotNull(year);
		CustomAssertionUtils.assertValueIsBetween(year.intValue(), CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED,
				Calendar.getInstance().get(Calendar.YEAR) + 1);
	}

}
