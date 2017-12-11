package org.wahlzeit.testEnvironmentProvider;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Work;
import org.junit.rules.ExternalResource;
import org.wahlzeit.model.EnglishModelConfig;
import org.wahlzeit.model.GermanModelConfig;
import org.wahlzeit.model.Guest;
import org.wahlzeit.model.LanguageConfigs;
import org.wahlzeit.model.User;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.SessionManager;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Rule that provides an authenticated <code>UserSession</code> in the
 * <code>SessionManager</code>
 */
public class PrivilegedUserSessionProvider extends ExternalResource {

	public static final String USER_SESSION_NAME = "testContext";

	private static UserSession userSession = null;

	private static byte[] loadLocalImageFileAsByteArray(String imageFileName) {
		File imgPath = new File(imageFileName);

		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imgPath);
		} catch (IOException e) {
			return null;
		}

		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte imageData = (DataBufferByte) raster.getDataBuffer();
	
		return imageData.getData();
	}

	@Override
	protected void before() throws Throwable {

		// Instantiate only once, so user is not duplicated!
		if (userSession == null) {

			// init language configs because they are used e.g. for AbstractWebPartHandler
			LanguageConfigs.put(Language.ENGLISH, new EnglishModelConfig());
			LanguageConfigs.put(Language.GERMAN, new GermanModelConfig());

			HttpSession httpSession = mock(HttpSession.class);
			when(httpSession.getAttribute(UserSession.INITIALIZED)).thenReturn(UserSession.INITIALIZED);
			String privilegedUserName = ObjectifyService.run(new Work<String>() {
				@Override
				public String run() {
					User user = new User("privilegedUserId", "privilegedUserNickname", "user@privileged.com");
					user.setLanguage(Language.ENGLISH);

					user.setUploadedImage(ImagesServiceFactory
							.makeImage(loadLocalImageFileAsByteArray("src/test/assets/test_image.jpg")));

					return user.getId();
				}
			});
			when(httpSession.getAttribute(UserSession.CLIENT_ID)).thenReturn(privilegedUserName);

			Map<String, Object> dummyMap = new HashMap<String, Object>();
			dummyMap.put(UserSession.MESSAGE, "dummy Message");
			when(httpSession.getAttribute(UserSession.SAVED_ARGS)).thenReturn(dummyMap);

			userSession = new UserSession(USER_SESSION_NAME, "", httpSession, "en");

		}

		SessionManager.setThreadLocalSession(userSession);
	}

	@Override
	protected void after() {
		SessionManager.setThreadLocalSession(null);
	}

}
