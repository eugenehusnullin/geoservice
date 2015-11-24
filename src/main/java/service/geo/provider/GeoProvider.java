package service.geo.provider;

import service.geo.dto.GeoObject;
import service.geo.dto.GeoRoute;
import retrofit.Callback;

import java.util.List;

public interface GeoProvider {
    List<GeoObject> getObjects(String name);
    void getObjectsAsync(String name, Callback<List<GeoObject>> callback);
    List<GeoObject> getObjects(float lat, float lng);
    List<GeoRoute> getRoute(float latOrigin, float lngOrigin, float latDest, float lngDest) throws GeoProviderException;
    String getLocationMeta(float lat, float lng, String locationType);
}