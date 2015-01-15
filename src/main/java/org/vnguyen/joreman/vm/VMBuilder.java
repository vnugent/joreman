package org.vnguyen.joreman.vm;


public interface VMBuilder<T extends VM> {
	T build() throws Exception;
}
