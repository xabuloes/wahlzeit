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

import org.wahlzeit.utils.CustomAssertionUtils;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;

@Entity
public class Car {

	private static long idCounter = 0;

	public static long getNextId() {
		return idCounter++;
	}

	@Id
	private long id = 0;

	private CarType type = null;

	@Ignore
	private CarManager carManager = null;

	public Car(CarType type) {

		CustomAssertionUtils.assertValueIsNotNull(type);

		// TODO
		this.type = type;

		this.carManager = null;

		this.id = Car.getNextId();

	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public CarType getType() {
		return this.type;
	}

	/**
	 * TODO
	 * 
	 * @return
	 */
	public CarManager getManager() {
		return this.carManager;
	}

}
