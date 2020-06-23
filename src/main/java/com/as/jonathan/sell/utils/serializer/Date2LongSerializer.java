package com.as.jonathan.sell.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author Yifan Chen
 * @version 1.0.0
 * @since 6/17/2020
 */
public class Date2LongSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeNumber(date.getTime() / 1000);
	}
}
