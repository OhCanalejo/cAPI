package com.ohcanalejo.capi.parser;

import com.ohcanalejo.capi.handler.Handler;


/**
 * This interfaces defines the contract for parsing a response.
 * The Parser is responsible of decoding the response into the given {@link Handler} 
 * Individual Parser implementations will be usually based on common encoding approaches
 * (e.g.: JSON, XML, ...)
 * 
 * @author oscar.canalejo
 *
 */
public interface Parser {

	/**
	 * Method for parsing a response and decode it into the given target object 
	 * @param data The response itself, as it has been received
	 * @param dtoType The target class type into which the response will be decoded 
	 * @return an instance of the target class where the response has been parsed 
	 */
	public <T> T parse(String data, Class<T> dtoType);

}
