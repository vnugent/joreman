package org.vnguyen.joreman;


public class ForemanVMBuilder implements VMBuilder<ForemanVM> {

	protected String vmName;
	protected HostGroup hostGroup;
	protected ForemanAPI foreman;
	protected String jsonHostTemplate;	
	
	public ForemanVMBuilder(ForemanAPI foreman) {
		this.foreman = foreman;
	}
	
	public ForemanVMBuilder withName(String vmName) {
		this.vmName = vmName;
		return this;
	}
	
	public ForemanVMBuilder withHostGroup(HostGroup hg) {
		this.hostGroup = hg;
		return this;
	}
	
	public ForemanVMBuilder using(String jsonHostTemplate) throws Exception {
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
		
		return new ForemanVM(foreman.newHost(newHost));
	}
}
