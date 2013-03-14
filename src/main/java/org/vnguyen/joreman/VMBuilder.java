package org.vnguyen.joreman;

public interface VMBuilder<T extends VM> {
	T build() throws Exception;
}
