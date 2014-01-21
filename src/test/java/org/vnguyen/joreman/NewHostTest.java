package org.vnguyen.joreman;

import java.net.MalformedURLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewHostTest {
	private ForemanClientFactory foremanFactory = new ForemanClientFactory();
	private ForemanClient foreman = null;
	private Set<ForemanVM> vms = new HashSet<ForemanVM>();
	private static final String hostname = "jman-" + StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(3));
	
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		foreman = foremanFactory.createClient();
	}
	@Test
	public void newHost1()  throws Exception {
		ForemanVM vm = foreman.newHost()
				.withName(hostname)
				.build();

		// TODO power management is not working
		//vm.power().on();
		
		//HostPowerController.PowerStatus status = vm.power().status();
		//System.out.println(status);
		vms.add(vm);
		
	}
	
	@AfterTest
	public void cleanup() throws Exception {
		for(ForemanVM vm : vms) {
			foreman.deleteHost(vm.name());
		}
	}
}
