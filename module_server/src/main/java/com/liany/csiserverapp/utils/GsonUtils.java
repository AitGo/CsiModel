package com.liany.csiserverapp.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.liany.csiserverapp.network.response.Response;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import ikidou.reflect.TypeBuilder;

/**
 * @创建者 ly
 * @创建时间 2019/4/22
 * @描述 ${TODO}
 * @更新者 $Author$
 * @更新时间 $Date$
 * @更新描述 ${TODO}
 */
public class GsonUtils {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 将object对象转成json字符串
     *
     * @param object
     * @return
     */
    public static String gsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 将gsonString转成泛型bean
     *
     * @param gsonString
     * @param cls
     * @return
     */
    public static <T> T gsonBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){
            list.add(new Gson().fromJson(elem, cls));
        }
        return list;
    }

    public static <T> Response<List<T>> fromJsonArray(String json, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(Response.class)
                .beginSubType(List.class)
                .addTypeParam(clazz)
                .endSubType()
                .build();
        return new Gson().fromJson(json, type);
    }

    public static <T> Response<T> fromJsonObject(String json, Class<T> clazz) {
        Type type = TypeBuilder
                .newInstance(Response.class)
                .addTypeParam(clazz)
                .build();
        return new Gson().fromJson(json, type);
    }

    public static String successJson(Object data) {
        Response response = new Response();
        response.setCode(200);
        response.setData(data);
        response.setMsg("");
        return GsonUtils.gsonString(response);
    }

    public static String faildJson(int code,String msg) {
        Response<String> response = new Response<String>();
        response.setCode(code);
        response.setMsg(msg);
//        response.setData("");
        return GsonUtils.gsonString(response);
    }
}
