/*
 * CarPhotoManagerTest
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

package org.wahlzeit.managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.model.Car;
import org.wahlzeit.model.CarManager;
import org.wahlzeit.model.CarPhoto;
import org.wahlzeit.model.CarPhotoManager;
import org.wahlzeit.model.CarType;
import org.wahlzeit.model.PhotoId;
import org.wahlzeit.model.PhotoManager;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserManager;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

public class CarPhotoManagerTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Closeable session = null;

	private User testUser = null;

	private CarPhoto carPhoto = null;
	private CarPhoto carPhoto2 = null;

	private PhotoManager photoManager = null;

	private CarManager carManager = null;

	private CarType fordCar = null;
	private CarType fordMustangCar = null;

	@BeforeClass
	public static void setupBeforeClass() {

		ObjectifyService.register(CarPhoto.class);

		// Initialize ImageStorage
		ImageStorage.setInstance(new DatastoreAdapter());
	}

	@Before
	public void setup() {

		helper.setUp();
		session = ObjectifyService.begin();

		// Create user, in case it does not exist yet
		if (UserManager.getInstance().getClientById("tommy") == null)
			testUser = new User("tommy", "Tom", "tommy@fau.de");

		carManager = CarManager.getInstance();

		fordCar = carManager.createType("Ford", null);
		fordMustangCar = carManager.createType("Ford", "Mustang");

		/*
		 * Prepare two test photos
		 */
		carPhoto = new CarPhoto(carManager.createCar(fordCar));
		carPhoto.setOwnerId(UserManager.getInstance().getClientById("tommy").getId());

		carPhoto2 = new CarPhoto(carManager.createCar(fordMustangCar));
		carPhoto2.setOwnerId(UserManager.getInstance().getClientById("tommy").getId());

		CarPhotoManager.getInstance().init();

		photoManager = CarPhotoManager.getInstance();

		carManager = CarManager.getInstance();

	}

	@After
	public void teardown() {

		this.helper.tearDown();
		this.session.close();

	}

	@Test
	public void testAddPhotoPersistsCarPhoto() throws IOException {

		photoManager.addPhoto(carPhoto);

		CarPhoto persistedPhoto = (CarPhoto) photoManager.getPhotoFromId(carPhoto.getId());

		assertEquals(carPhoto.hashCode(), persistedPhoto.hashCode());

		assertEquals(carPhoto.getCar().getMake(), persistedPhoto.getCar().getMake());
		assertEquals(carPhoto.getCar().getModel(), persistedPhoto.getCar().getModel());

	}

	@Test
	public void testHasPhotoReturnsFalseIfPhotoDoesNotExist() {

		assertFalse(photoManager.hasPhoto(PhotoId.getNextId()));
	}

}
