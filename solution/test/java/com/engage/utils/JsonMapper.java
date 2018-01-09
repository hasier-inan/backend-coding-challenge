package com.engage.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by hasiermetal on 09/01/2018.
 */
public class JsonMapper {

    public static String getJsonFromMap(Object map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
        return mapper.writeValueAsString(map);
    }
}
