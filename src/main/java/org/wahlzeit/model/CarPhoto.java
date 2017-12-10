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

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CarPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public static final int YEAR_THE_AUTOMOBILE_WAS_INVENTED = 1886;
	
	/**
	 * Make of the displayed car.
	 */
	private String make;
	
	/**
	 * Model of the displayed car.
	 */
	private String model;
	
	/**
	 * Make year of the displayed car (optional)
	 */
	private int year;
	
	/**
	 * Create a car photo with default values.
	 */
	public CarPhoto() {
		super();
		
		this.setDefaultCarDataValues();
		
		this.assertClassInvariants();
	}
	
	/**
	 * Create a car photo with given photo ID and default values.
	 * 
	 * @param photoId
	 */
	public CarPhoto(PhotoId photoId) {
		super(photoId);
		
		this.setDefaultCarDataValues();
		
		this.assertClassInvariants();
	}
	
	/**
	 * Create a car photo with the given values.
	 * 
	 * @param make	Make of the displayed car (e.g. "Ford")
	 * @param model	Model of the displayed car (e.g. "Mustang")
	 * @param year	Make year of the displayed car (e.g. 2013)
	 */
	public CarPhoto(String make, String model, int year) {
		super();
		
		this.setDefaultCarDataValues();
	
		this.setMake(make);
		this.setModel(model);
		this.setYear(year);
		
		this.assertClassInvariants();
	}
	
	/**
	 * Creates a car photo with the given values and the given photo ID.
	 * 
	 * @param photoId	Id for the new photo
	 * @param make	Make of the displayed car (e.g. "Ford")
	 * @param model	Model of the displayed car (e.g. "Mustang")
	 * @param year	Make year of the displayed car (e.g. 2013)
	 */
	public CarPhoto(PhotoId photoId, String make, String model, int year) {
		super(photoId);
		
		this.setDefaultCarDataValues();
		
		this.setMake(make);
		this.setModel(model);
		this.setYear(year);
		
		this.assertClassInvariants();
	}
	
	/**
	 * TODO
	 * 
	 * @param make
	 */
	protected void setMake(String make) {
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		
		this.make = make;
		
		this.assertClassInvariants();
	}
	
	/**
	 * Return car make.
	 * 
	 * @return
	 */
	public String getMake() {
		// No assertions, since this is only a getter
		return make;
	}
	
	/**
	 * Set car model.
	 * 
	 * @param model
	 */
	protected void setModel(String model) {
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(model);
		
		this.model = model;
		
		this.assertClassInvariants();
	}

	/**
	 * Return car model.
	 * 
	 * @return
	 */
	public String getModel() {
		// No assertions, since this is only a getter
		return model;
	}
	
	/**
	 * Set car manufacturing year.
	 * 
	 * @param year
	 */
	protected void setYear(int year) {
		CustomAssertionUtils.assertValueIsBetween(year, CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED, Calendar.getInstance().get(Calendar.YEAR)+1);
		
		this.year = year;
		
		this.assertClassInvariants();
	}

	/**
	 * Return car manufacturing year.
	 * 
	 * @return
	 */
	public int getYear() {
		// No assertions, since this is only a getter
		return year;
	}
	
	/**
	 * Return car manufacturing year as a string.
	 * 
	 * @return
	 */
	public String getYearAsString() {
		// No assertions, since this is only a getter
		return Integer.toString(this.getYear());
	}
	
	/**
	 * Sets some default values for properties.
	 */
	protected void setDefaultCarDataValues() {
		
		this.make = "UNKNOWN_MAKE";
		this.model = "UNKNOWN_MODEL";
		this.year = Calendar.getInstance().get(Calendar.YEAR); // Use current year as default year
		
		this.assertClassInvariants();
	}
	
	/**
	 * Assert class invariants.
	 */
	protected void assertClassInvariants() {
		
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(this.make);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(this.model);
		CustomAssertionUtils.assertValueIsBetween(this.year, CarPhoto.YEAR_THE_AUTOMOBILE_WAS_INVENTED, Calendar.getInstance().get(Calendar.YEAR)+1);

	}
	
	
	

}
