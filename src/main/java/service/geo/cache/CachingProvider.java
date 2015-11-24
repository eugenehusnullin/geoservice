package service.geo.cache;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;

public interface CachingProvider {
    void cache(String key, String value);
    void cache(String key, String value, int secondsExpire);
    String get(String key);
    Boolean exists(String key);
}