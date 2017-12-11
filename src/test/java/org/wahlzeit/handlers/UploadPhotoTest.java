package org.wahlzeit.handlers;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.wahlzeit.model.CarPhotoCreationException;
import org.wahlzeit.model.UserSession;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.SessionManager;
import org.wahlzeit.testEnvironmentProvider.LocalDatastoreServiceTestConfigProvider;
import org.wahlzeit.testEnvironmentProvider.PrivilegedUserSessionProvider;
import org.wahlzeit.testEnvironmentProvider.RegisteredOfyEnvironmentProvider;
import org.wahlzeit.testEnvironmentProvider.SysConfigProvider;
import org.wahlzeit.testEnvironmentProvider.UserSessionProvider;
import org.wahlzeit.testEnvironmentProvider.WebFormHandlerProvider;
import org.wahlzeit.webparts.WebPart;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Acceptance tests for the UploadPhoto feature.
 */
public class UploadPhotoTest {

	@ClassRule
	public static SysConfigProvider sysConfigProvider = new SysConfigProvider();
	@ClassRule
	public static WebFormHandlerProvider webFormHandlerProvider = new WebFormHandlerProvider();

	@Rule
	public TestRule chain = RuleChain.
			outerRule(new LocalDatastoreServiceTestConfigProvider()).
			around(new RegisteredOfyEnvironmentProvider()).
			around(new PrivilegedUserSessionProvider());

	private UserSession session;
	private WebFormHandler handler;


	@Before
	public void setUp() {
		session = (UserSession) SessionManager.getThreadLocalSession();
		handler = webFormHandlerProvider.getUploadPhotoFormHandler();
		
	}


	/**
	 *
	 */
	@Test
	public void testUploadPhotoWithCorrectParamsCreatesCarPhoto() {
		WebPart part = handler.makeWebPart(session);
		// no failure is good behavior

		Map<String, String> args = new HashMap<String, String>();
		args.put(UploadPhotoFormHandler.CAR_MAKE, "Jaguar");
		args.put(UploadPhotoFormHandler.CAR_MODEL, "F-Pace");
		args.put(UploadPhotoFormHandler.CAR_YEAR, "2013");
		handler.handlePost(session, args);

		part = handler.makeWebPart(session);

	}

	/**
	 *
	 */
	// @Test(expected = CarPhotoCreationException.class)
	public void testUploadPhotoFailsOnYearIsInvalid() {

		WebPart part = handler.makeWebPart(session);
		// no failure is good behavior

		Map<String, String> args = new HashMap<String, String>();
		args.put(UploadPhotoFormHandler.CAR_MAKE, "Jaguar");
		args.put(UploadPhotoFormHandler.CAR_MODEL, "F-Pace");
		args.put(UploadPhotoFormHandler.CAR_YEAR, "notANumber");
		
		handler.handlePost(session, args);

		part = handler.makeWebPart(session);
	
	}
	
	/**
	 *
	 */
	// @Test(expected = CarPhotoCreationException.class)
	public void testUploadPhotoFailsOnParamsAreNull() {

		WebPart part = handler.makeWebPart(session);
		// no failure is good behavior

		Map<String, String> args = new HashMap<String, String>();
		args.put(UploadPhotoFormHandler.CAR_MAKE, null);
		args.put(UploadPhotoFormHandler.CAR_MODEL, null);
		args.put(UploadPhotoFormHandler.CAR_YEAR, null);
		handler.handlePost(session, args);

		part = handler.makeWebPart(session);
	
	}
}
