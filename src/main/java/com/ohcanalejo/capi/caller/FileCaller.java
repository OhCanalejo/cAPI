package com.ohcanalejo.capi.caller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * A {@link Caller} implementation for working against files. 
 * This caller is useful when you need to simulate (mock-ups) API calls, 
 * and/or when you want to have the full control of the responses.
 * 
 * @author oscar.canalejo
 *
 */
public class FileCaller implements Caller {

	/* (non-Javadoc)
	 * @see com.ohcanalejo.capi.caller.Caller#call(java.lang.String, java.util.Map)
	 */
	public String call(String address, Map<String, ?> options) {

		String resp = null;
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(address)){
			resp = inputStream.toString();
		
		}catch (IOException ex) {
			// TODO: handle exception
		}
		return resp;
	}

}
