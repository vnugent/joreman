package org.vnguyen.joreman;

import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ForemanVMBuilder implements VMBuilder<ForemanVM> {
	final static Logger logger = LoggerFactory.getLogger(ForemanVMBuilder.class);
	
	protected String vmName;
	protected HostGroup hostGroup;
	protected ForemanClient foremanClient;
	protected String jsonHostTemplate;
	protected ScheduledExecutorService executor;	
	
	public ForemanVMBuilder(ForemanClient foreman) {
		this.foremanClient = foreman;
	}
	
	public ForemanVMBuilder withName(String vmName) {
		logger.debug("Setting a name for this VM to '{}'",vmName);
		this.vmName = vmName;
		return this;
	}
	
	public ForemanVMBuilder withHostGroup(HostGroup hg) {
		logger.debug("Setting a host group id for this VM to '{}'",hg.groupId());
		this.hostGroup = hg;
		return this;
	}
	
	public ForemanVMBuilder using(String jsonHostTemplate) throws Exception {
		logger.debug("Using a following template for this VM '{}'",jsonHostTemplate);
		this.jsonHostTemplate = jsonHostTemplate;
		return this;
	}
	
	public ForemanVM build() throws Exception {
		if (vmName==null) {
			throw new RuntimeException("must set vm name");
		}
		
		Host newHost = jsonHostTemplate == null ? HostFormBuilder.newTemplate(vmName) : HostFormBuilder.newTemplate(vmName, jsonHostTemplate);
		
		if (hostGroup!=null) {
			newHost.withHostGroup(hostGroup);
		}		
		
		logger.info("Creating a new VM with following parameters - name: {}, host group id: {}, ",newHost.name, newHost.hostGroup);
		logger.debug("Sending following json to the foreman: {}",JSONHelper.toJson(newHost));
		ForemanVM newVM = new ForemanVM(foremanClient.api().newHost(newHost));
		newVM.setForemanClient(foremanClient);
		return newVM;
	}
}
