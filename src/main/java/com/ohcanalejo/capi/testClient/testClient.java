package com.ohcanalejo.capi.testClient;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ohcanalejo.capi.RequestProcessor;
import com.ohcanalejo.capi.caller.HttpCaller;
import com.ohcanalejo.capi.parser.JSONParser;


public class testClient {

//	private static final String BASE_URL = "http://212.29.221.145/InternalWebAPI";
//	private static final String BASE_URL = "http://212.29.221.145:8080/ADL";
	private static final String BASE_URL = "http://localhost:8080/ADL";
	
//	private static final String LOGIN = "/api/user/getUserAuthenticate";
	private static final String LOGIN = "/processLogin";
	
	private static final Logger logger = LoggerFactory.getLogger("testClient");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		RequestProcessor<HttpCaller, JSONParser> processor = new RequestProcessor<>(new HttpCaller(), new JSONParser());
		
		LinkedHashMap<String, String> reqParams = new LinkedHashMap<>();
//		reqParams.put("sUsrNm", "admin_dev");
//		reqParams.put("sPassword", "1832f379f5bd42c2232f39bb9565e6f0c8b64f4ec519c09345cd2d5fa7a013cb");
		reqParams.put("user", "mcgiver");
		reqParams.put("pwd", "12345678");
		HashMap<String, Map<String, String>> options = new HashMap<>();
		options.put("reqParams", reqParams);
		
		SystemUser user = processor.performRequest(BASE_URL + LOGIN, UserLoginPrsr.class, options);

		logger.info("User ID: {}", user.getUserId());
		
	}

}
