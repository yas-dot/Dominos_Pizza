package hooks;

import org.junit.Before;
import org.junit.After;

import baseClass.BaseClass;

public class Hooks extends BaseClass {
	   @Before
	    public void setup() {
	        // This method is left empty as the browser setup will happen in step definitions
	    }

	    @After
	    public void tearDown() {
	        closeDriver();
	    }
}
