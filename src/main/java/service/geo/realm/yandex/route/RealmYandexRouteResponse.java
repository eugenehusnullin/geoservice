package service.geo.realm.yandex.route;

import service.geo.dto.GeoObject;
import service.geo.dto.GeoRoute;
import service.geo.dto.Point;

import java.util.ArrayList;
import java.util.List;

public class RealmYandexRouteResponse {
    RealmYandexRouteData data;

    public RealmYandexRouteData getData() {
        return data;
    }

    public void setData(RealmYandexRouteData data) {
        this.data = data;
    }

    public List<GeoRoute> getGeoRoutes(float latOrigin, float lngOrigin, float latDest, float lngDest){

        List<GeoRoute> routes = new ArrayList<>();

        for(RealmYandexRouteFeatures feature : data.getFeatures()) {

            routes.add(featureToGeoRoute(findWithCoords(feature), latOrigin, lngOrigin, latDest, lngDest));
        }

        return routes;

    }

    private RealmYandexRouteFeatures findWithCoords(RealmYandexRouteFeatures features){
        if(features.getFeatures() == null) return null;
        if(features.getProperties().getEncodedCoordinates() != null) return features;
        else{
            for (RealmYandexRouteFeatures feature: features.getFeatures()){
                RealmYandexRouteFeatures found = findWithCoords(feature);
                if(found != null) return found;
            }
        }
        return null;
    }

    private GeoRoute featureToGeoRoute(RealmYandexRouteFeatures feature, float latOrigin, float lngOrigin, float latDest, float lngDest){
        GeoRoute route = new GeoRoute();

        GeoObject origin = new GeoObject();
        origin.setPoint(new Point(latOrigin, lngOrigin));
        GeoObject dest = new GeoObject();
        dest.setPoint(new Point(latDest, lngDest));
        route.setOrigin(origin);
        route.setDestination(dest);
        route.setDistance(Float.parseFloat(feature.getProperties().getPathMetaData().getDistance().getValue()));
        route.setDuration(Float.parseFloat(feature.getProperties().getPathMetaData().getDurationInTraffic().getValue()));
        route.setRoute(feature.getProperties().getRoute());

        return route;

    }

}
