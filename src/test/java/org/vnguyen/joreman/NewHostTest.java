package org.vnguyen.joreman;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.vnguyen.joreman.client.ForemanClient;
import org.vnguyen.joreman.client.ForemanClientFactory;
import org.vnguyen.joreman.model.Host;
import org.vnguyen.joreman.vm.ForemanVM;

public class NewHostTest {
	private ForemanClient foreman = null;
	private List<ForemanVM> vms = new ArrayList<ForemanVM>();
	private static final String hostName1 = "jman-" + StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(3));
	private static final String hostName2 = "jman-" + StringUtils.lowerCase(RandomStringUtils.randomAlphabetic(3));
	
	
	@BeforeClass
	public void setUp() throws MalformedURLException{
		foreman = ForemanClientFactory.createClient();
	}
	@Test
	public void newHost1()  throws Exception {
		ForemanVM vm = foreman.newHost()
				.withName(hostName1)
				.build();

		// TODO power management is not working
		//vm.power().on();
		
		//HostPowerController.PowerStatus status = vm.power().status();
		//System.out.println(status);
		vms.add(vm);
		
	}
	
	@Test(dependsOnMethods={"newHost1"})
	public void getHost(){
	    Host host = foreman.api().getHost(Integer.toString(vms.get(0).id()));
	    Assert.assertTrue(host.name.startsWith(hostName1));
	}
	
	@Test
	public void newImageBased() throws Exception{
	    ForemanVM vm = foreman.newHost()
                .withName(hostName2)
                .usingImage("7", 2)
                .build();
	    vms.add(vm);
	}
	
	@AfterTest
	public void cleanup() throws Exception {
		for(ForemanVM vm : vms) {
			foreman.deleteHost(vm.name());
		}
	}
}
