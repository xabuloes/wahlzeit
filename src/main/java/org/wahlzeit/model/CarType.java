/*
 * CarType
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

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public class CarType {

	@Id
	private String typeName;

	@Ignore
	private CarManager carManager = null;

	@Ignore
	private CarType baseClass = null;

	private String make = null;

	private String model = null;

	/**
	 * Minimum value for the car's year (no car before the invention of the first
	 * automobile)
	 */
	public static final int YEAR_THE_AUTOMOBILE_WAS_INVENTED = 1886;

	/**
	 * Creates a new car type object. Instantiated by {@link CarManager}.
	 * 
	 * @param baseClass
	 * @param typeName
	 * @param carManager
	 * @param make
	 * @param model
	 */
	protected CarType(CarType baseClass, String typeName, CarManager carManager, String make, String model) {

		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(typeName);
		CustomAssertionUtils.assertValueIsNotNull(carManager);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);

		this.typeName = typeName;

		this.carManager = carManager;

		this.baseClass = baseClass;

		this.make = make;
		this.model = model;
	}

	public boolean isSubtype() {
		return baseClass != null;
	}

	/**
	 * Get associated car manager
	 * 
	 * @return
	 */
	public CarManager getCarManager() {
		return carManager;
	}

	/**
	 * Create a new instance of the type.
	 * 
	 * @return
	 */
	public Car createInstance() {
		return new Car(this, carManager);
	}

	/**
	 * Get the name identifier of the type.
	 * 
	 * @return
	 */
	public String getTypeName() {
		return this.typeName;
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
	 * Assert class invariants.
	 */
	private void assertClassInvariants() {

		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		// Model may be null

	}

}
