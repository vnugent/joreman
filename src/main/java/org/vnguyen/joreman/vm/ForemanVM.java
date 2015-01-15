package org.vnguyen.joreman.vm;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.vnguyen.joreman.client.ForemanClient;
import org.vnguyen.joreman.model.Host;
import org.vnguyen.joreman.model.HostPowerController;
import org.vnguyen.joreman.model.HostPowerController.PowerStatus;



public class ForemanVM implements VM {
	protected Host host;
	private ForemanClient foreman;
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
			       		append(host()).
			       		toHashCode();
		
	}

	public void setForemanClient(ForemanClient foremanClient) {
		foreman = foremanClient;
	}
	
	public void setPowerStatus(PowerStatus status) {
		this.powerStatus = status;
	}


    public ForemanClient getForeman() {
        return foreman;
    }
}
