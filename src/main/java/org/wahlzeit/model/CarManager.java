/*
 * CarManager
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

import java.util.HashMap;

import org.wahlzeit.services.ObjectManager;
import org.wahlzeit.utils.CustomAssertionUtils;

public class CarManager extends ObjectManager {

	public static final String UNKNOWN_CAR_TYPE_NAME = "UNKNOWN_CAR_TYPE";

	private static CarManager instance = null;

	public static CarManager getInstance() {
		if (instance == null) {
			instance = new CarManager();
		}
		return instance;
	}

	private HashMap<String, CarType> typeNameToTypeMap = null;

	private CarManager() {
		super();

		typeNameToTypeMap = new HashMap<String, CarType>();

		// Create a default type
		createType(UNKNOWN_CAR_TYPE_NAME, "UNKNOWN_MAKE", "UNKNOWN_MODEL");

	}

	/**
	 * Create car from type object.
	 * 
	 * @param type
	 * @return
	 */
	public Car createCar(CarType type) {
		return createCar(type.getTypeName());
	}

	/**
	 * Create car from type object (with year)
	 * 
	 * @param type
	 * @param year
	 * @return
	 */
	public Car createCar(CarType type, Integer year) {
		return createCar(type.getTypeName(), year);
	}

	/**
	 * Create car from type string.
	 * 
	 * @param typeName
	 * @return
	 */
	public Car createCar(String typeName) {

		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(typeName);

		CarType carType = typeNameToTypeMap.get(typeName);

		CustomAssertionUtils.assertValueIsNotNull(carType);

		return carType.createInstance();
	}

	/**
	 * Create car from type string (with year)
	 * 
	 * @param typeName
	 * @param year
	 * @return
	 */
	public Car createCar(String typeName, Integer year) {
		Car newCar = createCar(typeName);
		newCar.setYear(year);
		return newCar;
	}

	/**
	 * Create a car object with unknown properties (convenience method)
	 * 
	 * @return
	 */
	public Car createUnknownCar() {
		return createCar(UNKNOWN_CAR_TYPE_NAME);
	}

	/**
	 * Creates new type (no sub type) (typeName is generated).
	 * 
	 * @param typeName
	 *            name for the type
	 * @param model
	 */
	public CarType createType(String make, String model) {
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		// Model is optional

		return registerType(new CarType(null, this.generateTypeName(make, model), this, make, model));
	}

	/**
	 * Creates new type (no sub type). Specifies separate value for typeName.
	 * 
	 * @param typeName
	 *            name for the type
	 * @param make
	 * @param model
	 */
	public CarType createType(String typeName, String make, String model) {
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(typeName);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		// Model is optional

		return registerType(new CarType(null, typeName, this, make, model));
	}

	/**
	 * Create new sub type for given base class (typeName is generated).
	 * 
	 * @param baseClass
	 * @param typeName
	 * @param model
	 * @return
	 */
	public CarType createSubType(CarType baseClass, String make, String model) {
		CustomAssertionUtils.assertValueIsNotNull(baseClass);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		// Model is optional

		return registerType(new CarType(baseClass, this.generateTypeName(make, model), this, make, model));
	}

	/**
	 * Create new sub type for given base class (typeName is specified separately).
	 * 
	 * @param baseClass
	 * @param typeName
	 * @param model
	 * @return
	 */
	public CarType createSubType(CarType baseClass, String typeName, String make, String model) {
		CustomAssertionUtils.assertValueIsNotNull(baseClass);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(typeName);
		CustomAssertionUtils.assertValueIsNotNullAndNotEmpty(make);
		// Model is optional

		return registerType(new CarType(baseClass, typeName, this, make, model));
	}

	/**
	 * Helper method to generate a string from make and model properties.
	 * 
	 * @param make
	 * @param model
	 * @return
	 */
	private String generateTypeName(String make, String model) {
		CustomAssertionUtils.assertValueIsNotNull(make);

		if (model == null) {
			return make;
		} else {
			return make + model;
		}
	}

	/**
	 * Register given car type and return same CarType object for convenience.
	 * Overwrites existing type entries.
	 * 
	 * @param type
	 * @return
	 */
	private synchronized CarType registerType(CarType type) {

		typeNameToTypeMap.put(type.getTypeName(), type);

		CustomAssertionUtils.assertTrue(this.typeNameToTypeMap.containsKey(type.getTypeName()));

		return type;
	}

	/**
	 * Assert class invariants
	 */
	private void assertClassInvariants() {

		// TODO: Assert

	}

}
