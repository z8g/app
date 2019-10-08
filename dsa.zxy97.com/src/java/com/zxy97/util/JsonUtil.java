package com.zxy97.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonNull;

public class JsonUtil {

    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").serializeNulls().create();

    public static String toJson(Object src) {
        if (src == null) {
            return gson.toJson(JsonNull.INSTANCE);
        }
        return gson.toJson(src);
    }
}
