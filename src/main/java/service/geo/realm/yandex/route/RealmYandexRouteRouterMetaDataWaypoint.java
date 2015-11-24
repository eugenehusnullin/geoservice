package service.geo.realm.yandex.route;

import java.util.List;

public class RealmYandexRouteRouterMetaDataWaypoint {

    List<Float> coordinates;
    List<Float> request;

    public List<Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }

    public List<Float> getRequest() {
        return request;
    }

    public void setRequest(List<Float> request) {
        this.request = request;
    }
}
