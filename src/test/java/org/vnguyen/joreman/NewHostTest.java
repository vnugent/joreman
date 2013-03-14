package org.vnguyen.joreman;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class NewHostTest {
	private ForemanClient foreman = ForemanClientFactory.create();
	private Set<ForemanVM> vms = new HashSet<ForemanVM>();
	private static final String hostname = "jman-" + StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(8));
	
	@Test
	public void newHost1()  throws Exception {
		
		ForemanVM vm = foreman.newHost()
				.withName(hostname)
				.build();
		
		vms.add(vm);
		Thread.sleep(8000);
	}
	
	@AfterTest
	public void cleanup() throws Exception {
		for(ForemanVM vm : vms) {
			foreman.deleteHost(vm.name());
		}
	}
}
