package org.vnguyen.joreman;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.vnguyen.joreman.HostPowerController.PowerStatus;



public class ForemanVM implements VM {
	protected Host host;
	protected ForemanClient foreman;
	protected PowerStatus powerStatus;
	
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
	
	public HostPowerController power() {
		return new HostPowerController(this);
	}
	
	@Override
	public String toString() {
		return host.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).
			       		append(name()).
			       		append(ip()).
			       		toHashCode();
		
	}

	public void setForemanClient(ForemanClient foremanClient) {
		this.foreman = foremanClient;
	}
	
	public void setPowerStatus(PowerStatus status) {
		this.powerStatus = status;
	}
}
