package es.uji.ei1027.naturAdventure.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AuthentificationNifTest.class, AuthentificationTest.class,
		AuthentificationUsernameTest.class, DateServiceStringTest.class, RegularExpressionsTest.class })
public class AllTests {

}
