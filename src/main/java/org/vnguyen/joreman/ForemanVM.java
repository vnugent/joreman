package org.vnguyen.joreman;



public class ForemanVM implements VM {
	protected Host host;
	
	public ForemanVM(Host host) {
		this.host = host;
	}
	
	
	public String ip() {
		return host.ip;
	}
	
	public String name() {
		return host.name;
	}

	public int id() {
		return host.id;
	}
	
	public Host host() {
		return host;
	}
	
	@Override
	public String toString() {
		return host.toString();
	}


	
}
