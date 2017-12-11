package org.wahlzeit.testEnvironmentProvider;

import org.junit.rules.ExternalResource;
import org.wahlzeit.handlers.PartUtil;
import org.wahlzeit.handlers.TellFriendFormHandler;
import org.wahlzeit.handlers.UploadPhotoFormHandler;
import org.wahlzeit.handlers.WebFormHandler;
import org.wahlzeit.handlers.WebPartHandlerManager;

/**
 * A test setup class.
 * 
 * @review
 */
public class WebFormHandlerProvider extends ExternalResource {

	private WebFormHandler webFormHandler;

	private WebFormHandler uploadPhotoFormHandler;
	
	@Override
	protected void before() throws Throwable {
		WebPartHandlerManager.getInstance()
				.addWebPartHandler(PartUtil.TELL_FRIEND_FORM_NAME, new TellFriendFormHandler());
		webFormHandler = WebPartHandlerManager.getWebFormHandler(PartUtil.TELL_FRIEND_FORM_NAME);
		
		WebPartHandlerManager.getInstance()
				.addWebPartHandler(PartUtil.UPLOAD_PHOTO_FORM_NAME, new UploadPhotoFormHandler());
		uploadPhotoFormHandler = WebPartHandlerManager.getWebFormHandler(PartUtil.UPLOAD_PHOTO_FORM_NAME);
	}

	public WebFormHandler getWebFormHandler() {
		return webFormHandler;
	}
	
	public WebFormHandler getUploadPhotoFormHandler() {
		return uploadPhotoFormHandler;
	}
}
