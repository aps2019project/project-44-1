package server;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;

import java.util.HashMap;

public class Database {

    private static final String baseAddress = "http://127.0.0.1:8080/";
    private static Gson gson = new Gson();

    public static void makeNewDataBase(String name) {
        final String path = "init_DB";
        HttpResponse<String> response;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        try {
            response = Unirest.post(baseAddress + path).fields(parameters).asString();
            System.out.println(response.getStatus());
        } catch (UnirestException e) {
            e.printStackTrace();
            //do something
        }
    }

    public static void putElement(String name, Object key, Object value) {
        final String path = "put";
        HttpResponse<String> response = null;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("key", gson.toJson(key));
        parameters.put("value", gson.toJson(value));
        try {
            response = Unirest.post(baseAddress + path).fields(parameters).asString();
            System.out.println(response.getStatus());
        } catch (UnirestException e) {
            e.printStackTrace();
            //do something
        }
    }

    public static <T> T getAllValues(String name, Class<T> tClass) {
        final String path = "get_all_values";
        HttpResponse<String> response = null;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        try {
            response = Unirest.post(baseAddress + path).fields(parameters).asString();
            return gson.fromJson(response.getBody(), tClass);
        } catch (UnirestException e) {
            e.printStackTrace();
            //do something
            return null;
        }
    }

    public static <T> T getAllKeys(String name, Class<T> tClass) {
        final String path = "get_all_keys";
        HttpResponse<String> response = null;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        try {
            response = Unirest.post(baseAddress + path).fields(parameters).asString();
            return gson.fromJson(response.getBody(), tClass);
        } catch (UnirestException e) {
            e.printStackTrace();
            //do something
        }
        return null;
    }

    public static <T> Object getValue(String name, Object key, Class<T> tClass) {
        final String path = "get";
        HttpResponse<String> response = null;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("key", gson.toJson(key));
        try {
            response = Unirest.post(baseAddress + path).fields(parameters).asString();
            System.out.println(response.getBody());
            return gson.fromJson(response.getBody(), tClass);
        } catch (UnirestException e) {
            e.printStackTrace();
            //do something
        }
        return null;
    }

    public static void deleteValue(String name, Object key) {
        final String path = "del_from_DB";
        HttpResponse<String> response = null;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("name", name);
        parameters.put("key", key);
        try {
            response = Unirest.post(baseAddress + path).fields(parameters).asString();
        } catch (UnirestException e) {
            e.printStackTrace();
            //do something
        }
    }

}
