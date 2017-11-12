/*
 * CarPhotoFactory
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

import java.util.logging.Logger;

import org.wahlzeit.services.LogBuilder;

// TODO: 
public class CarPhotoFactory extends PhotoFactory {

	
	private static final Logger log = Logger.getLogger(CarPhotoFactory.class.getName());
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	private static CarPhotoFactory instance = null;

	
	protected CarPhotoFactory() {
		super();
	}
	
	/**
	 * Hidden singleton instance; needs to be initialized from the outside.
	 */
	public static void initialize() {
		getInstance(); // drops result due to getInstance() side-effects
	}

	/**
	 * Public singleton access method.
	 */
	public static synchronized CarPhotoFactory getInstance() {
		if (instance == null) {
			log.config(LogBuilder.createSystemMessage().addAction("setting CarPhotoFactory").toString());
			setInstance(new CarPhotoFactory());
		}

		return instance;
	}
	
	/**
	 * Method to set the singleton instance of CarPhotoFactory.
	 */
	protected static synchronized void setInstance(CarPhotoFactory photoFactory) {
		if (instance != null) {
			throw new IllegalStateException("attempt to initalize CarPhotoFactory twice");
		}

		instance = photoFactory;
	}
	
	/**
	 * @methodtype factory
	 */
	public CarPhoto createPhoto(String make, String model, Integer year) {
		return new CarPhoto(make, model, year);
	}

	/**
	 * Creates a new photo with the specified id
	 */
	public CarPhoto createPhoto(PhotoId id, String make, String model, Integer year) {
		return new CarPhoto(id, make, model, year);
	}
	
	// TODO

}
