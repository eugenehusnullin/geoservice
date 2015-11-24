package service.geo.api;

import service.geo.realm.google.directions.RealmGoogleDirections;
import service.geo.realm.google.geocode.RealmGoogleGeocode;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface GoogleMapsApi {
    @GET("/maps/api/directions/json")
    RealmGoogleDirections getRoute(@QueryMap Map<String, String> options);

    @GET("/maps/api/directions/json")
    void getRouteAsync(@QueryMap Map<String, String> options, Callback<RealmGoogleDirections> callback);

    @GET("/maps/api/geocode/json")
    RealmGoogleGeocode geocode (@QueryMap Map<String, String> options);

    @GET("/maps/api/geocode/json")
    void geocode (@QueryMap Map<String, String> options, Callback<RealmGoogleGeocode> callback);
}