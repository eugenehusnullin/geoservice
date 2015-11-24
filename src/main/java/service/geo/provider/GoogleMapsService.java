package service.geo.provider;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import service.Conf;
import service.geo.api.GoogleMapsApi;
import service.geo.cache.CachingProvider;
import service.geo.dto.GeoObject;
import service.geo.dto.GeoRoute;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class GoogleMapsService implements GeoProvider {

    private CachingProvider _cache;
    private Gson _gson;
    private GoogleMapsApi _geocodeApi;

    public GoogleMapsService(CachingProvider cache) {

        _cache = cache;
        _gson = new GsonBuilder()
                .serializeNulls()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                }).create();


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(Conf.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .setEndpoint(Conf.GOOGLE_DIRECTIONS_BASE_URL)
                .setConverter(new GsonConverter(_gson))
                .build();

        _geocodeApi = restAdapter.create(GoogleMapsApi.class);

    }

    @Override
    public List<GeoObject> getObjects(String name) {
        String cacheKey = String.format(Locale.ENGLISH, "%s%s", Conf.GOOGLE_GEOCODE_CACHE_PREFIX, name);

        if(_cache != null && _cache.exists(cacheKey)) {
            return _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
        }else{
            List<GeoObject> response = _geocodeApi.geocode(new HashMap<String, String>() {{
                put("address", name);
                put("key", Conf.GOOGLE_API_KEY);
                put("language", Conf.GOOGLE_LANG);
            }}).getGeoObjects();

            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson.toJson(response),
                        Conf.ROUTE_CACHE_SEC);

            return response;
        }
    }

    @Override
    public void getObjectsAsync(String name, Callback<List<GeoObject>> callback) {

    }

    @Override
    public List<GeoObject> getObjects(float lat, float lng) {
        String cacheKey = String.format(Locale.ENGLISH, "%s%f,%f", Conf.GOOGLE_GEOCODE_CACHE_PREFIX, lat, lng);

        if(_cache != null && _cache.exists(cacheKey)) {
            return _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
        }else{
            List<GeoObject> response = _geocodeApi.geocode(new HashMap<String, String>() {{
                put("latlng", String.format(Locale.ENGLISH, "%f,%f", lat, lng));
                put("key", Conf.GOOGLE_API_KEY);
                put("language", Conf.GOOGLE_LANG);
            }}).getGeoObjects();

            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson.toJson(response),
                        Conf.ROUTE_CACHE_SEC);

            return response;
        }
    }

    @Override
    public List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest) {
        String cacheKey = String.format(Locale.ENGLISH, "%s%f,%f,%f,%f", Conf.GOOGLE_GEOCODE_CACHE_PREFIX, latOrigin, lngOrigin, latDest, lngDest);

        if(_cache != null && _cache.exists(cacheKey)) {
            return _gson.fromJson(_cache.get(cacheKey), ArrayList.class);
        }else{
            List<GeoRoute> response = _geocodeApi.getRoute(new HashMap<String, String>() {{
                put("origin", String.format(Locale.ENGLISH, "%f,%f", latOrigin, lngOrigin));
                put("destination", String.format(Locale.ENGLISH, "%f,%f", latDest, lngDest));
                put("key", Conf.GOOGLE_API_KEY);
                put("language", Conf.GOOGLE_LANG);
            }}).getGeoRoutes();

            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson.toJson(response),
                        Conf.ROUTE_CACHE_SEC);

            return response;
        }
    }

    @Override
    public String getLocationMeta(float lat, float lng, String locationType) {
        throw new NotImplementedException();
    }
}
