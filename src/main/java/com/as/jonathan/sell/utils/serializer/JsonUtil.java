package com.as.jonathan.sell.utils.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/18/2020
 */
public class JsonUtil {
	public static String toJson(Object object) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		Gson gson = gsonBuilder.create();
		return gson.toJson(object);
	}
}
