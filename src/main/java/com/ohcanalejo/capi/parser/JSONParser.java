package com.ohcanalejo.capi.parser;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;


/**
 * A JSON based implementation of the {@link Parser} interface
 * 
 * @author oscar.canalejo
 *
 */
public class JSONParser implements Parser {

	/* (non-Javadoc)
	 * @see com.ohcanalejo.capi.parser.Parser#parse(java.lang.String, java.lang.Class)
	 */
	public <T> T parse(String data, Class<T> dtoType) {

		T dto = null;
		try {
			ObjectMapper jsonMapper = new ObjectMapper();
			jsonMapper.configure(JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS, true); // parser will allow values: 
																																							  // NaN, +INF, -INF, +Infinite, -Infinite 
																																							  // as floating-point numeric 
			
			jsonMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")); // add date format to avoid timezone errors 
			dto = jsonMapper.readValue(data, dtoType);
			
		} catch (IOException ex) {
			ex.printStackTrace();
			//throw new RemoteIntegrationException(e,"Error on Json Generation");
			
//		} catch (JsonMappingException ex) {
//			ex.printStackTrace();
//			//throw new RemoteIntegrationException(e,"Error While Mapping Response");
//		} catch (JsonParseException ex) {
//			ex.printStackTrace();
//		} catch (IOException ex) {
//			ex.printStackTrace();
		}
		return dto;
	}

}