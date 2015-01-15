package org.vnguyen.joreman.client;

import org.vnguyen.joreman.ForemanAPI;
import org.vnguyen.joreman.vm.ForemanVM;
import org.vnguyen.joreman.vm.ForemanVMBuilder;

public class ForemanClient {

	protected ForemanAPI foremanAPI;
	
	public ForemanClient(ForemanAPI api) {
		this.foremanAPI = api;
	}
	
	
	public ForemanVMBuilder newHost() {
		ForemanVMBuilder builder = new ForemanVMBuilder(this);
		return builder;
	}
	
	public ForemanVM getHost(String hostName) {
		return new ForemanVM(api().getHost(hostName));
	}
	
	public void deleteHost(String hostName) {
		api().deleteHost(hostName);
	}

	public ForemanAPI api() {
		return foremanAPI;
	}
}
