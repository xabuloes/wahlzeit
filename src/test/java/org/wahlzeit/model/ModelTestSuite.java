/*
 * ModelTestSuite
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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.wahlzeit.managers.CarPhotoManagerTest;
import org.wahlzeit.model.persistence.PersistenceModelTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	AccessRightsTest.class,
	CartesianCoordinateTest.class,
	SphericCoordinateTest.class,
	FlagReasonTest.class,
	GenderTest.class,
	GuestTest.class,
	CarTest.class,
	CarTypeTest.class,
	CarPhotoTest.class,
	CarPhotoManagerTest.class,
	CarPhotoFactoryTest.class,
	LocationTest.class,
	PhotoFilterTest.class,
	TagsTest.class,
	UserStatusTest.class,
	ValueTest.class,
	PersistenceModelTestSuite.class,
})
public class ModelTestSuite {
	// Remains empty, only used to hold annotations (see B02 p23)
}
