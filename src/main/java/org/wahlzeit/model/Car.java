/*
 * Car
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

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public class Car {

	private static long idCounter = 0;

	/**
	 * Get the next ID. Ensures unique ID within the application context.
	 * 
	 * @return
	 */
	public static long getNextId() {
		return idCounter++;
	}

	@Id
	private long id = 0;

	private CarType type = null;

	@Ignore
	private CarManager carManager = null;

	private Integer year;

	/**
	 * Create a car with given type. Instantiated by {@link CarManager}.
	 * 
	 * @param type
	 */
	protected Car(CarType type, CarManager carManager) {

		CustomAssertionUtils.assertValueIsNotNull(type);
		CustomAssertionUtils.assertValueIsNotNull(carManager);

		this.type = type;

		this.year = null;

		this.carManager = carManager;

		this.id = Car.getNextId();

	}

	/**
	 * Get type of the car.
	 * 
	 * @return
	 */
	public CarType getType() {
		return this.type;
	}

	/**
	 * Get the associated car manager.
	 * 
	 * @return
	 */
	public CarManager getManager() {
		return this.carManager;
	}

	/**
	 * Get car make.
	 * 
	 * @return
	 */
	public String getMake() {
		// Delegate to type object
		return getType().getMake();
	}

	/**
	 * Get car model.
	 * 
	 * @return
	 */
	public String getModel() {
		// Delegate to type object
		return getType().getModel();
	}

	/**
	 * Set manufacturing year.
	 * 
	 * @param year
	 */
	public void setYear(Integer year) {
		this.year = year;

		this.assertClassInvariants();
	}

	/**
	 * Get manufacturing year.
	 * 
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Get displayed car's year as a string.
	 * 
	 * @return
	 */
	public String getYearAsString() {
		if (year != null) {
			return year.toString();
		} else {
			return "";
		}
	}

	/**
	 * Assert class invariants.
	 */
	private void assertClassInvariants() {

		CustomAssertionUtils.assertValueIsNotNull(year);
		CustomAssertionUtils.assertValueIsBetween(year.intValue(), CarType.YEAR_THE_AUTOMOBILE_WAS_INVENTED,
				Calendar.getInstance().get(Calendar.YEAR) + 1);
		
	}

}
