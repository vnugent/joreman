package org.vnguyen.joreman;

import junit.framework.Assert;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.vnguyen.joreman.hostgroup.AbstractGroup;
import org.vnguyen.joreman.model.Host;

public class HostSerializationTest {
	
	@BeforeTest
	public void setup () throws ClassNotFoundException {
		Class.forName("org.vnguyen.joreman.client.ForemanClientFactory");
	}
	
	
	@Test
	public void serializeTest0() throws Exception {
		Host host = new Host();
		host.withHostGroup(new MyHostGroup());
				
		Assert.assertEquals(2, host.hostParams.size());
	}

	private static class MyHostGroup extends AbstractGroup {

		public String groupId() {
			return "3";
		}

		@Override
		protected void prepareParams() {
			addParam("name", "jackbauer");
			addParam("code", "secret");			
		}		
	}
}
