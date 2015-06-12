package com.ohcanalejo.capi.caller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A {@link Caller} implementation for http requests  
 *   
 * @author oscar.canalejo
 *
 */
public class HttpCaller implements Caller {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String METHOD_GET = "GET";
	private static final String METHOD_POST = "POST";
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	
	/* (non-Javadoc)
	 * @see com.ohcanalejo.gap.caller.Caller#call(java.lang.String, java.util.Map)
	 */
	public String call(String address, Map<String, ?> options) {

		HttpURLConnection con = null;
		BufferedReader br = null;
		int responseCode = 0; // (e.g.: 200 = OK)
		
		try {
			
			// request options lookup
			String reqMethod = METHOD_GET;
			Map<String, String> params = null;
			
			if (options != null) {
				params = (Map<String, String>) options.get("reqParams");
				if (options.containsKey("reqMethod")) {
					reqMethod = (String) options.get("reqMethod");
				}
			}
			
			String uriParams = getUriParams(params);
			if (reqMethod.equalsIgnoreCase(METHOD_GET)) {
				address = new StringBuffer(address).append("?").append(uriParams).toString();
			}
			URL url = new URL(address);
			con = (HttpURLConnection) url.openConnection();
			
			// add request headers
			con.setRequestMethod(reqMethod);

			// set post request
			if (reqMethod.equalsIgnoreCase(METHOD_POST)) {
				con.setDoOutput(true);
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				wr.writeBytes(uriParams);
				wr.flush();
				wr.close();
			}

			responseCode = con.getResponseCode();
			logger.info("\nSending {} request to URL: {} ", reqMethod, address);
			logger.info("Request parameters : " + uriParams);
			logger.info("Response Code : " + responseCode);

			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}

//			response = response.replace("\n", "");
//			response = response.replace("\t", "");

			return response.toString();
			
		} catch (Exception ex) {
			logger.error("\n\nError on Calling {}. Error details: {} - {} \n\n", address , ex.getClass(), ex.getMessage());
			if (responseCode > 0)
				logger.error("\n\nResponse Code was: {}", responseCode);
			
//			throw new RemoteIntegrationException(ex,HttpStatus.INTERNAL_SERVER_ERROR.toString());
			return null;
			
		} finally {
			/*
			 *  TODO: Try to use a Java7' "try-with" block instead of this messy finally block
			 *  We'd need a Autocloseable HttpURLConnection implementation 
			 */
			try {
//				if (is != null)
//					is.close();
				if (br != null)
					br.close();
				if (con != null)
					con.disconnect();
				// bw.close();
				// out.close();
			} catch (Exception ex) {
				logger.error("Error closing resources");
			}
		}
	}
	
	/**
	 * @param params
	 * @return
	 */
	protected String getUriParams(Map<String, String> params) {
		
		StringBuffer uriParams = new StringBuffer();
		for (String key : params.keySet()) {
			String value = params.get(key);
			if (value != null && !value.isEmpty()) {
				try {
					uriParams.append(key + "=" + URLEncoder.encode(value, DEFAULT_ENCODING) + "&"); // uri += (key + "=" + params.get(key) + "&");
		
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		return uriParams.deleteCharAt(uriParams.length()-1).toString(); // return uri.substring(0, uri.lastIndexOf("&"));
	}

	
}
