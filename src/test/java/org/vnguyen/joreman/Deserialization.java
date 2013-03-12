package org.vnguyen.joreman;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Deserialization {

	@BeforeTest
	public void setup () throws ClassNotFoundException {
		Class.forName("org.vnguyen.joreman.ForemanClientFactory");
	}
	@Test
	public void loadTemplate() throws Exception {
		Host host = Builders.newTemplate("/templates/simple.host.json");
		Assert.assertNotNull(host.computeAttrs.interfaces_attributes);
	}
	
}
