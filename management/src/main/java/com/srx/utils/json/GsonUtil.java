package com.srx.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class GsonUtil {
	private static Gson gson = new GsonBuilder().create();

	public static String bean2Json(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
		return gson.fromJson(jsonStr, objClass);
	}

	/**
	 * 把混乱的json字符串整理成缩进的json字符串
	 * 
	 * @param uglyJsonStr
	 * @return
	 */
	public static String jsonFormatter(String uglyJsonStr) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(uglyJsonStr);
		String prettyJsonString = gson.toJson(je);
		return prettyJsonString;
	}
}
