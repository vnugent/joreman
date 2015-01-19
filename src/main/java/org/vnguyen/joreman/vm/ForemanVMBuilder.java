package org.vnguyen.joreman.vm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vnguyen.joreman.client.ForemanClient;
import org.vnguyen.joreman.hostgroup.HostGroup;
import org.vnguyen.joreman.model.Compute_Attributes.Volumes_Attributes;
import org.vnguyen.joreman.model.Host;
import org.vnguyen.joreman.model.HostWrapper;
import org.vnguyen.joreman.model.Image;
import org.vnguyen.joreman.util.HostFormBuilder;
import org.vnguyen.joreman.util.JSONHelper;


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
	protected String ownedBy = null;
	protected int imageId = -1;
	protected int computeResId = -1;
	
	public ForemanVMBuilder(ForemanClient foreman) {
		this.foremanClient = foreman;
	}
	
	public ForemanVMBuilder withName(String vmName) {
		logger.debug("Setting a name for this VM to '{}'",vmName);
		this.vmName = vmName;
		return this;
	}
	public ForemanVMBuilder usingImage(int imageId, int computeResId) {
        logger.debug("Using following image id for this VM to '{}'",imageId);
        this.imageId = imageId;
        this.computeResId = computeResId;
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
	public ForemanVMBuilder ownedBy(String owner){
	    this.ownedBy = owner;
	    return this;
	}
	public ForemanVM build() throws Exception {
		if (vmName==null) {
			throw new RuntimeException("must set vm name");
		}
		
		Host newHost = jsonHostTemplate == null ? HostFormBuilder.newTemplate(vmName) : HostFormBuilder.newTemplate(vmName, "/templates/"+ jsonHostTemplate);
		
		
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
		
		setImage(newHost, this.imageId, this.computeResId);
		
		if(diskSizeGB > 0){
			Date now = new Date();
			Map<String, Volumes_Attributes> volumes = new HashMap<String, Volumes_Attributes>();
			
			volumes.put("new_"+now.getTime(), new Volumes_Attributes(storageId, String.valueOf(diskSizeGB), "", ""));
			newHost.computeAttrs.volumes_attributes = volumes;
		}
		if(ownedBy != null){
		    newHost.isOwnedBy = ownedBy;
		}
		
		logger.info("Creating a new VM with following parameters - name: {}, host group id: {}, ",newHost.name, newHost.hostGroup);
		
		// foreman expects wrapped host
		HostWrapper hostWrapped = new HostWrapper();
		hostWrapped.setHost(newHost);
		logger.debug("Sending following json to the foreman: {}",JSONHelper.toJson(hostWrapped));
		
		ForemanVM newVM = new ForemanVM(foremanClient.api().newHost(hostWrapped));
		newVM.setForemanClient(foremanClient);
		return newVM;
	}
	private void setImage(Host host, int imageId, int computeResId){
	    if (imageId > 0 && computeResId > 0){
	        Image img = foremanClient.api().getImage(Integer.toString(computeResId),Integer.toString(imageId));
	        host.imageId = Integer.toString(imageId);
	        host.provisionMethod = "image";
	        host.os = img.operatingSystemId;
	        host.arch = img.archId;
	        host.computeAttrs.imageId = img.uuid;
	        
	    }
	}
}
