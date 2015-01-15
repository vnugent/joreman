package org.vnguyen.joreman;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.vnguyen.joreman.hostgroup.ExampleHostGroup;
import org.vnguyen.joreman.model.Host;
import org.vnguyen.joreman.util.HostFormBuilder;
import org.vnguyen.joreman.util.JSONHelper;

public class DeserializationTest {

	@BeforeTest
	public void setup () throws ClassNotFoundException {
		Class.forName("org.vnguyen.joreman.client.ForemanClientFactory");
	}
	@Test
	public void loadTemplate() throws Exception {
		Host host = HostFormBuilder.newTemplate("testHost1");
		Assert.assertNotNull(host.computeAttrs.interfaces_attributes);
	}
	
	@Test
	public void toJson() throws Exception {
		Host host = HostFormBuilder.newTemplate("foos").withHostGroup(new ExampleHostGroup());
		
		System.out.println("toJson test: "+ JSONHelper.toJson(host));
	}
	
	

}
