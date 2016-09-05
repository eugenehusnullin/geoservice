package service.geo.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import service.Conf;
import service.geo.api.DaDataApi;
import service.geo.api.SuggestionRequestBody;
import service.geo.cache.CachingProvider;
import service.geo.dto.GeoObject;
import service.geo.dto.GeoRoute;
import service.geo.dto.GeoSuggestion;
import service.geo.realm.dadata.RealmDaDataAnswer;
import service.geo.realm.dadata.RealmDaDataSuggestion;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;

public class DaDataService implements SuggestionProvider, GeoProvider {

    private DaDataApi apiService;
    private CachingProvider _cache;
    private Gson _gson_encoder = new GsonBuilder().serializeNulls().create();
    private Gson _gson_builder;

	public DaDataService(CachingProvider cache) {
        _cache = cache;
        _gson_builder = new GsonBuilder()
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
                .setEndpoint(Conf.DADATA_BASE_URL)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Content-Type", "application/json");
                        request.addHeader("Accept", "application/json");
                        request.addHeader("Authorization", "Token ".concat(Conf.DADATA_API_KEY));
                    }
                })
                .setConverter(new GsonConverter(_gson_builder))
                .build();

        apiService = restAdapter.create(DaDataApi.class);
    }


    @Override
    public List<GeoSuggestion> getSuggestions(SuggestionRequestBody body, GeoProvider geoProvider, float lat, float lng, String locationsType, String locationsValue) throws IOException {
        String cacheKey = String.format("%s%d%s,%f,%f,%s,%s", Conf.SUGGESTIONS_CACHE_PREFIX, body.getCount(), body.getQuery(), lat, lng, locationsType, locationsValue);

        RealmDaDataSuggestion suggestion;

        if(_cache != null && _cache.exists(cacheKey)){
            suggestion = _gson_builder.fromJson(_cache.get(cacheKey), RealmDaDataSuggestion.class);
        }else {

            if(locationsValue != null && !locationsValue.isEmpty())
                body.setLocations(new ArrayList<HashMap<String, String>>(){{
                    add(new HashMap<String, String>(){{
                        put(locationsType, locationsValue);
                    }});
                }});
            else if(lat != 0.0 && lng != 0.0) {
                String locationsVal = geoProvider.getLocationMeta(lat, lng, locationsType);

                ArrayList<HashMap<String, String>> locations = new ArrayList<HashMap<String, String>>() {{
                    add(new HashMap<String, String>() {{
                        put(locationsType, locationsVal);
                    }});
                }};

                if(Objects.equals(locationsType, "region")){
                    if(Objects.equals(locationsVal, "Москва"))
                        locations.add(new HashMap<String, String>() {{
                            put(locationsType, "Московская");
                        }});
                    if(Objects.equals(locationsVal, "Московская"))
                        locations.add(new HashMap<String, String>() {{
                            put(locationsType, "Москва");
                        }});
                }

                if(locationsVal != null && !locationsVal.isEmpty())
                    body.setLocations(locations);
            }

            RealmDaDataSuggestion response = apiService.getSuggestion(body);
            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson_encoder.toJson(response),
                        Conf.SUGGESTIONS_CACHE_SEC);
            suggestion = response;
        }

        ArrayList<GeoSuggestion> r = new ArrayList<>();

        for(RealmDaDataAnswer answer : suggestion.getSuggestions()) {

            GeoSuggestion s = new GeoSuggestion();
            s.setCity(answer.getRealmData().getCity());
            s.setDistrict(answer.getRealmData().getCity_district());
            s.setAddress_full(answer.getValue());
            s.setCountry(answer.getRealmData().getCountry());
            s.setRegion(answer.getRealmData().getRegion_with_type());
            s.setHouse(answer.getRealmData().getHouse());
            s.setStreet(answer.getRealmData().getStreet_with_type());
            s.setLat(answer.getRealmData().getGeo_lat());
            s.setLng(answer.getRealmData().getGeo_lon());

            r.add(s);

        }

        return r;

    }

    @Override
    public List<GeoObject> getObjects(String name) {

        SuggestionRequestBody body = new SuggestionRequestBody(name, 1);

        String cacheKey = String.format("%s%d%s", Conf.SUGGESTIONS_CACHE_PREFIX, body.getCount(), body.getQuery());

        RealmDaDataSuggestion suggestion;

        if(_cache != null && _cache.exists(cacheKey)){
            suggestion = _gson_builder.fromJson(_cache.get(cacheKey), RealmDaDataSuggestion.class);
        }else {

            RealmDaDataSuggestion response = apiService.getSuggestion(body);
            if(_cache != null)
                _cache.cache(cacheKey,
                        _gson_encoder.toJson(response),
                        Conf.SUGGESTIONS_CACHE_SEC);
            suggestion = response;
        }

        List<GeoObject> objects = new ArrayList<GeoObject>();
        objects.add(suggestion.getGeoObject());
        return objects;
    }

    @Override
    public void getObjectsAsync(String name, Callback<List<GeoObject>> callback) {
        SuggestionRequestBody body = new SuggestionRequestBody(name, 1);

        String cacheKey = String.format("%s%d%s", Conf.SUGGESTIONS_CACHE_PREFIX, body.getCount(), body.getQuery());

        RealmDaDataSuggestion suggestion;

        if(_cache != null && _cache.exists(cacheKey)){
            suggestion = _gson_builder.fromJson(_cache.get(cacheKey), RealmDaDataSuggestion.class);
        }else {

            apiService.getSuggestionAsync(body, new Callback<RealmDaDataSuggestion>() {
                @Override
                public void success(RealmDaDataSuggestion realmDaDataSuggestion, Response response) {
                    if(_cache != null)
                        _cache.cache(cacheKey,
                                _gson_encoder.toJson(response),
                                Conf.SUGGESTIONS_CACHE_SEC);
                    List<GeoObject> objects = new ArrayList<GeoObject>();
                    objects.add(realmDaDataSuggestion.getGeoObject());
                    callback.success(objects, response);
                }

                @Override
                public void failure(RetrofitError error) {}
            }
        );

        }
    }

    @Override
    public List<GeoObject> getObjects(float lat, float lng) {
    	return null;
    }

    @Override
    public List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest) {
    	return null;
    }

    @Override
    public String getLocationMeta(float lat, float lng, String locationType) {
        return null;
    }
}
