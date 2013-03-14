package org.vnguyen.joreman;

public class ForemanClient {

	protected ForemanAPI foreman;
	
	public ForemanClient(ForemanAPI foreman) {
		this.foreman = foreman;
	}
	
	public ForemanVMBuilder newHost() {
		return new ForemanVMBuilder(foreman);
	}
	
	public ForemanVM getHost(String hostName) {
		return new ForemanVM(foreman.getHost(hostName));
	}
	
	public void deleteHost(String hostName) {
		foreman.deleteHost(hostName);
	}

}
