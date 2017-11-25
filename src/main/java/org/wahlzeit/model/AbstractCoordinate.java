/*
 * AbstractCoordinate
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

/**
 * TODO
 */
public abstract class AbstractCoordinate implements Coordinate {

	/**
	 * We assume that subclasses need to compare double (floating point) values, so
	 * we provide the delta/precision in a central place.
	 */
	protected final static double DOUBLE_COMPARISON_DELTA = 0.00001;

	/**
	 * TODO
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	protected boolean areDoublesEqual(double a, double b) {
		return (Math.abs(a - b) < DOUBLE_COMPARISON_DELTA);
	}

	/**
	 * TODO
	 * 
	 * @param toTestForNull
	 * @return
	 */
	protected final boolean isNull(Object toTestForNull) {
		return toTestForNull == null;
	}
	
	// TODO: Provide relevant assertion methods
	
	// TODO: Do design by primitives

}
