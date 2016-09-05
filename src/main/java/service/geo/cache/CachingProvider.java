package service.geo.cache;

public interface CachingProvider {
    void cache(String key, String value);
    void cache(String key, String value, int secondsExpire);
    String get(String key);
    Boolean exists(String key);
}