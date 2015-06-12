package com.ohcanalejo.capi;


import java.util.Map;

import org.apache.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohcanalejo.capi.caller.Caller;
import com.ohcanalejo.capi.handler.Handler;
import com.ohcanalejo.capi.parser.Parser;


/**
 * Defines the methods required to provide an integration tier with 
 * any external API
 *
 * @author oscar-canalejo
 *
 */
public class RequestProcessor<C extends Caller, P extends Parser> {

	private C caller;
	private P parser;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * Default Constructor
	 * @param caller A {@link Caller} specific implementation
	 * @param parser A {@link Parser} specific implementation
	 */
	public RequestProcessor(C caller, P parser) {
		this.setCaller(caller);
		this.setParser(parser);
	}

	/**
	 * Handles the request processing
	 * @param address defines where to call (e.g. : a URI)
	 * @param dtoType the target class type into which the response will be decoded. It must be a subclass of {@link Handler} 
	 * @param options a {@link Map} which encapsulates individual arguments and other staff necessary for the call
	 * @return an instance of the target class where the response has been parsed
	 */
	public <T> T performRequest(String address, Class<? extends Handler> dtoType, Map<String, ?> options) {

		logger.info("Performing Request: " + address);
		T resp = null;
		
		try {
				logger.info("\n========== REQUEST IN PROCESS ===============");
				/*
				 * Calling
				 */
				logger.info("Calling... {}", address);
				String response = caller.call(address, options);
				logger.info("Response: {}", response);
				/*
				 * Parsing 
				 */
				logger.info("Parsing...");
				Handler handler = parser.parse(response, dtoType);
				/*
				 * Handling
				 */
				logger.info("Handling...");
				resp = handler.handle();

				logger.info("=============================================\n");				
			
		} catch (ParseException ex) {
			ex.printStackTrace();
			//throw new RemoteIntegrationException(e,"Error While Parsing Response");
		}
		return resp;
	}
	
	
	public C getCaller() {
		return caller;
	}
	public void setCaller(C caller) {
		this.caller = caller;
	}
	public P getParser() {
		return parser;
	}
	public void setParser(P parser) {
		this.parser = parser;
	}
	
}
