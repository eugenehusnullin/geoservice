package service.geo.api;

import service.geo.realm.yandex.route.RealmYandexRouteResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface YandexMapsApi {

    @GET("/services/route/2.0/")
    RealmYandexRouteResponse getRoute(@QueryMap Map<String, String> options);

    @GET("/services/route/2.0/")
    void getRouteAsync(@QueryMap Map<String, String> options, Callback<RealmYandexRouteResponse> callback);

}