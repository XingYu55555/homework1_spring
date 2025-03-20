package com.xingyu.homework1_spring.services;

import org.springframework.stereotype.Service;

@Service
public interface KeyValueService {
    String put(String key, String value);
    String get(String key);
    String delete(String key);
}
