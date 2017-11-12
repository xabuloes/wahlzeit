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

package org.wahlzeit.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.wahlzeit.model.persistence.DatastoreAdapter;
import org.wahlzeit.model.persistence.ImageStorage;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.util.Closeable;

public class CarPhotoManagerTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

	private Closeable session = null;

	private User testUser = null;

	private CarPhoto carPhoto = null;
	private CarPhoto carPhoto2 = null;

	private CarPhotoManager carPhotoManager;

	@BeforeClass
	public static void setupBeforeClass() {
		
		// Reset the Factory so that all translators work properly
		ObjectifyService.setFactory(new ObjectifyFactory());
		ObjectifyService.register(CarPhoto.class);

		// Initialize ImageStorage
		ImageStorage.setInstance(new DatastoreAdapter());

	}

	@Before
	public void setup() {

		this.helper.setUp();
		this.session = ObjectifyService.begin();

		// Create user, in case it does not exist yet
		if (UserManager.getInstance().getClientById("tommy") == null)
			this.testUser = new User("tommy", "Tom", "tommy@fau.de");

		/*
		 * Prepare two test photos
		 */
		carPhoto = new CarPhoto("Ford", "Mustang", 2013);
		carPhoto.setOwnerId(UserManager.getInstance().getClientById("tommy").getId());

		carPhoto2 = new CarPhoto("Mercedes-Benz", "GLA Class", 2014);
		carPhoto2.setOwnerId(UserManager.getInstance().getClientById("tommy").getId());

		carPhotoManager = CarPhotoManager.getInstance();

	}

	@After
	public void teardown() {

		this.helper.tearDown();
		this.session.close();

	}

	// TODO: Add tests for singleton logic

	@Test
	public void testAddPhotoPersistsCarPhoto() throws IOException {

		carPhotoManager.addPhoto(carPhoto);

		CarPhoto persistedPhoto = (CarPhoto) carPhotoManager.getPhotoFromId(carPhoto.getId());

		assertEquals(carPhoto.hashCode(), persistedPhoto.hashCode());

		assertEquals(carPhoto.getMake(), persistedPhoto.getMake());
		assertEquals(carPhoto.getModel(), persistedPhoto.getModel());

	}

	@Test
	public void testGetPhotoReturnsNullIfPhotoDoesNotExist() {

		CarPhoto photoWhichDoesNotExist = carPhotoManager.getPhotoFromId(new PhotoId(0xdeadbeef));

		assertEquals(photoWhichDoesNotExist, null);
	}

	@Test
	public void testHasPhotoReturnsFalseIfPhotoDoesNotExist() {

		assertFalse(carPhotoManager.hasPhoto(new PhotoId(0xdeadbeef)));
	}

	// TODO: Further tests

}
