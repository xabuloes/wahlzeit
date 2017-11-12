/*
 * CarPhotoManager
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

public class CarPhotoManager extends PhotoManager {
	
	private static CarPhotoManager instance = new CarPhotoManager();
	
	public static CarPhotoManager getInstance() {
		return instance;
	}
	
	@Override
	public CarPhoto getPhoto(String id) {
		Photo photo = super.getPhoto(id);
		return (CarPhoto)photo;
	}
	
	@Override
	public CarPhoto getPhotoFromId(PhotoId id) {
		Photo photoFromId = super.getPhotoFromId(id);
		return (CarPhoto)photoFromId;
	}

}
