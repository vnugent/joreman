package org.vnguyen.joreman;

import java.util.concurrent.ScheduledExecutorService;

public class ForemanClient {

	protected ForemanAPI foremanAPI;
	protected ScheduledExecutorService executor;

	
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
	
	public ScheduledExecutorService executor() {
		return executor;
	}
	
	public ForemanClient setExecutor(ScheduledExecutorService executor) {
		this.executor = executor;
		return this;
	}
}
