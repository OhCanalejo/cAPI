package com.ohcanalejo.capi.caller;

import java.util.Map;

/**
 * Defines the contract for executing the requests. 
 * The individual implementations will determine 
 * which type of requests will be done and how they
 * will be executed 
 *   
 * @author oscar.canalejo
 *
 */
public interface Caller {
	
	/**
	 * Executes a request
	 * @param address defines where to call (e.g. : a URI)
	 * @param options a {@link Map} which encapsulates individual arguments and other staff necessary for the call
	 * @return a {@link String} containing the response received once the call has been executed
	 */
	public String call(String address, Map<String, ?> options);

}
