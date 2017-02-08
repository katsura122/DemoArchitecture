package com.katsuraf.demoarchitecture.net.converter;

import com.google.gson.Gson;
import com.katsuraf.demoarchitecture.net.exception.ApiException;
import com.katsuraf.demoarchitecture.net.exception.ResultException;
import com.katsuraf.demoarchitecture.response.ResultResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

public class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final Type type;

    public GsonResponseBodyConverter(Gson gson, Type type) {
        this.gson = gson;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String response = value.string();
        try {
//            Log.d("Network", "response>>" + response);
            JSONObject jsonObject = new JSONObject(response);
            //ResultResponse 只解析result字段
            ResultResponse resultResponse = gson.fromJson(response, ResultResponse.class);
            if (resultResponse.getCode() == 0) {
                if (jsonObject.has("Data")) {
                    return gson.fromJson(jsonObject.getString("Data"), type);
                } else {
                    throw new ResultException(ResultException.N0_DATA,
                            ResultException.MSG_NO_DATA_FOUND);
                }
            } else {
                throw new ResultException(resultResponse.getCode(), resultResponse.getResultMsg());
            }
        } catch (JSONException jsonException) {
            throw new ResultException(ApiException.PARSE_ERROR, "invalid json");
        } finally {
            value.close();
        }
    }

}