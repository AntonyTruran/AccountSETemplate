package com.qa.util;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtilJack {

	private ObjectMapper jack;
private static final Logger logger = Logger.getLogger(JSONUtilJack.class);
	
	public JSONUtilJack() {
		this.jack = new ObjectMapper();
	}

	public String getJSONJackForObject(Object obj) {
		try {
			return jack.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return null;
	}

	public <T> T getObjectForJSONJack(String jsonString, Class<T> clazz) {

		try {
			return jack.readValue(jsonString, clazz);
		} catch (JsonParseException | JsonMappingException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
		return null;
	}

}
