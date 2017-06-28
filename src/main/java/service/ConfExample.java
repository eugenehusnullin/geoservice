package service;

import java.util.HashMap;

// Todo replace conf to etc
public class ConfExample {

    public static boolean DEBUG = true;
    public static String DADATA_BASE_URL = "https://dadata.ru";
    public static String DADATA_API_KEY = "secret key";
    //public static String REDIS_HOST = "188.226.168.36";
    public static String REDIS_HOST = "";
    public static boolean WITH_CACHE = false;
    public static String SUGGESTIONS_CACHE_PREFIX = "SG@";
    public static int SUGGESTIONS_CACHE_SEC = 86400; // 1 day
    public static String YANDEX_GEOCODE_BASE_URL = "https://geocode-maps.yandex.ru/";
    public static String YANDEX_MAPS_API_BASE_URL = "https://api-maps.yandex.ru/";
    public static String YANDEX_MAPS_JS = "https://api-maps.yandex.ru/2.1.23/?lang=ru_RU";
    public static int YANDEX_TOKEN_LENGTH = 32;
    public static String YANDEX_TOKEN_SEARCH = "project_data[\"token\"]=\"";
    public static String YANDEX_GEOCODE_CACHE_PREFIX = "GCY@";
    public static String GOOGLE_GEOCODE_CACHE_PREFIX = "GCG@";
    public static int GEOCODE_CACHE_SEC = 86400; // 1 day
    public static String GOOGLE_API_KEY = "secret key";
    public static String GOOGLE_DIRECTIONS_BASE_URL = "https://maps.googleapis.com/";
    public static int ROUTE_CACHE_SEC = 30; // 30 sec
    public static String GOOGLE_LANG = "ru";
    public static String YANDEX_LANG = "ru_RU";
    public static HashMap<String, String> YANDEX_DADATA_KINDS = new HashMap<String, String>(){{
        put("street", "street");
        put("locality", "city");
        put("area", "area");
        put("province", "region");
    }};

    public static HashMap<String, Integer> YANDEX_DADATA_PERCISION = new HashMap<String, Integer>(){{
        put("exact", 0);
        put("number", 1);
        put("near", 1);
        put("range", 1);
        put("street", 2);
        put("other", 4);
    }};

}