package com.xingyu.homework1_spring.services;



import java.util.HashMap;
import java.util.Map;


public class KeyValueServiceImpl implements KeyValueService {
    private final Map<String, String> storage = new HashMap<>();

    @Override
    public String put(String key, String value) {
        storage.put(key, value);
        return "OK";
    }

    @Override
    public String get(String key) {
        return storage.getOrDefault(key, "Key not found");
    }

    @Override
    public String delete(String key) {
        if (storage.remove(key) != null) {
            return "OK";
        }
        return "Key not found";
    }
}
