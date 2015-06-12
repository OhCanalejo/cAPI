package com.ohcanalejo.capi.parser;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

/**
 * A XML based implementation of the {@link Parser} interface
 * 
 * @author oscar.canalejo
 *
 */
public class XMLParser implements Parser {

	/* (non-Javadoc)
	 * @see com.ohcanalejo.capi.parser.Parser#parse(java.lang.String, java.lang.Class)
	 */
	public <T> T parse(String data, Class<T> dtoType) {
		T dto = null;
		try {
			Serializer serializer = new Persister();
			dto = serializer.read(dtoType, data);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return dto;
	}

}
