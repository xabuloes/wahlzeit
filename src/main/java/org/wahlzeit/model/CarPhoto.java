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

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CarPhoto extends Photo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	 * TODO
	 * 
	 * @param make	Make of the displayed car (e.g. "Ford")
	 * @param model	Model of the displayed car (e.g. "Mustang")
	 * @param year	Make year of the displayed car (e.g. 2013)
	 */
	public CarPhoto(String make, String model, Integer year) {
		super();
		
		if(make == null || make.isEmpty() ) {
			throw new IllegalArgumentException("No make has been given for car photo");
		}
		
		if(model == null || model.isEmpty() ) {
			throw new IllegalArgumentException("No model has been given for car photo");
		}
		
		this.make = make;
		this.model = model;
		
		// TODO: Year is optional
		this.year = year;
		
	}
	
	/**
	 * TODO
	 * 
	 * @param photoId	Id for the new photo
	 * @param make	Make of the displayed car (e.g. "Ford")
	 * @param model	Model of the displayed car (e.g. "Mustang")
	 * @param year	Make year of the displayed car (e.g. 2013)
	 */
	public CarPhoto(PhotoId photoId, String make, String model, Integer year) {
		super(photoId);
		
		if(make == null || make.isEmpty() ) {
			throw new IllegalArgumentException("No make has been given for car photo");
		}
		
		if(model == null || model.isEmpty() ) {
			throw new IllegalArgumentException("No model has been given for car photo");
		}
		
		this.make = make;
		this.model = model;
		
		// TODO: Year is optional
		this.year = year;
		
	}
	

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public Integer getYear() {
		return year;
	}
	
	public String getYearAsString() {
		return year.toString();
	}
	

}
