/*
 * CustomAssertionUtils
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

package org.wahlzeit.utils;
import org.wahlzeit.model.CustomAssertionError;

public class CustomAssertionUtils {
	
	/**
	 * @param shouldBeNotNullAndNotEmpty
	 */
	public static final void assertValueIsNotNullAndNotEmpty(String shouldBeNotNullAndNotEmpty) {
	
		if(shouldBeNotNullAndNotEmpty == null) {
			throw new CustomAssertionError("String value is null");
		} else if(shouldBeNotNullAndNotEmpty.isEmpty()) {
			throw new CustomAssertionError("String has empty value");
		}
		
	}
	
	/**
	 * @param value
	 * @param beginValueIncl
	 * @param endValueExcl
	 */
	public static final void assertValueIsBetween(int value, int beginValueIncl, int endValueExcl) {
		
		if(beginValueIncl >= endValueExcl) {
			throw new IllegalArgumentException("Given value range [" + beginValueIncl + ";" + endValueExcl + "] for assertValueIsBetween() is invalid");
		}
		
		if(value < beginValueIncl || value >= endValueExcl) {
			throw new CustomAssertionError("Integer value '" + value + "' is not between range from '" + beginValueIncl + "' to '" + endValueExcl + "'");
		}
		
	}
	

	/**
	 * @param object
	 */
	public static final void assertValueIsNotNull(Object object) {
		if (object == null) {
			throw new CustomAssertionError("null value was given to calculate distance between to coordinates");
		}
	}
	
	/**
	 * Assert that shouldBeGreaterOrEqualZero has a value greater or equal to zero.
	 * 
	 * @param shouldBeGreaterOrEqualZero
	 */
	public static final void assertDoubleValueIsGreaterOrEqualThanZero(double shouldBeGreaterOrEqualZero) {
		if(shouldBeGreaterOrEqualZero < 0) {
			throw new CustomAssertionError("Double value " + shouldBeGreaterOrEqualZero + " is smaller than zero");
		}
	}
	
	/**
	 * Assert that shouldBeAFiniteNumber is finite and not NaN.
	 * 
	 * @param shouldBeAFiniteNumber
	 */
	public static final void assertDoubleIsFiniteNumber(double shouldBeAFiniteNumber) {
		if(Double.isNaN(shouldBeAFiniteNumber) || Double.isInfinite(shouldBeAFiniteNumber)) {
			throw new CustomAssertionError("Double value " + shouldBeAFiniteNumber + " is either NaN or infinite");
		}
	}

	
	

}
