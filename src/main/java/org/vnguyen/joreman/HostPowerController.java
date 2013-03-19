package org.vnguyen.joreman;

import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.annotation.JsonRootName;

public class HostPowerController {
	@JsonRootName("status")
	public static class PowerStatus {
		private String status;
		public PowerStatus(String status) {
			this.status=status;
		}
		
		public String toString() {
			return status;
		}
	}

	public enum POWER_ACTION {
		ON { public String toString() {return "start";}},
		OFF { public String toString() {return "stop";}},
		STATUS { public String toString() {return "status";}},

		SHUTDOWN //not support 
	}
	
	protected ForemanVM vm;
	
	public HostPowerController(ForemanVM vm) {
		this.vm = vm;
	}
	
	public PowerStatus status() {
		return vm.foreman.api().hostPower(vm.name(), POWER_ACTION.STATUS);
	}
	
	/**
	 * Power On. Due to a bug in Foreman this action is delayed for a few seconds 
	 * or we will get Disk locked error
	 * @return
	 */
	public ForemanVM on() {
		Runnable delayedPowerTask = new Runnable() {
			public void run() {
				power(POWER_ACTION.ON);
			}
		};
		vm.foreman.executor.schedule(delayedPowerTask, 10, TimeUnit.SECONDS);		
		return vm;
	}
	
	public ForemanVM off() {
		return power(POWER_ACTION.OFF);
	}
	
	

	
	/**
	 * perform a power action
	 * @param action
	 * @return
	 */
	protected ForemanVM power(POWER_ACTION action) {
		vm.foreman.api().hostPower(vm.name(), action);
		return vm;
	}
}
