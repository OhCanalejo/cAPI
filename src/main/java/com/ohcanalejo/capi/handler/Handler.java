/**
 * 
 */
package com.ohcanalejo.capi.handler;

import com.ohcanalejo.capi.RequestProcessor;
import com.ohcanalejo.capi.parser.Parser;


/**
 * This interface defines the contract for handling a response. 
 * A specific Handler implementation fits the content of the response whe it is decoded
 * by the {@link Parser}, and is in charge of handling it.
 * The Handler is intended to be passed as argument when invoking {@link RequestProcessor}
 * to execute a request.  
 * 
 * @author oscar.canalejo
 *
 */
public interface Handler {

	/**
	 * Handles the response after it has been decoded 
	 * 
	 * @return
	 */
	public <T> T handle();
}
