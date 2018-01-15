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

import org.wahlzeit.utils.CustomAssertionUtils;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
public class CarPhoto extends Photo {

	/**
	 * TODO
	 */
	private static final long serialVersionUID = 1L;

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
	 * Constructor with specified ID (unknown car object is created).
	 * 
	 * @param photoId
	 */
	public CarPhoto(PhotoId photoId) {
		super(photoId);

		this.car = CarManager.getInstance().createUnknownCar();

	}

	/**
	 * Constructor with car.
	 * 
	 * @param car
	 *            Car that is shown in the photo.
	 * 
	 */
	public CarPhoto(Car car) {
		super();

		CustomAssertionUtils.assertValueIsNotNull(car);

		this.car = car;

		this.assertClassInvariants();
	}

	/**
	 * Constructor with car and ID.
	 * 
	 * @param photoId
	 *            Id for the new photo
	 * @param car
	 *            Car that is shown in the photo.
	 */
	public CarPhoto(PhotoId photoId, Car car) {
		super(photoId);

		CustomAssertionUtils.assertValueIsNotNull(car);

		this.car = car;

		this.assertClassInvariants();
	}

	/**
	 * Return the displayed car's object.
	 * 
	 * @return
	 */
	public Car getCar() {
		return this.car;
	}

	/**
	 * Set the displayed car's object.
	 * 
	 * @param car
	 */
	public void setCar(Car car) {
		CustomAssertionUtils.assertValueIsNotNull(car);

		this.car = car;

		this.assertClassInvariants();
	}

	/**
	 * Assert all class invariants.
	 */
	private void assertClassInvariants() {
		CustomAssertionUtils.assertValueIsNotNull(car);
	}

}
