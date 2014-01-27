package org.vnguyen.joreman;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vnguyen.joreman.Compute_Attributes.Volumes_Attributes;


public class ForemanVMBuilder implements VMBuilder<ForemanVM> {
	final static Logger logger = LoggerFactory.getLogger(ForemanVMBuilder.class);
	
	protected String vmName;
	protected HostGroup hostGroup;
	protected int memorySizeMB = -1;
	protected int diskSizeGB = -1;
	protected String storageId = null;
	protected int numberOfCpuCores = -1;
	protected String clusterId = null;
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
	
	public ForemanVMBuilder withMemorySize(int sizeMB){
		this.memorySizeMB = sizeMB;
		return this;
	}
	
	/**
	 * Following disc will be used for this host. Other discs from used JSON template will be ignored.
	 * @param storageId
	 * @param sizeGB
	 * @return
	 */
	public ForemanVMBuilder withDisk(String storageId, int sizeGB){
		this.diskSizeGB = sizeGB;
		this.storageId = storageId;
		return this;
	}
	
	public ForemanVMBuilder withCPUCores(int numberOfCores){
		this.numberOfCpuCores = numberOfCores;
		return this;
	}
	
	public ForemanVMBuilder withinCluster(String clusterId){
		this.clusterId = clusterId;
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
		
		if(memorySizeMB > 0){
			newHost.computeAttrs.memory = String.valueOf((long)memorySizeMB*1024*1024);
		}
		if(clusterId != null){
			newHost.computeAttrs.cluster = clusterId;
		}
		if(numberOfCpuCores > 0){
			newHost.computeAttrs.cores = String.valueOf(numberOfCpuCores);
		}
		
		if(diskSizeGB > 0){
			Date now = new Date();
			Map<String, Volumes_Attributes> volumes = new HashMap<String, Volumes_Attributes>();
			
			volumes.put("new_"+now.getTime(), new Volumes_Attributes(storageId, String.valueOf(diskSizeGB), "", ""));
			newHost.computeAttrs.volumes_attributes = volumes;
		}
		
		logger.info("Creating a new VM with following parameters - name: {}, host group id: {}, ",newHost.name, newHost.hostGroup);
		logger.debug("Sending following json to the foreman: {}",JSONHelper.toJson(newHost));
		ForemanVM newVM = new ForemanVM(foremanClient.api().newHost(newHost));
		newVM.setForemanClient(foremanClient);
		return newVM;
	}
	
	
}
